package com.example.ourapplication_kohl_roux_m.ui.management.consumptionInputs;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.ContentView;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.core.view.GravityCompat;
import androidx.lifecycle.ViewModel;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.ourapplication_kohl_roux_m.R;
import com.example.ourapplication_kohl_roux_m.adapter.RecyclerAdapter;
import com.example.ourapplication_kohl_roux_m.ui.BaseActivity;
import com.example.ourapplication_kohl_roux_m.util.RecyclerViewItemClickListener;

import java.util.ArrayList;
import java.util.List;

public class NewTrajetConsumptionInput extends BaseActivity {

    private static final String TAG = "InputConsumptionsActivity";
/*
    @BindView(R.id.saisieElect)
    EditText inputElectEditText;
    @BindView(R.id.editTextElect) EditText listElectInputEditText;
    @BindView(R.id.saisieCarb) EditText inputFuelEditText;
    @BindView(R.id.editTextCarb) EditText listFuelInputEditText;
*/
    Button addElectButton;
    Button addFuelButton;

    EditText addElectItem;
    EditText addFuelItem;

    TextView electInputToList;
    TextView fuelInputToList;

    private RecyclerAdapter<String> electAdapter;
    private List<String> electInputs;

//    private List<ItemValue> electInputs;

    private RecyclerAdapter<String> fuelAdapter;
    private List<String> fuelInputs;
    private int electInputsHashcode;
    private int fuelInputsHashcode;
    private ViewModel viewmodel;
    private RecyclerView recyclerViewElect;
    private RecyclerView recyclerViewFuel;

 //   private MyAdapterElectricityEntries myAdapterElect;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getLayoutInflater().inflate(R.layout.activity_comsumption_input, frameLayout);

        setTitle("Saisie des données de consommation");
        navigationView.setCheckedItem(position);

        addElectItem = (EditText) findViewById(R.id.saisieElect);
        addFuelItem = (EditText) findViewById(R.id.saisieCarb);
        addElectButton = (Button) findViewById(R.id.addElectButton);
        addFuelButton = (Button) findViewById(R.id.addFuelButton);

        recyclerViewElect = findViewById(R.id.electInputsRecyclerView);
        recyclerViewFuel = findViewById(R.id.fuelInputsRecyclerView);


        // use a linear layout manager
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

//        SharedPreferences settings = getSharedPreferences(BaseActivity.PREFS_NAME, 0);
//        String user = settings.getString(BaseActivity.PREFS_USER, null);

        electInputs = new ArrayList<String>();
//        electInputs = new ArrayList<ItemValue>();

        electInputsHashcode = electInputs.hashCode();

//        myAdapterElect = new MyAdapterElectricityEntries(electInputs);

        electAdapter = new RecyclerAdapter<>(new RecyclerViewItemClickListener() {
            @Override
            public void onItemClick(View v, int position) {
 //               Log.d(TAG, "clicked position:" + position);
 //               Log.d(TAG, "clicked on: " + intputsElect.get(position).getName());

                Toast toast = Toast.makeText(NewTrajetConsumptionInput.this, "Appui long pour modifier", Toast.LENGTH_LONG);
            }

            @Override
            public void onItemLongClick(View v, int position) {
 //               Log.d(TAG, "longClicked position:" + position  );
 //               Log.d(TAG, "longClicked on: " + electInputs.get(position));

                // Faire fonction avec ouverture d'une fenêtre de dialogue éditant le nombre permettant "Modify"(modifie la donnée), "Delete"(efface la donnée) ou "Abord"(pour ne rien modifier)
                createModifyDialog(position, electInputsHashcode);
            }
        });

        fuelInputs = new ArrayList<String>();
        fuelInputsHashcode = fuelInputs.hashCode();

        fuelAdapter = new RecyclerAdapter<String>(new RecyclerViewItemClickListener() {
            @Override
            public void onItemClick(View v, int position) {
//                Log.d(TAG, "clicked position:" + position);
//                Log.d(TAG, "clicked on: " + intputsFuel.get(position).doubleValue());

                Toast toast = Toast.makeText(NewTrajetConsumptionInput.this, "Appui long pour modifier", Toast.LENGTH_LONG);
            }

            @Override
            public void onItemLongClick(View v, int position) {
//                Log.d(TAG, "longClicked position:" + position);
//                Log.d(TAG, "longClicked on: " + fuelInputs.get(position).getName());

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
//               double newValue = Double.valueOf(newValueString);

                electInputs.add(newValue);

                electAdapter.setData(electInputs);
 //               electAdapter.onAttachedToRecyclerView(recyclerViewElect);
 //               recyclerViewElect.getDisplay();

                addElectItem.setText("");
//                Intent intent = new Intent(NewTrajetConsumptionInput.this, NewTrajetConsumptionInput.class);
//                startActivity(intent);

//                recyclerViewElect.refreshDrawableState();
                recyclerViewElect.setAdapter(electAdapter);
            }
        });
        addFuelButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String newValue = addFuelItem.getText().toString();
//                double newValue = Double.valueOf(newValueString);

                fuelInputs.add(newValue);
                fuelAdapter.setData(fuelInputs);
                addFuelItem.setText("");

//                recyclerViewFuel.refreshDrawableState();
 //               recyclerViewFuel.setAdapter(fuelAdapter);
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

    @SuppressLint("StaticFieldLeak")
    private void createModifyDialog(final int position, final int hashcodeInputs) {

        List <String> aModif = getList(hashcodeInputs);
        RecyclerView recyclerAmodif =  getRecyclerView(hashcodeInputs);

        final String value = (String) getList(hashcodeInputs).get(position);
        LayoutInflater inflater = LayoutInflater.from(this);
        final View view = inflater.inflate(R.layout.row_delete_item, null);

        final AlertDialog alertDialog = new AlertDialog.Builder(this).create();
        alertDialog.setTitle(getString(R.string.title_activity_modify_Inputs));
        alertDialog.setCancelable(false);

        final TextView modifyMessage = view.findViewById(R.id.editNumberToModify);
//        modifyMessage.setText(String.valueOf(value));
        modifyMessage.setText(value);

        alertDialog.setButton(AlertDialog.BUTTON_POSITIVE, "Modify", (dialog, which) -> {
            Toast toast = Toast.makeText(this, "Valeur modifiée", Toast.LENGTH_LONG);

            String newValue = modifyMessage.getText().toString();

            aModif.set(position, newValue);
            recyclerAmodif.refreshDrawableState();


            toast.show();
        });

        alertDialog.setButton(AlertDialog.BUTTON_NEGATIVE, "Delete", (dialog, which) -> {
            Toast toast = Toast.makeText(this, "Valeur effacée", Toast.LENGTH_LONG);


            aModif.remove(position);

            recyclerAmodif.refreshDrawableState();

            toast.show();
        });

        alertDialog.setButton(AlertDialog.BUTTON_NEUTRAL, "Cancel", (dialog, which) -> alertDialog.dismiss());
        alertDialog.setView(view);
        alertDialog.show();
    }

    private List <String> getList(int hashcode){

        if(hashcode == electInputsHashcode)
            return electInputs;
        return fuelInputs;
    }
    private RecyclerView getRecyclerView(int hashcode){

        if(hashcode == electInputsHashcode){
            electAdapter.setData(electInputs);
            recyclerViewElect.setAdapter(electAdapter);
            return recyclerViewElect;
        }
        fuelAdapter.setData(fuelInputs);
        recyclerViewElect.setAdapter(fuelAdapter);
        return recyclerViewFuel;
    }

    private void refreshView(List<String> electInputs, List<Number> fuelInputs){}
}
