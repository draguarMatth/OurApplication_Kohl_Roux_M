package com.example.ourapplication_kohl_roux_m.dbClass;

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

 /*   private static void addTrajet(final AppDataBase db, final int carId, final String date) {
        Trajet trajet = new Trajet(carId, date);
        db.trajetDao().insert(trajet);
    }
*/
    private static void addTrajet(final AppDataBase db, final int carId, final String name, final String date,
                                      final double kmTot, final double totRise, final double totDeep,
                                      final double gasolinTot, final double electricityTot) {
        Trajet trajet = new Trajet(carId, name, date, kmTot, totRise, totDeep, gasolinTot, electricityTot);
        db.trajetDao().insert(trajet);
    }

/*    private static void addCar(final AppDataBase db, final String carTradeMark, final String model,
                               final double consoEssence, final double batteryPower, Boolean activate) {

        Car car = new Car(carTradeMark, model, consoEssence, batteryPower, activate);
        db.carDao().insert(car);
    }

 */
    private static void addCar(final AppDataBase db, final String nickName, final String carTradeMark, final String model,
                               final double consoEssence, final double batteryPower, final String wheelSize, final boolean carForTrip) {

        Car car = new Car(nickName, carTradeMark, model, consoEssence, batteryPower, wheelSize, carForTrip);
        db.carDao().insert(car);
    }
    // String nickName, @NonNull String carTradeMark, @NonNull String model,
    //               double consoEssence, double batteryPower, double wheelSize, @NonNull boolean carForTrip

    private static void populateWithTestData(AppDataBase db) {
        db.carDao().deleteAll();
        db.trajetDao().deleteAll();

        addCar(db, "","Hyundahi", "Bionic", 1.1, 8.9, "",true);
 //       addCar(db, "Merco", "Cé 350", 2.1, 6.2);
        // String name, float kmTot, String date, float totRise, float totDeep, float gasolinTot, float electricityTot

        addTrajet(db, db.carDao().getActive(),"Anonymous", "07 novembre 2020", 0, 0,0,0,0);
        addTrajet(db, db.carDao().getActive(), "Jvéoboulo","07.11.2019", 152.3, 3, 4, 5, 95);
        addTrajet(db, db.carDao().getActive(), "", "24.02.2020", 64.8, 12, 3, 42.3, 56.4);
        addTrajet(db, db.carDao().getActive(), "Parlavass","12.08.2020", 152.3, 3, 4, 12.6, 60);
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
