package com.firecod.avcm_android.components.TableView;

import android.app.AlertDialog;
import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;

import com.evrencoskun.tableview.ITableView;
import com.evrencoskun.tableview.listener.ITableViewListener;
import com.firecod.avcm_android.components.TableView.holder.ProductoColumnHeaderViewHolder;
import com.firecod.avcm_android.components.TableView.popup.ColumnHeaderLongPressPopupProducto;
import com.firecod.avcm_android.fragmentsProducto.CatalogoProducto;
import com.firecod.avcm_android.fragmentsProducto.FormularioProducto;


import java.util.ArrayList;
import java.util.List;


public class TableAdapterListenerProducto implements ITableViewListener {
    private static final String LOG_TAG = TableAdapterListenerProducto.class.getSimpleName();
    private Boolean editar = false;
    private ITableView mTableView;
    private List productos = new ArrayList();
    private CatalogoProducto catalogoProducto;


    public TableAdapterListenerProducto(ITableView pTableView, CatalogoProducto cp) {
        this.mTableView = pTableView;
        this.catalogoProducto = cp;
    }

    @Override
    public void onCellClicked(@NonNull RecyclerView.ViewHolder cellView, int column, int row) {
        Log.d(LOG_TAG, "onCellClicked has been clicked for x= " + column + " y= " + row);
    }

    @Override
    public void onCellLongPressed(@NonNull RecyclerView.ViewHolder cellView, int column, int row) {
        Log.d(LOG_TAG, "onCellLongPressed has been clicked for " + row);
    }

    @Override
    public void onColumnHeaderClicked(@NonNull RecyclerView.ViewHolder columnHeaderView, int
            column) {
        Log.d(LOG_TAG, "onColumnHeaderClicked has been clicked for " + column);
    }

    @Override
    public void onColumnHeaderLongPressed(@NonNull RecyclerView.ViewHolder columnHeaderView, int
            column) {
        if (columnHeaderView != null && columnHeaderView instanceof ProductoColumnHeaderViewHolder) {

            // Create Long Press Popup
            ColumnHeaderLongPressPopupProducto popup = new ColumnHeaderLongPressPopupProducto
                    ((ProductoColumnHeaderViewHolder) columnHeaderView, mTableView);

            popup.show();
        }
    }

    @Override
    public void onRowHeaderClicked(@NonNull RecyclerView.ViewHolder rowHeaderView, int row) {


        Log.d(LOG_TAG, "onRowHeaderClicked has been clicked for " + row);
    }

    @Override
    public void onRowHeaderLongPressed(@NonNull RecyclerView.ViewHolder owHeaderView, int row) {
        Log.d(LOG_TAG, "onRowHeaderLongPressed has been clicked for " + row);
        for(int i = 0; i<7; i++){
            productos.add(mTableView.getAdapter().getCellItem(i, row).toString());
            System.out.println("xdxd " + productos.get(i));
        }
        catalogoProducto.datos(productos);
    }
}
