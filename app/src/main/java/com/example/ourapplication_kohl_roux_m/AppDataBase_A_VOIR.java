package com.example.ourapplication_kohl_roux_m;

import android.content.Context;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.sqlite.db.SupportSQLiteDatabase;

import com.example.ourapplication_kohl_roux_m.dbClass.entities.Car;
import com.example.ourapplication_kohl_roux_m.dbClass.DatabaseInitializer;
import com.example.ourapplication_kohl_roux_m.dbClass.entities.Trajet;
import com.example.ourapplication_kohl_roux_m.dbClass.dao.DbTrajetDao;

import java.util.concurrent.Executors;

@Database(entities = {Trajet.class, Car.class}, version = 1)
//@Database(entities = {Car.class}, version= 1)
public abstract class AppDataBase_A_VOIR extends RoomDatabase {

    public abstract DbTrajetDao trajetDao();
    public abstract DbCarDao carDao();

    private static final String TAG = "AppDatabase";

    private static AppDataBase_A_VOIR instance;

    private static final String DATABASE_NAME = "app-database";

    private final MutableLiveData<Boolean> mIsDatabaseCreated = new MutableLiveData<>();


    private final MutableLiveData<Boolean> isDatabaseCreated = new MutableLiveData<>();

    public static AppDataBase_A_VOIR getInstance(final Context context) {
        if (instance == null) {
            synchronized (AppDataBase_A_VOIR.class) {
                if (instance == null) {
                    instance = buildDatabase(context.getApplicationContext());
                    instance.updateDatabaseCreated(context.getApplicationContext());
                }
            }
        }
        return instance;
    }

    /**
     * Build the database. {@link Builder#build()} only sets up the database configuration and
     * creates a new instance of the database.
     * The SQLite database is only created when it's accessed for the first time.
     */
    private static AppDataBase_A_VOIR buildDatabase(final Context appContext) {
        Log.i(TAG, "Database will be initialized.");
        return Room.databaseBuilder(appContext, AppDataBase_A_VOIR.class, DATABASE_NAME)
                .addCallback(new Callback() {
                    @Override
                    public void onCreate(@NonNull SupportSQLiteDatabase db) {
                        super.onCreate(db);
                        Executors.newSingleThreadExecutor().execute(() -> {
                            AppDataBase_A_VOIR database = AppDataBase_A_VOIR.getInstance(appContext);
                            DatabaseInitializer.populateDatabase(database);
                            // notify that the database was created and it's ready to be used
                            database.setDatabaseCreated();
                        });
                    }
                }).build();
    }

    /**
     * Check whether the database already exists and expose it via {@link #getDatabaseCreated()}
     */
    private void updateDatabaseCreated(final Context context) {
        if (context.getDatabasePath(DATABASE_NAME).exists()) {
            Log.i(TAG, "Database initialized.");
            setDatabaseCreated();
        }
    }

    private void setDatabaseCreated(){
        isDatabaseCreated.postValue(true);
    }

    public LiveData<Boolean> getDatabaseCreated() {
        return isDatabaseCreated;
    }

}
