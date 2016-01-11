package com.nate.frescostudydemo.activity;

import android.content.Intent;
import android.widget.Button;

import com.facebook.drawee.controller.ControllerListener;
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
    @Bind(R.id.gifBtn)
    Button gifBtn;
    @Bind(R.id.multiBtn)
    Button multiBtn;
    @Bind(R.id.listenerBtn)
    Button listenerBtn;
    @Bind(R.id.resizeBtn)
    Button resizeBtn;
    @Bind(R.id.modifyImgBtn)
    Button modifyImgBtn;
    @Bind(R.id.autoSizeImgBtn)
    Button autoSizeImgBtn;

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
        gifBtn.setOnClickListener(this);
        multiBtn.setOnClickListener(this);
        listenerBtn.setOnClickListener(this);
        resizeBtn.setOnClickListener(this);
        modifyImgBtn.setOnClickListener(this);
        autoSizeImgBtn.setOnClickListener(this);
    }

    @Override
    public void initData() {

    }

    @Override
    public void bindClick(int viewId) {

        switch (viewId) {
            case R.id.spimgBtn: {
                Intent intent = new Intent();
                intent.setClass(MainActivity.this, ShowProgressBarImgActivity.class);
                startActivity(intent);
            }
            break;
            case R.id.cropBtn: {
                Intent intent = new Intent();
                intent.setClass(MainActivity.this, CropImgActivity.class);
                startActivity(intent);
            }
            break;
            case R.id.circleAndCornerBtn: {
                Intent intent = new Intent();
                intent.setClass(MainActivity.this, CircleAndCornerActivity.class);
                startActivity(intent);
            }
            break;
            case R.id.jpegBtn: {
                Intent intent = new Intent();
                intent.setClass(MainActivity.this, JPEGImgActivity.class);
                startActivity(intent);
            }
            break;
            case R.id.gifBtn: {
                Intent intent = new Intent();
                intent.setClass(MainActivity.this, GifActivity.class);
                startActivity(intent);
            }
            break;
            case R.id.multiBtn: {
                Intent intent = new Intent();
                intent.setClass(MainActivity.this, MultiAndMultiPlexImgActivity.class);
                startActivity(intent);
            }
            break;
            case R.id.listenerBtn: {
                Intent intent = new Intent();
                intent.setClass(MainActivity.this, ControllerListenerActivity.class);
                startActivity(intent);
            }
            break;
            case R.id.resizeBtn: {
                Intent intent = new Intent();
                intent.setClass(MainActivity.this, ReSizeAndRotateActivity.class);
                startActivity(intent);
            }
            break;
            case R.id.modifyImgBtn: {
                Intent intent = new Intent();
                intent.setClass(MainActivity.this, ModifyPicActivity.class);
                startActivity(intent);
            }
            break;
            case R.id.autoSizeImgBtn: {
                Intent intent = new Intent();
                intent.setClass(MainActivity.this, AutoSizeActivity.class);
                startActivity(intent);
            }
            break;
            default:
                break;
        }
    }

}
