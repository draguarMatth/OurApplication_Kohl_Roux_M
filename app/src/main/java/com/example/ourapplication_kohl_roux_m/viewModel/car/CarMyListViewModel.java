package com.example.ourapplication_kohl_roux_m.viewModel.car;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MediatorLiveData;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;

import com.example.ourapplication_kohl_roux_m.BaseApp;
import com.example.ourapplication_kohl_roux_m.dbClass.Repository.CarRepository;
import com.example.ourapplication_kohl_roux_m.dbClass.entities.Car;
import com.example.ourapplication_kohl_roux_m.util.OnAsyncEventListener;

import java.util.List;

public class CarMyListViewModel extends AndroidViewModel {

    private Application application;

    private CarRepository repository;

    // MediatorLiveData can observe other LiveData objects and react on their emissions.
    private final MediatorLiveData<List<Car>> observableCars;
 //   private final MediatorLiveData<List<AccountEntity>> observableOwnAccounts;

    public CarMyListViewModel(@NonNull Application application,
                                CarRepository carRepository) {
        super(application);

        this.application = application;

        repository = carRepository;

//        observableClientAccounts = new MediatorLiveData<>();
        observableCars = new MediatorLiveData<>();
        // set by default null, until we get data from the database.
        observableCars.setValue(null);

        LiveData<List<Car>> carList =
                carRepository.getMyCars(application);
        LiveData<List<Car>> ownAccounts = repository.getMyCars(application);

        // observe the changes of the entities from the database and forward them
//        observableClientAccounts.addSource(clientAccounts, observableClientAccounts::setValue);
        observableCars.addSource(carList, observableCars::setValue);
    }

    /**
     * A creator is used to inject the account id into the ViewModel
     */
    public static class Factory extends ViewModelProvider.NewInstanceFactory {

        @NonNull
        private final Application application;

//        private final boolean active;

        private final CarRepository carRepsitory;

//        private final AccountRepository accountRepository;

        public Factory(@NonNull Application application) {
            this.application = application;
            carRepsitory = ((BaseApp) application).getCarRepository();
//            accountRepository = ((BaseApp) application).getAccountRepository();
        }

        @Override
        public <T extends ViewModel> T create(Class<T> modelClass) {
            //noinspection unchecked
            return (T) new CarMyListViewModel(application, carRepsitory);
        }
    }

    /**
     * Expose the LiveData ClientAccounts query so the UI can observe it.
     */
    public LiveData<List<Car>> getMyCars() {
        return observableCars;
    }

    /**
     * Expose the LiveData AccountEntities query so the UI can observe it.
     */
    public LiveData<List<Car>> getOwnAccounts() {
        return observableCars;
    }

    public void deleteOneCar(Car car, OnAsyncEventListener callback) {
        repository.delete(car, callback, application);
    }

    public void executeModify(final Car car, OnAsyncEventListener callback) {
        repository.update(car, callback, application);
    }
}
