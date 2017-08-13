package com.example.android.lab07_activities;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = "MainActivity";

    private static final int 取得選擇顏色 = 0;
    private static final int 取得選擇圖片 = 1;

    private TextView m_tv_color;
    private ImageView m_iv_logo;

    public static final String BUNDLE_KEY_COLOR_INT = "com.example.android.MainActivity.colorInt";
    public static final String BUNDLE_KEY_COLOR_NAME = "com.example.android.MainActivity.colorName";
    private int mColorInt;
    private String mColorName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Log.d(TAG, "onCreate()");
        init();
        resotreData();
        Log.d(TAG,"restoreData()");
    }


    private void init() {
        m_tv_color = (TextView)findViewById(R.id.tv_color);
        m_iv_logo = (ImageView)findViewById(R.id.iv_logo);
    }

    public void selectColor(View view) {
        Intent intent = new Intent(this, ColorPickerActivity.class);

        startActivityForResult(intent, 取得選擇顏色);
    }



    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        Log.d(TAG,"onActivityResult()");

        if (resultCode != RESULT_OK){
            return;
        }
        Bundle bundle = data.getExtras();

        switch (requestCode){
            case 取得選擇顏色:
                mColorInt = bundle.getInt(ColorPickerActivity.BUNDLE_KEY_COLOR_INT);
                mColorName = bundle.getString(ColorPickerActivity.BUNDLE_KEY_COLOR_NAME);

                m_tv_color.setText(mColorName);
                m_tv_color.setBackgroundColor(mColorInt);
                break;
            case 取得選擇圖片:
                int drawableId = bundle.getInt(ImagePickerActivity.KEY_IMAGE);
                m_iv_logo.setImageResource(drawableId);
                break;
        }
    }
    public void selectImage(View view){
        Intent intent = new Intent(this, ImagePickerActivity.class);

        startActivityForResult(intent, 取得選擇圖片);
    }
    private void saveData(){
        SharedPreferences prefs = getPreferences(MODE_PRIVATE);
        SharedPreferences.Editor editor = prefs.edit();
        editor.putInt(MainActivity.BUNDLE_KEY_COLOR_INT,mColorInt);
        editor.putString(MainActivity.BUNDLE_KEY_COLOR_NAME,mColorName);
        editor.commit();
    }

    private void resotreData(){
        SharedPreferences prefs = getPreferences(MODE_PRIVATE);
        mColorInt = prefs.getInt(MainActivity.BUNDLE_KEY_COLOR_INT,0);
        mColorName = prefs.getString(MainActivity.BUNDLE_KEY_COLOR_NAME,null);
        if (mColorName == null){
            return;
        }
        m_tv_color.setBackgroundColor(mColorInt);
        m_tv_color.setText(mColorName);
    }

    @Override
    protected void onStart() {
        super.onStart();
        Log.d(TAG,"onStart()");
    }

    @Override
    protected void onStop() {
        super.onStop();
        Log.d(TAG,"onStop()");

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.d(TAG,"onDestroy()");

    }

    @Override
    protected void onPause() {
        super.onPause();
        Log.d(TAG,"onPause()");
        saveData();
        Log.d(TAG,"saveData()");
    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.d(TAG,"onResume()");
    }
}

