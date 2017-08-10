package com.example.android.lab07_activities;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;

public class ImagePickerActivity extends AppCompatActivity {

    public static final  String KEY_IMAGE = "com.example.android.img";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_image_picker);

    }



    public void clickImage(View view) {
        Intent intent = new Intent();

        switch (view.getId()){
            case R.id.ib_hornets:
                intent.putExtra(KEY_IMAGE,R.drawable.hornets);
                break;
            case R.id.ib_rockets:
                intent.putExtra(KEY_IMAGE,R.drawable.rockets);
                break;
        }
        setResult(RESULT_OK,intent);
        finish();
    }
}

