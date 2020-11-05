package com.example.ourapplication_kohl_roux_m.UI;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

import com.example.ourapplication_kohl_roux_m.R;

public class main_app extends AppCompatActivity {

    private Button choix1;
    private Button choix2;

    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_app);


        choix1 = findViewById(R.id.btn);
        choix2 = findViewById(R.id.button2);

        choix1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(main_app.this, list_trajet.class);
                startActivity(intent);
            }
        });

        choix2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(main_app.this, list_trajet.class);
                startActivity(intent);
            }
        });
    }
}
