package com.example.ourapplication_kohl_roux_m.dbClass.Repository;

import android.os.AsyncTask;
import android.util.Log;

import com.example.ourapplication_kohl_roux_m.dbClass.AppDataBase;
import com.example.ourapplication_kohl_roux_m.dbClass.Car;
import com.example.ourapplication_kohl_roux_m.dbClass.Trajet;

public class DatabaseInitializer {

    public static final String TAG = "DatabaseInitializer";

    public static void populateDatabase(final AppDataBase db) {
        Log.i(TAG, "Inserting demo data.");
        PopulateDbAsync task = new PopulateDbAsync(db);
        task.execute();
    }

    private static void addTrajet(final AppDataBase db, final int carId, final String date) {
        Trajet trajet = new Trajet(carId, date);
        db.trajetDao().insert(trajet);
    }

    private static void addTrajetTest(final AppDataBase db, final int carId, final String name, final String date,
                                      final double kmTot, final double totRise, final double totDeep,
                                      final double gasolinTot, final double electricityTot) {
        Trajet trajet = new Trajet(carId, name, date, kmTot, totRise, totDeep, gasolinTot, electricityTot);
        db.trajetDao().insert(trajet);
    }

    private static void addCar(final AppDataBase db, final String carTradeMark, final String model,
                               final double consoEssence, final double batteryPower, Boolean activate) {

        Car car = new Car(carTradeMark, model, consoEssence, batteryPower, activate);
        db.carDao().insert(car);
    }

    private static void populateWithTestData(AppDataBase db) {
        db.carDao().deleteAll();
        db.trajetDao().deleteAll();

        addCar(db, "Hyundahi", "Bionic", 1.1, 8.9, true);
 //       addCar(db, "Merco", "Cé 350", 2.1, 6.2);
        // String name, float kmTot, String date, float totRise, float totDeep, float gasolinTot, float electricityTot

        addTrajet(db, db.carDao().getActive(),"Anonymous");
        addTrajetTest(db, db.carDao().getActive(), "07.11.2019","Jvéoboulo", 152.3, 3, 4, 5, 95);
        addTrajetTest(db, db.carDao().getActive(), "24.02.2020","Parlavass", 64.8, 12, 3, 42.3, 56.4);
        addTrajetTest(db, db.carDao().getActive(), "12.08.2020","Jvéoboulo", 152.3, 3, 4, 12.6, 60);
    }

    private static class PopulateDbAsync extends AsyncTask<Void, Void, Void> {

        private final AppDataBase database;

        PopulateDbAsync(AppDataBase db) {
            database = db;
        }

        @Override
        protected Void doInBackground(final Void... params) {
            populateWithTestData(database);
            return null;
        }

    }

}
