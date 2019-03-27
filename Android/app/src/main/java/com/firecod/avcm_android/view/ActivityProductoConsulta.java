package com.firecod.avcm_android.view;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.firecod.avcm_android.R;
import com.firecod.avcm_android.core.ControllerProducto;
import com.firecod.avcm_android.model.Producto;

import java.util.List;

public class ActivityProductoConsulta extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_producto_consulta);
        crearTabla();
    }

    public void crearTabla(){
        ControllerProducto cp = new ControllerProducto();
        cp.getAll(this);
    }
}
