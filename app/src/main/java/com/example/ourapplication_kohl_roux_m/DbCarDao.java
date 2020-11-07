package com.example.ourapplication_kohl_roux_m;

import androidx.room.Dao;
import androidx.room.Query;

import java.util.List;

@Dao
public interface DbCarDao {
    Car car = new Car();

    @Query("SELECT * FROM car")
    List<Car> getAllCar();

    @Query("SELECT * FROM car WHERE uid IN (:carIds)")
    List<Car> loadAllByIds(int[] carIds);

    @Query("SELECT * FROM car WHERE Nickname LIKE (:name) LIMIT 1")
    Car findByNickName(String name);

}
