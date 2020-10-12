package com.example.ourapplication_kohl_roux_m;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

@Dao
public interface DbTrajetDao {

    @Query("SELECT * FROM trajet")
    List<Trajet> getAllTrajets();

    @Query("SELECT * FROM trajet WHERE carName LIKE :carName Limit 1")
    List<Trajet> getAllTrajetsByCar(String carName);

    @Query("SELECT * FROM trajet WHERE uid IN (:trajetIds)")
    List<Trajet> loadAllByIds(int[] trajetIds);

    @Query("SELECT * FROM trajet WHERE name LIKE (:name) LIMIT 1")
    Trajet findByName(String name);

    @Query("SELECT * FROM trajet WHERE totRise LIKE :rise LIMIT 1")
    Trajet findByRising(float rise);

    @Query("SELECT * FROM trajet WHERE totRise BETWEEN :rise LIMIT 1")
    Trajet findByIntervalRising(float rise);

    @Query("SELECT * FROM trajet WHERE totDeep LIKE :deep LIMIT 1")
    Trajet findByDeep(float deep);

    @Query("SELECT * FROM trajet WHERE totDeep LIKE :deep LIMIT 1")
    Trajet findByIntevalDeep(float deep);

    @Insert
    void insertAll(Trajet... trajets);

    @Update
    public void updateTrajet(Trajet... trajets);

    @Delete
    void delete(Trajet trajet);
}
