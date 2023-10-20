package com.nandodev1997.androidtestfernandomorales.Database;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import com.nandodev1997.androidtestfernandomorales.Models.Beer;

@Database(entities = {Beer.class}, version = 1)
public abstract class AppDatabase extends RoomDatabase {
    public abstract BeerDao beerDao();

    private static volatile AppDatabase INSTANCE;

    public static AppDatabase getInstance(Context context) {
        if (INSTANCE == null) {
            synchronized (AppDatabase.class) {
                if (INSTANCE == null) {
                    INSTANCE = Room.databaseBuilder(context.getApplicationContext(), AppDatabase.class, "beer_database")
                            .build();
                }
            }
        }
        return INSTANCE;
    }
}