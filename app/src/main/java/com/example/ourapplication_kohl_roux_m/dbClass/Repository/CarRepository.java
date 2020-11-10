package com.example.ourapplication_kohl_roux_m.dbClass.Repository;

import android.app.Application;

import androidx.lifecycle.LiveData;

import com.example.ourapplication_kohl_roux_m.BaseApp;
import com.example.ourapplication_kohl_roux_m.dbClass.asynch.car.UpdateCar;
import com.example.ourapplication_kohl_roux_m.dbClass.entities.Car;
import com.example.ourapplication_kohl_roux_m.dbClass.asynch.car.CreateCar;
import com.example.ourapplication_kohl_roux_m.dbClass.asynch.car.DeleteCar;
import com.example.ourapplication_kohl_roux_m.util.OnAsyncEventListener;

import java.util.List;

public class CarRepository {

    private static CarRepository instance;

    private CarRepository() {
    }

    public static CarRepository getInstance() {
        if (instance == null) {
            synchronized (CarRepository.class) {
                if (instance == null) {
                    instance = new CarRepository();
                }
            }
        }
        return instance;
    }

    public LiveData<Car> getCar(final int carId, Application application) {
        return ((BaseApp) application).getDatabase().carDao().getById(carId);
    }

    public LiveData<List<Car>> getMyCars(Application application) {
        return ((BaseApp) application).getDatabase().carDao().getByActivity();
    }

    public LiveData<List<Car>> getAllCar(Application application) {
        return ((BaseApp) application).getDatabase().carDao().getAll();
    }

    public void insert(final Car car, OnAsyncEventListener callback,
                       Application application) {
        new CreateCar(application, callback).execute(car);
    }

    public void update(final Car car, OnAsyncEventListener callback,
                       Application application) {
        new UpdateCar(application, callback).execute(car);
    }



    public void delete(final Car car, OnAsyncEventListener callback,
                       Application application) {
        new DeleteCar(application, callback).execute(car);
    }
}
