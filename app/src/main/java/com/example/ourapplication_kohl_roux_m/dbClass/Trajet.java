package com.example.ourapplication_kohl_roux_m.dbClass;
import android.graphics.Bitmap;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;

import com.example.ourapplication_kohl_roux_m.dbClass.Car;

@Entity (tableName = "trajets", foreignKeys = @ForeignKey(entity = Car.class, parentColumns = "uid", childColumns = "Voiture_id"))
public class Trajet {

    @PrimaryKey(autoGenerate = true)
    public int uid;

    @ColumnInfo(name="Voiture_id")
    @NonNull
    public int carId;

    @ColumnInfo(name = "Nom_trajet")
    public String name;

    @ColumnInfo(name = "Date")
    @NonNull
    public String date;

    @ColumnInfo(name = "Distance")
    public double kmTot;

    @ColumnInfo(name = "Denivellation_positif")
    public double totRise;

    @ColumnInfo(name = "Denivellation_negatif")
    public double totDeep;

    @ColumnInfo(name = "Consommation_Essence")
    public double gasolinTot;

    @ColumnInfo(name = "Recharge_electrique")
    public double electricityTot;

    @Ignore
    Bitmap picture;

/*    public Trajet(int carId, String date) {
        this.carId = carId;
        this.date = date;
    }
*/
    public Trajet(@NonNull int carId, String name, @NonNull String date, double kmTot,
                  double totRise, double totDeep, double gasolinTot, double electricityTot) {
        this.carId = carId;
        this.name = name;
        this.kmTot = kmTot;
        this.date = date;
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

    public int getCarId() {
        return carId;
    }

    public double getKmTot() {
        return kmTot;
    }

    public void setKmTot(float kmTot) {
        this.kmTot = kmTot;
    }

    public String getDate() {return date;}

    public double getTotRise() {
        return totRise;
    }

    public void setTotRise(float totRise) {
        this.totRise = totRise;
    }

    public double getTotDeep() {
        return totDeep;
    }

    public void setTotDeep(float totDeep) {
        this.totDeep = totDeep;
    }

    public double getGasolinTot() {
        return gasolinTot;
    }

    public void addGasolin(float gasolinTot) {
        this.gasolinTot += gasolinTot;
    }

    public void removeGasolin(float gasolinTot) {
        this.gasolinTot -= gasolinTot;
    }

    public double getElectricityTot() {
        return electricityTot;
    }

    public void addElectricity(float electricityTot) {
        this.electricityTot += electricityTot;
    }

    public void removeElectricity(float electricityTot) { this.electricityTot -= electricityTot;
    }

}
