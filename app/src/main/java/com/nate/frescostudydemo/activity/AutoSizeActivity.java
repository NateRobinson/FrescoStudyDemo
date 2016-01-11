package com.nate.frescostudydemo.activity;

import android.annotation.TargetApi;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Build;
import android.util.Log;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;

import com.facebook.drawee.backends.pipeline.Fresco;
import com.facebook.drawee.backends.pipeline.PipelineDraweeController;
import com.facebook.drawee.view.SimpleDraweeView;
import com.facebook.imagepipeline.request.BasePostprocessor;
import com.facebook.imagepipeline.request.ImageRequest;
import com.facebook.imagepipeline.request.ImageRequestBuilder;
import com.facebook.imagepipeline.request.Postprocessor;
import com.nate.frescostudydemo.R;
import com.nate.frescostudydemo.ScreenUtils;
import com.nate.frescostudydemo.base.BaseActivity;

import butterknife.Bind;

/**
 * Created by Nate on 2015/9/16.
 */
public class AutoSizeActivity extends BaseActivity {
    @Bind(R.id.loadBigPicBtn)
    Button loadBigPicBtn;
    @Bind(R.id.loadSmallPicBtn)
    Button loadSmallPicBtn;
    @Bind(R.id.picContent)
    LinearLayout picContent;
    private SimpleDraweeView simpleDraweeView;

    @Override
    public void initContentLayout() {
        setContentView(R.layout.auto_size_img_layout);
    }

    @Override
    public void initListener() {
        loadBigPicBtn.setOnClickListener(this);
        loadSmallPicBtn.setOnClickListener(this);
    }

    @Override
    public void initData() {
        simpleDraweeView = new SimpleDraweeView(this);
    }

    @Override
    public void bindClick(int viewId) {

        picContent.removeAllViews();
        if (viewId == R.id.loadBigPicBtn) {
            LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(ScreenUtils.getScreenWidth(this), ViewGroup.LayoutParams.WRAP_CONTENT);
            simpleDraweeView.setLayoutParams(params);
            simpleDraweeView.setAspectRatio(1.0f);
            final Uri uri = Uri.parse("http://cdn.duitang.com/uploads/item/201405/10/20140510221440_zvkHi.thumb.700_0.jpeg");
            Postprocessor redMeshPostprocessor = new BasePostprocessor() {
                @Override
                public String getName() {
                    return "redMeshPostprocessor";
                }

                @TargetApi(Build.VERSION_CODES.KITKAT)
                @Override
                public void process(Bitmap bitmap) {
//                    for (int x = 0; x < bitmap.getWidth(); x+=2) {
//                        for (int y = 0; y < bitmap.getHeight(); y+=2) {
//                            bitmap.setPixel(x, y, Color.RED);
//                        }
//                    }
                    float picWidth = bitmap.getWidth();
                    float picHeight = bitmap.getHeight();
                    float ratio = picHeight / picWidth;
                    Log.d("guxuewu", "picWidth==>" + picWidth + "picHeight==>" + picHeight + "ratio===>" + ratio);
//                    int newPicHeight = (int) (ScreenUtils.getScreenWidth(AutoSizeActivity.this) * ratio);
                    bitmap.setHeight((int) ((float) (ScreenUtils.getScreenWidth(AutoSizeActivity.this)) * picHeight / picWidth));
                    bitmap.setWidth(ScreenUtils.getScreenWidth(AutoSizeActivity.this));
                }
            };
            ImageRequest request = ImageRequestBuilder.newBuilderWithSource(uri)
                    .setPostprocessor(redMeshPostprocessor)
                    .build();
            PipelineDraweeController controller = (PipelineDraweeController)
                    Fresco.newDraweeControllerBuilder()
                            .setImageRequest(request)
                            .setOldController(simpleDraweeView.getController())
                            .build();
            simpleDraweeView.setController(controller);
            picContent.addView(simpleDraweeView);

        } else if (viewId == R.id.loadSmallPicBtn) {
            LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(ScreenUtils.getScreenWidth(this), ViewGroup.LayoutParams.WRAP_CONTENT);
            simpleDraweeView.setLayoutParams(params);
            simpleDraweeView.setAspectRatio(1.0f);
            final Uri uri = Uri.parse("http://img4q.duitang.com/uploads/item/201304/27/20130427043538_wAfHC.jpeg");
            Postprocessor redMeshPostprocessor = new BasePostprocessor() {

                @Override
                public String getName() {
                    return "redMeshPostprocessor";
                }

                @TargetApi(Build.VERSION_CODES.KITKAT)
                @Override
                public void process(Bitmap bitmap) {
//                    for (int x = 0; x < bitmap.getWidth(); x+=2) {
//                        for (int y = 0; y < bitmap.getHeight(); y+=2) {
//                            bitmap.setPixel(x, y, Color.RED);
//                        }
//                    }
                    float picWidth = bitmap.getWidth();
                    float picHeight = bitmap.getHeight();
                    float ratio = picHeight / picWidth;
                    Log.d("guxuewu", "picWidth==>" + picWidth + "picHeight==>" + picHeight + "ratio===>" + ratio);
//                    int newPicHeight = (int) (ScreenUtils.getScreenWidth(AutoSizeActivity.this) * ratio);

                }
            };
            ImageRequest request = ImageRequestBuilder.newBuilderWithSource(uri)
                    .setPostprocessor(redMeshPostprocessor)
                    .build();
            PipelineDraweeController controller = (PipelineDraweeController)
                    Fresco.newDraweeControllerBuilder()
                            .setImageRequest(request)
                            .setOldController(simpleDraweeView.getController())
                            .build();
            simpleDraweeView.setController(controller);

            picContent.addView(simpleDraweeView);
        }
    }

}
