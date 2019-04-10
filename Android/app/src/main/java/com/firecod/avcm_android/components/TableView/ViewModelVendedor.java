package com.firecod.avcm_android.components.TableView;

import android.view.Gravity;

import com.firecod.avcm_android.components.TableView.model.CellProducto;
import com.firecod.avcm_android.components.TableView.model.CellVendedor;
import com.firecod.avcm_android.components.TableView.model.ColumnHeaderProducto;
import com.firecod.avcm_android.components.TableView.model.ColumnHeaderVendedor;
import com.firecod.avcm_android.components.TableView.model.RowHeaderProducto;
import com.firecod.avcm_android.components.TableView.model.RowHeaderVendedor;
import com.firecod.avcm_android.model.Producto;
import com.firecod.avcm_android.model.Vendedor;

import java.util.ArrayList;
import java.util.List;

public class ViewModelVendedor {
    public static final int GENDER_TYPE = 1;
    public static final int MONEY_TYPE = 2;

    private List<ColumnHeaderVendedor> mColumnHeaderModelList;
    private List<RowHeaderVendedor> mRowHeaderModelList;
    private List<List<CellVendedor>> mCellModelList;

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

    private List<ColumnHeaderVendedor> createColumnHeaderModelList() {
        List<ColumnHeaderVendedor> list = new ArrayList<>();

        // Create Column Headers

        list.add(new ColumnHeaderVendedor("Nombre"));
        list.add(new ColumnHeaderVendedor("Primer Apellido"));
        list.add(new ColumnHeaderVendedor("Segundo Apellido"));
        list.add(new ColumnHeaderVendedor("RFC"));
        list.add(new ColumnHeaderVendedor("Domicilio"));
        list.add(new ColumnHeaderVendedor("Teléfono"));
        list.add(new ColumnHeaderVendedor("Nombre de Usuario"));
        list.add(new ColumnHeaderVendedor("Rol"));
        list.add(new ColumnHeaderVendedor("Nombre Almacén"));
        list.add(new ColumnHeaderVendedor("Número Único de Vendedor"));
        list.add(new ColumnHeaderVendedor("Reputación"));
        list.add(new ColumnHeaderVendedor("Clave Persona"));
        list.add(new ColumnHeaderVendedor("Clave Usuario"));
        list.add(new ColumnHeaderVendedor("Estatus"));
        return list;
    }

    private List<List<CellVendedor>> createCellModelList(List<Vendedor> vendedorList) {
        List<List<CellVendedor>> lists = new ArrayList<>();

        // Creating cell model list from User list for Cell Items
        // In this example, User list is populated from web service

        for (int i = 0; i < vendedorList.size(); i++) {
            Vendedor vendedor = vendedorList.get(i);

            List<CellVendedor> list = new ArrayList<>();

            // The order should be same with column header list;
            // "Id"
            list.add(new CellVendedor("1-" + i, vendedor.getPersona().getNombre()));        // "Name"
            list.add(new CellVendedor("2-" + i, vendedor.getPersona().getApellidoPaterno()));
            list.add(new CellVendedor("3-" + i, vendedor.getPersona().getApellidoMaterno()));
            list.add(new CellVendedor("4-" + i, vendedor.getPersona().getRfc()));
            list.add(new CellVendedor("5-" + i, vendedor.getPersona().getDomicilio()));
            list.add(new CellVendedor("6-" + i, vendedor.getPersona().getTelefono()));       // "Email"
            list.add(new CellVendedor("7-" + i, vendedor.getUsuario().getNombreUsuario()));
            list.add(new CellVendedor("8-" + i, vendedor.getUsuario().getRol()));
            list.add(new CellVendedor("9-" + i, vendedor.getAlmacen().getNombre()));
            list.add(new CellVendedor("10-" + i, vendedor.getNumeroVendedor()));
            list.add(new CellVendedor("11-" + i, vendedor.getReputacion()));
            list.add(new CellVendedor("12-" + i, vendedor.getPersona().getId()));
            list.add(new CellVendedor("13-" + i, vendedor.getUsuario().getId()));

            lists.add(list);

        }

        return lists;
    }

    private List<RowHeaderVendedor> createRowHeaderList(List<Vendedor> vendedorList) {
        List<RowHeaderVendedor> list = new ArrayList<>();
        for (int i = 0; i < vendedorList.size(); i++) {
            // In this example, Row headers just shows the index of the TableView List.
            list.add(new RowHeaderVendedor(String.valueOf(vendedorList.get(i).getId())));
        }
        return list;
    }


    public List<ColumnHeaderVendedor> getColumHeaderModeList() {
        return mColumnHeaderModelList;
    }

    public List<RowHeaderVendedor> getRowHeaderModelList() {
        return mRowHeaderModelList;
    }

    public List<List<CellVendedor>> getCellModelList() {
        return mCellModelList;
    }


    public void generateListForTableView(List<Vendedor> vendedores) {
        mColumnHeaderModelList = createColumnHeaderModelList();
        mCellModelList = createCellModelList(vendedores);
        mRowHeaderModelList = createRowHeaderList(vendedores);
    }
}
