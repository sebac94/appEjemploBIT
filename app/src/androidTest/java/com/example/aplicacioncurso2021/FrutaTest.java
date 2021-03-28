package com.example.aplicacioncurso2021;

import android.content.Context;

import androidx.room.Room;
import androidx.test.core.app.ApplicationProvider;
import androidx.test.ext.junit.runners.AndroidJUnit4;

import com.example.aplicacioncurso2021.daos.FrutaDao;
import com.example.aplicacioncurso2021.database.AppDatabase;
import com.example.aplicacioncurso2021.entities.Fruta;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.io.IOException;

import static org.junit.Assert.assertTrue;

@RunWith(AndroidJUnit4.class)
public class FrutaTest {
    private FrutaDao frutaDao;
    private AppDatabase appDatabase;

    @Before
    public void createDb(){
        Context context = ApplicationProvider.getApplicationContext();
        appDatabase= Room.inMemoryDatabaseBuilder(context,AppDatabase.class).build();
        frutaDao=appDatabase.frutaDao();
    }

    @After
    public void closeDb() throws IOException{
        appDatabase.close();
    }

    @Test
    public void findByNameTest() throws Exception{
        Fruta fruta=new Fruta();
        fruta.setId(1);
        fruta.setNombre("Naranja");
        frutaDao.insert(fruta);
        Fruta buscada=frutaDao.findByNombre("Naranja");

        assertTrue("No encontrado",fruta.getId()==buscada.getId());
    }
}
