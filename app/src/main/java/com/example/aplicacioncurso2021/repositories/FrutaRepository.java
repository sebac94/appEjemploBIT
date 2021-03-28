package com.example.aplicacioncurso2021.repositories;

import android.app.Application;

import androidx.lifecycle.LiveData;

import com.example.aplicacioncurso2021.daos.FrutaDao;
import com.example.aplicacioncurso2021.database.AppDatabase;
import com.example.aplicacioncurso2021.entities.Fruta;

import java.util.List;

public class FrutaRepository {
    private FrutaDao frutaDao;

    private LiveData<List<Fruta>> frutas;

    public FrutaRepository(Application application){
        AppDatabase db=AppDatabase.getInstance(application);
        frutaDao=db.frutaDao();
        frutas=frutaDao.getAll();
    }

    public LiveData<List<Fruta>> getFrutas(){
        return frutas;
    }

    public void insert(Fruta fruta){
        AppDatabase.databaseWriteExecutor.execute(() -> {
            frutaDao.insert(fruta);
        });
    }

}
