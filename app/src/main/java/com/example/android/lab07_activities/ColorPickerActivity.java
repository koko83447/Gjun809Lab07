package com.example.android.lab07_activities;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.RadioButton;

public class ColorPickerActivity extends Activity {

    private static final String TAG = "ColorPickerActivity";

    private int mColorInt;
    private CharSequence mColorName;

    public static final String BUNDLE_KEY_COLOR_INT = "com.example.android.colorInt";
    public static final String BUNDLE_KEY_COLOR_NAME = "com.example.android.colorName";

    private void saveData(){
        SharedPreferences prefs = getPreferences(MODE_PRIVATE);
        SharedPreferences.Editor editor = prefs.edit();
        editor.putInt(BUNDLE_KEY_COLOR_INT,mColorInt);
        editor.putString(BUNDLE_KEY_COLOR_NAME,mColorName.toString());
        editor.commit();
        Log.d(TAG,"saveData() mColorInt = " + mColorInt + "mColorName = " + mColorName);
    }

    private void restoreData(){
        SharedPreferences prefs = getPreferences(MODE_PRIVATE);
        mColorInt = prefs.getInt(BUNDLE_KEY_COLOR_INT,0);
        mColorName = prefs.getString(BUNDLE_KEY_COLOR_NAME,"holo_red_light");
        Log.d(TAG,"restoreData() mColorInt = " + mColorInt + "mColorName = " + mColorName);

        RadioButton radio = null;
        switch (mColorName.toString()){
            case "holo_red_light":
                radio = (RadioButton)findViewById(R.id.radio_holo_red_light);
                Log.d(TAG,"holo_red_light");
                break;
            case "holo_orange_light":
                radio = (RadioButton)findViewById(R.id.radio_holo_orange_light);
                Log.d(TAG,"holo_orange_dark");
                break;
            case "holo_green_light":
                radio = (RadioButton)findViewById(R.id.holo_green_light);
                Log.d(TAG,"holo_green_light");
                break;
            case "holo_blue_light":
                radio = (RadioButton)findViewById(R.id.holo_blue_light);
                Log.d(TAG,"holo_blue_dark");
                break;
        }
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
        setContentView(R.layout.activity_color_picker);
        Log.d(TAG,"onCreate");
        initColorData();
    }

    private void initColorData() {
        RadioButton radio = (RadioButton) findViewById(R.id.radio_holo_red_light);
        mColorInt = radio.getCurrentTextColor();
        mColorName = radio.getText();
        Log.d(TAG,"clickColor() mColorInt = " + mColorInt + " mColorName = " +mColorName );
    }

    public void clickColor(View view) {
        RadioButton radio = (RadioButton)view;
        mColorInt = radio.getCurrentTextColor();
        mColorName = radio.getText();

    }

    public void ok(View view){
        Intent intent = new Intent();
        intent.putExtra(BUNDLE_KEY_COLOR_INT, mColorInt);
        intent.putExtra(BUNDLE_KEY_COLOR_NAME, mColorName);
        setResult(RESULT_OK, intent);
        finish();
    }

    public void cancel(View view) {
        setResult(RESULT_CANCELED);
        finish();
    }
}
