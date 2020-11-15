package com.example.ourapplication_kohl_roux_m.dbClass.dao;

import android.database.sqlite.SQLiteConstraintException;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.example.ourapplication_kohl_roux_m.dbClass.entities.TrajetEntity;
import com.example.ourapplication_kohl_roux_m.dbClass.pojo.TrajetByThisCar;

import java.util.List;

@Dao
public interface DbTrajetDao {
//    TrajetEntity trajetEntity = new TrajetEntity();

    @Query("SELECT * FROM trajets WHERE Date LIKE (:date)")
    LiveData<List<TrajetEntity>> getByDate(String date);

    @Query("SELECT * FROM trajets WHERE Nom_trajet LIKE (:name)")
    /* LiveData<TrajetEntity> */ LiveData<List<TrajetEntity>> getByName(String name);

    @Query("SELECT * FROM trajets WHERE Voiture_id = (:carId)")
        /* LiveData<TrajetEntity> */ LiveData<List<TrajetByThisCar>> getByCarId(int carId);

    @Query("SELECT * FROM trajets")
    LiveData<List<TrajetEntity>> getAll();

    @Query("SELECT * FROM trajets")
    List<TrajetEntity> getAllTrajets();

    @Query("SELECT * FROM trajets WHERE Voiture_id = :carId Limit 1")
    List<TrajetEntity> getAllTrajetsByCar(String carId);

    @Query("SELECT * FROM trajets WHERE uid IN (:trajetIds)")
    List<TrajetEntity> loadAllByIds(int[] trajetIds);

    @Query("SELECT * FROM trajets WHERE Nom_trajet LIKE (:name) LIMIT 1")
    TrajetEntity findByName(String name);

    @Query("SELECT * FROM trajets WHERE Date LIKE (:date) LIMIT 1")
    TrajetEntity findByDate(String date);

    @Query("SELECT * FROM trajets WHERE Distance LIKE :km LIMIT 1")
    TrajetEntity findByKilometers(double km);

    @Query("SELECT * FROM trajets WHERE Denivellation_positif LIKE :rise LIMIT 1")
    TrajetEntity findByRising(double rise);

    @Query("SELECT * FROM trajets WHERE Denivellation_positif <= :rise LIMIT 1")
    TrajetEntity findByIntervalRising(double rise);

    @Query("SELECT * FROM trajets WHERE Denivellation_negatif LIKE :deep LIMIT 1")
    TrajetEntity findByDeep(double deep);

    @Query("SELECT * FROM trajets WHERE Denivellation_negatif LIKE :deep LIMIT 1")
    TrajetEntity findByIntevalDeep(double deep);

    @Insert
    void insertAll(TrajetEntity... trajets);

    @Insert
    void insert(TrajetEntity trajetEntity) throws SQLiteConstraintException;

    @Update
    public void update(TrajetEntity trajets);

    @Update
    public void updateConsoGasolin(TrajetEntity trajetEntity);
    @Update
    public void updateConsoElectric(TrajetEntity trajetEntity);

    @Delete
    void delete(TrajetEntity trajetEntity);

    @Query("DELETE FROM trajets")
    void deleteAll();

}
