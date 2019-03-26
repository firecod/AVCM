package com.firecod.avcm_android.view;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.evrencoskun.tableview.TableView;
import com.firecod.avcm_android.R;
import com.firecod.avcm_android.components.TableAdapterProducto;
import com.firecod.avcm_android.core.ControllerProducto;
import com.firecod.avcm_android.model.Producto;

import java.util.List;

import static java.security.AccessController.getContext;

public class ActivityProductoConsulta extends AppCompatActivity {

    private List<Producto> mRowHeaderList;
    private List<Producto> mColumnHeaderList;
    private List<List<Producto>> mCellList;

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
