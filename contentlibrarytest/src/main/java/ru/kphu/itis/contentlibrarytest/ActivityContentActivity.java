package ru.kphu.itis.contentlibrarytest;

import android.content.Context;
import android.content.Intent;
import android.graphics.Typeface;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.os.Bundle;
import android.support.annotation.StringRes;
import android.widget.Toast;

import butterknife.ButterKnife;
import butterknife.OnClick;
import ru.kphu.itis.contentlibrary.ContentLibrary;
import ru.kphu.itis.contentlibrary.activity.base.ContentActivity;
import ru.kphu.itis.contentlibrary.content_source.ContentSource;
import ru.kphu.itis.contentlibrary.content_source.ContentSourceType;
import ru.kphu.itis.contentlibrary.content_source.content_camera.DefaultContentCamera;

public class ActivityContentActivity extends BaseActivity {

    private static final int REQUEST_CODE_CONTENT = 1;

    @NonNull
    public static Intent makeIntent(@NonNull Context context){
        return new Intent(context, ActivityContentActivity.class);
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
        ContentLibrary
                .getInstance()
                .asActivity()
                .start(this, REQUEST_CODE_CONTENT);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (resultCode == RESULT_OK){
            switch (requestCode){
                case REQUEST_CODE_CONTENT:
                    ContentSourceType sourceType = (ContentSourceType) data.getSerializableExtra(ContentActivity.KEY_CONTENT_SOURCE_TYPE);
                    String uriStr = data.getStringExtra(ContentActivity.KEY_URI_STR);
                    Toast.makeText(this, sourceType + "\n" + uriStr, Toast.LENGTH_LONG).show();
                    break;
            }
        }
    }
}
