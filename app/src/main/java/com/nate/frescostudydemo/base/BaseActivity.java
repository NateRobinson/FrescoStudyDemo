package com.nate.frescostudydemo.base;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;

import butterknife.ButterKnife;

/**
 * Created by Nate on 2015/7/24.
 * the base activity of this application
 */
public abstract class BaseActivity extends Activity implements View.OnClickListener{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ButterKnife.bind(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            default:bindClick(v.getId());
                break;
        }
    }

    //abstract method for child activity onclick events
    public abstract void bindClick(int viewId);
}
