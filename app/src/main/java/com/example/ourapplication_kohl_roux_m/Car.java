package com.example.ourapplication_kohl_roux_m;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.PrimaryKey;

public class Car {

    @PrimaryKey(autoGenerate = true)
    public int uid;

    @ColumnInfo(name="Nickname")
    public String nickName;

    @ColumnInfo(name="Marque")
    @NonNull
    public String carTradeMark;

    @ColumnInfo(name = "model")
    @NonNull
    public String model;

    @ColumnInfo(name = "conso_essence")
    @NonNull
    public float consoEssence;

    @ColumnInfo(name = "charge_batterie")
    public float batteryPower;

    @ColumnInfo(name = "taille_jante")
    public float wheelSize;

}
