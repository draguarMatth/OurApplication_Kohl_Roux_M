package com.example.ourapplication_kohl_roux_m.dbClass.asynch.car;

import android.content.Context;
import android.os.AsyncTask;

import com.example.ourapplication_kohl_roux_m.dbClass.AppDataBase;
import com.example.ourapplication_kohl_roux_m.dbClass.entities.Car;
import com.example.ourapplication_kohl_roux_m.util.OnAsyncEventListener;

public class DeleteCar extends AsyncTask<Car, Void, Void> {

    private AppDataBase database;
    private OnAsyncEventListener callback;
    private Exception exception;

    public DeleteCar(Context context, OnAsyncEventListener callback) {
        database = AppDataBase.getInstance(context);
        this.callback = callback;
    }

    @Override
    protected Void doInBackground(Car... params) {
        try {
            for (Car car : params)
                database.carDao().delete(car);
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
