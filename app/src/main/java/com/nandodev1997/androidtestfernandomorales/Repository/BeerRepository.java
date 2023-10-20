package com.nandodev1997.androidtestfernandomorales.Repository;

import static com.nandodev1997.androidtestfernandomorales.Api.RetrofitHelper.getClientRetrofit;

import android.content.Context;
import android.os.AsyncTask;
import android.widget.Toast;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.Observer;

import com.nandodev1997.androidtestfernandomorales.Api.ApiBeer;
import com.nandodev1997.androidtestfernandomorales.Database.AppDatabase;
import com.nandodev1997.androidtestfernandomorales.Database.BeerDao;
import com.nandodev1997.androidtestfernandomorales.Models.Beer;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class BeerRepository {
    private ApiBeer apiService;
    private BeerDao beerDao;
    public Context context;
    public BeerRepository(Context context) {
        this.context = context;
        apiService = getClientRetrofit().create(ApiBeer.class);
        beerDao = AppDatabase.getInstance(context).beerDao();
    }


    public LiveData<List<Beer>> getBeers() {
        // Verificar si hay datos en la base de datos
        LiveData<List<Beer>> localData = beerDao.getAllBeers();
        localData.observeForever(new Observer<List<Beer>>() {
            @Override
            public void onChanged(List<Beer> beers) {
                if (beers == null || beers.isEmpty()) {
                    // No hay datos locales, obtener datos de la API
                    fetchBeersFromApi(1);
                }
                localData.removeObserver(this);
            }
        });
        return localData;
    }

    public void fetchBeersFromApi(int page) {
        // Realiza la solicitud a la API y almacena los resultados en la base de datos
        Call<List<Beer>> call = apiService.getListBeers(String.valueOf(page));
        call.enqueue(new Callback<List<Beer>>() {
            @Override
            public void onResponse(Call<List<Beer>> call, Response<List<Beer>> response) {
                if (response.isSuccessful()) {
                    List<Beer> beers = response.body();
                    InsertBeerTask insertTask = new InsertBeerTask(beerDao);
                    insertTask.execute(beers);
                }else {
                    Toast.makeText(context.getApplicationContext(), response.code(), Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<List<Beer>> call, Throwable t) {
                Toast.makeText(context.getApplicationContext(), t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }

    public class InsertBeerTask extends AsyncTask<List<Beer>, Void, Void> {
        private BeerDao beerDao;

        public InsertBeerTask(BeerDao beerDao) {
            this.beerDao = beerDao;
        }

        @Override
        protected Void doInBackground(List<Beer>... beers) {
            beerDao.insertAll(beers[0]);
            return null;
        }
    }
}


