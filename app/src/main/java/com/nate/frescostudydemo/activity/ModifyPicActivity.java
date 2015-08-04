package com.nate.frescostudydemo.activity;

import android.graphics.Bitmap;
import android.graphics.Color;
import android.net.Uri;
import android.support.v4.widget.SwipeRefreshLayout;
import android.util.TimingLogger;
import android.widget.Button;

import com.facebook.drawee.backends.pipeline.Fresco;
import com.facebook.drawee.backends.pipeline.PipelineDraweeController;
import com.facebook.drawee.view.SimpleDraweeView;
import com.facebook.imagepipeline.request.BasePostprocessor;
import com.facebook.imagepipeline.request.ImageRequest;
import com.facebook.imagepipeline.request.ImageRequestBuilder;
import com.facebook.imagepipeline.request.Postprocessor;
import com.nate.frescostudydemo.R;
import com.nate.frescostudydemo.base.BaseActivity;

import butterknife.Bind;

/**
 * Created by Administrator on 2015/8/1.
 * 图片修改功能实现
 */
public class ModifyPicActivity extends BaseActivity {
    @Bind(R.id.my_image_view)
    SimpleDraweeView myImageView;
    @Bind(R.id.modifyImgBtn)
    Button modifyImgBtn;

    @Override
    public void initContentLayout() {
        setContentView(R.layout.modify_img_layout);
    }

    @Override
    public void initListener() {
        modifyImgBtn.setOnClickListener(this);
    }

    @Override
    public void initData() {

    }

    @Override
    public void bindClick(int viewId) {
        if (viewId == R.id.modifyImgBtn) {
            Uri uri = Uri.parse("http://c.hiphotos.baidu.com/image/pic/item/962bd40735fae6cd21a519680db30f2442a70fa1.jpg");
            Postprocessor redMeshPostprocessor = new BasePostprocessor() {
                @Override
                public String getName() {
                    return "redMeshPostprocessor";
                }

                //绘制红色点状网格
                @Override
                public void process(Bitmap bitmap) {
                    for (int x = 0; x < bitmap.getWidth(); x += 2) {
                        for (int y = 0; y < bitmap.getHeight(); y += 2) {
                            bitmap.setPixel(x, y, Color.RED);
                        }
                    }
                }
            };

            ImageRequest request = ImageRequestBuilder.newBuilderWithSource(uri)
                    .setPostprocessor(redMeshPostprocessor)
                    .build();

            PipelineDraweeController controller = (PipelineDraweeController)
                    Fresco.newDraweeControllerBuilder()
                            .setImageRequest(request)
                            .setOldController(myImageView.getController())
                            .build();
            myImageView.setController(controller);
        }
    }
}
