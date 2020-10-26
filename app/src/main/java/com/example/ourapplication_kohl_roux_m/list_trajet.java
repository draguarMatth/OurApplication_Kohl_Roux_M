package com.example.ourapplication_kohl_roux_m;

import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import androidx.appcompat.app.AppCompatActivity;

public class list_trajet extends AppCompatActivity {

    ListView listView;

    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_trajet);

        listView = findViewById(R.id.listview);

        String[] values = new String[]{
                "Trajet 24.10.20    12km", "Trajet 25.10.20    12km","Trajet 26.10.20    13km"
        };
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, android.R.id.text1, values);

        listView.setAdapter(adapter);
    }
}
