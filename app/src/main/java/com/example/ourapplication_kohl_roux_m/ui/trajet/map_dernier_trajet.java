package com.example.ourapplication_kohl_roux_m.ui.trajet;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.GravityCompat;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.ourapplication_kohl_roux_m.R;
import com.example.ourapplication_kohl_roux_m.ui.BaseActivity;
import com.example.ourapplication_kohl_roux_m.ui.InitApp;
import com.example.ourapplication_kohl_roux_m.ui.Settings.SettingsActivity;

public class map_dernier_trajet extends BaseActivity {


    protected void onCreate(Bundle savecInstacneState){
        super.onCreate(savecInstacneState);
        getLayoutInflater().inflate(R.layout.activity_map_dernier_trajet, frameLayout);

        setTitle( "Map Trajets");
        navigationView.setCheckedItem(position);
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        if (item.getItemId() == BaseActivity.position) {
            drawerLayout.closeDrawer(GravityCompat.START);
            return false;
        }
        finish();
        return super.onNavigationItemSelected(item);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        switch (item.getItemId()) {
            case R.id.action_settings:
                Intent intent = new Intent(map_dernier_trajet.this, SettingsActivity.class);
                map_dernier_trajet.this.startActivity(intent);
                break;
        }
        return true;
    }

}
