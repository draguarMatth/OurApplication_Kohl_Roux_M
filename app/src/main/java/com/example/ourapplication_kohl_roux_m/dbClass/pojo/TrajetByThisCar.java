package com.example.ourapplication_kohl_roux_m.dbClass.pojo;

import androidx.room.Embedded;
import androidx.room.Relation;

import com.example.ourapplication_kohl_roux_m.dbClass.entities.Car;
import com.example.ourapplication_kohl_roux_m.dbClass.entities.Trajet;

import java.util.List;

public class TrajetByThisCar {
    @Embedded
    public Trajet trajet;

    @Relation(parentColumn = "Voiture_id", entityColumn = "uid", entity = Car.class)
    public Car car;
}
