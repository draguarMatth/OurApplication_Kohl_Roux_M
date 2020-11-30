package com.example.ourapplication_kohl_roux_m.ui.management;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.Application;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.core.view.GravityCompat;
import androidx.lifecycle.ViewModel;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.ourapplication_kohl_roux_m.R;
import com.example.ourapplication_kohl_roux_m.adapter.RecyclerAdapter;
import com.example.ourapplication_kohl_roux_m.dbClass.Repository.TrajetRepository;
import com.example.ourapplication_kohl_roux_m.dbClass.asynch.trajet.UpdateTrajet;
import com.example.ourapplication_kohl_roux_m.dbClass.entities.TrajetEntity;
import com.example.ourapplication_kohl_roux_m.ui.BaseActivity;
import com.example.ourapplication_kohl_roux_m.ui.Settings.SettingsActivity;
import com.example.ourapplication_kohl_roux_m.ui.trajet.ListTrajet_BazActivity;
import com.example.ourapplication_kohl_roux_m.util.RecyclerViewItemClickListener;
import com.example.ourapplication_kohl_roux_m.viewModel.trajet.TrajetSingleViewModel;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.List;

public class NewTrajetConsumptionInput extends BaseActivity {

    private static final String TAG = "InputConsumptionsActivity";
    public Bundle bundleTemp;
    private Intent previousIntent;
    private Bundle bundle;

    private Button addElectButton;
    private Button addFuelButton;
    private FloatingActionButton save;
    private EditText addElectItem;
    private EditText addFuelItem;
    private TextView valueInList;

    private Application application;

    private TrajetSingleViewModel viewModel;
    private TrajetEntity upDTrajet;
    private String trajetDate;
    private long carId;
    private RecyclerAdapter<String> electAdapter;
    private List<String> electInputs;
    private RecyclerAdapter<String> fuelAdapter;
    private List<String> fuelInputs;
    private int electInputsHashcode;
    private int fuelInputsHashcode;
    private RecyclerView recyclerViewElect;
    private RecyclerView recyclerViewFuel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        application = getApplication();

        getLayoutInflater().inflate(R.layout.activity_comsumption_input, frameLayout);
        previousIntent = getIntent();
        bundle = previousIntent.getExtras();
        trajetDate = (String) bundle.get("TrajetDate");
        carId = (long) bundle.get("CarId");
        trajetDate = (String) bundle.get("TrajetDate");

        setTitle("Saisie des données de consommation");
        navigationView.setCheckedItem(position);

        addElectItem = findViewById(R.id.saisieElect);
        addFuelItem = findViewById(R.id.saisieCarb);
        addElectButton = findViewById(R.id.addElectButton);
        addFuelButton = findViewById(R.id.addFuelButton);
        valueInList = findViewById(R.id.tvRecyclerView);
        recyclerViewElect = findViewById(R.id.electInputsRecyclerView);
        recyclerViewFuel = findViewById(R.id.fuelInputsRecyclerView);
        save = findViewById(R.id.saveTrip);

        RecyclerView.LayoutManager layoutManagerElect = new LinearLayoutManager(null);
        recyclerViewElect.setLayoutManager(layoutManagerElect);
        RecyclerView.LayoutManager layoutManagerFuel = new LinearLayoutManager(null);
        recyclerViewFuel.setLayoutManager(layoutManagerFuel);

        DividerItemDecoration dividerItemDecorationElect = new DividerItemDecoration(recyclerViewElect.getContext(),
                LinearLayoutManager.VERTICAL);
        recyclerViewElect.addItemDecoration(dividerItemDecorationElect);
        DividerItemDecoration dividerItemDecorationFuel = new DividerItemDecoration(recyclerViewFuel.getContext(),
                LinearLayoutManager.VERTICAL);
        recyclerViewFuel.addItemDecoration(dividerItemDecorationFuel);

        electInputs = new ArrayList<String>();
        electInputsHashcode = electInputs.hashCode();

        electAdapter = new RecyclerAdapter<>(new RecyclerViewItemClickListener() {
            @SuppressLint("LongLogTag")
            @Override
            public void onItemClick(View v, int position) {
                Log.d(TAG, "clicked position:" + position);
                Log.d(TAG, "clicked on: " + electInputs.get(position));

                Toast toast = Toast.makeText(NewTrajetConsumptionInput.this, "Appui long pour modifier", Toast.LENGTH_LONG);
            }

            @SuppressLint("LongLogTag")
            @Override
            public void onItemLongClick(View v, int position) {
                Log.d(TAG, "longClicked position:" + position);
                Log.d(TAG, "longClicked on: " + electInputs.get(position));

                createModifyDialog(position, electInputsHashcode);
            }
        });

        fuelInputs = new ArrayList<String>();
        fuelInputsHashcode = fuelInputs.hashCode();

        fuelAdapter = new RecyclerAdapter<String>(new RecyclerViewItemClickListener() {
            @SuppressLint("LongLogTag")
            @Override
            public void onItemClick(View v, int position) {
                Log.d(TAG, "clicked position:" + position);
                Log.d(TAG, "clicked on: " + fuelInputs.get(position));

                Toast toast = Toast.makeText(NewTrajetConsumptionInput.this, "Appui long pour modifier", Toast.LENGTH_LONG);
            }

            @SuppressLint("LongLogTag")
            @Override
            public void onItemLongClick(View v, int position) {
                Log.d(TAG, "longClicked position:" + position);
                Log.d(TAG, "longClicked on: " + fuelInputs.get(position));

                createModifyDialog(position, fuelInputsHashcode);
            }
        });

