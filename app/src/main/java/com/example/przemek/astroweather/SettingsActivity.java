package com.example.przemek.astroweather;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class SettingsActivity extends AppCompatActivity {

    private SettingsStorage settingsStorage = SettingsStorage.getInstance();
    private EditText et_longitude;
    private EditText et_latitude;
    private EditText et_frequency;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);
        init();
        saveAndExitHandle();
        showCurrentSettings();
    }

    private void init(){
        et_longitude = (EditText) findViewById(R.id.et_longitude);
        et_latitude = (EditText) findViewById(R.id.et_latitude);
        et_frequency = (EditText) findViewById(R.id.et_frequency);
    }

    private void saveAndExitHandle(){
        Button button = (Button) findViewById(R.id.btn_save_and_exit);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                settingsStorage.setLongitude(
                        Float.parseFloat(et_longitude.getText().toString()));
                settingsStorage.setLatitude(
                        Float.parseFloat(et_latitude.getText().toString()));
                settingsStorage.setDataFrequencyRefresh(
                        Integer.parseInt(et_frequency.getText().toString()));
                startActivity(new Intent(SettingsActivity.this, MainActivity.class));
            }
        });
    }

    private void showCurrentSettings(){
        et_longitude.setText(String.valueOf(settingsStorage.getLongitude()));
        et_latitude.setText(String.valueOf(settingsStorage.getLatitude()));
        et_frequency.setText(String.valueOf(settingsStorage.getDataFrequencyRefresh()));
    }
}
