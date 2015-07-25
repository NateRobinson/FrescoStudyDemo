package com.nate.frescostudydemo.activity;

import android.content.Intent;
import android.widget.Button;

import com.nate.frescostudydemo.R;
import com.nate.frescostudydemo.base.BaseActivity;

import butterknife.Bind;

/**
 * 应用主页面，在这里面选择进入哪一个模块
 */
public class MainActivity extends BaseActivity {

    @Bind(R.id.spimgBtn)
    Button spimgBtn;
    @Bind(R.id.cropBtn)
    Button cropBtn;
    @Bind(R.id.circleAndCornerBtn)
    Button circleAndCornerBtn;
    @Bind(R.id.jpegBtn)
    Button jpegBtn;

    @Override
    public void initContentLayout() {
        setContentView(R.layout.activity_main);
    }

    @Override
    public void initListener() {
        spimgBtn.setOnClickListener(this);
        cropBtn.setOnClickListener(this);
        circleAndCornerBtn.setOnClickListener(this);
        jpegBtn.setOnClickListener(this);
    }

    @Override
    public void initData() {

    }

    @Override
    public void bindClick(int viewId) {
        Intent intent = new Intent();
        switch (viewId) {
            case R.id.spimgBtn:
                intent.setClass(MainActivity.this, ShowProgressBarImgActivity.class);
                startActivity(intent);
                break;
            case R.id.cropBtn:
                intent.setClass(MainActivity.this, CropImgActivity.class);
                startActivity(intent);
                break;
            case R.id.circleAndCornerBtn:
                intent.setClass(MainActivity.this, CircleAndCornerActivity.class);
                startActivity(intent);
                break;
            case R.id.jpegBtn:
                intent.setClass(MainActivity.this, JPEGImgActivity.class);
                startActivity(intent);
                break;
            default:
                break;
        }
    }

}
