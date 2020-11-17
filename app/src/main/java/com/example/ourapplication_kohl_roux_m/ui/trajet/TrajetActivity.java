package com.example.ourapplication_kohl_roux_m.ui.trajet;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

import com.example.ourapplication_kohl_roux_m.BaseApp;
import com.example.ourapplication_kohl_roux_m.R;
import com.example.ourapplication_kohl_roux_m.dbClass.entities.CarEntity;
import com.example.ourapplication_kohl_roux_m.dbClass.entities.TrajetEntity;
import com.example.ourapplication_kohl_roux_m.ui.BaseActivity;

public class TrajetActivity extends BaseActivity {

    private static final String TAG = "ViewTrajet";

    private Intent previousIntent;
    Bundle bundle;
    private long carId;
    private long trajetId;
    private TrajetEntity trajet;
    private CarEntity carConcerned;

    TextView nameTrajet;
    TextView modelCar;
    TextView nicknameCar;
    TextView distance;
    TextView down;
    TextView up;
    TextView consoElect;
    TextView consoFuel;

    Button ModifyButton;
    Button backButton;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getLayoutInflater().inflate(R.layout.activity_trajet, frameLayout);
        previousIntent = getIntent();
        bundle = previousIntent.getExtras();

        setTitle(/* getString(R.string.title_activity_accounts) */ "Détails Trajet");
        navigationView.setCheckedItem(position);

        nameTrajet = findViewById(R.id.name);
        modelCar = findViewById(R.id.modelCar);
        nicknameCar = findViewById(R.id.nickNameCar);
        distance = findViewById(R.id.distance);
        down = findViewById(R.id.downTrip);
        up = findViewById(R.id.upTrip);
        consoElect = findViewById(R.id.consoElect);
        consoFuel = findViewById(R.id.consoFuel);

        carId = (long) bundle.get("CarId");
        trajetId = (long) bundle.get("TrajetId");
        trajet = (TrajetEntity) bundle.get("Trajet");
//        trajet = ((BaseApp)getApplication()).getDatabase().trajetDao().getById(trajetId);
        carConcerned = ((BaseApp)getApplication()).getDatabase().carDao().getCar(carId);

        nameTrajet.setText(trajet.getName());
        modelCar.setText(carConcerned.getModel());
        nicknameCar.setText(carConcerned.getNickName());
        distance.setText(String.valueOf(trajet.getKmTot()));
        down.setText(String.valueOf(trajet.getTotDeep()));
        up.setText(String.valueOf(trajet.getTotRise()));
        consoElect.setText(String.valueOf(trajet.getElectricityTot()));
        consoFuel.setText(String.valueOf(trajet.getGasolinTot()));

    }
}
