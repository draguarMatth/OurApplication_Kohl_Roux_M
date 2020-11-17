package com.example.ourapplication_kohl_roux_m.ui.management;

import android.app.Application;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.example.ourapplication_kohl_roux_m.dbClass.asynch.trajet.CreateTrajet;
import com.example.ourapplication_kohl_roux_m.dbClass.entities.TrajetEntity;
import com.example.ourapplication_kohl_roux_m.R;
import com.example.ourapplication_kohl_roux_m.ui.BaseActivity;
import com.example.ourapplication_kohl_roux_m.ui.MainActivity;
import com.example.ourapplication_kohl_roux_m.ui.management.consumptionInputs.NewTrajetConsumptionInput;
import com.example.ourapplication_kohl_roux_m.util.OnAsyncEventListener;

import java.util.Date;


public class CreateTrip extends AppCompatActivity {

     private static final String TAG = "RegisterNewRoadTrip";

     private Toast toast;

     private EditText name;
     private EditText date;
     private EditText voitureId;
     String nameNewTrip, dateNewTrip;
     long carId;

     @Override
     protected void onCreate(Bundle savedInstanceState) {
         super.onCreate(savedInstanceState);
         setContentView(R.layout.activity_new_roadtrip_init);
         initializeForm();
         toast = Toast.makeText(this, "Saisissez les niveaux de batterie et de carburant actuel", Toast.LENGTH_LONG);
     }

     private void initializeForm() {

         String format = "dd/MM/yy H:mm:ss";
         java.text.SimpleDateFormat formater = new java.text.SimpleDateFormat( format );
         java.util.Date date1 = new java.util.Date();
//         System.out.println( formater.format( date1 ) );

         name = findViewById(R.id.name);
         date= findViewById(R.id.date);
         date.setText(formater.format( date1 ));

//         voitureId = findViewById(R.id.voitureId);


         Button saveBtn = findViewById(R.id.editButtonSAve);
         saveBtn.setOnClickListener(view -> saveChanges(
                 nameNewTrip = name.getText().toString(),
                 dateNewTrip = date.getText().toString(),
                 carId = 20 /* long.valueOf(voitureId) */
                 ));
     }


     private void saveChanges(String name, String date, long carId ) {

         TrajetEntity newTrajet = new TrajetEntity (carId, nameNewTrip, dateNewTrip, 0,
         0,0, 0, 0);


         System.out.println("--------------------------------------newTrajet ---------------------" + newTrajet.toString());
         new CreateTrajet(getApplication(), new OnAsyncEventListener() {
             @Override
             public void onSuccess() {
                 Intent intent = new Intent(CreateTrip.this, NewTrajetConsumptionInput.class);
                 startActivity(intent);
                 Log.d(TAG, "createIntitRoadTrip : success");
             }

             @Override
             public void onFailure(Exception e) {
                 Log.d(TAG, "createIntitRoadTrip: failure", e);
         //        toast = Toast.makeText(this, "Erreur HOREUR !", Toast.LENGTH_LONG);
                 System.out.println("Erreur HOREUR ! " + "-------------------Erreur HOREUR !" + " ------------------Erreur HOREUR !");
             }
         }).execute(newTrajet);
     }
    private void setResponse(Boolean response) {
        if (response) {
            Intent intent = new Intent(CreateTrip.this, NewTrajetConsumptionInput.class);
            startActivity(intent);
        } else {
            toast = Toast.makeText(this, "Erreur dans la saisie", Toast.LENGTH_LONG);

        }
    }
 }
