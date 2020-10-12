package com.example.ourapplication_kohl_roux_m;

import androidx.room.Database;
import androidx.room.RoomDatabase;

@Database(entities = {Trajet.class}, version = 1)
public abstract class AppDataBase extends RoomDatabase {

    public abstract DbTrajetDao trajetDao();
}
