package com.example.android.lab07_activities;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private static final int 取得選擇顏色 = 0;
    private static final int 取得選擇圖片 = 1;

    private TextView m_tv_color;
    private ImageView m_iv_logo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        init();
    }

    private void init() {
        m_tv_color = (TextView)findViewById(R.id.tv_color);
        m_iv_logo = (ImageView)findViewById(R.id.iv_logo);
    }

    public void selectColor(View view) {
        Intent intent = new Intent(this, ColorPickerActivity.class);

        startActivityForResult(intent, 取得選擇顏色);
    }
    public void selectImage(View view){
        Intent intent = new Intent(this, ImagePickerActivity.class);

        startActivityForResult(intent, 取得選擇圖片);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (resultCode != RESULT_OK){
            return;
        }
        Bundle bundle = data.getExtras();

        switch (requestCode){
            case 取得選擇顏色:
                int colorInt = bundle.getInt(ColorPickerActivity.BUNDLE_KEY_COLOR_INT);
                CharSequence colorName = bundle.getCharSequence(ColorPickerActivity.BUNDLE_KEY_COLOR_NAME);

                m_tv_color.setText(colorName);
                m_tv_color.setBackgroundColor(colorInt);
                break;
            case 取得選擇圖片:
                int drawableId = bundle.getInt(ImagePickerActivity.KEY_IMAGE);
                m_iv_logo.setImageResource(drawableId);
                break;
        }

    }
}

