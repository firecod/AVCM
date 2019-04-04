package com.firecod.avcm_android.components.TableView;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;

import com.evrencoskun.tableview.ITableView;
import com.evrencoskun.tableview.listener.ITableViewListener;
import com.firecod.avcm_android.fragmentsAlmacen.CatalogoAlmacen;
import com.firecod.avcm_android.fragmentsProducto.CatalogoProducto;

import java.util.ArrayList;
import java.util.List;

public class TableAdapterListenerAlmacen implements ITableViewListener {

    private static final String LOG_TAG = TableAdapterListenerProducto.class.getSimpleName();
    private Boolean editar = false;
    private ITableView mTableView;
    private CatalogoAlmacen catalogoAlmacen;

    public TableAdapterListenerAlmacen(ITableView aTableView, CatalogoAlmacen ca) {
        this.mTableView = aTableView;
        this.catalogoAlmacen = ca;
    }


    @Override
    public void onCellClicked(@NonNull RecyclerView.ViewHolder cellView, int column, int row) {

    }

    @Override
    public void onCellLongPressed(@NonNull RecyclerView.ViewHolder cellView, int column, int row) {

    }

    @Override
    public void onColumnHeaderClicked(@NonNull RecyclerView.ViewHolder columnHeaderView, int column) {

    }

    @Override
    public void onColumnHeaderLongPressed(@NonNull RecyclerView.ViewHolder columnHeaderView, int column) {

    }

    @Override
    public void onRowHeaderClicked(@NonNull RecyclerView.ViewHolder rowHeaderView, int row) {

    }

    @Override
    public void onRowHeaderLongPressed(@NonNull RecyclerView.ViewHolder rowHeaderView, int row) {
        System.out.println("se preciono");
        List almacenes = new ArrayList();
        for (int i = 0; i < 4; i++) {
            almacenes.add(mTableView.getAdapter().getCellItem(i, row).toString());
        }
        catalogoAlmacen.datos(almacenes);
    }

}

