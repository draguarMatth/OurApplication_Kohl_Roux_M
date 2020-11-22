package com.example.ourapplication_kohl_roux_m.ui.car;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.Spinner;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.ourapplication_kohl_roux_m.R;
import com.example.ourapplication_kohl_roux_m.adapter.ListAdapter;
import com.example.ourapplication_kohl_roux_m.ui.BaseActivity;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.navigation.NavigationView;

import java.util.ArrayList;
import java.util.List;
import java.util.SortedMap;

public class FragementChooseNewCar extends Fragment {
    private static final String TAG = "TransactionActivity";

    protected NavigationView navigationView;
    protected static int position;
    private View v;

    private Spinner spinnerTradeMark;
    private Spinner spinnerModel;
    private Spinner spinnerConsoFuel;
    private Spinner spinnerBattery;
    private Spinner spinnerWheelSize;

    private List <String> listTradeMark;
    private List <String> listModel;
    private List <String> lisConsoFuel;
    private List <String> listBattery;
    private List <String> listWheelSize;

    private EditText NickName;

    private ListAdapter<String> adapterTradeMark, adapterModel,  adapterConsoFuel, adapterbattery, adapterWheelSize;

 //   public FragementChooseNewCar(){}

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        v = inflater.inflate(R.layout.fragment_choose_new_car, container, false);

        FloatingActionButton fab = v.findViewById(R.id.floatingActionButton2);
        fab.setVisibility(v.VISIBLE);
        fab.setOnClickListener(view -> {
            Intent intent = new Intent(getActivity(), ListCarActivity.class);
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


        return v;
    }

 /*   @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        getActivity();
       getLayoutInflater().inflate(R.layout.activity_list_my_cars, );

 //       setTitle( "Liste Trajets");
//        navigationView.setCheckedItem(position);

        RecyclerView recyclerView = findViewById(R.id.trajetsRecyclerView);

        // use a linear layout manager

        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getActivity());
        recyclerView.setLayoutManager(layoutManager);

        DividerItemDecoration dividerItemDecoration = new DividerItemDecoration(recyclerView.getContext(),
                LinearLayoutManager.VERTICAL);
        recyclerView.addItemDecoration(dividerItemDecoration);

    }
*/
}


