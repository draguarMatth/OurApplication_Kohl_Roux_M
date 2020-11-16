package com.example.ourapplication_kohl_roux_m.ui;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

import com.example.ourapplication_kohl_roux_m.BaseApp;
import com.example.ourapplication_kohl_roux_m.R;
import com.example.ourapplication_kohl_roux_m.dbClass.Repository.TrajetRepository;
import com.example.ourapplication_kohl_roux_m.ui.trajet.ListTrajet_BazActivity;
import com.google.android.material.bottomnavigation.BottomNavigationView;

public class main_app extends AppCompatActivity {

    private Button choix1;
    private Button choix2;
    BottomNavigationView bottomNavigationView;

    TrajetRepository trajetRepo;

    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_app);

        trajetRepo = ((BaseApp) getApplication()).getTrajetRepository();
//        progressBar = findViewById(R.id.progress);

//        ImageView imageView = (ImageView) findViewById(R.id.imageView);
//        imageView.setImageResource(R.drawable.i8);

        choix1 = (Button) findViewById(R.id.btn);
        choix2 = (Button) findViewById(R.id.addFuelButton);

        choix1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
 //               Intent intent = new Intent(main_app.this, list_trajet.class);
                Intent intent = new Intent(main_app.this, ListTrajet_BazActivity.class);
                startActivity(intent);
            }
        });

        choix2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
   //             Intent intent = new Intent(main_app.this, list_trajet.class);
     //           startActivity(intent);
            }
        });
    }

    @Override
    protected void onResume() {
        super.onResume();
    }

    @Override
    protected void onPostResume() {
        super.onPostResume();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu){
        getMenuInflater().inflate(R.menu.menu_add_car, menu);
        return true;
    }


}
