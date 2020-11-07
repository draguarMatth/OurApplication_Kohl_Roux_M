package com.example.ourapplication_kohl_roux_m;

import android.database.sqlite.SQLiteConstraintException;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;

import java.util.List;

@Dao
public interface DbCarDao {
//    Car car = new Car();

    @Query("SELECT * FROM car WHERE uid LIKE (:UID) LIMIT 1")
    Car getById(int UID);

    @Query("SELECT * FROM car")
    List<Car> getAllCar();

    @Query("SELECT * FROM car WHERE uid IN (:carIds)")
    List<Car> loadAllByIds(int[] carIds);

    @Query("SELECT uid FROM car WHERE active IS 1 ")
    int getActive();

    @Query("SELECT * FROM car WHERE Nickname LIKE (:name)")
    Car findByNickName(String name);

    @Insert
    void insert(Car car) throws SQLiteConstraintException;

    @Query("DELETE FROM car")
    void deleteAll();

    @Delete
    void delete(Car car);

}
