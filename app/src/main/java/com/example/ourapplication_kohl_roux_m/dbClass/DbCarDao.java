package com.example.ourapplication_kohl_roux_m.dbClass;

import android.database.sqlite.SQLiteConstraintException;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

import com.example.ourapplication_kohl_roux_m.dbClass.Car;

import java.util.List;

@Dao
public interface DbCarDao {
//    Car car = new Car();

    @Query("SELECT * FROM car")
    List<Car> getAllCar();

    @Query("SELECT * FROM car WHERE uid IN (:carIds)")
    List<Car> loadAllByIds(int[] carIds);

    @Query("SELECT uid FROM car WHERE active IS 1 ")
    int getActive();

    @Query("SELECT * FROM car WHERE Nickname LIKE (:name) LIMIT 1")
    Car findByNickName(String name);

    @Insert
    void insert(Car car) throws SQLiteConstraintException;

    @Query("DELETE FROM car")
    void deleteAll();

}
