package com.example.aplicacioncurso2021;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.example.aplicacioncurso2021.entities.Fruta;
import com.example.aplicacioncurso2021.repositories.FrutaListAdapter;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import models.FrutaViewModel;

public class MainActivity extends AppCompatActivity {
    private FrutaViewModel frutaViewModel;
    public static final int NEW_FRUTA_REQ_CODE = 1;

    public static final String EXTRA_MESSAGE = "MESSAGE";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
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
    }

    public void onActivityResult(int requestCode, int resultCoded, Intent data){
        super.onActivityResult(requestCode, resultCoded, data);
        if(requestCode==NEW_FRUTA_REQ_CODE && resultCoded==RESULT_OK){
            Fruta fruta = new Fruta();
            fruta.setNombre(data.getStringExtra(AgregarFrutaActivity.EXTRA_MSG));
            frutaViewModel.insert(fruta);
        }else{
            Toast.makeText(getApplicationContext(),R.string.no_guardado,Toast.LENGTH_LONG).show();
        }
    }

   /* public void sendMessage(View v){
        Intent intent = new Intent(this, DisplayMsgActivity.class);
        EditText editText = (EditText) findViewById(R.id.editTextText);
        String msg= editText.getText().toString();
        intent.putExtra(EXTRA_MESSAGE,msg);
        startActivity(intent);
    } */

}