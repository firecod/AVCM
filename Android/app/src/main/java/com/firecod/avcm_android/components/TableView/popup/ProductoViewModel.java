package com.firecod.avcm_android.components.TableView.popup;

import android.view.Gravity;

import com.firecod.avcm_android.components.TableView.model.CellProducto;
import com.firecod.avcm_android.components.TableView.model.ColumnHeaderProducto;
import com.firecod.avcm_android.components.TableView.model.RowHeaderProducto;
import com.firecod.avcm_android.model.Producto;

import java.util.ArrayList;
import java.util.List;

public class ProductoViewModel {
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

     /*
       - Each of Column Header -
            "Id"
            "Name"
            "Nickname"
            "Email"
            "Birthday"
            "Gender"
            "Age"
            "Job"
            "Salary"
            "CreatedAt"
            "UpdatedAt"
            "Address"
            "Zip Code"
            "Phone"
            "Fax"
     */

    public int getColumnTextAlign(int column) {
        switch (column) {
            // Id
            case 0:
                return Gravity.CENTER;
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
                return Gravity.CENTER;
            // Gender (Sex)
            case 5:
                return Gravity.CENTER;
            // Age
            case 6:
                return Gravity.CENTER;
            // Job
            case 7:
                return Gravity.LEFT;
            // Salary
            case 8:
                return Gravity.CENTER;
            // CreatedAt
            case 9:
                return Gravity.CENTER;
            // UpdatedAt
            case 10:
                return Gravity.CENTER;
            // Address
            case 11:
                return Gravity.LEFT;
            // Zip Code
            case 12:
                return Gravity.RIGHT;
            // Phone
            case 13:
                return Gravity.RIGHT;
            // Fax
            case 14:
                return Gravity.RIGHT;
            default:
                return Gravity.CENTER;
        }

    }

    private List<ColumnHeaderProducto> createColumnHeaderModelList() {
        List<ColumnHeaderProducto> list = new ArrayList<>();

        // Create Column Headers
        list.add(new ColumnHeaderProducto("Id"));
        list.add(new ColumnHeaderProducto("Name"));
        list.add(new ColumnHeaderProducto("Nickname"));
        list.add(new ColumnHeaderProducto("Email"));
        list.add(new ColumnHeaderProducto("Birthday"));
        list.add(new ColumnHeaderProducto("Sex"));
        list.add(new ColumnHeaderProducto("Age"));
        list.add(new ColumnHeaderProducto("Job"));
        list.add(new ColumnHeaderProducto("Salary"));
        list.add(new ColumnHeaderProducto("CreatedAt"));
        list.add(new ColumnHeaderProducto("UpdatedAt"));
        list.add(new ColumnHeaderProducto("Address"));
        list.add(new ColumnHeaderProducto("Zip Code"));
        list.add(new ColumnHeaderProducto("Phone"));
        list.add(new ColumnHeaderProducto("Fax"));

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
            list.add(new CellProducto("1-" + i, producto.getId()));          // "Id"
            list.add(new CellProducto("2-" + i, producto.getNombre()));        // "Name"
            list.add(new CellProducto("3-" + i, producto.getAlmacen()));    // "Nickname"
            list.add(new CellProducto("4-" + i, producto.getCategoria()));       // "Email"
            list.add(new CellProducto("5-" + i, producto.getEstatus()));   // "BirthDay"
            list.add(new CellProducto("6-" + i, producto.getMarca()));      // "Gender"
            list.add(new CellProducto("7-" + i, producto.getPrecio()));         // "Age"
             // "Fax"
            // Add
            lists.add(list);
        }

        return lists;
    }

    private List<RowHeaderProducto> createRowHeaderList(int size) {
        List<RowHeaderProducto> list = new ArrayList<>();
        for (int i = 0; i < size; i++) {
            // In this example, Row headers just shows the index of the TableView List.
            list.add(new RowHeaderProducto(String.valueOf(i + 1)));
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
        mRowHeaderModelList = createRowHeaderList(productos.size());
    }
}
