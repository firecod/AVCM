package com.firecod.avcm_android.components.TableView;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;

import com.evrencoskun.tableview.ITableView;
import com.evrencoskun.tableview.listener.ITableViewListener;
import com.firecod.avcm_android.fragmentsCliente.CatalogoCliente;
import com.firecod.avcm_android.fragmentsProducto.CatalogoProducto;

import java.util.ArrayList;
import java.util.List;

public class TableAdapterListenerCliente implements ITableViewListener {

    private static final String LOG_TAG = TableAdapterListenerProducto.class.getSimpleName();
    private Boolean editar = false;
    private ITableView cTableView;
    private CatalogoCliente catalogoCliente ;

    public TableAdapterListenerCliente(ITableView cTableView, CatalogoCliente cc) {
        this.cTableView = cTableView;
        this.catalogoCliente = cc;
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

        List clientes = new ArrayList();
        clientes.add(cTableView.getAdapter().getRowHeaderItem(row).toString());
        for(int i = 0; i<6; i++){
            clientes.add(cTableView.getAdapter().getCellItem(i, row).toString());
        }
        catalogoCliente.datos(clientes);
    }
}
