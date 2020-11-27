package com.example.ourapplication_kohl_roux_m.ui.car;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.ourapplication_kohl_roux_m.R;
import com.example.ourapplication_kohl_roux_m.adapter.ListAdapter;
import com.example.ourapplication_kohl_roux_m.dbClass.entities.CarEntity;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.navigation.NavigationView;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class ChooseNewCar extends AppCompatActivity {
    private static final String TAG = "TransactionActivity";
    protected static int position;
    protected NavigationView navigationView;
    private View v;

    private Spinner spinnerTradeMark;
    private Spinner spinnerModel;
    private Spinner spinnerConsoFuel;
    private Spinner spinnerBattery;
    private Spinner spinnerWheelSize;

    private List<String> listTradeMark;
    private List<String> listModel;
    private List<String> lisConsoFuel;
    private List<String> listBattery;
    private List<String> listWheelSize;

    private EditText NickNameEditText;

    private ListAdapter<String> adapterTradeMark, adapterModel, adapterConsoFuel, adapterbattery, adapterWheelSize;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_choose_new_car);

        setTitle("Ajout d'une voiture");

        FloatingActionButton fab = v.findViewById(R.id.floatingActionButton2);
        fab.setVisibility(View.VISIBLE);
        fab.setOnClickListener(view -> {
            Intent intent = new Intent(ChooseNewCar.this, ListCarActivity.class);
            intent.setFlags(
                    Intent.FLAG_ACTIVITY_NO_ANIMATION |
                            Intent.FLAG_ACTIVITY_NO_HISTORY
            );
            startActivity(intent);
        });

        listTradeMark = new ArrayList<>();
        listModel = new ArrayList<>();
        lisConsoFuel = new ArrayList<>();
        listBattery = new ArrayList<>();
        listWheelSize = new ArrayList<>();

        RecyclerView recyclerView = findViewById(R.id.carsRecyclerView);

        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);

        DividerItemDecoration dividerItemDecoration = new DividerItemDecoration(recyclerView.getContext(),
                LinearLayoutManager.VERTICAL);
        recyclerView.addItemDecoration(dividerItemDecoration);

    }

    private void initializeForm() {

/*        spinnerTradeMark;
          spinnerTradeMark = (Spinner)findViewById(R.id.spinnerTradeMark);
          extractListFromCsv(1, spinnerTradeMark);

        spinnerModel;
        spinnerConsoFuel;
        spinnerBattery;
        spinnerWheelSize;
  */

        Button saveBtn = findViewById(R.id.editButtonSAve);
/*        saveBtn.setOnClickListener(view -> addNewCar(

        ));

 */

        FloatingActionButton save = findViewById(R.id.saveTrip);
/*        save.setOnClickListener(view ->  addNewCar(


        ));

 */
    }


    private void modifyCar(final int position) {
/*
        final CarEntity car = cars.get(position);
        LayoutInflater inflater = LayoutInflater.from(this);

        final View view = inflater.inflate(R.layout.row_delete_item, null);

        final AlertDialog alertDialog = new AlertDialog.Builder(this).create();
        alertDialog.setTitle("La modification des caractéristique de la voiture modifiera les statistiques des anciens trajets\nNe voulez vous pas plutôt créer une nouvelle voiture et la tuner ?");
        alertDialog.setCancelable(true);

        final TextView modifyMessage = view.findViewById(R.id.tv_delete_item);
        modifyMessage.setText("Tuner sa voiture " + car.getNickName() + ", model "+ car.getModel());

        alertDialog.setButton(AlertDialog.BUTTON_POSITIVE, "modify", (dialog, which) -> {
            Toast toast = Toast.makeText(this, "Voiture Modifié", Toast.LENGTH_LONG);

            viewModel.modifyOneCar(car, new OnAsyncEventListener() {
                        @Override
                        public void onSuccess() {
                            Log.d(TAG, "modifyCar: success");
                        }

                        @Override
                        public void onFailure(Exception e) {
                            Log.d(TAG, "modifyCar: failure", e);
                        }
                    }
            );

            toast.show();
        });
        alertDialog.setButton(androidx.appcompat.app.AlertDialog.BUTTON_NEUTRAL, "Cancel", (dialog, which) -> alertDialog.dismiss());
        alertDialog.setView(view);
        alertDialog.show();

 */
    }

    private void addNewCar(final CarEntity newCar) {

 /*       final CarEntity car = newCar;

        LayoutInflater inflater = LayoutInflater.from(this);


           CarMyListViewModel viewModelDel;
            CarMyListViewModel.Factory factory = new CarMyListViewModel.Factory(
                    getApplication());
            viewModelDel = ViewModelProviders.of(this, factory).get(CarMyListViewModel.class);
            viewModelDel.getMyCarsViewMod().observe(this, carsSL -> {
                if (carsSL != null) {
                    cars = carsSL;
                    adapter.setData(cars);
                }
            });

        Toast toast = Toast.makeText(this, "Nouvelle voiture crée.", Toast.LENGTH_LONG);
        viewModel.createTunedCar(car, new OnAsyncEventListener() {
                @Override
                public void onSuccess() {
                    Log.d(TAG, "Add new car : success");
                    toast.show();
                }

                @Override
                public void onFailure(Exception e) {
                    Log.d(TAG, "Add new car: failure", e);
                }
            });
            toast.show();
        });
 */
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
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_add_car, menu);
        return true;
    }


    private Spinner extractListFromCsv(int position, Spinner spinner) {

        try {
            Scanner scanner = new Scanner(getResources().openRawResource(R.rawbis.listeVoitPlugIn_pointVirgule));

            while (scanner.hasNext()) {
                String data = (scanner.next());
                String[] values = data.split(";");
                String item = values[position];

                // si position 0  // si position 1 et après >> il faut trier en fonction de la position précédente
                /*
                while (true) {
                récurssive!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!
                }
                 */

                ArrayAdapter<String> adapter = new ArrayAdapter<String>
                        (this, android.R.layout.simple_spinner_item, values);
                spinner.setAdapter(adapter);
            }
            scanner.close();

        } catch (Exception e) {
            Log.e(TAG, "Exception: " + Log.getStackTraceString(e));
        }
        return spinner;
    }

}


