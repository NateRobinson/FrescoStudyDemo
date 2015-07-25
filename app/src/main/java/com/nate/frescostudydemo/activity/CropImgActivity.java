package com.nate.frescostudydemo.activity;

import android.graphics.PointF;
import android.net.Uri;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;

import com.facebook.drawee.drawable.ScalingUtils;
import com.facebook.drawee.generic.GenericDraweeHierarchy;
import com.facebook.drawee.generic.GenericDraweeHierarchyBuilder;
import com.facebook.drawee.view.SimpleDraweeView;
import com.nate.frescostudydemo.R;
import com.nate.frescostudydemo.base.BaseActivity;

import butterknife.Bind;

/**
 * Created by Administrator on 2015/7/25.
 */
public class CropImgActivity extends BaseActivity {

    @Bind(R.id.my_image_view)
    SimpleDraweeView myImageView;
    @Bind(R.id.spinner)
    Spinner spinner;
    @Bind(R.id.askImgBtn)
    Button askImgBtn;
    @Bind(R.id.cropExplainTv)
    TextView cropExplainTv;
    private int type = 0;
    private String[] mExplain = null;

    @Override
    public void initContentLayout() {
        setContentView(R.layout.crop_img_layout);
    }

    @Override
    public void initListener() {
        askImgBtn.setOnClickListener(this);
    }

    @Override
    public void initData() {
        // 建立数据源
        String[] mItems = getResources().getStringArray(R.array.croptype);
        mExplain = getResources().getStringArray(R.array.croptypeexplain);
        // 建立Adapter并且绑定数据源
        ArrayAdapter<String> mAdapter = new ArrayAdapter(this, android.R.layout.simple_spinner_item, mItems);
        //绑定 Adapter到控件
        spinner.setAdapter(mAdapter);
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view,
                                       int position, long id) {
                type = position;
                cropExplainTv.setText(mExplain[position]);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
    }

    @Override
    public void bindClick(int viewId) {
        if (viewId == R.id.askImgBtn) {
            GenericDraweeHierarchyBuilder builder = new GenericDraweeHierarchyBuilder(getResources());
            GenericDraweeHierarchy hierarchy = null;
            switch (type) {
                case 0:
                    hierarchy = builder.setActualImageScaleType(ScalingUtils.ScaleType.CENTER).build();
                    break;
                case 1:
                    hierarchy = builder.setActualImageScaleType(ScalingUtils.ScaleType.CENTER_CROP).build();
                    break;
                case 2:
                    //设置focusCrop的缩放形式  并指定缩放的中心点在左上角
                    PointF point = new PointF(0f, 0f);
                    hierarchy = builder.setActualImageScaleType(ScalingUtils.ScaleType.FOCUS_CROP)
                            .setActualImageFocusPoint(point)
                            .build();
                    break;
                case 3:
                    hierarchy = builder.setActualImageScaleType(ScalingUtils.ScaleType.CENTER_INSIDE).build();
                    break;
                case 4:
                    hierarchy = builder.setActualImageScaleType(ScalingUtils.ScaleType.FIT_CENTER).build();
                    break;
                case 5:
                    hierarchy = builder.setActualImageScaleType(ScalingUtils.ScaleType.FIT_START).build();
                    break;
                case 6:
                    hierarchy = builder.setActualImageScaleType(ScalingUtils.ScaleType.FIT_END).build();
                    break;
                case 7:
                    hierarchy = builder.setActualImageScaleType(ScalingUtils.ScaleType.FIT_XY).build();
                    break;
                case 8:
                    hierarchy = builder.setActualImageScaleType(null).build();
                    break;
                default:
                    hierarchy=builder.build();
                    break;
            }
            myImageView.setHierarchy(hierarchy);
            Uri uri = Uri.parse("http://img4q.duitang.com/uploads/item/201305/20/20130520115416_VrUUR.jpeg");
            myImageView.setImageURI(uri);
        }
    }
}
