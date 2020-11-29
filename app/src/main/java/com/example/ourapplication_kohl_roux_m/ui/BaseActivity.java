package com.example.ourapplication_kohl_roux_m.ui;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import androidx.annotation.NonNull;

import com.example.ourapplication_kohl_roux_m.ui.management.CreateTrip;
import com.example.ourapplication_kohl_roux_m.ui.management.NewTrajetConsumptionInput;
import com.example.ourapplication_kohl_roux_m.ui.management.SettingsActivity;
import com.example.ourapplication_kohl_roux_m.ui.trajet.ListTrajet_BazActivity;
import com.example.ourapplication_kohl_roux_m.ui.trajet.map_dernier_trajet;
import com.google.android.material.navigation.NavigationView;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.FrameLayout;

import com.example.ourapplication_kohl_roux_m.R;


public class BaseActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

    public static final String PREFS_NAME = "SharedPrefs";
    public static final String PREFS_USER = "LoggedIn";
    /**
     *  Frame layout: Which is going to be used as parent layout for child activity layout.
     *  This layout is protected so that child activity can access this
     */
    protected FrameLayout frameLayout;

    protected DrawerLayout drawerLayout;

    protected NavigationView navigationView;

    /**
     * Static variable for selected item position. Which can be used in child activity to know which item is selected from the list.
     */
    protected static int position;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_base);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        frameLayout = findViewById(R.id.flContent);

        drawerLayout = findViewById(R.id.base_drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawerLayout, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawerLayout.addDrawerListener(toggle);
        toggle.syncState();

        navigationView = findViewById(R.id.base_nav_view);
        navigationView.setNavigationItemSelectedListener(this);

    }

    @Override
    protected void onResume() {
        super.onResume();
    }

    @Override
    public void onBackPressed() {
        if (drawerLayout.isDrawerOpen(GravityCompat.START)) {
            drawerLayout.closeDrawer(GravityCompat.START);
            return;
        }
        BaseActivity.position = 0;
        super.onBackPressed();
    }



    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.settings, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.

        switch (item.getItemId()) {
            case R.id.action_settings:
                Intent intent = new Intent(BaseActivity.this, SettingsActivity.class);
                BaseActivity.this.startActivity(intent);
                break;
        }
        return true;


    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == BaseActivity.position) {
            drawerLayout.closeDrawer(GravityCompat.START);
            return false;
        }
        BaseActivity.position = id;
        Intent intent = null;

        navigationView.setCheckedItem(id);

        if (id == R.id.nav_vehicule) {
            intent = new Intent(this, InitApp.class);
        } else if (id == R.id.nav_trajet) {
            intent = new Intent(this, ListTrajet_BazActivity.class);
        } else if (id == R.id.nav_map) {
            intent = new Intent(this, map_dernier_trajet.class);
        }


        if (intent != null) {
            intent.setFlags(
                    Intent.FLAG_ACTIVITY_NO_ANIMATION
            );
            startActivity(intent);
        }
        drawerLayout.closeDrawer(GravityCompat.START);
        return true;
    }

    public void logout() {
        SharedPreferences.Editor editor = getSharedPreferences(BaseActivity.PREFS_NAME, 0).edit();
        editor.remove(BaseActivity.PREFS_USER);
        editor.apply();
    }
}
