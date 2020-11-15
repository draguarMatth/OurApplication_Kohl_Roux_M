package com.example.ourapplication_kohl_roux_m.dbClass.asynch.trajet;

import android.content.Context;
import android.os.AsyncTask;

import com.example.ourapplication_kohl_roux_m.dbClass.AppDataBase;
import com.example.ourapplication_kohl_roux_m.dbClass.entities.TrajetEntity;
import com.example.ourapplication_kohl_roux_m.util.OnAsyncEventListener;

public class CreateTrajet extends AsyncTask<TrajetEntity, Void, Void> {

    private AppDataBase database;
    private OnAsyncEventListener callback;
    private Exception exception;

    public CreateTrajet(Context context, OnAsyncEventListener callback) {
        database = AppDataBase.getInstance(context);
        this.callback = callback;
    }

    @Override
    protected Void doInBackground(TrajetEntity... params) {
        try {
            for (TrajetEntity trajetEntity : params)
                database.trajetDao().insert(trajetEntity);
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
