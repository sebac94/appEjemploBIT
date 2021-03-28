package com.example.aplicacioncurso2021.database;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import com.example.aplicacioncurso2021.daos.FrutaDao;
import com.example.aplicacioncurso2021.entities.Fruta;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@Database(entities = {Fruta.class}, version=4)
public abstract class AppDatabase extends RoomDatabase {
    public abstract FrutaDao frutaDao();

    private static volatile AppDatabase instance;
    public static final ExecutorService databaseWriteExecutor = Executors.newFixedThreadPool(4);
    public static AppDatabase getInstance(final Context context){
        if(instance == null){
            instance = Room.databaseBuilder(context.getApplicationContext(), AppDatabase.class, "fruteria")
                    .fallbackToDestructiveMigration()
                    .build();
        }
        return instance;
    }
}
