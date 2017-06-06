package ru.kphu.itis.contentlibrarytest;

import android.os.Bundle;
import android.support.annotation.LayoutRes;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import butterknife.Unbinder;

/**
 * Created by Дмитрий on 03.04.2017.
 */

public abstract class BaseActivity extends AppCompatActivity{

    protected Unbinder unbinder;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(getLayoutResId());


    }

    @Override
    protected void onDestroy() {

        if(unbinder!=null){
            unbinder.unbind();
        }

        super.onDestroy();
    }

    @LayoutRes
    protected abstract int getLayoutResId();
}
