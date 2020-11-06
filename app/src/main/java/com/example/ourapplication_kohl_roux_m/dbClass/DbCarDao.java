package com.example.ourapplication_kohl_roux_m.dbClass;

import android.database.sqlite.SQLiteConstraintException;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.example.ourapplication_kohl_roux_m.dbClass.Car;

import java.util.List;

@Dao
public interface DbCarDao {
    Car car = new Car();

    @Query("SELECT * FROM voitures")
    List<Car> getAllCar();

    @Query("SELECT * FROM voitures WHERE uid IN (:carIds)")
    List<Car> loadAllByIds(int[] carIds);

    @Query("SELECT * FROM voitures WHERE Nickname LIKE (:name) LIMIT 1")
    Car findByNickName(String name);

    @Insert
    void insert(Car car) throws SQLiteConstraintException;

    /*
    @Update
    void update(Car car);

     */

    @Delete
    void delete(Car car);

    @Query("DELETE FROM voitures")
    void deleteAll();

}
