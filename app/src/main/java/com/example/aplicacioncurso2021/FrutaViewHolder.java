package com.example.aplicacioncurso2021;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

public class FrutaViewHolder extends RecyclerView.ViewHolder {
    private final TextView nombreItemView;
    private final TextView descripcionItemView;

    private FrutaViewHolder(View itemView){
        super(itemView);
        nombreItemView =itemView.findViewById(R.id.textViewNombre);
        descripcionItemView =itemView.findViewById(R.id.textViewDescripcion);
    }

    public void bind(String nombre, String descripcion){
        nombreItemView.setText(nombre);
        descripcionItemView.setText(descripcion);
    }

    public static FrutaViewHolder create(ViewGroup parent){
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.fruta_item,parent,false);
        return new FrutaViewHolder(view);
    }

}
