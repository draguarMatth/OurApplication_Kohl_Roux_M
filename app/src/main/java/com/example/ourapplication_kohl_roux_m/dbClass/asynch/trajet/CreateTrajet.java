package com.example.ourapplication_kohl_roux_m.dbClass.asynch.trajet;

import android.app.Application;
import android.content.Context;
import android.os.AsyncTask;

import com.example.ourapplication_kohl_roux_m.BaseApp;
import com.example.ourapplication_kohl_roux_m.dbClass.AppDataBase;
import com.example.ourapplication_kohl_roux_m.dbClass.entities.TrajetEntity;
import com.example.ourapplication_kohl_roux_m.util.OnAsyncEventListener;

public class CreateTrajet extends AsyncTask<TrajetEntity, Void, Void> {

//    private AppDataBase appDatabase;
    private Application application;
    private OnAsyncEventListener callback;
    private Exception exception;

    public CreateTrajet( Application application , OnAsyncEventListener callback) {
//        database = AppDataBase.getInstance(context);
        this.application = application;
        this.callback = callback;
    }

    @Override
    protected Void doInBackground(TrajetEntity... params) {
        try {
            AppDataBase.getInstance(application.getBaseContext()).getDatabaseCreated();

            for (TrajetEntity trajetEntity : params) {
                ((BaseApp) application).getDatabase().trajetDao().insert(trajetEntity);
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
