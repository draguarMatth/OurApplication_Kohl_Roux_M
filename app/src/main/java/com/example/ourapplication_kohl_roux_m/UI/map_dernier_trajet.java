package com.example.ourapplication_kohl_roux_m.UI;

import android.os.Bundle;
import android.view.Menu;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

import com.example.ourapplication_kohl_roux_m.R;

public class map_dernier_trajet extends AppCompatActivity {


    protected void onCreate(Bundle savecInstacneState){
        super.onCreate(savecInstacneState);
        setContentView(R.layout.activity_map_dernier_trajet);

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu){
        getMenuInflater().inflate(R.menu.menu_map_trajet, menu);
        return true;
    }
}
