package com.example.ourapplication_kohl_roux_m.ui;

import android.content.Intent;
import android.os.Bundle;
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

public class Start extends AppCompatActivity {

    private Button choix;
    private Switch myswitch;

    protected void onCreate(Bundle savecInstacneState){

        if(AppCompatDelegate.getDefaultNightMode()==AppCompatDelegate.MODE_NIGHT_YES){
            setTheme(R.style.DarkTheme);
        }
        else setTheme(R.style.AppTheme);

        super.onCreate(savecInstacneState);
        setContentView(R.layout.activity_start);

        choix = findViewById(R.id.btnstart);

        choix.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Start.this, InitApp.class);
                startActivity(intent);
            }
        });


        myswitch = findViewById(R.id.myswitch);
        if(AppCompatDelegate.getDefaultNightMode()==AppCompatDelegate.MODE_NIGHT_YES){
            myswitch.setChecked(true);
        }

        myswitch.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(isChecked){
                    AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES);
                    restartApp();
                }
                else {
                    AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);
                    restartApp();
                }
            }
        });


    }

    public void restartApp(){
        Intent i = new Intent(getApplicationContext(), Start.class);
        startActivity(i);
        finish();
    }


}
