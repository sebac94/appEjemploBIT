package models;

import android.app.Application;

import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.example.aplicacioncurso2021.entities.Fruta;
import com.example.aplicacioncurso2021.repositories.FrutaRepository;

import java.util.List;

public class FrutaViewModel extends AndroidViewModel {
    private FrutaRepository frutaRepository;
    private final LiveData<List<Fruta>> frutas;

    public FrutaViewModel(Application application){
        super(application);
        frutaRepository = new FrutaRepository(application);
        frutas = frutaRepository.getFrutas();
    }

    public LiveData<List<Fruta>> getFrutas(){
        return frutas;
    }

    public void insert(Fruta fruta){
        frutaRepository.insert(fruta);
    }

    public void update(Fruta fruta){
        frutaRepository.update(fruta);
    }

    public void delete(Fruta fruta){
        frutaRepository.delete(fruta);
    }

}
