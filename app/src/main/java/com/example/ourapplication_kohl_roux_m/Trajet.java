package com.example.ourapplication_kohl_roux_m;
import android.graphics.Bitmap;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;

@Entity (tableName = "trajets", foreignKeys = @ForeignKey(entity = Car.class, parentColumns = "uid", childColumns = "Voiture_id"))
public class Trajet {

    @PrimaryKey(autoGenerate = true)
    public int uid;

    @ColumnInfo(name = "Nom_trajet")
    public String name;

    @ColumnInfo(name = "Distance")
    public float kmTot;

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


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public float getKmTot() {
        return kmTot;
    }

    public void setKmTot(float kmTot) {
        this.kmTot = kmTot;
    }

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

}
