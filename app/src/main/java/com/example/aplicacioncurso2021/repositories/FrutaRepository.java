package com.example.aplicacioncurso2021.repositories;

import android.app.Application;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.lifecycle.LiveData;

import com.example.aplicacioncurso2021.daos.FrutaDao;
import com.example.aplicacioncurso2021.database.AppDatabase;
import com.example.aplicacioncurso2021.entities.Fruta;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class FrutaRepository {
    private FrutaDao frutaDao;

    private LiveData<List<Fruta>> frutas;

    public FrutaRepository() {

    }

    public FrutaRepository(Application application){
        AppDatabase db=AppDatabase.getInstance(application);
        frutaDao=db.frutaDao();
        frutas=frutaDao.getAll();
    }

    public LiveData<List<Fruta>> getFrutas(){
       /* FirebaseFirestore db = FirebaseFirestore.getInstance();
        List<Fruta> listado = new ArrayList<Fruta>();

        db.collection("conjuntoB")
                .get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        if (task.isSuccessful()) {
                            for (QueryDocumentSnapshot document : task.getResult()) {

                                DocumentReference docRef = db.collection("conjuntoB").document();
                                docRef.get().addOnSuccessListener(new OnSuccessListener<DocumentSnapshot>() {
                                    @Override
                                    public void onSuccess(DocumentSnapshot documentSnapshot) {
                                        Fruta fruta2 = documentSnapshot.toObject(Fruta.class);
                                    }
                                });

                                Fruta frutaAux = new Fruta();
                                listado.add(frutaAux);

                            }
                        } else {
                            Log.w("TAG", "Error getting documents.", task.getException());
                        }
                    }
                });*/
//original!
        return frutas;
    }

    public void insert(Fruta fruta){
         AppDatabase.databaseWriteExecutor.execute(() -> {
            frutaDao.insert(fruta);
        });

      //andando firebase insert
        /*
        Map<String, Object> conjuntoB = new HashMap<>();
        conjuntoB.put("id", fruta.getId());
        conjuntoB.put("nombre", fruta.getNombre());
        conjuntoB.put("descripcion", fruta.getDescripcion());

        FirebaseFirestore db = FirebaseFirestore.getInstance();

        db.collection("conjuntoB")
                .add(conjuntoB);

         */
    }

    public void update(Fruta fruta){
        AppDatabase.databaseWriteExecutor.execute(() -> {
            frutaDao.update(fruta);
        });
    }

    public void delete(Fruta fruta){
        AppDatabase.databaseWriteExecutor.execute(() -> {
            frutaDao.delete(fruta);
        });
    }
}
