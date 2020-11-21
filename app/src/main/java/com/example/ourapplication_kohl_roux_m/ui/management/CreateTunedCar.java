package com.example.ourapplication_kohl_roux_m.ui.management;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.ourapplication_kohl_roux_m.R;
import com.example.ourapplication_kohl_roux_m.dbClass.asynch.car.CreateCar;
import com.example.ourapplication_kohl_roux_m.dbClass.asynch.trajet.CreateTrajet;
import com.example.ourapplication_kohl_roux_m.dbClass.entities.CarEntity;
import com.example.ourapplication_kohl_roux_m.dbClass.entities.TrajetEntity;
import com.example.ourapplication_kohl_roux_m.ui.car.ListCarActivity;
import com.example.ourapplication_kohl_roux_m.ui.management.consumptionInputs.NewTrajetConsumptionInput;
import com.example.ourapplication_kohl_roux_m.util.OnAsyncEventListener;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class CreateTunedCar extends AppCompatActivity {

    private static final String TAG = "RegisterNewRoadTrip";

    private Toast toast;

    private EditText TradeMark;
    private EditText Model;
    private EditText Nickname;
    private EditText FuelConsumption;
    private EditText ElectricityConsumption;
    private EditText WheelSize;


    private String mark, model, nickname;


    private double fuelConsumption;
    private double batteryCapacity;
    private boolean activity;
    private double wheel;

    private CarEntity newCar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_tunedcar);

        initializeForm();
        toast = Toast.makeText(this, "Saisissez les données de votre nouvelle voiture", Toast.LENGTH_LONG);

        // SPINNER SPINNER SPINNER SPINNER SPINNER SPINNER SPINNER

    }

    private void initializeForm() {


        // SPINNER SPINNER SPINNER SPINNER SPINNER SPINNER SPINNER

        name = findViewById(R.id.name);
        date= findViewById(R.id.date);

        date.setText(formater.format( date1 ));

        Button saveBtn = findViewById(R.id.editButtonSAve);
        saveBtn.setOnClickListener(view -> saveChanges(
                nameNewTrip = name.getText().toString(),
                dateNewTrip = date.getText().toString(),
                carId
        ));

        FloatingActionButton save = findViewById(R.id.saveTrip);
        save.setOnClickListener(view ->  saveChanges(
                nameNewTrip = name.getText().toString(),
                dateNewTrip = date.getText().toString(),
                carId
        ));
    }


    private void saveChanges(String name, String date, long carID ) {

        newCar = new CarEntity (nickname, mark, model, fuelConsumption, batteryCapacity, wheel,true);

        new CreateCar(getApplication(), new OnAsyncEventListener() {
            @Override
            public void onSuccess() {
                setResponse(true);
                Log.d(TAG, "createIntitRoadTrip : success");
            }

            @Override
            public void onFailure(Exception e) {
                Log.d(TAG, "createIntitRoadTrip: failure", e);
                //        toast = Toast.makeText(this, "Erreur HOREUR !", Toast.LENGTH_LONG);
            }
        }).execute(newTrajet);


    }
    private void setResponse(Boolean response) {
        if (response) {
            Intent intent = new Intent(CreateTunedCar.this, ListCarActivity.class);
            startActivity(intent);
        } else {
            toast = Toast.makeText(this, "Erreur dans la saisie", Toast.LENGTH_LONG);

        }
    }


}
