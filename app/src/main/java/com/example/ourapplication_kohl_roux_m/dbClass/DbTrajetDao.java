package com.example.ourapplication_kohl_roux_m;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

@Dao
public interface DbTrajetDao {
    Trajet trajet = new Trajet();

    @Query("SELECT * FROM trajets")
    List<Trajet> getAllTrajets();

    @Query("SELECT * FROM trajets WHERE Voiture_id LIKE :carId Limit 1")
    List<Trajet> getAllTrajetsByCar(String carId);

    @Query("SELECT * FROM trajets WHERE uid IN (:trajetIds)")
    List<Trajet> loadAllByIds(int[] trajetIds);

    @Query("SELECT * FROM trajets WHERE Nom_trajet LIKE (:name) LIMIT 1")
    Trajet findByName(String name);

    @Query("SELECT * FROM trajets WHERE Date LIKE (:date) LIMIT 1")
    Trajet findByDate(String date);

    @Query("SELECT * FROM trajets WHERE Distance LIKE :km LIMIT 1")
    Trajet findByKilometers(float km);

    @Query("SELECT * FROM trajets WHERE Denivellation_positif LIKE :rise LIMIT 1")
    Trajet findByRising(float rise);

    @Query("SELECT * FROM trajets WHERE Denivellation_positif <= :rise LIMIT 1")
    Trajet findByIntervalRising(float rise);

    @Query("SELECT * FROM trajets WHERE Denivellation_negatif LIKE :deep LIMIT 1")
    Trajet findByDeep(float deep);

    @Query("SELECT * FROM trajets WHERE Denivellation_negatif LIKE :deep LIMIT 1")
    Trajet findByIntevalDeep(float deep);

    @Insert
    void insertAll(Trajet... trajets);

    @Update
    public void update(Trajet trajets);

    @Update
    public void updateConsoGasolin(Trajet trajet);
    @Update
    public void updateConsoElectric(Trajet trajet);

    @Delete
    void delete(Trajet trajet);
}
