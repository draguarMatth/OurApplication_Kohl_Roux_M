package com.example.ourapplication_kohl_roux_m.dbClass.asynch.trajet;

import android.content.Context;
import android.os.AsyncTask;

import com.example.ourapplication_kohl_roux_m.dbClass.AppDataBase;
import com.example.ourapplication_kohl_roux_m.dbClass.entities.Trajet;
import com.example.ourapplication_kohl_roux_m.util.OnAsyncEventListener;

public class CreateTrajet extends AsyncTask<Trajet, Void, Void> {

    private AppDataBase database;
    private OnAsyncEventListener callback;
    private Exception exception;

    public CreateTrajet(Context context, OnAsyncEventListener callback) {
        database = AppDataBase.getInstance(context);
        this.callback = callback;
    }

    @Override
    protected Void doInBackground(Trajet... params) {
        try {
            for (Trajet trajet : params)
                database.trajetDao().insert(trajet);
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
