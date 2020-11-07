package com.example.ourapplication_kohl_roux_m.dbClass.pojo;

import androidx.room.Embedded;
import androidx.room.Relation;

import com.example.ourapplication_kohl_roux_m.dbClass.Car;
import com.example.ourapplication_kohl_roux_m.dbClass.Trajet;

import java.util.List;

public class TrajetByThisCar {
    @Embedded
    public Trajet trajet;

    @Relation(parentColumn = "uid", entityColumn = "Voiture_id", entity = Car.class)

    public List<Car> carList;
}
