package com.example.ourapplication_kohl_roux_m.dbClass.Repository;

import android.app.Application;

import androidx.lifecycle.LiveData;

import com.example.ourapplication_kohl_roux_m.BaseApp;
import com.example.ourapplication_kohl_roux_m.dbClass.Car;
import com.example.ourapplication_kohl_roux_m.dbClass.Trajet;
import com.example.ourapplication_kohl_roux_m.util.OnAsyncEventListener;

public class CarRepository {

    private static CarRepository instance;

    private CarRepository() {
    }


    public static CarRepository getInstance() {
        if (instance == null) {
  //          synchronized (AccountRepository.class) {
                if (instance == null) {
                    instance = new CarRepository();
                }
            }
   //     }
        return instance;
    }



    public LiveData<Car> getCar(final int carId, Application application) {
        return ((BaseApp) application).getDatabase().carDao().getById(carId);
    }

/*    public LiveData<List<Trajet>> getOtherClientsWithAccounts(final String owner,
                                                              Application application) {
        return ((BaseApp) application).getDatabase().clientDao().getOtherClientsWithAccounts(owner);
    }

 */

    public void insert(final Car car, OnAsyncEventListener callback,
                       Application application) {
        new CreateCar(application, callback).execute(car);
    }

/*    public void update(final Car client, OnAsyncEventListener callback,
                       Application application) {
        new UpdateClient(application, callback).execute(client);
    }

 */

    public void delete(final Car car, OnAsyncEventListener callback,
                       Application application) {
        new DeleteCar(application, callback).execute(car);
    }
}
