package com.example.ourapplication_kohl_roux_m.UI;

import android.app.AppComponentFactory;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

import com.example.ourapplication_kohl_roux_m.R;

public class stats_dernier_trajet extends AppCompatActivity {

    private Button map;

    protected void onCreate(Bundle savecInstacneState){
        super.onCreate(savecInstacneState);
        setContentView(R.layout.activity_stats_dernier_trajet);

        map = (Button) findViewById(R.id.map);

        map.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(stats_dernier_trajet.this, map_dernier_trajet.class);
                startActivity(intent);
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu){
        getMenuInflater().inflate(R.menu.menu_dernier_trajet, menu);
        return true;
    }
}
