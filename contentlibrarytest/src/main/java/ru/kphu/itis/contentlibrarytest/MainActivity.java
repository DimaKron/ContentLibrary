package ru.kphu.itis.contentlibrarytest;

import android.app.Activity;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;

import butterknife.ButterKnife;
import butterknife.OnClick;

public class MainActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        unbinder = ButterKnife.bind(this);
    }

    @Override
    protected int getLayoutResId() {
        return R.layout.activity_main;
    }

    @OnClick(R.id.button_view)
    public void onViewButtonClick(){
        startActivity(ViewContentActivity.makeIntent(this));
    }

    @OnClick(R.id.button_dialog)
    public void onDialogButtonClick(){
        startActivity(DialogContentActivity.makeIntent(this));
    }

    @OnClick(R.id.button_activity)
    public void onActivityButtonClick(){
        startActivity(ActivityContentActivity.makeIntent(this));
    }
}
