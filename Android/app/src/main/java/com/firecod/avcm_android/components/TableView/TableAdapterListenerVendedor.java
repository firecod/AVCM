package com.firecod.avcm_android.components.TableView;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;

import com.evrencoskun.tableview.ITableView;
import com.evrencoskun.tableview.listener.ITableViewListener;
import com.firecod.avcm_android.fragmentsAlmacen.CatalogoAlmacen;
import com.firecod.avcm_android.fragmentsVendedor.CatalogoVendedor;

import java.util.ArrayList;
import java.util.List;

public class TableAdapterListenerVendedor  implements ITableViewListener {

    private static final String LOG_TAG = TableAdapterListenerProducto.class.getSimpleName();
    private ITableView mTableView;
    private CatalogoVendedor catalogoVendedor;

    public TableAdapterListenerVendedor(ITableView aTableView, CatalogoVendedor cv) {
        this.mTableView = aTableView;
        this.catalogoVendedor = cv;
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
        List vendedores = new ArrayList();
        vendedores.add(mTableView.getAdapter().getRowHeaderItem(row).toString());
        for (int i = 0; i < 12; i++) {
            vendedores.add(mTableView.getAdapter().getCellItem(i,row).toString());
        }
        catalogoVendedor.datos(vendedores);
    }
}
