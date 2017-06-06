package ru.kphu.itis.contentlibrarytest;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.LinearLayout;

import butterknife.BindView;
import butterknife.ButterKnife;

public class ViewContentActivity extends BaseActivity {

    @BindView(R.id.linear_layout_root) LinearLayout rootLayout;

    @NonNull
    public static Intent makeIntent(@NonNull Context context){
        return new Intent(context, ViewContentActivity.class);
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        unbinder = ButterKnife.bind(this);

        //rootLayout.addView();
    }

    @Override
    protected int getLayoutResId() {
        return R.layout.activity_view_content;
    }
}
