package com.nate.frescostudydemo.activity;

import android.graphics.drawable.Animatable;
import android.net.Uri;
import android.widget.Button;

import com.facebook.drawee.backends.pipeline.Fresco;
import com.facebook.drawee.controller.ControllerListener;
import com.facebook.drawee.interfaces.DraweeController;
import com.facebook.drawee.view.SimpleDraweeView;
import com.nate.frescostudydemo.R;
import com.nate.frescostudydemo.base.BaseActivity;

import butterknife.Bind;

/**
 * Created by Administrator on 2015/7/25.
 * 展示gif动画
 */
public class GifActivity extends BaseActivity {
    @Bind(R.id.my_image_view)
    SimpleDraweeView myImageView;
    @Bind(R.id.askImgBtn)
    Button askImgBtn;
    @Bind(R.id.stopAnim)
    Button stopAnim;
    @Bind(R.id.startAnim)
    Button startAnim;
    //构建ControllerListener
    private ControllerListener listener = new ControllerListener() {
        @Override
        public void onSubmit(String s, Object o) {

        }

        @Override
        public void onFinalImageSet(String s, Object o, Animatable anim) {
            //if (anim != null) {
            //anim.start();
            //}
        }

        @Override
        public void onIntermediateImageSet(String s, Object o) {

        }

        @Override
        public void onIntermediateImageFailed(String s, Throwable throwable) {

        }

        @Override
        public void onFailure(String s, Throwable throwable) {

        }

        @Override
        public void onRelease(String s) {

        }
    };

    @Override
    public void initContentLayout() {
        setContentView(R.layout.gif_img_layout);
    }

    @Override
    public void initListener() {
        askImgBtn.setOnClickListener(this);
        stopAnim.setOnClickListener(this);
        startAnim.setOnClickListener(this);
    }

    @Override
    public void initData() {

    }

    @Override
    public void bindClick(int viewId) {
        if (viewId == R.id.askImgBtn) {
            Uri uri = Uri.parse("http://www.sznews.com/humor/attachement/gif/site3/20140902/4487fcd7fc66156f51db5d.gif");
            DraweeController controller = Fresco.newDraweeControllerBuilder()
                    .setUri(uri)
                    .setAutoPlayAnimations(false)
                    .setOldController(myImageView.getController())
                    .setControllerListener(listener)
                    .build();
            myImageView.setController(controller);
        } else if (viewId == R.id.stopAnim) {
            Animatable animation = myImageView.getController().getAnimatable();
            if (animation != null && animation.isRunning()) {
                animation.stop();
            }
        } else if (viewId == R.id.startAnim) {
            Animatable animation = myImageView.getController().getAnimatable();
            if (animation != null && !animation.isRunning()) {
                animation.start();
            }
        }
    }
}
