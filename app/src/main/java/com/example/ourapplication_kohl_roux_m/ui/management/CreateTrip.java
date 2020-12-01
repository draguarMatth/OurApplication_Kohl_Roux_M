package com.example.ourapplication_kohl_roux_m.ui.management;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.GravityCompat;
import androidx.annotation.NonNull;
import androidx.core.view.GravityCompat;
import androidx.lifecycle.ViewModelProviders;

import com.example.ourapplication_kohl_roux_m.R;
import com.example.ourapplication_kohl_roux_m.dbClass.asynch.trajet.CreateTrajet;
import com.example.ourapplication_kohl_roux_m.dbClass.entities.TrajetEntity;
import com.example.ourapplication_kohl_roux_m.ui.BaseActivity;
import com.example.ourapplication_kohl_roux_m.R;
import com.example.ourapplication_kohl_roux_m.ui.BaseActivity;
import com.example.ourapplication_kohl_roux_m.ui.Settings.SettingsActivity;
import com.example.ourapplication_kohl_roux_m.ui.trajet.ListTrajet_BazActivity;
import com.example.ourapplication_kohl_roux_m.util.OnAsyncEventListener;
import com.example.ourapplication_kohl_roux_m.viewModel.trajet.TrajetListViewModel;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.List;


public class CreateTrip extends BaseActivity {

    private static final String TAG = "RegisterNewRoadTrip";

    private EditText name;
    private EditText date;
    private String nameNewTrip, dateNewTrip;

    private Intent previousIntent;
    public Bundle bundle;
    private long carId;
    private String trajetDate;
    private TrajetEntity newTrajet;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_roadtrip_init);

        previousIntent = getIntent();
        bundle = previousIntent.getExtras();
        carId = (long) bundle.get("CarId");

        initializeForm();
        Toast toast = Toast.makeText(this, "Saisissez les niveaux de batterie et de carburant actuel", Toast.LENGTH_LONG);

         setTitle( "Create Trajets");
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
                Intent intent = new Intent(CreateTrip.this, SettingsActivity.class);
                CreateTrip.this.startActivity(intent);
                break;
        }
        return true;
    }

    private void initializeForm() {

        String format = "dd/MM/yy H:mm:ss";
        java.text.SimpleDateFormat formater = new java.text.SimpleDateFormat(format);
        java.util.Date date1 = new java.util.Date();

        name = findViewById(R.id.name);
        date = findViewById(R.id.date);

        date.setText(formater.format(date1));

        Button saveBtn = findViewById(R.id.editButtonSAve);
        saveBtn.setOnClickListener(view -> saveChanges(
                nameNewTrip = name.getText().toString(),
                dateNewTrip = date.getText().toString(),
                carId
        ));

        FloatingActionButton save = findViewById(R.id.saveTrip);
        save.setOnClickListener(view -> saveChanges(
                nameNewTrip = name.getText().toString(),
                dateNewTrip = date.getText().toString(),
                carId
        ));
    }

    private void saveChanges(String name, String date, long carID) {

        newTrajet = new TrajetEntity(carId, nameNewTrip, dateNewTrip, 0,
                0, 0, 0, 0);

        new CreateTrajet(getApplication(), new OnAsyncEventListener() {
            @Override
            public void onSuccess() {
                trajetDate = newTrajet.getDate();
                Intent intent = new Intent(CreateTrip.this, NewTrajetConsumptionInput.class);
                intent.putExtra("TrajetDate", trajetDate);
                intent.putExtra("CarId", carId);
                startActivity(intent);

                Log.d(TAG, "createIntitRoadTrip : success");
            }

            @Override
            public void onFailure(Exception e) {
                Log.d(TAG, "createIntitRoadTrip: failure", e);
            }

        }).execute(newTrajet);
    }

    private void setResponse(Boolean response) {
        if (response) {
            trajetDate = newTrajet.getDate();
            Intent intent = new Intent(CreateTrip.this, NewTrajetConsumptionInput.class);
            intent.putExtra("TrajetDate", trajetDate);
            intent.putExtra("CarId", carId);
            startActivity(intent);
        } else {
            Toast toast = Toast.makeText(this, "Erreur dans la saisie", Toast.LENGTH_LONG);
            toast.show();
        }
    }

}
