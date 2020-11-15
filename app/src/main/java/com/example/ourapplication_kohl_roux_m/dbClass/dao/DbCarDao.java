package com.example.ourapplication_kohl_roux_m.dbClass.dao;

import android.database.sqlite.SQLiteConstraintException;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.example.ourapplication_kohl_roux_m.dbClass.entities.CarEntity;

import java.util.List;

@Dao
public interface DbCarDao {
//    CarEntity carEntity = new CarEntity();

    @Query("SELECT * FROM cars WHERE uid IN (:carIds)")
    LiveData<CarEntity> getById(int [] carIds);

    @Query("SELECT * FROM cars")
    LiveData<List<CarEntity>> getAll();

    @Query("SELECT * FROM cars WHERE active = 1 ")
    LiveData<List<CarEntity>> getByActivity();

/*    @Query("SELECT * FROM carEntity WHERE uid LIKE (:UID) LIMIT 1")
    CarEntity getById(int UID);

    @Query("SELECT * FROM carEntity")
    List<CarEntity> getAllCar();

 */

    @Query("SELECT * FROM cars WHERE uid = (:uid) ")
    CarEntity getCar(int uid);

    @Query("SELECT uid FROM cars WHERE active = 1 ")
    int getActive();

    @Query("SELECT * FROM cars WHERE Nickname LIKE (:name)")
    CarEntity findByNickName(String name);

    @Insert
    void insert(CarEntity carEntity) throws SQLiteConstraintException;

    @Update
    public void update(CarEntity carEntity);

    @Query("DELETE FROM cars")
    void deleteAll();

    @Delete
    void delete(CarEntity carEntity);

}
