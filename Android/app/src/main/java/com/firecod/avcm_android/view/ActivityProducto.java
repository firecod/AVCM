package com.firecod.avcm_android.view;

import android.app.ProgressDialog;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Spinner;

import com.android.volley.RequestQueue;
import com.android.volley.toolbox.Volley;
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
        txtIdProducto.setEnabled(false);
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
      asignarCategorias();

    }

    public void asignarCategorias(){
        String[] categorias = new String[] {"Produtos de Temporada","Alimentos", "Línea Blanca", "Muebles", "Papeleria y Merceria",
                                        "Moda", "Bebes", "Entretenimiento", "Herramientas", "Limpieza"};
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_spinner_item, categorias);
        spCategoriaProducto.setAdapter(adapter);
    }

    public void guardarProducto(){
      //  Intent i = new Intent(this, ControllerProducto.class);
        p = new Producto();
        a = new Almacen();
      //  Gson gson = new Gson();
        cp = new ControllerProducto();
        //p.setId(Integer.parseInt(txtIdProducto.getText().toString()));
        p.setCategoria(spCategoriaProducto.getSelectedItem().toString());
        p.setEstatus(1);
        p.setMarca(txtMarcaProducto.getText().toString());
        p.setNombre(txtNombreProducto.getText().toString());
        p.setPrecio(Float.parseFloat(txtPrecioProducto.getText().toString()));
        a.setId(1);
        p.setAlmacen(a);
       // String productoJson = gson.toJson(p);
        RequestQueue queue = Volley.newRequestQueue(this);
        final ProgressDialog pDialog = new ProgressDialog(this);
         cp.construirParametros(p, queue, pDialog);

        txtIdProducto.setText("" + cp.getId());
//        txtIdProducto.setText(cp.id);
    }
}
