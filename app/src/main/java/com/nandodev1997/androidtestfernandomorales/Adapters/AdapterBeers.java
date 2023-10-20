package com.nandodev1997.androidtestfernandomorales.Adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.nandodev1997.androidtestfernandomorales.Models.Beer;
import com.nandodev1997.androidtestfernandomorales.R;
import com.nandodev1997.androidtestfernandomorales.databinding.ItemBeerBinding;

import java.util.List;

public class AdapterBeers extends RecyclerView.Adapter<AdapterBeers.ViewHolderVersions> {
    private final Context context;
    private List<Beer> data;
    private View.OnClickListener listener;

    public List<Beer> getData() {
        return data;
    }

    public void setData(List<Beer> data) {
        this.data = data;
    }

    public AdapterBeers(List<Beer> datos, Context context) {
        this.data = datos;
        this.context = context;
    }

    @NonNull
    @Override
    public ViewHolderVersions onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        ItemBeerBinding binding = ItemBeerBinding.inflate(LayoutInflater.from(parent.getContext()), parent, false);
        return new ViewHolderVersions(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolderVersions holder, int position) {
        holder.asignarNombreVersion(data.get(position));
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (listener != null) {
                    listener.onClick(v);
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    public void setOnClickListener(View.OnClickListener listener) {
        this.listener = listener;
    }

    public void setBeers(List<Beer> beers) {
        this.data = beers;
    }

    public class ViewHolderVersions extends RecyclerView.ViewHolder {
        ItemBeerBinding binding;

        public ViewHolderVersions(ItemBeerBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }

        public void asignarNombreVersion(Beer beer) {
            RequestOptions options = new RequestOptions()
                    .centerInside()
                    .placeholder(R.drawable.loading)
                    .error(R.drawable.error_img);
            Glide.with(context).load(beer.getImage_url()).apply(options).into(binding.imagebeer);
            binding.name.setText(beer.getName());
            binding.tagline.setText(beer.getTagline());
        }
    }
}