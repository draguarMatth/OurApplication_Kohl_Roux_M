package com.example.ourapplication_kohl_roux_m.ui;

import android.app.admin.DevicePolicyManager;
import android.content.Intent;
import android.os.Bundle;
import android.os.Environment;
import android.os.FileUtils;
import android.view.Menu;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

import com.example.ourapplication_kohl_roux_m.R;
import com.example.ourapplication_kohl_roux_m.ui.car.ListMyCar;
import com.google.android.gms.common.util.DeviceProperties;
import com.opencsv.CSVWriter;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;

public class Start extends AppCompatActivity {

    private Button choix;
    private ArrayList <String> fileList;

    protected void onCreate(Bundle savecInstacneState) {
        super.onCreate(savecInstacneState);
        setContentView(R.layout.activity_start);

        choix = findViewById(R.id.btnstart);
        fileList = new ArrayList<>();

        choix.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Start.this, ListMyCar.class);
                startActivity(intent);
            }
        });

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_add_car, menu);
        return true;
    }

}
