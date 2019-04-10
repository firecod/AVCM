package com.firecod.avcm_android.components.TableView;

import android.view.Gravity;

import com.firecod.avcm_android.components.TableView.model.CellCliente;
import com.firecod.avcm_android.components.TableView.model.CellProducto;
import com.firecod.avcm_android.components.TableView.model.ColumnHeaderCliente;
import com.firecod.avcm_android.components.TableView.model.ColumnHeaderProducto;
import com.firecod.avcm_android.components.TableView.model.RowHeaderCliente;
import com.firecod.avcm_android.components.TableView.model.RowHeaderProducto;
import com.firecod.avcm_android.model.Cliente;
import com.firecod.avcm_android.model.Producto;

import java.util.ArrayList;
import java.util.List;

public class ViewModelCliente {

    public static final int GENDER_TYPE = 1;
    public static final int MONEY_TYPE = 2;

    private List<ColumnHeaderCliente> mColumnHeaderModelList;
    private List<RowHeaderCliente> mRowHeaderModelList;
    private List<List<CellCliente>> mCellModelList;

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

    private List<ColumnHeaderCliente> createColumnHeaderModelList() {
        List<ColumnHeaderCliente> list = new ArrayList<>();

        // Create Column Headers

        list.add(new ColumnHeaderCliente("Nombre"));
        list.add(new ColumnHeaderCliente("Primer Apellido"));
        list.add(new ColumnHeaderCliente("Segundo Apellido"));
        list.add(new ColumnHeaderCliente("RFC"));
        list.add(new ColumnHeaderCliente("Domicilio"));
        list.add(new ColumnHeaderCliente("Telefono"));
        list.add(new ColumnHeaderCliente("Nombre de Usuario"));
        list.add(new ColumnHeaderCliente("Número único de cliente"));
        list.add(new ColumnHeaderCliente("Correo Electronico"));
        list.add(new ColumnHeaderCliente("Clave Persona"));
        list.add(new ColumnHeaderCliente("Clave Usuario"));
        list.add(new ColumnHeaderCliente("Estatus"));
        return list;
    }

    private List<List<CellCliente>> createCellModelList(List<Cliente> clienteList) {
        List<List<CellCliente>> lists = new ArrayList<>();

        // Creating cell model list from User list for Cell Items
        // In this example, User list is populated from web service

        for (int i = 0; i < clienteList.size(); i++) {
            Cliente cliente = clienteList.get(i);

            List<CellCliente> list = new ArrayList<>();

            // The order should be same with column header list;
            // "Id"
            list.add(new CellCliente("1-" + i, cliente.getPersona().getNombre()));        // "Name"
            list.add(new CellCliente("2-" + i, cliente.getPersona().getApellidoPaterno()));
            list.add(new CellCliente("3-" + i, cliente.getPersona().getApellidoMaterno()));
            list.add(new CellCliente("4-" + i, cliente.getPersona().getRfc()));       // "Email"
            list.add(new CellCliente("5-" + i, cliente.getPersona().getDomicilio()));
            list.add(new CellCliente("6-" + i, cliente.getPersona().getTelefono()));
            list.add(new CellCliente("7-" + i, cliente.getUsuario().getNombreUsuario()));
            list.add(new CellCliente("8-" + i, cliente.getNumeroCliente()));
            list.add(new CellCliente("9-" + i, cliente.getCorreoElectronico()));
            list.add(new CellCliente("10-" + i, cliente.getPersona().getId()));
            list.add(new CellCliente("11-" + i, cliente.getUsuario().getId()));
            list.add(new CellCliente("12-" + i, cliente.getEstatus()));
            lists.add(list);

        }

        return lists;
    }

    private List<RowHeaderCliente> createRowHeaderList(List<Cliente> clienteList) {
        List<RowHeaderCliente> list = new ArrayList<>();
        for (int i = 0; i < clienteList.size(); i++) {
            // In this example, Row headers just shows the index of the TableView List.
            list.add(new RowHeaderCliente(String.valueOf(clienteList.get(i).getId())));
        }
        return list;
    }


    public List<ColumnHeaderCliente> getColumHeaderModeList() {
        return mColumnHeaderModelList;
    }

    public List<RowHeaderCliente> getRowHeaderModelList() {
        return mRowHeaderModelList;
    }

    public List<List<CellCliente>> getCellModelList() {
        return mCellModelList;
    }


    public void generateListForTableView(List<Cliente> clientes) {
        mColumnHeaderModelList = createColumnHeaderModelList();
        mCellModelList = createCellModelList(clientes);
        mRowHeaderModelList = createRowHeaderList(clientes);
    }
}
