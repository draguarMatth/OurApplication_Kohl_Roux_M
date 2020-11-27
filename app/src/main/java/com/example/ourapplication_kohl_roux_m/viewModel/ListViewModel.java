package com.example.ourapplication_kohl_roux_m.viewModel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MediatorLiveData;

import com.example.ourapplication_kohl_roux_m.dbClass.Repository.TrajetRepository;
import com.example.ourapplication_kohl_roux_m.dbClass.entities.TrajetEntity;

import java.util.ArrayList;
import java.util.List;

public class ListViewModel extends AndroidViewModel {

    private final Application application;
    // MediatorLiveData can observe other LiveData objects and react on their emissions.
    private final MediatorLiveData<List<TrajetEntity>> observableData;
    private TrajetRepository repository;
    private LiveData<List<String>> observed;
    private ArrayList<String> dataObserved;

    public ListViewModel(@NonNull Application application /* ,
/*                               ArrayList<String> dataObserved */) {
        super(application);

        this.application = application;

        this.dataObserved = dataObserved;

        observableData = new MediatorLiveData<>();

    }

}
