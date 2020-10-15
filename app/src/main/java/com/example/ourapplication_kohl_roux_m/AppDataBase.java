package com.example.ourapplication_kohl_roux_m;

import androidx.room.Database;
import androidx.room.RoomDatabase;

@Database(entities = {Trajet.class, Car.class}, version = 1)
//@Database(entities = {Car.class}, version= 1)
public abstract class AppDataBase extends RoomDatabase {

    public abstract DbTrajetDao trajetDao();
    public abstract DbCarDao carDao();
}
