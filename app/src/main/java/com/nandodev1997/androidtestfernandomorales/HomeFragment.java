package com.nandodev1997.androidtestfernandomorales;

import android.app.AlertDialog;
import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.nandodev1997.androidtestfernandomorales.Adapters.AdapterBeers;
import com.nandodev1997.androidtestfernandomorales.Models.Beer;
import com.nandodev1997.androidtestfernandomorales.Viewmodel.HomeViewModel;
import com.nandodev1997.androidtestfernandomorales.databinding.FragmentHomeBinding;

import java.util.ArrayList;

public class HomeFragment extends Fragment {
    FragmentHomeBinding binding;
    private HomeViewModel viewModel;
    private AdapterBeers adapter;
    private boolean isScrolling;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        binding = FragmentHomeBinding.inflate(getLayoutInflater());
        adapter = new AdapterBeers(new ArrayList<>(),getActivity());
        adapter.setOnClickListener(view -> {
            Beer beer = adapter.getData().get(binding.recyclerBeer.getChildAdapterPosition(view));
            showBeerDetailsDialog(getActivity(),beer);
        });

        viewModel = new ViewModelProvider(this).get(HomeViewModel.class);
        viewModel.init(getActivity());
        viewModel.getBeers().observe(getViewLifecycleOwner(), beers -> {
            // Actualizar la interfaz de usuario con los datos
            adapter.setBeers(beers);
            adapter.notifyDataSetChanged();
            Log.e("BEERS COUNT", String.valueOf(beers.size()));
            showEmptyView(false);
            showProgressLoading(false);
            showRecycler(true);
        });

        // Si ya tienes datos en la base de datos Room, se mostrarán automáticamente debido a la observación.

        // Para obtener datos nuevos de la API:
        return binding.getRoot();
    }
    int i = 1;
    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        binding.recyclerBeer.setAdapter(adapter);
        binding.recyclerBeer.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(@NonNull RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);
                if (newState == AbsListView.OnScrollListener.SCROLL_STATE_TOUCH_SCROLL) {
                    isScrolling = true;
                }
            }

            @Override
            public void onScrolled(@NonNull RecyclerView recyclerView, int dx, int dy) {
                LinearLayoutManager layoutManager = (LinearLayoutManager) binding.recyclerBeer.getLayoutManager();
                int currentItems = layoutManager.getChildCount();
                int totalItems = adapter.getItemCount();
                int scrollOutItems = layoutManager.findFirstVisibleItemPosition();
                if (isScrolling && (currentItems + scrollOutItems == totalItems)) {
                    showProgressLoading(true);
                    i++;
                    getData(i);
                }
                super.onScrolled(recyclerView, dx, dy);
            }
        });
    }

    void setErrorEmptyView(String error) {
        binding.emptyView.setText(error);
    }

    private void getData(int page ) {
        viewModel.refreshBeers(page);
        showProgressLoading(false);
    }
    void showEmptyView(boolean show) {
        if (show) {
            binding.emptyView.setVisibility(View.VISIBLE);
        } else {
            binding.emptyView.setVisibility(View.GONE);
        }
    }
    void showRecycler(boolean show) {
        if (show) {
            binding.recyclerBeer.setVisibility(View.VISIBLE);
        } else {
            binding.recyclerBeer.setVisibility(View.GONE);
        }
    }

    void showProgressLoading(boolean show) {
        if (show) {
            binding.progress.setVisibility(View.VISIBLE);
        } else {
            binding.progress.setVisibility(View.GONE);
        }
    }


    public void showBeerDetailsDialog(Context context, Beer beer) {
        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        LayoutInflater inflater = getLayoutInflater();
        View dialogView = inflater.inflate(R.layout.beers_details_dialog, null);
        builder.setView(dialogView);

        ImageView imageView = dialogView.findViewById(R.id.imageView);
        TextView nameTextView = dialogView.findViewById(R.id.nameTextView);
        TextView taglineTextView = dialogView.findViewById(R.id.taglineTextView);
        TextView label2 = dialogView.findViewById(R.id.descTextView);
        TextView label3 = dialogView.findViewById(R.id.brewTextView);
        Button btnaceptar = dialogView.findViewById(R.id.btnacceptdetails);
        // Agrega más vistas según sea necesario para otros campos

        nameTextView.setText(beer.getName());
        taglineTextView.setText(beer.getTagline());
        label2.setText(beer.getDescription());
        label3.setText(beer.getBrewers_tips());
        // Configura otras vistas con los datos de Beer

        // Carga la imagen utilizando una biblioteca de carga de imágenes (Glide, Picasso, etc.)
        String imageUrl = beer.getImage_url();
        Glide.with(context).load(imageUrl).into(imageView);
        AlertDialog a = builder.create();
        btnaceptar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                a.cancel();
            }
        });
        a.show();
    }


}