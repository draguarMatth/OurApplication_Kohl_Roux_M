package com.example.ourapplication_kohl_roux_m.ui.Settings;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Switch;

import androidx.appcompat.app.AppCompatActivity;

import com.example.ourapplication_kohl_roux_m.R;
import com.example.ourapplication_kohl_roux_m.ui.InitApp;


public class SettingsActivity extends AppCompatActivity {

    private Switch myswitch;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        // TODO Auto-generated method stub
        super.onCreate(savedInstanceState);
        getFragmentManager().beginTransaction()
                .replace(android.R.id.content,new SettingsFragment())
                .commit();
    }

    public void restartApp(){
        Intent i = new Intent(getApplicationContext(),SettingsActivity.class);
        startActivity(i);
        finish();
    }

    //insert action - toolbar
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.save_settings, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        switch (item.getItemId()) {

            case R.id.action_back:
                Intent i = new Intent(SettingsActivity.this, InitApp.class);
                SettingsActivity.this.startActivity(i);
                this.finish();
                break;
        }
        return true;
    }

}
