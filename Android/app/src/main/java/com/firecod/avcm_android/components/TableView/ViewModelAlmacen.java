package com.firecod.avcm_android.components.TableView;

import android.view.Gravity;

import com.firecod.avcm_android.components.TableView.model.CellAlmacen;
import com.firecod.avcm_android.components.TableView.model.ColumnHeaderAlmacen;
import com.firecod.avcm_android.components.TableView.model.RowHeaderAlmacen;
import com.firecod.avcm_android.model.Almacen;

import java.util.ArrayList;
import java.util.List;


public class ViewModelAlmacen {
    public static final int GENDER_TYPE = 1;
    public static final int MONEY_TYPE = 2;

    private List<ColumnHeaderAlmacen> mColumnHeaderModelList;
    private List<RowHeaderAlmacen> mRowHeaderModelList;
    private List<List<CellAlmacen>> mCellModelList;


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

    private List<ColumnHeaderAlmacen> createColumnHeaderModelList() {
        List<ColumnHeaderAlmacen> list = new ArrayList<>();

        // Create Column Headers

        list.add(new ColumnHeaderAlmacen("Nombre"));
        list.add(new ColumnHeaderAlmacen("Domicilio"));
        list.add(new ColumnHeaderAlmacen("Estatus"));



        return list;
    }

    private List<List<CellAlmacen>> createCellModelList(List<Almacen> almacenList) {
        List<List<CellAlmacen>> lists = new ArrayList<>();

        // Creating cell model list from User list for Cell Items
        // In this example, User list is populated from web service

        for (int i = 0; i < almacenList.size(); i++) {
            Almacen almacen = almacenList.get(i);

            List<CellAlmacen> list = new ArrayList<>();

            // The order should be same with column header list;
                    // "Id"
            list.add(new CellAlmacen("1-" + i, almacen.getNombre()));        // "Name"
            list.add(new CellAlmacen("2-" + i, almacen.getDomicilio()));
            list.add(new CellAlmacen("3-" + i, almacen.getEstatus()));

            lists.add(list);

        }

        return lists;
    }

    private List<RowHeaderAlmacen> createRowHeaderList(int size) {
        List<RowHeaderAlmacen> list = new ArrayList<>();
        for (int i = 0; i < size; i++) {
            // In this example, Row headers just shows the index of the TableView List.
            list.add(new RowHeaderAlmacen(String.valueOf(i + 1)));
        }
        return list;
    }


    public List<ColumnHeaderAlmacen> getColumHeaderModeList() {
        return mColumnHeaderModelList;
    }

    public List<RowHeaderAlmacen> getRowHeaderModelList() {
        return mRowHeaderModelList;
    }

    public List<List<CellAlmacen>> getCellModelList() {
        return mCellModelList;
    }


    public void generateListForTableView(List<Almacen> almacenes) {
        mColumnHeaderModelList = createColumnHeaderModelList();
        mCellModelList = createCellModelList(almacenes);
        mRowHeaderModelList = createRowHeaderList(almacenes.size());
    }
}
