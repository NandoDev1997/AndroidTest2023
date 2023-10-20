package com.nandodev1997.androidtestfernandomorales.Database;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Update;

import com.nandodev1997.androidtestfernandomorales.Models.Beer;

import java.util.List;

@Dao
public interface BeerDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertBeer(Beer beer);

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertAll(List<Beer> beers);

    @Update
    void updateBeer(Beer beer);

    @Delete
    void deleteBeer(Beer beer);

    @Query("SELECT * FROM beer")
    LiveData<List<Beer>> getAllBeers();

    @Query("SELECT * FROM beer WHERE id = :beerId")
    Beer getBeerById(int beerId);
}
