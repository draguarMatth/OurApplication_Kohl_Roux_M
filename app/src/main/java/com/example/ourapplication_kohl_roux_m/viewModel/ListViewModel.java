package com.example.ourapplication_kohl_roux_m.viewModel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MediatorLiveData;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;

import com.example.ourapplication_kohl_roux_m.BaseApp;
import com.example.ourapplication_kohl_roux_m.dbClass.Repository.TrajetRepository;
import com.example.ourapplication_kohl_roux_m.dbClass.entities.TrajetEntity;
import com.example.ourapplication_kohl_roux_m.util.OnAsyncEventListener;
import com.example.ourapplication_kohl_roux_m.viewModel.trajet.TrajetListViewModel;

import java.util.ArrayList;
import java.util.List;

public class ListViewModel  extends AndroidViewModel {

    private Application application;

    private TrajetRepository repository;

    private LiveData<List<String>> observed ;

    private ArrayList<String> dataObserved;

    // MediatorLiveData can observe other LiveData objects and react on their emissions.
    private final MediatorLiveData<List<TrajetEntity>> observableData;

    public ListViewModel(@NonNull Application application /* ,
/*                               ArrayList<String> dataObserved */ ) {
        super(application);

        this.application = application;

        this.dataObserved = dataObserved;

        observableData = new MediatorLiveData<>();

    }

}
