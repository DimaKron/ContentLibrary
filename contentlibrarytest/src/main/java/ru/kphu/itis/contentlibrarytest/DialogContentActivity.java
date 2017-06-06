package ru.kphu.itis.contentlibrarytest;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.os.Bundle;

import butterknife.ButterKnife;
import butterknife.OnClick;

public class DialogContentActivity extends BaseActivity {

    @NonNull
    public static Intent makeIntent(@NonNull Context context){
        return new Intent(context, DialogContentActivity.class);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        unbinder = ButterKnife.bind(this);
    }

    @Override
    protected int getLayoutResId() {
        return R.layout.activity_one_button;
    }

    @OnClick(R.id.floating_action_button_get)
    public void onGetClick(){
        //
    }
}
