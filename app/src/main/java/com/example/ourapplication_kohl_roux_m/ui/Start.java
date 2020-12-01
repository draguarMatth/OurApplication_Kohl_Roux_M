package com.example.ourapplication_kohl_roux_m.ui;

import android.app.admin.DevicePolicyManager;
import android.content.Intent;
import android.os.Bundle;
import android.os.Environment;
import android.os.FileUtils;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.Switch;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;

import com.example.ourapplication_kohl_roux_m.R;
import com.example.ourapplication_kohl_roux_m.ui.Settings.SettingsActivity;
import com.example.ourapplication_kohl_roux_m.ui.trajet.ListTrajet_BazActivity;
//import com.example.ourapplication_kohl_roux_m.ui.car.ListMyCar;
import com.google.android.gms.common.util.DeviceProperties;
//import com.opencsv.CSVWriter;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;

public class Start extends AppCompatActivity {

    private Button choix;
    private Switch myswitch;
    private ArrayList<String> fileList;

    protected void onCreate(Bundle savecInstacneState) {

        if (AppCompatDelegate.getDefaultNightMode() == AppCompatDelegate.MODE_NIGHT_YES) {
            setTheme(R.style.DarkTheme);
        } else setTheme(R.style.AppTheme);

            super.onCreate(savecInstacneState);
            setContentView(R.layout.activity_start);

            choix = findViewById(R.id.btnstart);
            fileList = new ArrayList<>();

            choix.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    //Intent intent = new Intent(Start.this, ListMyCar.class);
                    //startActivity(intent);
                }
            });


            myswitch = findViewById(R.id.myswitch);
            if (AppCompatDelegate.getDefaultNightMode() == AppCompatDelegate.MODE_NIGHT_YES) {
                myswitch.setChecked(true);
            }

            myswitch.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                @Override
                public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                    if (isChecked) {
                        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES);
                        restartApp();
                    } else {
                        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);
                        restartApp();
                    }
                }
            });

        }


        @Override
        public boolean onCreateOptionsMenu (Menu menu){
            getMenuInflater().inflate(R.menu.menu_add_car, menu);
            return true;
        }

        public void restartApp () {
            Intent i = new Intent(getApplicationContext(), Start.class);
            startActivity(i);
            finish();
        }
    }




