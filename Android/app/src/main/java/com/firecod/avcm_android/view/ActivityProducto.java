package com.firecod.avcm_android.view;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Spinner;

import com.firecod.avcm_android.R;
import com.firecod.avcm_android.core.ControllerProducto;
import com.firecod.avcm_android.model.Almacen;
import com.firecod.avcm_android.model.Producto;
import com.google.gson.Gson;

public class ActivityProducto extends AppCompatActivity {
    EditText txtIdProducto;
    EditText txtNombreProducto;
    EditText txtMarcaProducto;
    EditText txtPrecioProducto;
    Spinner spCategoriaProducto;
    Spinner spAlmacenProducto;
    CheckBox cbEstatus;
    Button btnGuardar;
    Producto p;
    Almacen a;
    ControllerProducto cp;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        inicializar();
    }

    public void inicializar(){
        spAlmacenProducto = findViewById(R.id.spAlmacenProducto);
        cbEstatus = findViewById(R.id.cbEstatus);
        spCategoriaProducto = findViewById(R.id.spCategoriaProducto);
        txtIdProducto = findViewById(R.id.txtIdProducto);
        txtNombreProducto = findViewById(R.id.txtNombreProducto);
        txtMarcaProducto = findViewById(R.id.txtMarcaProducto);
        txtPrecioProducto = findViewById(R.id.txtPrecioProducto);
        btnGuardar = findViewById(R.id.btnGuardar);
        btnGuardar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                guardarProducto();
            }
        });

    }

    public void guardarProducto(){
      //  Intent i = new Intent(this, ControllerProducto.class);
        p = new Producto();
        a = new Almacen();
      //  Gson gson = new Gson();
        cp = new ControllerProducto();
        //p.setId(Integer.parseInt(txtIdProducto.getText().toString()));
        p.setCategoria("xd");
        p.setEstatus(1);
        p.setMarca(txtMarcaProducto.getText().toString());
        p.setNombre(txtNombreProducto.getText().toString());
        a.setId(1);
        p.setAlmacen(a);
       // String productoJson = gson.toJson(p);
        cp.construirParametros(p);
    }
}
