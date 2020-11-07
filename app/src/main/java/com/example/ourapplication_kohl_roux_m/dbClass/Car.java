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
    public double consoEssence;

    @ColumnInfo(name = "charge_batterie")
    public double batteryPower;

    @ColumnInfo(name = "taille_jante")
    public double wheelSize;

    @ColumnInfo(name = "active")
    public boolean carForTrip;

    public Car(String carTradeMark, String model,  double consoEssence,  double batteryPower, boolean activate) {

        this.carTradeMark = carTradeMark;
        this.model = model;
        this.consoEssence = consoEssence;
        this.batteryPower = batteryPower;
        this.carForTrip = activate;

    }

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

    public double getConsoEssence() {
        return consoEssence;
    }

    public double getBatteryPower() {
        return batteryPower;
    }

    public double getWheelSize() {
        return wheelSize;
    }
}
