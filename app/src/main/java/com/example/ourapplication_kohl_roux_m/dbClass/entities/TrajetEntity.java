package com.example.ourapplication_kohl_roux_m.dbClass.entities;
import android.graphics.Bitmap;
import android.os.Parcel;
import android.os.Parcelable;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;

@Entity (tableName = "trajets", foreignKeys = @ForeignKey(entity = CarEntity.class, parentColumns = "uid", childColumns = "Voiture_id"))
public class TrajetEntity implements Comparable, Parcelable {

    @PrimaryKey(autoGenerate = true)
    public long uid;

    @ColumnInfo(name="Voiture_id")
    @NonNull
    public long carId ;

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

/*    public TrajetEntity(int carId, String date) {
        this.carId = carId;
        this.date = date;
    }
*/
    private TrajetEntity () {};

    public TrajetEntity(@NonNull long carId, String name, @NonNull String date, double kmTot,
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

    public long getUid() { return uid; }

//    public int getUid() { return uid; }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public long getCarId() {
        return carId;
    }

    public double getKmTot() {
        return kmTot;
    }

    public String getDate() {return date;}

    public double getTotRise() {
        return totRise;
    }

    public double getTotDeep() {
        return totDeep;
    }

    public double getGasolinTot() {
        return gasolinTot;
    }

    public void addGasolin(double gasolinTot) {
        this.gasolinTot += gasolinTot;
    }

    public void removeGasolin(double gasolinTot) {
        this.gasolinTot -= gasolinTot;
    }

    public double getElectricityTot() {
        return electricityTot;
    }

    public void addElectricity(double electricityTot) {
        this.electricityTot += electricityTot;
    }

    public void removeElectricity(double electricityTot) { this.electricityTot -= electricityTot;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) return false;
        if (obj == this) return true;
        if (!(obj instanceof TrajetEntity)) return false;
        TrajetEntity o = (TrajetEntity) obj;
        if (o.getUid() != this.getUid() && o.getCarId() != this.getCarId()) return false;
        return true ;
    }

    public void setUid(long uid) {
        this.uid = uid;
    }

    public void setCarId(long carId) {
        this.carId = carId;
    }

    public void setDate(@NonNull String date) {
        this.date = date;
    }

    public void setKmTot(double kmTot) {
        this.kmTot = kmTot;
    }

    public void setTotRise(double totRise) {
        this.totRise = totRise;
    }

    public void setTotDeep(double totDeep) {
        this.totDeep = totDeep;
    }

    public void setGasolinTot(double gasolinTot) {
        this.gasolinTot = gasolinTot;
    }

    public void setElectricityTot(double electricityTot) {
        this.electricityTot = electricityTot;
    }

    @Override
    public String toString() {
        return uid + " / " + carId + " / " + name + " / " + date;
    }

    @Override
    public int compareTo(@NonNull Object o) {
        return toString().compareTo(o.toString());
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        TrajetEntity trajet = this;
        dest.writeString(trajet.name);
        dest.writeString(trajet.date);
        dest.writeString(String.valueOf(trajet.carId));
        dest.writeString(String.valueOf(trajet.uid));
        dest.writeString(String.valueOf(trajet.electricityTot));
        dest.writeString(String.valueOf(trajet.gasolinTot));
        dest.writeString(String.valueOf(trajet.kmTot));
        dest.writeString(String.valueOf(trajet.totDeep));
        dest.writeString(String.valueOf(trajet.totRise));


    }

    public void getFromParcel(Parcel in)
    {
        TrajetEntity trajet = new TrajetEntity();
        trajet.setName(in.readString());
        trajet.setDate(in.readString());
        trajet.setCarId(Long.parseLong(in.readString()));
        trajet.setUid(Long.parseLong(in.readString()));
        trajet.setElectricityTot(Double.parseDouble(in.readString()));
        trajet.setGasolinTot(Double.parseDouble(in.readString()));
        trajet.setKmTot(Double.parseDouble(in.readString()));
        trajet.setTotDeep(Double.parseDouble(in.readString()));
        trajet.setTotRise(Double.parseDouble(in.readString()));

    }
}
