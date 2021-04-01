package com.example.aplicacioncurso2021;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;
import androidx.appcompat.widget.Toolbar;

import com.example.aplicacioncurso2021.entities.Fruta;
import com.example.aplicacioncurso2021.repositories.FrutaListAdapter;
import com.example.aplicacioncurso2021.repositories.FrutaRepository;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.navigation.NavigationView;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.HashMap;
import java.util.Map;

import models.FrutaViewModel;

public class MainActivity extends AppCompatActivity implements  NavigationView.OnNavigationItemSelectedListener{
    private FrutaViewModel frutaViewModel;
    public static final int NEW_FRUTA_REQ_CODE = 1;
    public static final int UPDATE_FRUTA_REQ_CODE = 2;

    public static final String EXTRA_MESSAGE = "MESSAGE";


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater menuInf = getMenuInflater();
        menuInf.inflate(R.menu.main_activity_menu,menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()){
            case R.id.action_add:
                Intent intent=new Intent(MainActivity.this,AgregarFrutaActivity.class);
                startActivityForResult(intent,NEW_FRUTA_REQ_CODE);
                return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        // Write a message to the database
        /*FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference myRef = database.getReference("mensaje_Seba");

        myRef.setValue("prueba 3");
*/

        //SEGUNDA PRUEEBA FIREBASE FIRESTORE
        /*FirebaseFirestore db = FirebaseFirestore.getInstance();

        // Create a new user with a first and last name
        Map<String, Object> user = new HashMap<>();
        user.put("first", "Láá chÉÉnnY");
        user.put("last", "DeL CáP");
        user.put("born", 1815);

        Map<String, Object> conjuntoB = new HashMap<>();
        conjuntoB.put("codigo", "FHG23323");
        conjuntoB.put("nom", "PRUEBA OBJETO");
        conjuntoB.put("valor1", "2534534");

        // Add a new document with a generated ID
        db.collection("conjuntoB")
                .add(conjuntoB)
                .addOnSuccessListener(new OnSuccessListener<DocumentReference>() {
                    @Override
                    public void onSuccess(DocumentReference documentReference) {
                        Log.d("TAG", "DocumentSnapshot added with ID: " + documentReference.getId());
                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Log .w("TAG", "Error adding document", e);
                    }
                });

        db.collection("users")
                .get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        if (task.isSuccessful()) {
                            for (QueryDocumentSnapshot document : task.getResult()) {
                                Log.d("TAG", document.getId() + " => " + document.getData());
                            }
                        } else {
                            Log.w("TAG", "Error getting documents.", task.getException());
                        }
                    }
                });
        */
        FrutaRepository f= new FrutaRepository();
        f.getFrutas(); //PRUEBASS

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        RecyclerView recyclerView = findViewById(R.id.recyclerViewFrutas);
        final FrutaListAdapter adapter = new FrutaListAdapter(new FrutaListAdapter.FrutaDiff());
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        frutaViewModel=new ViewModelProvider(this, new FrutaFactory(getApplication())).get(FrutaViewModel.class);

        frutaViewModel.getFrutas().observe(this, frutas -> {
            adapter.submitList(frutas);
        });

        FloatingActionButton fao = findViewById(R.id.btnAgregar);
        fao.setOnClickListener(view -> {
            Intent intent = new Intent(MainActivity.this, AgregarFrutaActivity.class);
            startActivityForResult(intent, NEW_FRUTA_REQ_CODE);
        });

        adapter.setOnItemClickListener(new FrutaListAdapter.OnItemClickListener() {
            @Override
            public void onItemDelete(Fruta fruta) {
                frutaViewModel.delete(fruta);
                Toast.makeText(getApplicationContext(),R.string.borrado,Toast.LENGTH_LONG).show();
            }

            @Override
            public void onItemClick(Fruta fruta) {
                Intent intent = new Intent(MainActivity.this,AgregarFrutaActivity.class);
                //intent.putExtra(AgregarFrutaActivity.EXTRA_MSG_NOMBRE, fruta.getNombre());
                //intent.putExtra(AgregarFrutaActivity.EXTRA_MSG_DESCRIPCION, fruta.getDescripcion());
                //intent.putExtra(AgregarFrutaActivity.EXTRA_MSG_ID, fruta.getId());

                Bundle extras = new Bundle();
                extras.putInt("EXTRA_MSG_ID",fruta.getId());
                extras.putString("EXTRA_MSG_NOMBRE",fruta.getNombre());
                extras.putString("EXTRA_MSG_DESCRIPCION",fruta.getDescripcion());
                intent.putExtras(extras);
                startActivityForResult(intent,UPDATE_FRUTA_REQ_CODE);
            }
        });

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setSubtitle(R.string.app_subtitle);

        DrawerLayout drawerLayout = findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, drawerLayout, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawerLayout.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = findViewById(R.id.design_navigation_view);
        navigationView.setNavigationItemSelectedListener(this);

    }

    public void onActivityResult(int requestCode, int resultCoded, Intent data){
        super.onActivityResult(requestCode, resultCoded, data);
        if(data!=null){
            Bundle extras = data.getExtras();
            if(requestCode==NEW_FRUTA_REQ_CODE && resultCoded==RESULT_OK){
                Fruta fruta = new Fruta();
                fruta.setNombre(extras.getString("EXTRA_MSG_NOMBRE"));
                fruta.setDescripcion(extras.getString("EXTRA_MSG_DESCRIPCION"));
                //fruta.setNombre(data.getStringExtra(AgregarFrutaActivity.EXTRA_MSG_NOMBRE));
                //fruta.setDescripcion(data.getStringExtra(AgregarFrutaActivity.EXTRA_MSG_DESCRIPCION));
                frutaViewModel.insert(fruta);
            }else if(requestCode==UPDATE_FRUTA_REQ_CODE && resultCoded==RESULT_OK){
                int id=extras.getInt("EXTRA_MSG_ID");
                if(id==-1){
                    Toast.makeText(getApplicationContext(),R.string.no_actualizado,Toast.LENGTH_LONG).show();
                }

                String nom = extras.getString("EXTRA_MSG_NOMBRE");
                String desc= extras.getString("EXTRA_MSG_DESCRIPCION");

                Fruta fruta = new Fruta(id,nom,desc);
                frutaViewModel.update(fruta);
            }else{
                Toast.makeText(getApplicationContext(),R.string.no_guardado,Toast.LENGTH_LONG).show();
            }
        }

    }

   /* public void sendMessage(View v){
        Intent intent = new Intent(this, DisplayMsgActivity.class);
        EditText editText = (EditText) findViewById(R.id.editTextText);
        String msg= editText.getText().toString();
        intent.putExtra(EXTRA_MESSAGE,msg);
        startActivity(intent);
    } */

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()){
            case R.id.nav_favoritos:
                Toast.makeText(getApplicationContext(), R.string.menu_favoritos, Toast.LENGTH_LONG).show();
                break;
            case R.id.nav_perfil:
                Toast.makeText(getApplicationContext(), R.string.menu_perfil, Toast.LENGTH_LONG).show();
                break;
            case R.id.nav_send:
                Toast.makeText(getApplicationContext(), R.string.menu_send, Toast.LENGTH_LONG).show();
                break;
            default:
                throw new IllegalArgumentException("Opcion no existente");
        }

        return true;
    }
}