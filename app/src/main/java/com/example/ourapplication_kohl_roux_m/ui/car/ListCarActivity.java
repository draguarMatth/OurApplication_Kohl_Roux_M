package com.example.ourapplication_kohl_roux_m.ui.car;

import android.app.AlertDialog;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.core.view.GravityCompat;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager.widget.ViewPager;

import com.example.ourapplication_kohl_roux_m.R;
import com.example.ourapplication_kohl_roux_m.adapter.RecyclerAdapter;
import com.example.ourapplication_kohl_roux_m.dbClass.entities.CarEntity;
import com.example.ourapplication_kohl_roux_m.dbClass.entities.TrajetEntity;
import com.example.ourapplication_kohl_roux_m.ui.BaseActivity;
import com.example.ourapplication_kohl_roux_m.ui.management.AddMyNewCar;
import com.example.ourapplication_kohl_roux_m.ui.management.CreateTrip;
import com.example.ourapplication_kohl_roux_m.ui.trajet.ListTrajet_BazActivity;
import com.example.ourapplication_kohl_roux_m.ui.trajet.TrajetActivity;
import com.example.ourapplication_kohl_roux_m.util.OnAsyncEventListener;
import com.example.ourapplication_kohl_roux_m.util.RecyclerViewItemClickListener;
import com.example.ourapplication_kohl_roux_m.viewModel.car.CarMyListViewModel;
import com.example.ourapplication_kohl_roux_m.viewModel.trajet.TrajetListViewModel;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.tabs.TabLayout;

import java.util.ArrayList;
import java.util.List;

public class ListCarActivity extends BaseActivity {

    private static final String TAG = "CarsList";

    private TabLayout tabLayout;
    private ViewPager viewPager;
//    private ViewPagerAdapter adapterFragment;

    private List<CarEntity> cars;
    private RecyclerAdapter<CarEntity> adapter;
    private CarMyListViewModel viewModel;
    private long carId;

    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        getLayoutInflater().inflate(R.layout.activity_list_my_cars, frameLayout);


        setTitle( "Gestion des Voitures");
        navigationView.setCheckedItem(position);


        RecyclerView recyclerView = findViewById(R.id.carsRecyclerView);

        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);

        DividerItemDecoration dividerItemDecoration = new DividerItemDecoration(recyclerView.getContext(),
                LinearLayoutManager.VERTICAL);
        recyclerView.addItemDecoration(dividerItemDecoration);


        cars = new ArrayList<>();

        adapter = loadMyCars();
/*
        if (adapter == null)
        {
 //           tabLayout = (TabLayout) findViewById(R.id.tablayout_id);
 //           viewPager = (ViewPager) findViewById(R.id.viewpager_id);
//            adapter = new ViewPagerAdapter(getSupportFragmentManager());

            //Add Fragment Here
   //         adapter.AddFragment(new List_trajet(), "TRAJET");
   //         viewPager.setAdapter(adapter);
   //         tabLayout.setupWithViewPager(viewPager);
            Fragment nwFgmt = new FragementChooseNewCar();
            nwFgmt.onCreate(savedInstanceState);

        }
*/
        FloatingActionButton fab = findViewById(R.id.floatingActionButton);
        fab.setOnClickListener(view -> {
            Intent intent = new Intent(ListCarActivity.this, FragementChooseNewCar.class);
            //                  startActivity(intent);
            Fragment nwFgmt = new FragementChooseNewCar();
            //    nwFgmt=(FragementChooseNewCar) getSupportFragmentManager().findFragmentById(R.id.chooseNewCarFragment);
            nwFgmt.setArguments(getIntent().getExtras());
            nwFgmt.startActivity(getIntent());

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

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        if (item.getItemId() == BaseActivity.position) {
            drawerLayout.closeDrawer(GravityCompat.START);
            return false;
        }

        finish();
        return super.onNavigationItemSelected(item);

    }

    private void createModifyDialog (final int position) {

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
    }

    private void createDeleteDialog(final int position) {

        final CarEntity car = cars.get(position);
        LayoutInflater inflater = LayoutInflater.from(this);

        final View view = inflater.inflate(R.layout.row_delete_item, null);

        final AlertDialog alertDialog = new AlertDialog.Builder(this).create();
        alertDialog.setTitle("Voiture effacé, voiture && trajets de la voiture perdus");
        alertDialog.setCancelable(true);

        final TextView deleteMessage = view.findViewById(R.id.tv_delete_item);
        deleteMessage.setText("Attention, la voiture " + car.getNickName() + ", model "+ car.getModel() + ", sera définitivement perdu !");

        alertDialog.setButton(AlertDialog.BUTTON_POSITIVE, "Effacer", (dialog, which) -> {
            Toast toast = Toast.makeText(this, "Voiture avec trajets effacés.", Toast.LENGTH_LONG);

         /*   CarMyListViewModel viewModelDel;
            CarMyListViewModel.Factory factory = new CarMyListViewModel.Factory(
                    getApplication());
            viewModelDel = ViewModelProviders.of(this, factory).get(CarMyListViewModel.class);
            viewModelDel.getMyCarsViewMod().observe(this, carsSL -> {
                if (carsSL != null) {
                    cars = carsSL;
                    adapter.setData(cars);
                }
            });
            */
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

        alertDialog.setButton(AlertDialog.BUTTON_NEGATIVE, "Créer nouvelle voiture", (dialog, which) -> {
            Toast toast = Toast.makeText(this, "Voiture avec trajets effacés.", Toast.LENGTH_LONG);

            viewModel.createTunedCar(car, new OnAsyncEventListener() {
                        @Override
                        public void onSuccess() {
                            Log.d(TAG, "deleteCar: success");
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
    public boolean onCreateOptionsMenu(Menu menu){
        getMenuInflater().inflate(R.menu.menu_add_car, menu);
        return true;
    }

    public RecyclerAdapter<CarEntity> loadMyCars() {
        RecyclerAdapter<CarEntity> adapter = new RecyclerAdapter<>(new RecyclerViewItemClickListener() {
            @Override
            public void onItemClick(View v, int position) {
                Log.d(TAG, "clicked position:" + position);
                Log.d(TAG, "clicked on: " + cars.get(position).getModel() + cars.get(position).getNickName());

                CarEntity car = cars.get(position);
                Intent intent = new Intent(ListCarActivity.this, ListTrajet_BazActivity.class);
                intent.setFlags(
                        Intent.FLAG_ACTIVITY_NO_ANIMATION |
                                Intent.FLAG_ACTIVITY_NO_HISTORY
                );
                intent.putExtra("TrajetId", cars.get(position).getUid());
                intent.putExtra("CardId", carId);
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

}
