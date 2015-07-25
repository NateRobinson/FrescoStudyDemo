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
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.show_progress_img_layout);
        ButterKnife.bind(this);
        askImgBtn.setOnClickListener(this);
    }

    @Override
    public void bindClick(int viewId) {
        if (viewId == R.id.askImgBtn) {
            GenericDraweeHierarchyBuilder builder = new GenericDraweeHierarchyBuilder(getResources());
            GenericDraweeHierarchy hierarchy = builder.setProgressBarImage(new ProgressBarDrawable()).build();
            myImageView.setHierarchy(hierarchy);
            Uri uri = Uri.parse("http://img4q.duitang.com/uploads/item/201305/20/20130520115416_VrUUR.jpeg");
            myImageView.setImageURI(uri);
        }
    }
}
