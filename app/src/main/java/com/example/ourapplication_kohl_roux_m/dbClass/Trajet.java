package com.example.ourapplication_kohl_roux_m.dbClass;
import android.graphics.Bitmap;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;

import com.example.ourapplication_kohl_roux_m.dbClass.Car;

import java.text.DateFormat;
import java.util.Date;
import java.util.Objects;

@Entity (tableName = "trajets", foreignKeys = @ForeignKey(entity = Car.class, parentColumns = "uid", childColumns = "Voiture_id"))
public class Trajet implements Comparable {

    @PrimaryKey(autoGenerate = true)
    public Long uid;

    @ColumnInfo(name="Voiture_id")
    public Long carId;

    @ColumnInfo(name = "Nom_trajet")
    public String name;

    @ColumnInfo(name = "Distance")
    public float kmTot;

    @ColumnInfo(name = "Date")
    public String date;

    @ColumnInfo(name = "Denivellation_positif")
    public float totRise;

    @ColumnInfo(name = "Denivellation_negatif")
    public float totDeep;

    @ColumnInfo(name = "Consommation_Essence")
    public float gasolinTot;

    @ColumnInfo(name = "Recharge_electrique")
    public float electricityTot;

    @Ignore
    Bitmap picture;

    public Trajet (){

        this.date = DateFormat.getDateTimeInstance(DateFormat.SHORT, DateFormat.SHORT).format(new Date());
    }

    public Trajet (Long carId, String name, float kmTot,float totRise, float totDeep, float gasolinTot, float electricityTot) {

        this.carId = carId;
        this.name = name;
        this.date = DateFormat.getDateTimeInstance(DateFormat.SHORT, DateFormat.SHORT).format(new Date());
        this.kmTot = kmTot;
        this.totRise = totRise;
        this.totDeep = totDeep;
        this.gasolinTot = gasolinTot;
        this.electricityTot = electricityTot;

    }


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Long getCarId() {
        return carId;
    }

    public float getKmTot() {
        return kmTot;
    }

    public void setKmTot(float kmTot) {
        this.kmTot = kmTot;
    }

    public String getDate() {return date;}

    public float getTotRise() {
        return totRise;
    }

    public void setTotRise(float totRise) {
        this.totRise = totRise;
    }

    public float getTotDeep() {
        return totDeep;
    }

    public void setTotDeep(float totDeep) {
        this.totDeep = totDeep;
    }

    public float getGasolinTot() {
        return gasolinTot;
    }

    public void addGasolin(float gasolinTot) {
        this.gasolinTot += gasolinTot;
    }

    public void removeGasolin(float gasolinTot) {
        this.gasolinTot -= gasolinTot;
    }

    public float getElectricityTot() {
        return electricityTot;
    }

    public void addElectricity(float electricityTot) {
        this.electricityTot += electricityTot;
    }

    public void removeElectricity(float electricityTot) { this.electricityTot -= electricityTot;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Trajet trajet = (Trajet) o;
        return carId == trajet.carId &&
                Float.compare(trajet.kmTot, kmTot) == 0 &&
                Float.compare(trajet.totRise, totRise) == 0 &&
                Float.compare(trajet.totDeep, totDeep) == 0 &&
                Float.compare(trajet.gasolinTot, gasolinTot) == 0 &&
                Float.compare(trajet.electricityTot, electricityTot) == 0 &&
                name.equals(trajet.name) &&
                date.equals(trajet.date);
    }

    @Override
    public int hashCode() {
        return Objects.hash(carId, name, kmTot, date, totRise, totDeep, gasolinTot, electricityTot);
    }

    @Override
    public int compareTo(@NonNull Object o) {
        return toString().compareTo(o.toString());
    }

    @Override
    public String toString()
    {
        return uid + "/" + carId + "/" + date;
    }
}
