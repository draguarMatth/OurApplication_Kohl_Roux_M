package com.example.ourapplication_kohl_roux_m.dbClass.asynch.car;

import android.app.Application;
import android.content.Context;
import android.os.AsyncTask;

import androidx.lifecycle.LiveData;

import com.example.ourapplication_kohl_roux_m.BaseApp;
import com.example.ourapplication_kohl_roux_m.dbClass.AppDataBase;
import com.example.ourapplication_kohl_roux_m.dbClass.entities.CarEntity;
import com.example.ourapplication_kohl_roux_m.dbClass.entities.TrajetEntity;
import com.example.ourapplication_kohl_roux_m.util.OnAsyncEventListener;

import java.util.ArrayList;
import java.util.List;

public class DeleteCar extends AsyncTask<CarEntity, Void, Void> {

    private Application application;
    private OnAsyncEventListener callback;
    private Exception exception;
    private ArrayList <TrajetEntity> trajets;

    public DeleteCar(Application application, OnAsyncEventListener callback) {
        this.application = application;
        this.callback = callback;
    }

    @Override
    protected Void doInBackground(CarEntity... params) {
        try {
            for (CarEntity carEntity : params) {

                LiveData <List<TrajetEntity>> trajetsLD = ((BaseApp) application).getDatabase().trajetDao().getByCarId(carEntity.getUid());
                trajets = (ArrayList <TrajetEntity>) trajetsLD.getValue();
                for (TrajetEntity trajetEntity : trajets)
                    ((BaseApp) application).getDatabase().trajetDao().delete(trajetEntity);

                ((BaseApp) application).getDatabase().carDao().delete(carEntity);
            }

        } catch (Exception e) {
            exception = e;
        }
        return null;
    }

    @Override
    protected void onPostExecute(Void aVoid) {
        if (callback != null) {
            if (exception == null) {
                callback.onSuccess();
            } else {
                callback.onFailure(exception);
            }
        }
    }

}
