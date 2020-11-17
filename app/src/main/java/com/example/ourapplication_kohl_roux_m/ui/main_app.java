package com.example.ourapplication_kohl_roux_m.ui;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.GravityCompat;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.ourapplication_kohl_roux_m.BaseApp;
import com.example.ourapplication_kohl_roux_m.R;
import com.example.ourapplication_kohl_roux_m.adapter.RecyclerAdapter;
import com.example.ourapplication_kohl_roux_m.dbClass.Repository.CarRepository;
import com.example.ourapplication_kohl_roux_m.dbClass.Repository.TrajetRepository;
import com.example.ourapplication_kohl_roux_m.dbClass.entities.CarEntity;
import com.example.ourapplication_kohl_roux_m.dbClass.entities.TrajetEntity;
import com.example.ourapplication_kohl_roux_m.ui.management.AddNewMyCar;
import com.example.ourapplication_kohl_roux_m.ui.management.CreateTrip;
import com.example.ourapplication_kohl_roux_m.ui.trajet.ListTrajet_BazActivity;
import com.example.ourapplication_kohl_roux_m.ui.trajet.TrajetActivity;
import com.example.ourapplication_kohl_roux_m.util.RecyclerViewItemClickListener;
import com.example.ourapplication_kohl_roux_m.viewModel.car.CarMyListViewModel;
import com.example.ourapplication_kohl_roux_m.viewModel.trajet.TrajetListViewModel;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.List;

public class main_app extends AppCompatActivity {

    private Button choix1;
    private Button choix2;
    BottomNavigationView bottomNavigationView;

    private List<CarEntity> cars;
    private RecyclerAdapter<CarEntity> adapter;

    TrajetRepository trajetRepo;
    CarRepository carRepository;
    LiveData<List<CarEntity>> carsLiveD;

    CarMyListViewModel carMyListViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_app);


 //       trajetRepo = ((BaseApp) getApplication()).getTrajetRepository();

        ImageView imageView = findViewById(R.id.imageView);
        imageView.setImageResource(R.drawable.i8);

        ImageView imageView2 = findViewById(R.id.imageView2);
        imageView.setImageResource(R.drawable.i8);


  /*      cars = new ArrayList<>();
        carsLiveD = ((BaseApp)getApplication()).getDatabase().carDao().getByActivity();
        cars = carsLiveD.getValue();

        if(cars.isEmpty())
            System.out.println("----------------------------------- CARS VIDE ----------------------------------------");
*/
        choix1 = findViewById(R.id.btn);
 //       choix1.setText(cars.get(0).getNickName());
        choix2 = findViewById(R.id.addFuelButton);
 //       choix2.setText(cars.get(1).getNickName());

        choix1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
 //               Intent intent = new Intent(main_app.this, list_trajet.class);
                Intent intent = new Intent(main_app.this, ListTrajet_BazActivity.class);
 //               intent.putExtra("CarId", cars.get(0).getUid());
                startActivity(intent);
            }
        });


        choix2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (cars.size()>1) {
                    Intent intent = new Intent(main_app.this, ListTrajet_BazActivity.class);
//                    intent.putExtra("CarId", cars.get(1).getUid());
                    startActivity(intent);
                }
            }
        });

        FloatingActionButton fab = findViewById(R.id.floatingActionButton);
/*        fab.setOnClickListener(view -> {
                    Intent intent = new Intent(main_app.this, AddNewMyCar.class);
                    startActivity(intent);
                }
        );
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
    public boolean onCreateOptionsMenu(Menu menu){
        getMenuInflater().inflate(R.menu.menu_add_car, menu);
        return true;
    }

/*    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        if (item.getItemId() == BaseActivity.position) {
            drawerLayout.closeDrawer(GravityCompat.START);
            return false;
        }

        finish();
        return super.onNavigationItemSelected(item);
    }
*/


}
