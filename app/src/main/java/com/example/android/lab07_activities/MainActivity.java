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

    private static final int SELECT_COLOR_REQUEST = 0;
    private static final int SELECT_IMAGE_REQUEST = 1;

    private TextView m_tv_color;
    private ImageView m_iv_logo;

    public static final String BUNDLE_KEY_COLOR_INT = "com.example.android.MainActivity.colorInt";
    public static final String BUNDLE_KEY_COLOR_NAME = "com.example.android.MainActivity.colorName";
    public static final String BUNDLE_KEY_DRAWABLE_ID = "com.example.android.MainActivity.drawableId";
    private int mColorInt;
    private String mColorName;
    private int mDrawableId;

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

        startActivityForResult(intent, SELECT_COLOR_REQUEST);
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
            case SELECT_COLOR_REQUEST:
                mColorInt = bundle.getInt(ColorPickerActivity.BUNDLE_KEY_COLOR_INT);
                mColorName = bundle.getString(ColorPickerActivity.BUNDLE_KEY_COLOR_NAME);

                m_tv_color.setText(mColorName);
                m_tv_color.setBackgroundColor(mColorInt);
                break;
            case SELECT_IMAGE_REQUEST:
                mDrawableId = bundle.getInt(ImagePickerActivity.BUNDLE_KEY_DRAWABLE_ID_INT);
                m_iv_logo.setImageResource(mDrawableId);
                break;
        }
    }
    public void selectImage(View view){
        Intent intent = new Intent(this, ImagePickerActivity.class);

        startActivityForResult(intent, SELECT_IMAGE_REQUEST);
    }
    private void saveData(){
        SharedPreferences prefs = getPreferences(MODE_PRIVATE);
        SharedPreferences.Editor editor = prefs.edit();
        editor.putInt(MainActivity.BUNDLE_KEY_COLOR_INT,mColorInt);
        editor.putString(MainActivity.BUNDLE_KEY_COLOR_NAME,mColorName);
        editor.putInt(MainActivity.BUNDLE_KEY_DRAWABLE_ID,mDrawableId);
        editor.commit();
    }

    private void resotreData(){
        SharedPreferences prefs = getPreferences(MODE_PRIVATE);
        mColorInt = prefs.getInt(MainActivity.BUNDLE_KEY_COLOR_INT,0);
        mColorName = prefs.getString(MainActivity.BUNDLE_KEY_COLOR_NAME,null);
        mDrawableId = prefs.getInt(MainActivity.BUNDLE_KEY_DRAWABLE_ID,-1);
        if (mColorName != null){
            m_tv_color.setBackgroundColor(mColorInt);
            m_tv_color.setText(mColorName);
        }
        if (mDrawableId != -1){
            m_iv_logo.setImageResource(mDrawableId);
        }
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

