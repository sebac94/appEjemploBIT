package com.example.aplicacioncurso2021.repositories;

import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.ListAdapter;

import com.example.aplicacioncurso2021.FrutaViewHolder;
import com.example.aplicacioncurso2021.entities.Fruta;

import java.util.List;

import models.FrutaViewModel;

public class FrutaListAdapter extends ListAdapter<Fruta, FrutaViewHolder> {

    public FrutaListAdapter(@NonNull DiffUtil.ItemCallback<Fruta> diffCallbak){
        super(diffCallbak);
    }

    @NonNull
    @Override
    public FrutaViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return FrutaViewHolder.create(parent);
    }

    @Override
    public void onBindViewHolder(@NonNull FrutaViewHolder holder, int position) {
        Fruta frutaActual=getItem(position);
        holder.bind(frutaActual.getNombre());
    }

    public static class FrutaDiff extends DiffUtil.ItemCallback<Fruta>{
        @Override
        public boolean areItemsTheSame(@NonNull Fruta oldItem, @NonNull Fruta newItem) {
            return oldItem.getId()==newItem.getId();
        }

        @Override
        public boolean areContentsTheSame(@NonNull Fruta oldItem, @NonNull Fruta newItem) {
            return oldItem.getNombre().equals(newItem.getNombre());
        }
    }
}
