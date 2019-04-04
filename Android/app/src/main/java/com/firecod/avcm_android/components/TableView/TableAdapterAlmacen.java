package com.firecod.avcm_android.components.TableView;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.evrencoskun.tableview.adapter.AbstractTableAdapter;
import com.evrencoskun.tableview.adapter.recyclerview.holder.AbstractSorterViewHolder;
import com.evrencoskun.tableview.adapter.recyclerview.holder.AbstractViewHolder;
import com.firecod.avcm_android.R;
import com.firecod.avcm_android.components.TableView.holder.AlmacenCellViewHolder;
import com.firecod.avcm_android.components.TableView.holder.AlmacenColumnHeaderViewHolder;
import com.firecod.avcm_android.components.TableView.holder.AlmacenRowHeaderViewHolder;
import com.firecod.avcm_android.components.TableView.holder.ProductoCellViewHolder;
import com.firecod.avcm_android.components.TableView.holder.ProductoColumnHeaderViewHolder;
import com.firecod.avcm_android.components.TableView.holder.ProductoRowHeaderViewHolder;
import com.firecod.avcm_android.components.TableView.model.CellAlmacen;
import com.firecod.avcm_android.components.TableView.model.CellProducto;
import com.firecod.avcm_android.components.TableView.model.ColumnHeaderAlmacen;
import com.firecod.avcm_android.components.TableView.model.ColumnHeaderProducto;
import com.firecod.avcm_android.components.TableView.model.RowHeaderAlmacen;
import com.firecod.avcm_android.components.TableView.model.RowHeaderProducto;
import com.firecod.avcm_android.model.Almacen;
import com.firecod.avcm_android.model.Producto;

import java.util.List;

public class TableAdapterAlmacen  extends AbstractTableAdapter<ColumnHeaderAlmacen, RowHeaderAlmacen, CellAlmacen> {

    private ViewModelAlmacen viewModelAlmacen;

    public TableAdapterAlmacen(Context context) {
        super(context);
        this.viewModelAlmacen = new ViewModelAlmacen();
    }

    @Override
    public AbstractViewHolder onCreateCellViewHolder(ViewGroup parent, int viewType) {

        View layout;
        // Get default Cell xml Layout
        layout = LayoutInflater.from(mContext).inflate(R.layout.tableview_almacen_cell_layout,
                parent, false);

        // Create a Cell ViewHolder
        return new AlmacenCellViewHolder(layout);


    }

    @Override
    public void onBindCellViewHolder(AbstractViewHolder holder, Object p_jValue, int
            p_nXPosition, int p_nYPosition) {
        CellAlmacen cellAlmacen = (CellAlmacen) p_jValue;

        if (holder instanceof AlmacenCellViewHolder) {
            // Get the holder to update cell item text
            ((AlmacenCellViewHolder) holder).setCellModel(cellAlmacen, p_nXPosition);


        }
    }

    @Override
    public AbstractSorterViewHolder onCreateColumnHeaderViewHolder(ViewGroup parent, int viewType){

        View layout = LayoutInflater.from(mContext).inflate(R.layout
                .tableview_almacen_column_header_layout, parent, false);

        return new AlmacenColumnHeaderViewHolder(layout, getTableView());

    }

    @Override
    public void onBindColumnHeaderViewHolder(AbstractViewHolder holder, Object p_jValue, int
            p_nXPosition) {
        ColumnHeaderAlmacen columnHeader = (ColumnHeaderAlmacen) p_jValue;

        // Get the holder to update cell item text
        AlmacenColumnHeaderViewHolder columnHeaderViewHolder = (AlmacenColumnHeaderViewHolder) holder;

        columnHeaderViewHolder.setColumnHeaderModel(columnHeader, p_nXPosition);
    }

    @Override
    public AbstractViewHolder onCreateRowHeaderViewHolder(ViewGroup parent, int viewType) {

        // Get Row Header xml Layout
        View layout = LayoutInflater.from(mContext).inflate(R.layout.tableview_almacen_row_header_layout,
                parent, false);

        // Create a Row Header ViewHolder
        return new AlmacenRowHeaderViewHolder(layout);
    }

    @Override
    public void onBindRowHeaderViewHolder(AbstractViewHolder holder, Object p_jValue, int
            p_nYPosition) {

        RowHeaderAlmacen rowHeaderModel = (RowHeaderAlmacen) p_jValue;

        AlmacenRowHeaderViewHolder rowHeaderViewHolder = (AlmacenRowHeaderViewHolder) holder;
        rowHeaderViewHolder.row_header_textview.setText(rowHeaderModel.getData());
    }

    @Override
    public View onCreateCornerView() {
        return LayoutInflater.from(mContext).inflate(R.layout.tableview_almacen_corner_layout, null, false);
    }

    @Override
    public int getColumnHeaderItemViewType(int position) {
        return 0;
    }

    @Override
    public int getRowHeaderItemViewType(int position) {
        return 0;
    }

    @Override
    public int getCellItemViewType(int position) {
        return viewModelAlmacen.getCellItemViewType(position);
    }


    /**
     * This method is not a generic Adapter method. It helps to generate lists from single user
     * list for this adapter.
     */
    public void setUserList(List<Almacen> almacenes) {
        // Generate the lists that are used to TableViewAdapter
        viewModelAlmacen.generateListForTableView(almacenes);

        // Now we got what we need to show on TableView.
        setAllItems(viewModelAlmacen.getColumHeaderModeList(), viewModelAlmacen
                .getRowHeaderModelList(), viewModelAlmacen.getCellModelList());
    }
}
