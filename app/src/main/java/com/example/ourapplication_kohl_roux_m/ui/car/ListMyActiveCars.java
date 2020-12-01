package com.example.ourapplication_kohl_roux_m.ui.car;

import android.app.AlertDialog;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.core.view.GravityCompat;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.ourapplication_kohl_roux_m.R;
import com.example.ourapplication_kohl_roux_m.adapter.RecyclerAdapterWithPicture;
import com.example.ourapplication_kohl_roux_m.dbClass.entities.CarEntity;
import com.example.ourapplication_kohl_roux_m.ui.BaseActivity;
import com.example.ourapplication_kohl_roux_m.ui.trajet.ListTrajet_BazActivity;
import com.example.ourapplication_kohl_roux_m.ui.Settings.SettingsActivity;
import com.example.ourapplication_kohl_roux_m.util.OnAsyncEventListener;
import com.example.ourapplication_kohl_roux_m.util.RecyclerViewItemClickListener;
import com.example.ourapplication_kohl_roux_m.viewModel.car.CarMyListViewModel;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.List;

public class ListMyActiveCars extends BaseActivity {

    private static final String TAG = "CarsList";

    private List<CarEntity> cars;
    private RecyclerAdapterWithPicture<CarEntity> adapter;
    private CarMyListViewModel viewModel;
    private long carId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getLayoutInflater().inflate(R.layout.activity_list_my_cars, frameLayout);

        setTitle("Gestion des Voitures");
        navigationView.setCheckedItem(position);

        RecyclerView recyclerView = findViewById(R.id.carsRecyclerView);

        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);

        DividerItemDecoration dividerItemDecoration = new DividerItemDecoration(recyclerView.getContext(),
                LinearLayoutManager.VERTICAL);
        recyclerView.addItemDecoration(dividerItemDecoration);

        cars = new ArrayList<>();
        adapter = loadMyCars();

        FloatingActionButton fab = findViewById(R.id.floatingActionButton);
        fab.setOnClickListener(view -> {
            Intent intent = new Intent(ListMyActiveCars.this, ChooseNewCar.class);
            startActivity(intent);
        });

        CarMyListViewModel.Factory factory = new CarMyListViewModel.Factory(
                getApplication());
        viewModel = ViewModelProviders.of(this, factory).get(CarMyListViewModel.class);
        viewModel.getMyCarsViewMod().observe(this, carsSL -> {
            if (carsSL != null) {
                cars = carsSL;
                adapter.setData(cars);
            }
        });

        recyclerView.setAdapter(adapter);
    }

    private void createDeleteDialog(final int position) {

        final CarEntity car = cars.get(position);
        LayoutInflater inflater = LayoutInflater.from(this);

        final View view = inflater.inflate(R.layout.row_delete_item, null);

        final AlertDialog alertDialog = new AlertDialog.Builder(this).create();
        alertDialog.setTitle("Voiture effacé, voiture && trajets de la voiture perdus");
        alertDialog.setCancelable(true);

        final TextView deleteMessage = view.findViewById(R.id.tv_delete_item);
        deleteMessage.setText("Attention, la voiture " + car.getNickName() + ", model " + car.getModel() + ", sera définitivement perdu !");

        alertDialog.setButton(AlertDialog.BUTTON_POSITIVE, "Effacer", (dialog, which) -> {
            Toast toast = Toast.makeText(this, "Voiture avec trajets effacés.", Toast.LENGTH_LONG);

            viewModel.deleteOneCar(car, new OnAsyncEventListener() {
                @Override
                public void onSuccess() {
                    Log.d(TAG, "deleteCar: success");
                    toast.show();
                }

                @Override
                public void onFailure(Exception e) {
                    Log.d(TAG, "deleteCar: failure", e);
                }
            });

            toast.show();
        });

        alertDialog.setButton(androidx.appcompat.app.AlertDialog.BUTTON_NEUTRAL, "Cancel", (dialog, which) -> alertDialog.dismiss());
        alertDialog.setView(view);
        alertDialog.show();
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
                Intent intent = new Intent(ListMyActiveCars.this, SettingsActivity.class);
                ListMyActiveCars.this.startActivity(intent);
                break;
        }
        return true;
    }

    public RecyclerAdapterWithPicture<CarEntity> loadMyCars() {
        RecyclerAdapterWithPicture<CarEntity> adapter = new RecyclerAdapterWithPicture<>(new RecyclerViewItemClickListener() {
            @Override
            public void onItemClick(View v, int position) {
                Log.d(TAG, "clicked position:" + position);
                Log.d(TAG, "clicked on: " + cars.get(position).getModel() + cars.get(position).getNickName());

                CarEntity car = cars.get(position);
                carId = car.getUid();
                Intent intent = new Intent(ListMyActiveCars.this, CarActivities.class);
                intent.putExtra("CarId", carId);
                startActivity(intent);
            }

            @Override
            public void onItemLongClick(View v, int position) {
                Log.d(TAG, "longClicked position:" + position);
                Log.d(TAG, "clicked on: " + cars.get(position).getModel() + cars.get(position).getNickName());
                createDeleteDialog(position);
            }
        });

        return adapter;
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        super.onCreateOptionsMenu(menu);
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_add_car, menu);
        return true;
    }
    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        int id = item.getItemId();

        if (id == BaseActivity.position) {
            drawerLayout.closeDrawer(GravityCompat.START);
            return false;
        }
        BaseActivity.position = id;
        Intent intent = null;

        navigationView.setCheckedItem(id);

        if (id == R.id.nav_vehicule) {
            intent = new Intent(this, ListMyActiveCars.class);
        } else if (id == R.id.nav_all_cars) {
            intent = new Intent(this, ListAllMyCars.class);
        }

        if (intent != null) {
            intent.setFlags(
                    Intent.FLAG_ACTIVITY_NO_ANIMATION
            );
            startActivity(intent);
        }
        drawerLayout.closeDrawer(GravityCompat.START);

        return super.onNavigationItemSelected(item);
    }
}
