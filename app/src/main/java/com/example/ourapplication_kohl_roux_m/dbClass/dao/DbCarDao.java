package com.example.ourapplication_kohl_roux_m.dbClass.dao;

import android.database.sqlite.SQLiteConstraintException;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.example.ourapplication_kohl_roux_m.dbClass.entities.Car;
import com.example.ourapplication_kohl_roux_m.dbClass.entities.Trajet;

import java.util.List;

@Dao
public interface DbCarDao {
//    Car car = new Car();

    @Query("SELECT * FROM car WHERE uid LIKE (:id) LIMIT 1")
    LiveData<Car> getById(int id);

    @Query("SELECT * FROM car")
    LiveData<List<Car>> getAll();

    @Query("SELECT * FROM car WHERE active = 1 ")
    LiveData<List<Car>> getByActivity();

/*    @Query("SELECT * FROM car WHERE uid LIKE (:UID) LIMIT 1")
    Car getById(int UID);

    @Query("SELECT * FROM car")
    List<Car> getAllCar();

 */

    @Query("SELECT * FROM car WHERE uid = (:uid) ")
    Car getCar(int uid);

    @Query("SELECT * FROM car WHERE uid IN (:carIds)")
    List<Car> loadAllByIds(int[] carIds);

    @Query("SELECT uid FROM car WHERE active = 1 ")
    int getActive();

    @Query("SELECT * FROM car WHERE Nickname LIKE (:name)")
    Car findByNickName(String name);

    @Insert
    void insert(Car car) throws SQLiteConstraintException;

    @Update
    public void update(Car car);

    @Query("DELETE FROM car")
    void deleteAll();

    @Delete
    void delete(Car car);

}
