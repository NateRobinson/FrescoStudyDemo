package com.nate.frescostudydemo.activity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;

import com.nate.frescostudydemo.R;
import com.nate.frescostudydemo.base.BaseActivity;

import butterknife.Bind;
import butterknife.ButterKnife;

public class MainActivity extends BaseActivity {

    @Bind(R.id.spimgBtn)
    Button spimgBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        initData();
    }

    private void initData() {
        spimgBtn.setOnClickListener(this);
    }

    @Override
    public void bindClick(int viewId) {
        Intent intent =new Intent();
        switch (viewId) {
            case R.id.spimgBtn:
                intent.setClass(MainActivity.this,ShowProgressBarImgActivity.class);
                startActivity(intent);
                break;
            default:
                break;
        }
    }

}
