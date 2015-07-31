package com.nate.frescostudydemo.activity;

import android.net.Uri;
import android.widget.Button;

import com.facebook.drawee.backends.pipeline.Fresco;
import com.facebook.drawee.backends.pipeline.PipelineDraweeController;
import com.facebook.drawee.view.SimpleDraweeView;
import com.facebook.imagepipeline.common.ResizeOptions;
import com.facebook.imagepipeline.request.ImageRequest;
import com.facebook.imagepipeline.request.ImageRequestBuilder;
import com.nate.frescostudydemo.R;
import com.nate.frescostudydemo.base.BaseActivity;

import butterknife.Bind;

/**
 * Created by Nate on 2015/7/31.
 * 图像大小调整和旋转
 */
public class ReSizeAndRotateActivity extends BaseActivity {
    @Bind(R.id.my_image_view)
    SimpleDraweeView myImageView;
    @Bind(R.id.resizeImgBtn)
    Button resizeImgBtn;
    @Bind(R.id.rotateImgBtn)
    Button rotateImgBtn;

    @Override
    public void initContentLayout() {
        setContentView(R.layout.resize_and_rotate_img_layout);
    }

    @Override
    public void initListener() {
        resizeImgBtn.setOnClickListener(this);
        rotateImgBtn.setOnClickListener(this);
    }

    @Override
    public void initData() {

    }

    @Override
    public void bindClick(int viewId) {
        switch (viewId) {
            case R.id.resizeImgBtn:
                int width = 50;
                int height = 50;
                Uri uri = Uri.parse("file:///storage/sdcard0/DCIM/Camera/1434088743922.jpg");
                ImageRequest request = ImageRequestBuilder.newBuilderWithSource(uri)
                        .setResizeOptions(new ResizeOptions(width, height)).build();
                PipelineDraweeController controller= (PipelineDraweeController) Fresco.newDraweeControllerBuilder()
                        .setOldController(myImageView.getController())
                        .setImageRequest(request)
                        .build();
                myImageView.setController(controller);
                break;
            case R.id.rotateImgBtn:

                break;
            default:

                break;
        }
    }

}
