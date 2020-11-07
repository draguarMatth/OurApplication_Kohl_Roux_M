package com.example.ourapplication_kohl_roux_m.dbClass.Repository;

import android.app.Application;

import androidx.lifecycle.LiveData;

import com.example.ourapplication_kohl_roux_m.BaseApp;
import com.example.ourapplication_kohl_roux_m.dbClass.Trajet;
<<<<<<< HEAD
import com.example.ourapplication_kohl_roux_m.dbClass.asynch.CreateTrajet;
=======
>>>>>>> Accès db 1
import com.example.ourapplication_kohl_roux_m.dbClass.asynch.DeleteTrajet;
import com.example.ourapplication_kohl_roux_m.dbClass.asynch.UpdateTrajet;
import com.example.ourapplication_kohl_roux_m.dbClass.pojo.TrajetByThisCar;
import com.example.ourapplication_kohl_roux_m.util.OnAsyncEventListener;

import java.util.List;

public class TrajetRepository {

    private static TrajetRepository instance;

    private TrajetRepository() {
    }

    public static TrajetRepository getInstance() {
        if (instance == null) {
            synchronized (CarRepository.class) {
                if (instance == null) {
                    instance = new TrajetRepository();
                }
            }
        }
        return instance;
    }

<<<<<<< HEAD
    public LiveData<List<Trajet>> getTrajetByDate(final String date,
                                                                    Application application) {
        return ((BaseApp) application).getDatabase().trajetDao().getByDate(date);
    }

    public LiveData<TrajetByThisCar> getTrajetByName(final String date,
=======
    public LiveData<Trajet> getTrajet(final String date, Application application) {
        return ((BaseApp) application).getDatabase().trajetDao().getByDate(date);
    }

    public LiveData<List<TrajetByThisCar>> getOtherTrajetByThisCar(final String date,
>>>>>>> Accès db 1
                                                                   Application application) {
        return ((BaseApp) application).getDatabase().trajetDao().getByName(date);
    }

    public void insert(final Trajet trajet, OnAsyncEventListener callback,
                       Application application) {
<<<<<<< HEAD
        new CreateTrajet(application, callback).execute(trajet);
    }
=======
trajet    }
>>>>>>> Accès db 1

    public void update(final Trajet trajet, OnAsyncEventListener callback,
                       Application application) {
        new UpdateTrajet(application, callback).execute(trajet);
    }

    public void delete(final Trajet trajet, OnAsyncEventListener callback,
                       Application application) {
        new DeleteTrajet(application, callback).execute(trajet);
    }
}
