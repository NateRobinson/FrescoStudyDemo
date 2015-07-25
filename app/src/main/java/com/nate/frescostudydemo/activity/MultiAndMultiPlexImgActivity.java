package com.nate.frescostudydemo.activity;

import android.net.Uri;
import android.widget.Button;

import com.facebook.drawee.backends.pipeline.Fresco;
import com.facebook.drawee.interfaces.DraweeController;
import com.facebook.drawee.view.SimpleDraweeView;
import com.facebook.imagepipeline.request.ImageRequest;
import com.facebook.imagepipeline.request.ImageRequestBuilder;
import com.nate.frescostudydemo.R;
import com.nate.frescostudydemo.base.BaseActivity;

import java.io.File;

import butterknife.Bind;

/**
 * Created by Administrator on 2015/7/25.
 * 多图请求及图片复用
 */
public class MultiAndMultiPlexImgActivity extends BaseActivity {
    @Bind(R.id.my_image_view)
    SimpleDraweeView myImageView;
    @Bind(R.id.multiImgBtn)
    Button multiImgBtn;
    @Bind(R.id.thumbnailImgBtn)
    Button thumbnailImgBtn;

    @Override
    public void initContentLayout() {
        setContentView(R.layout.multi_img_layout);
    }

    @Override
    public void initListener() {
        multiImgBtn.setOnClickListener(this);
        thumbnailImgBtn.setOnClickListener(this);
    }

    @Override
    public void initData() {

    }

    @Override
    public void bindClick(int viewId) {
        if (viewId == R.id.multiImgBtn) {
            //同一张图片，不同品质的两个uri
            Uri lowResUri =Uri.parse("http://img1.gamedog.cn/2012/03/11/19-120311133617-50.jpg");
            Uri highResUri=Uri.parse("http://img5.duitang.com/uploads/item/201312/03/20131203153823_Y4y8F.jpeg");
            DraweeController controller = Fresco.newDraweeControllerBuilder()
                    .setLowResImageRequest(ImageRequest.fromUri(lowResUri))
                    .setImageRequest(ImageRequest.fromUri(highResUri))
                    .setOldController(myImageView.getController())
                    .build();
            myImageView.setController(controller);
        } else if (viewId == R.id.thumbnailImgBtn) {
            //将本地图片地址转换成Uri
            Uri uri=Uri.fromFile(new File("/storage/sdcard0/DCIM/Camera/1434088743922.jpg"));
            ImageRequest request = ImageRequestBuilder.newBuilderWithSource(uri)
                    .setLocalThumbnailPreviewsEnabled(true)
                    .build();
            DraweeController controller = Fresco.newDraweeControllerBuilder()
                    .setImageRequest(request)
                    .setOldController(myImageView.getController())
                    .build();
            myImageView.setController(controller);
        }
    }

}