        electAdapter.setData(electInputs);
        fuelAdapter.setData(fuelInputs);

        recyclerViewElect.setAdapter(electAdapter);
        recyclerViewFuel.setAdapter(fuelAdapter);

        addElectButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String newValue = addElectItem.getText().toString();

                electInputs.add(newValue);
                electAdapter.setData(electInputs);
                addElectItem.setText("");

                recyclerViewElect.setAdapter(electAdapter);
                recyclerViewElect.refreshDrawableState();

                bundleTemp = getIntent().getBundleExtra("tempSaisiesConsom");
            }
        });

        addFuelButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String newValue = addFuelItem.getText().toString();

                fuelInputs.add(newValue);
                fuelAdapter.setData(fuelInputs);
                addFuelItem.setText("");

                recyclerViewFuel.setAdapter(fuelAdapter);
                recyclerViewFuel.refreshDrawableState();

                bundleTemp = getIntent().getBundleExtra("tempSaisiesConsom");
            }
        });


            TrajetSingleViewModel.Factory factory = new TrajetSingleViewModel.Factory(
                    getApplication(), carId, trajetDate);
            TrajetSingleViewModel viewModel = ViewModelProviders.of(this, factory).get(TrajetSingleViewModel.class);
            viewModel = ViewModelProviders.of(this, factory).get(TrajetSingleViewModel.class);
            viewModel.getSingleTripviewMod().observe(this, trajetL -> {
                if (trajetL != null) {
                    upDTrajet = trajetL;
                    save.setOnClickListener(view -> {
                    saveChanges();
                            }
                    );
                }
            });

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
                Intent intent = new Intent(NewTrajetConsumptionInput.this, SettingsActivity.class);
                NewTrajetConsumptionInput.this.startActivity(intent);
                break;
        }
        return true;
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu){
        getMenuInflater().inflate(R.menu.menu_map_trajet, menu);
        return true;
    }

    @SuppressLint("StaticFieldLeak")
    private void createModifyDialog(final int position, final int hashcodeInputs) {

        List<String> aModif = getList(hashcodeInputs);

        final String value = getList(hashcodeInputs).get(position);
        LayoutInflater inflater = LayoutInflater.from(this);
        final View view = inflater.inflate(R.layout.row_delete_item, null);

        final AlertDialog alertDialog = new AlertDialog.Builder(this).create();
        alertDialog.setTitle(getString(R.string.title_activity_modify_Inputs));
        alertDialog.setCancelable(false);

        final TextView modifyMessage = view.findViewById(R.id.editNumberToModify);
        modifyMessage.setText(value);

        alertDialog.setButton(AlertDialog.BUTTON_POSITIVE, "Modify", (dialog, which) -> {
            Toast toast = Toast.makeText(this, "Valeur modifiée", Toast.LENGTH_LONG);

            String newValue = modifyMessage.getText().toString();

            aModif.set(position, newValue);
            getRecyclerView(hashcodeInputs).refreshDrawableState();

            toast.show();
        });

        alertDialog.setButton(AlertDialog.BUTTON_NEGATIVE, "Delete", (dialog, which) -> {
            Toast toast = Toast.makeText(this, "Valeur effacée", Toast.LENGTH_LONG);

            aModif.remove(position);
            getRecyclerView(hashcodeInputs).refreshDrawableState();

            toast.show();
        });

        alertDialog.setButton(AlertDialog.BUTTON_NEUTRAL, "Cancel", (dialog, which) -> alertDialog.dismiss());
        alertDialog.setView(view);

        alertDialog.show();
    }

    private List<String> getList(int hashcode) {

        if (hashcode == electInputsHashcode)
            return electInputs;
        return fuelInputs;
    }

    private RecyclerView getRecyclerView(int hashcode) {

        if (hashcode == electInputsHashcode) {
            electAdapter.setData(electInputs);
            recyclerViewElect.setAdapter(electAdapter);
            return recyclerViewElect;
        }
        fuelAdapter.setData(fuelInputs);
        recyclerViewElect.setAdapter(fuelAdapter);
        return recyclerViewFuel;
    }

    private void saveChanges() {

        upDTrajet.electricityTot = calcul(electInputs) ;
        upDTrajet.gasolinTot = calcul(fuelInputs);
        upDTrajet.kmTot = 1000;
        upDTrajet.totDeep = 5;
        upDTrajet.totRise = 5;

        new UpdateTrajet(getApplication(), new OnAsyncEventListener() {
            @SuppressLint("LongLogTag")
            @Override
            public void onSuccess() {
                setResponse(true);
                Log.d(TAG, "Enregistrement consommation : succès");
            }

            @SuppressLint("LongLogTag")
            @Override
            public void onFailure(Exception e) {
                Log.d(TAG, "Enregistrement consommation : échoué", e);
            }

        }).execute(upDTrajet);

    }

    private void setResponse(Boolean response) {
        if (response) {
            Intent intent = new Intent(NewTrajetConsumptionInput.this, ListTrajet_BazActivity.class);
            intent.putExtra("CarId", carId);
            startActivity(intent);
        } else {
            Toast toast = Toast.makeText(this, "Trajet non enregistré", Toast.LENGTH_LONG);

        }
    }

    private double calcul(List<String> saisies) {
        double result = 0;
        for (String item : saisies) {
            result += (Double.valueOf(item));
        }

        return result;
    }


}
