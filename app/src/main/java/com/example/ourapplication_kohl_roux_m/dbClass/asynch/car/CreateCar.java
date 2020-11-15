package com.example.ourapplication_kohl_roux_m.dbClass.asynch.car;

import android.content.Context;
import android.os.AsyncTask;

import com.example.ourapplication_kohl_roux_m.dbClass.AppDataBase;
import com.example.ourapplication_kohl_roux_m.dbClass.entities.CarEntity;
import com.example.ourapplication_kohl_roux_m.util.OnAsyncEventListener;

public class CreateCar extends AsyncTask<CarEntity, Void, Void> {

    private AppDataBase database;
    private OnAsyncEventListener callback;
    private Exception exception;

    public CreateCar(Context context, OnAsyncEventListener callback) {
        database = AppDataBase.getInstance(context);
        this.callback = callback;
    }

    @Override
    protected Void doInBackground(CarEntity... params) {
        try {
            for (CarEntity carEntity : params)
                database.carDao().insert(carEntity);
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
