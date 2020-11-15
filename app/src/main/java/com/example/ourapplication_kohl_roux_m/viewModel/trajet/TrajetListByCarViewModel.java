package com.example.ourapplication_kohl_roux_m.viewModel.trajet;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MediatorLiveData;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;

import com.example.ourapplication_kohl_roux_m.BaseApp;
import com.example.ourapplication_kohl_roux_m.dbClass.Repository.TrajetRepository;
import com.example.ourapplication_kohl_roux_m.dbClass.pojo.TrajetByThisCar;

import java.util.List;

public class TrajetListByCarViewModel extends AndroidViewModel {

    private Application application;

    private TrajetRepository repository;

    // MediatorLiveData can observe other LiveData objects and react on their emissions.
    private final MediatorLiveData<List<TrajetByThisCar>> observableTrajets;
//    private final MediatorLiveData<List<CarEntity>> observableCars;

    public TrajetListByCarViewModel(@NonNull Application application,
                                     final int carId,
                                     // CarRepository carRepository,
                                     TrajetRepository trajetRepository) {
        super(application);

        this.application = application;

        repository = trajetRepository;

        observableTrajets = new MediatorLiveData<>();
//        observableCars = new MediatorLiveData<>();
        // set by default null, until we get data from the database.
        observableTrajets.setValue(null);
        //       observableCars.setValue(null);

        LiveData<List<TrajetByThisCar>> trajetList =
                repository.getTrajetByCarId(carId, application);
//        LiveData<List<CarEntity>> ownAccounts = repository.getByOwner(ownerId, application);

        // observe the changes of the entities from the database and forward them
        observableTrajets.addSource(trajetList, observableTrajets::setValue);
//        observableOwnAccounts.addSource(ownAccounts, observableOwnAccounts::setValue);
    }

    /**
     * A creator is used to inject the account id into the ViewModel
     */
    public static class Factory extends ViewModelProvider.NewInstanceFactory {

        @NonNull
        private final Application application;

 //       private final String tripName;

        private final int carId;

        private final TrajetRepository trajetRepository;

  //      private final CarRepository carRepository;

        public Factory(@NonNull Application application, int carId) {
            this.application = application;
            this.carId = carId;
            trajetRepository = ((BaseApp) application).getTrajetRepository();
   //         carRepository = ((BaseApp) application).getCarRepository();
        }

        @Override
        public <T extends ViewModel> T create(Class<T> modelClass) {
            //noinspection unchecked
            return (T) new TrajetListByCarViewModel(application, carId, trajetRepository /* , carRepository */);
        }
    }

    /**
     * Expose the LiveData ClientAccounts query so the UI can observe it.
     */
    public LiveData<List<TrajetByThisCar>> getTrajet() {
        return observableTrajets;
    }

    /**
     * Expose the LiveData AccountEntities query so the UI can observe it.
     */
/*    public LiveData<List<AccountEntity>> getOwnAccounts() {
        return observableOwnAccounts;
    }

    public void deleteAccount(AccountEntity account, OnAsyncEventListener callback) {
        repository.delete(account, callback, application);
    }

    public void executeTransaction(final AccountEntity sender, final AccountEntity recipient,
                                   OnAsyncEventListener callback) {
        repository.transaction(sender, recipient, callback, application);

    }
 */
}
