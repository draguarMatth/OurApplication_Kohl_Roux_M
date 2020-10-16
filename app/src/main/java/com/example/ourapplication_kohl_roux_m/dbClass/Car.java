package com.example.ourapplication_kohl_roux_m.dbClass;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity
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

    public int getUid() {
        return uid;
    }

    public String getNickName() {
        return nickName;
    }

    @NonNull
    public String getCarTradeMark() {
        return carTradeMark;
    }

    @NonNull
    public String getModel() {
        return model;
    }

    public float getConsoEssence() {
        return consoEssence;
    }

    public float getBatteryPower() {
        return batteryPower;
    }

    public float getWheelSize() {
        return wheelSize;
    }
}
