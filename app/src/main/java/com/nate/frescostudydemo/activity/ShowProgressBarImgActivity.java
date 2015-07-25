package com.nate.frescostudydemo.activity;

import android.net.Uri;
import android.os.Bundle;
import android.widget.Button;

import com.facebook.drawee.backends.pipeline.Fresco;
import com.facebook.drawee.drawable.ProgressBarDrawable;
import com.facebook.drawee.generic.GenericDraweeHierarchy;
import com.facebook.drawee.generic.GenericDraweeHierarchyBuilder;
import com.facebook.drawee.view.SimpleDraweeView;
import com.nate.frescostudydemo.R;
import com.nate.frescostudydemo.base.BaseActivity;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by Nate on 2015/7/24.
 * 带进度条的图片
 */
public class ShowProgressBarImgActivity extends BaseActivity {

    @Bind(R.id.my_image_view)
    SimpleDraweeView myImageView;
    @Bind(R.id.askImgBtn)
    Button askImgBtn;

    @Override
    public void initContentLayout() {
        setContentView(R.layout.show_progress_img_layout);
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
            GenericDraweeHierarchyBuilder builder = new GenericDraweeHierarchyBuilder(getResources());
            GenericDraweeHierarchy hierarchy = builder.setProgressBarImage(new ProgressBarDrawable()).build();
            myImageView.setHierarchy(hierarchy);
            Uri uri = Uri.parse("http://img4.duitang.com/uploads/item/201211/24/20121124175330_ruKEK.jpeg");
            myImageView.setImageURI(uri);
        }
    }
}
