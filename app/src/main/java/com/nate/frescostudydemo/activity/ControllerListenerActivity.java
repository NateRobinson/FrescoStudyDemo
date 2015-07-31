package com.nate.frescostudydemo.activity;

import android.graphics.drawable.Animatable;
import android.net.Uri;
import android.widget.Button;
import android.widget.TextView;

import com.facebook.drawee.backends.pipeline.Fresco;
import com.facebook.drawee.controller.BaseControllerListener;
import com.facebook.drawee.controller.ControllerListener;
import com.facebook.drawee.interfaces.DraweeController;
import com.facebook.drawee.view.SimpleDraweeView;
import com.facebook.imagepipeline.core.ImagePipelineConfig;
import com.facebook.imagepipeline.decoder.ProgressiveJpegConfig;
import com.facebook.imagepipeline.image.ImageInfo;
import com.facebook.imagepipeline.image.ImmutableQualityInfo;
import com.facebook.imagepipeline.image.QualityInfo;
import com.facebook.imagepipeline.request.ImageRequest;
import com.facebook.imagepipeline.request.ImageRequestBuilder;
import com.nate.frescostudydemo.R;
import com.nate.frescostudydemo.base.BaseActivity;

import java.io.File;

import butterknife.Bind;

/**
 * Created by Nate on 2015/7/31.
 * 图片下载监听
 */
public class ControllerListenerActivity extends BaseActivity {
    @Bind(R.id.my_image_view)
    SimpleDraweeView myImageView;
    @Bind(R.id.loadImgBtn)
    Button loadImgBtn;
    @Bind(R.id.listenerTv)
    TextView listenerTv;
    @Bind(R.id.listener2Tv)
    TextView listener2Tv;
    //对所有的图片加载，onFinalImageSet 或者 onFailure 都会被触发。前者在成功时，后者在失败时。
    //如果允许呈现渐进式JPEG，同时图片也是渐进式图片，onIntermediateImageSet会在每个扫描被解码后回调。
    // 具体图片的那个扫描会被解码，参见渐进式JPEG图
    private ControllerListener controllerListener = new BaseControllerListener<ImageInfo>() {
        @Override
        public void onFinalImageSet(String id, ImageInfo imageInfo, Animatable animatable) {
            super.onFinalImageSet(id, imageInfo, animatable);
            if (imageInfo == null) {
                return;
            }
            QualityInfo qualityInfo = imageInfo.getQualityInfo();
            listenerTv.setText("Final image received! " +
                            "\nSize: " + imageInfo.getWidth()
                            + "x" + imageInfo.getHeight()
                            + "\nQuality level: " + qualityInfo.getQuality()
                            + "\ngood enough: " + qualityInfo.isOfGoodEnoughQuality()
                            + "\nfull quality: " + qualityInfo.isOfFullQuality());
        }

        @Override
        public void onIntermediateImageSet(String id, ImageInfo imageInfo) {
            super.onIntermediateImageSet(id, imageInfo);
            listener2Tv.setText("IntermediateImageSet image receiced");
        }

        @Override
        public void onFailure(String id, Throwable throwable) {
            super.onFailure(id, throwable);
            listenerTv.setText("Error loading"+id);
        }
    };

    @Override
    public void initContentLayout() {
        setContentView(R.layout.load_listener_img_layout);
    }

    @Override
    public void initListener() {
        loadImgBtn.setOnClickListener(this);
    }

    @Override
    public void initData() {

    }

    @Override
    public void bindClick(int viewId) {
        if(viewId==R.id.loadImgBtn){
            ProgressiveJpegConfig jpegConfig = new ProgressiveJpegConfig() {
                @Override
                public int getNextScanNumberToDecode(int scanNumber) {
                    return scanNumber + 2;
                }

                @Override
                public QualityInfo getQualityInfo(int scanNumber) {
                    boolean isGoodEnough = (scanNumber >= 5);
                    return ImmutableQualityInfo.of(scanNumber, isGoodEnough, false);
                }
            };

            ImagePipelineConfig.newBuilder(this).setProgressiveJpegConfig(jpegConfig).build();
            Uri uri = Uri.fromFile(new File("/storage/sdcard0/DCIM/Camera/1434088743922.jpg"));
//            Uri uri= Uri.parse("http://h.hiphotos.baidu.com/zhidao/pic/item/58ee3d6d55fbb2fbac4f2af24f4a20a44723dcee.jpg");
            ImageRequest request = ImageRequestBuilder.newBuilderWithSource(uri).setProgressiveRenderingEnabled(true).build();
            DraweeController controller= Fresco.newDraweeControllerBuilder()
                    .setOldController(myImageView.getController())
                    .setImageRequest(request)
                            //设置监听器监听图片加载状态
                    .setControllerListener(controllerListener)
                    .build();
            myImageView.setController(controller);
        }
    }

}
