package com.example.ourapplication_kohl_roux_m.ui;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.ourapplication_kohl_roux_m.R;

public class list_trajet extends AppCompatActivity {

    ListView listView;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_trajet);

        listView = findViewById(R.id.listview);

        String[] values = new String[]{
                "Trajet 24.10.20    12km", "Trajet 25.10.20    12km", "Trajet 26.10.20    13km"
        };
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, android.R.id.text1, values);

        listView.setAdapter(adapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                if (position == 0) {
                    Intent intent = new Intent(view.getContext(), stats_dernier_trajet.class);
                    startActivity(intent);
                }
            }
        });

      //  BottomNavigationView bottomNavigationView = findViewById(R.id.nav_vehicule);
      //  bottomNavigationView.setOnNavigationItemSelectedListener(navigationItemReselectedListener);
    }

    /*
    private BottomNavigationView.OnNavigationItemSelectedListener navigationItemReselectedListener =
            new BottomNavigationView.OnNavigationItemSelectedListener(){
                @Override
                public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                   Fragment selectedFragment = null;

                   switch (item.getItemId()){
                       case R.id.nav_vehicule:
                           selectedFragment = new List_Trajet_Fragment();
                           break;
                   }
                   getSupportFragmentManager().beginTransaction().replace(R.id.nav_host_fragment, selectedFragment).commit();
                   return true;
                }
            };


     */


    @Override
    public boolean onCreateOptionsMenu(Menu menu){
        getMenuInflater().inflate(R.menu.menu_list_trajet, menu);
        return true;
    }
}
