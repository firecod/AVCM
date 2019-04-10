package com.firecod.avcm_android.components.TableView;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.evrencoskun.tableview.adapter.AbstractTableAdapter;
import com.evrencoskun.tableview.adapter.recyclerview.holder.AbstractSorterViewHolder;
import com.evrencoskun.tableview.adapter.recyclerview.holder.AbstractViewHolder;
import com.firecod.avcm_android.R;
import com.firecod.avcm_android.components.TableView.holder.VendedorCellViewHolder;
import com.firecod.avcm_android.components.TableView.holder.VendedorColumnHeaderViewHolder;
import com.firecod.avcm_android.components.TableView.holder.VendedorRowHeaderViewHolder;
import com.firecod.avcm_android.components.TableView.model.CellVendedor;
import com.firecod.avcm_android.components.TableView.model.ColumnHeaderVendedor;
import com.firecod.avcm_android.components.TableView.model.RowHeaderVendedor;
import com.firecod.avcm_android.model.Vendedor;

import java.util.List;

public class TableAdapterVendedor extends AbstractTableAdapter<ColumnHeaderVendedor, RowHeaderVendedor, CellVendedor> {
    private ViewModelVendedor viewModelVendedor;

    public TableAdapterVendedor(Context context) {
        super(context);
        this.viewModelVendedor = new ViewModelVendedor();
    }

    @Override
    public AbstractViewHolder onCreateCellViewHolder(ViewGroup parent, int viewType) {

        View layout;
        // Get default Cell xml Layout
        layout = LayoutInflater.from(mContext).inflate(R.layout.tableview_producto_cell_layout,
                parent, false);

        // Create a Cell ViewHolder
        return new VendedorCellViewHolder(layout);


    }

    @Override
    public void onBindCellViewHolder(AbstractViewHolder holder, Object p_jValue, int
            p_nXPosition, int p_nYPosition) {
        CellVendedor cellVendedor = (CellVendedor) p_jValue;

        if (holder instanceof VendedorCellViewHolder) {
            // Get the holder to update cell item text
            ((VendedorCellViewHolder) holder).setCellModel(cellVendedor, p_nXPosition);
        }
    }

    @Override
    public AbstractSorterViewHolder onCreateColumnHeaderViewHolder(ViewGroup parent, int viewType){

        View layout = LayoutInflater.from(mContext).inflate(R.layout
                .tableview_producto_column_header_layout, parent, false);

        return new VendedorColumnHeaderViewHolder(layout, getTableView());

    }

    @Override
    public void onBindColumnHeaderViewHolder(AbstractViewHolder holder, Object p_jValue, int
            p_nXPosition) {
        ColumnHeaderVendedor columnHeader = (ColumnHeaderVendedor) p_jValue;

        // Get the holder to update cell item text
        VendedorColumnHeaderViewHolder columnHeaderViewHolder = (VendedorColumnHeaderViewHolder) holder;

        columnHeaderViewHolder.setColumnHeaderModel(columnHeader, p_nXPosition);
    }

    @Override
    public AbstractViewHolder onCreateRowHeaderViewHolder(ViewGroup parent, int viewType) {

        // Get Row Header xml Layout
        View layout = LayoutInflater.from(mContext).inflate(R.layout.tableview_producto_row_header_layout,
                parent, false);

        // Create a Row Header ViewHolder
        return new VendedorRowHeaderViewHolder(layout);
    }

    @Override
    public void onBindRowHeaderViewHolder(AbstractViewHolder holder, Object p_jValue, int
            p_nYPosition) {

        RowHeaderVendedor rowHeaderModel = (RowHeaderVendedor) p_jValue;

        VendedorRowHeaderViewHolder rowHeaderViewHolder = (VendedorRowHeaderViewHolder) holder;
        rowHeaderViewHolder.row_header_textview.setText(rowHeaderModel.getData());
    }

    @Override
    public View onCreateCornerView() {
        return LayoutInflater.from(mContext).inflate(R.layout.tableview_producto_corner_layout, null, false);
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
        return viewModelVendedor.getCellItemViewType(position);
    }

    /**
     * This method is not a generic Adapter method. It helps to generate lists from single user
     * list for this adapter.
     */
    public void setUserList(List<Vendedor> vendedores) {
        // Generate the lists that are used to TableViewAdapter
        viewModelVendedor.generateListForTableView(vendedores);

        // Now we got what we need to show on TableView.
        setAllItems(viewModelVendedor.getColumHeaderModeList(), viewModelVendedor
                .getRowHeaderModelList(), viewModelVendedor.getCellModelList());
    }
}
