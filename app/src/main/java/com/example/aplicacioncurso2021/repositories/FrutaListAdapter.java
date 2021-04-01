package com.example.aplicacioncurso2021.repositories;

import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.ListAdapter;

import com.example.aplicacioncurso2021.FrutaViewHolder;
import com.example.aplicacioncurso2021.R;
import com.example.aplicacioncurso2021.entities.Fruta;

import java.util.List;

import models.FrutaViewModel;

public class FrutaListAdapter extends ListAdapter<Fruta, FrutaViewHolder> {

    private  OnItemClickListener listener;

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
        holder.bind(frutaActual.getNombre(),frutaActual.getDescripcion());
        ImageButton deleteButton=holder.itemView.findViewById(R.id.imageButtonDelete);
        deleteButton.setOnClickListener(view -> {
            if(listener!=null){
                listener.onItemDelete(frutaActual);
            }
        });

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(listener!=null){
                    listener.onItemClick(frutaActual);
                }
            }
        });
    }

    public static class FrutaDiff extends DiffUtil.ItemCallback<Fruta>{
        @Override
        public boolean areItemsTheSame(@NonNull Fruta oldItem, @NonNull Fruta newItem) {
            return oldItem.getId()==newItem.getId();
        }

        @Override
        public boolean areContentsTheSame(@NonNull Fruta oldItem, @NonNull Fruta newItem) {
            if(oldItem.getNombre()!=null){
                return oldItem.getNombre().equals(newItem.getNombre()) && oldItem.getDescripcion().equals(newItem.getDescripcion());
            }else{
                return false;
            }

        }
    }

    public interface OnItemClickListener{
        void onItemDelete(Fruta fruta);
        void onItemClick(Fruta fruta);
    }

    public void setOnItemClickListener(OnItemClickListener listener){
        this.listener=listener;
    }


}
