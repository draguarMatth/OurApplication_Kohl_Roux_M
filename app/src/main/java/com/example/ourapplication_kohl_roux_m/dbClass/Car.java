package com.example.ourapplication_kohl_roux_m.dbClass;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import java.util.Objects;

@Entity(tableName = "voitures")
public class Car implements Comparable {

    @PrimaryKey(autoGenerate = true)
    public Long uid;

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

    public Car(){}
    public Car(String nickName, @NonNull String carTradeMark, @NonNull String model, float consoEssence, float batteryPower, float wheelSize) {

        this.nickName = nickName;
        this.carTradeMark = carTradeMark;
        this.model = model;
        this.consoEssence = consoEssence;
        this.batteryPower = batteryPower;
        this.wheelSize = wheelSize;
    }

    public Long getUid() {
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Car car = (Car) o;
        return uid == car.uid &&
                Float.compare(car.batteryPower, batteryPower) == 0 &&
                Float.compare(car.wheelSize, wheelSize) == 0 &&
                nickName.equals(car.nickName) &&
                carTradeMark.equals(car.carTradeMark) &&
                model.equals(car.model);
    }

    @Override
    public int compareTo(@NonNull Object o) {
        return toString().compareTo(o.toString());
    }

    @Override
    public String toString()
    {
        return uid + "/" + model + "/" + nickName;
    }


}
