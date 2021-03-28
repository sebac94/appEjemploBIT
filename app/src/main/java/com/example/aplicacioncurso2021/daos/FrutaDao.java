package com.example.aplicacioncurso2021.daos;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;;
import androidx.room.Update;

import com.example.aplicacioncurso2021.entities.Fruta;

import java.util.List;

@Dao
public interface FrutaDao {
    @Query("SELECT * FROM fruta")
    LiveData<List<Fruta>> getAll();

    @Insert
    void insert(Fruta fruta);

    @Update
    void update(Fruta fruta);

    @Delete
    void delete(Fruta fruta);

    @Query("SELECT * FROM FRUTA WHERE NOMBRE LIKE :nombre")
    Fruta findByNombre(String nombre);

    @Query("SELECT * FROM FRUTA WHERE ID LIKE :id")
    Fruta findByiD(int id);

}
