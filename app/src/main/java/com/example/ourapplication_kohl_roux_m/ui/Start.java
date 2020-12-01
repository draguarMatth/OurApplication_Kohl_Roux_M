package com.example.ourapplication_kohl_roux_m.ui;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

import com.example.ourapplication_kohl_roux_m.R;
import com.example.ourapplication_kohl_roux_m.ui.car.ListMyActiveCars;

import java.io.File;
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
                Intent intent = new Intent(Start.this, ListMyActiveCars.class);
                startActivity(intent);
            }
        });

        File downLoadFile = getExternalFilesDir("/liste") ;

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_add_car, menu);
        return true;
    }

}
