package com.nate.frescostudydemo.activity;

import android.graphics.drawable.Animatable;
import android.net.Uri;
import android.widget.Button;

import com.facebook.drawee.backends.pipeline.Fresco;
import com.facebook.drawee.controller.ControllerListener;
import com.facebook.drawee.interfaces.DraweeController;
import com.facebook.drawee.view.SimpleDraweeView;
import com.facebook.imagepipeline.core.ImagePipelineConfig;
import com.facebook.imagepipeline.decoder.ProgressiveJpegConfig;
import com.facebook.imagepipeline.image.ImmutableQualityInfo;
import com.facebook.imagepipeline.image.QualityInfo;
import com.facebook.imagepipeline.request.ImageRequest;
import com.facebook.imagepipeline.request.ImageRequestBuilder;
import com.nate.frescostudydemo.R;
import com.nate.frescostudydemo.base.BaseActivity;

import butterknife.Bind;

/**
 * Created by Administrator on 2015/7/25.
 * 采用渐进式的方式展示图片，在网络情况不好的情况下可以提高用户体验
 */
public class JPEGImgActivity extends BaseActivity {
    @Bind(R.id.my_image_view)
    SimpleDraweeView myImageView;
    @Bind(R.id.askImgBtn)
    Button askImgBtn;

    @Override
    public void initContentLayout() {
        setContentView(R.layout.jpeg_img_layout);
    }

    @Override
    public void initListener() {
        askImgBtn.setOnClickListener(this);
    }

    @Override
    public void initData() {

    }

    @Override
    public void bindClick(int viewId) {
        if (viewId == R.id.askImgBtn) {
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
            Uri uri = Uri.parse("http://cdn.duitang.com/uploads/item/201303/12/20130312021353_45Qix.jpeg");
            ImageRequest request = ImageRequestBuilder.newBuilderWithSource(uri).setProgressiveRenderingEnabled(true).build();
            DraweeController draweeController = Fresco.newDraweeControllerBuilder()
                    .setImageRequest(request)
                    .setTapToRetryEnabled(true)
                    .setOldController(myImageView.getController())//使用oldController可以节省不必要的内存分配
                    .build();
            myImageView.setController(draweeController);
        }
    }
}
