package com.example.ourapplication_kohl_roux_m;

import android.app.Application;

import com.example.ourapplication_kohl_roux_m.dbClass.AppDataBase;
import com.example.ourapplication_kohl_roux_m.dbClass.Repository.CarRepository;
import com.example.ourapplication_kohl_roux_m.dbClass.Repository.TrajetRepository;


/**
 * Android Application class. Used for accessing singletons.
 */
public class BaseApp extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
    }

    public AppDataBase getDatabase() {
        return AppDataBase.getInstance(this);
    }

    public CarRepository getCarRepository(int id) {
        return CarRepository.getInstance();
    }

    public TrajetRepository getTrajetRepository() {
        return TrajetRepository.getInstance();
    }
}