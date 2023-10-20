package com.nandodev1997.androidtestfernandomorales.Viewmodel;

import android.content.Context;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

import com.nandodev1997.androidtestfernandomorales.Models.Beer;
import com.nandodev1997.androidtestfernandomorales.Repository.BeerRepository;

import java.util.List;

public class HomeViewModel extends ViewModel {

    private BeerRepository repository;
    private LiveData<List<Beer>> beers;

    public void init(Context context) {
        repository = new BeerRepository(context);
        beers = repository.getBeers();
    }

    public LiveData<List<Beer>> getBeers() {
        return beers;
    }

    public void refreshBeers(int page) {
        repository.fetchBeersFromApi(page);
    }

}
