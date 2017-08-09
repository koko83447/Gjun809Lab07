package com.example.android.lab07_activities;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.RadioButton;

public class ColorPickerActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_color_picker);
    }

    public void clickColor(View view) {
        boolean checked = ((RadioButton)view).isChecked();

        switch (view.getId()){
            case R.id.holo_red_light:
                if (checked)

                    break;
            case R.id.holo_orange_light:
                if (checked)

                    break;
            case R.id.holo_green_light:
                if (checked)

                    break;
            case R.id.holo_blue_light:
                if (checked)

                    break;
        }
    }
}
