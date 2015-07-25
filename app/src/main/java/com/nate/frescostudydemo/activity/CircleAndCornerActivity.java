package com.nate.frescostudydemo.activity;

import android.net.Uri;
import android.os.Bundle;
import android.widget.Button;

import com.facebook.drawee.generic.GenericDraweeHierarchy;
import com.facebook.drawee.generic.GenericDraweeHierarchyBuilder;
import com.facebook.drawee.generic.RoundingParams;
import com.facebook.drawee.view.SimpleDraweeView;
import com.nate.frescostudydemo.R;
import com.nate.frescostudydemo.base.BaseActivity;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by Administrator on 2015/7/25.
 * 显示圆角和圆圈
 */
public class CircleAndCornerActivity extends BaseActivity {
    @Bind(R.id.my_image_view)
    SimpleDraweeView myImageView;
    @Bind(R.id.circleBtn)
    Button circleBtn;
    @Bind(R.id.cornerBtn)
    Button cornerBtn;

    @Override
    public void initContentLayout() {
        setContentView(R.layout.circle_and_corner_img_layout);
    }

    @Override
    public void initListener() {
        circleBtn.setOnClickListener(this);
        cornerBtn.setOnClickListener(this);
    }

    @Override
    public void initData() {

    }

    @Override
    public void bindClick(int viewId) {
        Uri uri = Uri.parse("http://img4q.duitang.com/uploads/item/201304/27/20130427043538_wAfHC.jpeg");
        GenericDraweeHierarchyBuilder builder = new GenericDraweeHierarchyBuilder(getResources());
        RoundingParams params = null;
        if (viewId == R.id.circleBtn) {
            //代码中设置圆角
            params = RoundingParams.asCircle();
        } else if (viewId == R.id.cornerBtn) {
            params = RoundingParams.fromCornersRadius(50f);//设置圆角大小
            //params.setOverlayColor(R.color.blue);//覆盖层
            //params.setBorder(R.color.red, 5);//边框
            //params.setRoundAsCircle(true);//如果是RoundingParams.fromCornersRadius，这个可以强制进行圆形展示
        }
        GenericDraweeHierarchy hierarchy = builder.setRoundingParams(params).build();
        myImageView.setHierarchy(hierarchy);
        myImageView.setImageURI(uri);
    }

}
