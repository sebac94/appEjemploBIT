package com.example.aplicacioncurso2021;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class AgregarFrutaActivity extends AppCompatActivity {
    public static final String EXTRA_MSG_NOMBRE = "GUARDAR";
    public static final String EXTRA_MSG_DESCRIPCION = "GUARDAR";
    public static final String EXTRA_MSG_ID = "GUARDAR";

    private EditText editTextNombre;
    private EditText editTextDescripcion;



    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_agregar_fruta);

        editTextNombre =findViewById(R.id.textViewIngresarNombre);
        editTextDescripcion =findViewById(R.id.textViewIngresarDescripcion);

        final Button btnAgregar = findViewById(R.id.btnGuardar);
        btnAgregar.setOnClickListener(view -> {
            Intent respuesta = new Intent();
            if(TextUtils.isEmpty(editTextNombre.getText())){
                setResult(RESULT_CANCELED, respuesta);
            }else{
                String nombre = editTextNombre.getText().toString();
                String descripcion = editTextDescripcion.getText().toString();
                //respuesta.putExtra(EXTRA_MSG_NOMBRE, "pruebaNOM");
                //respuesta.putExtra(EXTRA_MSG_DESCRIPCION, "pruebaDESC");

                Bundle extras = new Bundle();
                extras.putString("EXTRA_MSG_NOMBRE",nombre);
                extras.putString("EXTRA_MSG_DESCRIPCION",descripcion);


                Intent intent = getIntent();
                Bundle extras2 = intent.getExtras();
                if(extras2!=null) {
                    int idAux = (extras2.getInt("EXTRA_MSG_ID"));
                    //int id= getIntent().getIntExtra(EXTRA_MSG_ID,-1);
                    if (idAux != -1) {
                        //espuesta.putExtra(EXTRA_MSG_ID,id);
                        extras.putInt("EXTRA_MSG_ID", idAux);
                    }

                }
                respuesta.putExtras(extras);
                setResult(RESULT_OK, respuesta);
            }
            finish();
        });

        Intent intent = getIntent();

        Bundle extras = intent.getExtras();
        if(extras!=null){
            int idAux=(extras.getInt("EXTRA_MSG_ID"));
            if(idAux!=-1){
                String nom = extras.getString("EXTRA_MSG_NOMBRE");
                String desc= extras.getString("EXTRA_MSG_DESCRIPCION");

                editTextNombre.setText(nom);
                editTextDescripcion.setText(desc);

            }
        }
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setSubtitle(R.string.app_subtitle);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        //OPCION PERSONALIZAR VOLVER

        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(AgregarFrutaActivity.this,R.string.volver,Toast.LENGTH_LONG).show();
                AgregarFrutaActivity.super.onBackPressed();
            }
        });



    }
}