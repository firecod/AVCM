package com.firecod.avcm_android.components.TableView;

import android.view.Gravity;

import com.firecod.avcm_android.components.TableView.model.CellProducto;
import com.firecod.avcm_android.components.TableView.model.ColumnHeaderProducto;
import com.firecod.avcm_android.components.TableView.model.RowHeaderProducto;
import com.firecod.avcm_android.model.Producto;

import java.util.ArrayList;
import java.util.List;

public class ViewModelProducto {
    public static final int GENDER_TYPE = 1;
    public static final int MONEY_TYPE = 2;

    private List<ColumnHeaderProducto> mColumnHeaderModelList;
    private List<RowHeaderProducto> mRowHeaderModelList;
    private List<List<CellProducto>> mCellModelList;

    public int getCellItemViewType(int column) {

        switch (column) {
            case 5:
                // 5. column header is gender.
                return GENDER_TYPE;
            case 8:
                // 8. column header is Salary.
                return MONEY_TYPE;
            default:
                return 0;
        }
    }

    public int getColumnTextAlign(int column) {
        switch (column) {
            // Id
            case 0:
                return Gravity.LEFT;
            // Name
            case 1:
                return Gravity.LEFT;
            // Nickname
            case 2:
                return Gravity.LEFT;
            // Email
            case 3:
                return Gravity.LEFT;
            // BirthDay
            case 4:
                return Gravity.LEFT;
            // Gender (Sex)
            case 5:
                return Gravity.LEFT;
            // Age
            case 6:
                return Gravity.LEFT;
            // Job
            case 7:
                return Gravity.LEFT;
            // Salary
            case 8:
                return Gravity.LEFT;
            // CreatedAt
            case 9:
                return Gravity.LEFT;
            // UpdatedAt
            case 10:
                return Gravity.LEFT;
            // Address
            case 11:
                return Gravity.LEFT;
            // Zip Code
            case 12:
                return Gravity.LEFT;
            // Phone
            case 13:
                return Gravity.LEFT;
            // Fax
            case 14:
                return Gravity.LEFT;
            default:
                return Gravity.LEFT;
        }

    }

    private List<ColumnHeaderProducto> createColumnHeaderModelList() {
        List<ColumnHeaderProducto> list = new ArrayList<>();

        // Create Column Headers

        list.add(new ColumnHeaderProducto("Nombre"));
        list.add(new ColumnHeaderProducto("Marca"));
        list.add(new ColumnHeaderProducto("Precio"));
        list.add(new ColumnHeaderProducto("Categoría"));
        list.add(new ColumnHeaderProducto("Almacen"));
        list.add(new ColumnHeaderProducto("Estatus"));


        return list;
    }

    private List<List<CellProducto>> createCellModelList(List<Producto> productoList) {
        List<List<CellProducto>> lists = new ArrayList<>();

        // Creating cell model list from User list for Cell Items
        // In this example, User list is populated from web service

        for (int i = 0; i < productoList.size(); i++) {
            Producto producto = productoList.get(i);

            List<CellProducto> list = new ArrayList<>();

            // The order should be same with column header list;
                      // "Id"
            list.add(new CellProducto("2-" + i, producto.getNombre()));        // "Name"
            list.add(new CellProducto("6-" + i, producto.getMarca()));
            list.add(new CellProducto("7-" + i, producto.getPrecio()));
            list.add(new CellProducto("4-" + i, producto.getCategoria()));       // "Email"
            list.add(new CellProducto("3-" + i, producto.getAlmacen().getNombre()));
            list.add(new CellProducto("5-" + i, producto.getEstatus()));  // "Age"
            lists.add(list);

        }

        return lists;
    }

    private List<RowHeaderProducto> createRowHeaderList(List<Producto> productoList) {
        List<RowHeaderProducto> list = new ArrayList<>();
        for (int i = 0; i < productoList.size(); i++) {
            // In this example, Row headers just shows the index of the TableView List.
                list.add(new RowHeaderProducto(String.valueOf(productoList.get(i).getId())));
        }
        return list;
    }


    public List<ColumnHeaderProducto> getColumHeaderModeList() {
        return mColumnHeaderModelList;
    }

    public List<RowHeaderProducto> getRowHeaderModelList() {
        return mRowHeaderModelList;
    }

    public List<List<CellProducto>> getCellModelList() {
        return mCellModelList;
    }


    public void generateListForTableView(List<Producto> productos) {
        mColumnHeaderModelList = createColumnHeaderModelList();
        mCellModelList = createCellModelList(productos);
        mRowHeaderModelList = createRowHeaderList(productos);
    }
}
