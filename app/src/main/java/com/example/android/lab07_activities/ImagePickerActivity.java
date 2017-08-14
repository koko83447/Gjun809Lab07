package com.example.android.lab07_activities;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageButton;
import android.widget.RadioButton;

public class ImagePickerActivity extends AppCompatActivity {

    private static final String TAG = "ImagePickerActivity";

    private int mDrawableId;

    public static final  String KEY_IMAGE = "com.example.android.img";
    public static final String BUNDLE_KEY_DRAWABLE_ID_INT = "com.example.android.drawable";


    private void saveData(){
        SharedPreferences prefs = getPreferences(MODE_PRIVATE);
        SharedPreferences.Editor editor = prefs.edit();
        editor.putInt(BUNDLE_KEY_DRAWABLE_ID_INT,mDrawableId);
        editor.commit();
        Log.d(TAG,"saveData() mDrawableInt = " + mDrawableId);
    }

    private void restoreData(){
        SharedPreferences prefs = getPreferences(MODE_PRIVATE);
        mDrawableId = prefs.getInt(BUNDLE_KEY_DRAWABLE_ID_INT,0);
        Log.d(TAG,"restoreData() mDrawableInt = " + mDrawableId );

        RadioButton radio = null;

        if (radio != null){
            radio.setChecked(true);
        }
    }


    @Override
    protected void onResume(){
        super.onResume();
        Log.d(TAG,"onResume");
        restoreData();
    }
    @Override
    protected void onPause(){
        super.onPause();
        Log.d(TAG,"onPause");
        saveData();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_image_picker);
        Log.d(TAG,"onCreate");

    }

    public void clickImage(View view) {
        Intent intent = new Intent();

        switch (view.getId()){
            case R.id.ib_hornets:
                intent.putExtra(BUNDLE_KEY_DRAWABLE_ID_INT,R.drawable.hornets);
                break;
            case R.id.ib_rockets:
                intent.putExtra(BUNDLE_KEY_DRAWABLE_ID_INT,R.drawable.rockets);
                break;
        }
        setResult(RESULT_OK,intent);
        finish();
    }
}

