package com.firecod.avcm_android.components.TableView;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.evrencoskun.tableview.adapter.AbstractTableAdapter;
import com.evrencoskun.tableview.adapter.recyclerview.holder.AbstractSorterViewHolder;
import com.evrencoskun.tableview.adapter.recyclerview.holder.AbstractViewHolder;
import com.firecod.avcm_android.R;
import com.firecod.avcm_android.components.TableView.holder.ClienteCellViewHolder;
import com.firecod.avcm_android.components.TableView.holder.ClienteColumnHeaderViewHolder;
import com.firecod.avcm_android.components.TableView.holder.ClienteRowHeaderViewHolder;
import com.firecod.avcm_android.components.TableView.model.CellCliente;
import com.firecod.avcm_android.components.TableView.model.ColumnHeaderCliente;
import com.firecod.avcm_android.components.TableView.model.RowHeaderCliente;
import com.firecod.avcm_android.model.Cliente;

import java.util.List;

public class TableAdapterCliente extends AbstractTableAdapter<ColumnHeaderCliente, RowHeaderCliente, CellCliente> {
    private ViewModelCliente viewModelCliente;

    public TableAdapterCliente(Context context) {
        super(context);
        this.viewModelCliente = new ViewModelCliente();
    }

    @Override
    public AbstractViewHolder onCreateCellViewHolder(ViewGroup parent, int viewType) {

        View layout;
        // Get default Cell xml Layout
        layout = LayoutInflater.from(mContext).inflate(R.layout.tableview_producto_cell_layout,
                parent, false);

        // Create a Cell ViewHolder
        return new ClienteCellViewHolder(layout);


    }

    @Override
    public void onBindCellViewHolder(AbstractViewHolder holder, Object p_jValue, int
            p_nXPosition, int p_nYPosition) {
        CellCliente cellCliente = (CellCliente) p_jValue;

        if (holder instanceof ClienteCellViewHolder) {
            // Get the holder to update cell item text
            ((ClienteCellViewHolder) holder).setCellModel(cellCliente, p_nXPosition);


        }
    }

    @Override
    public AbstractSorterViewHolder onCreateColumnHeaderViewHolder(ViewGroup parent, int viewType){

        View layout = LayoutInflater.from(mContext).inflate(R.layout
                .tableview_producto_column_header_layout, parent, false);

        return new ClienteColumnHeaderViewHolder(layout, getTableView());

    }

    @Override
    public void onBindColumnHeaderViewHolder(AbstractViewHolder holder, Object p_jValue, int
            p_nXPosition) {
        ColumnHeaderCliente columnHeader = (ColumnHeaderCliente) p_jValue;

        // Get the holder to update cell item text
        ClienteColumnHeaderViewHolder columnHeaderViewHolder = (ClienteColumnHeaderViewHolder) holder;

        columnHeaderViewHolder.setColumnHeaderModel(columnHeader, p_nXPosition);
    }

    @Override
    public AbstractViewHolder onCreateRowHeaderViewHolder(ViewGroup parent, int viewType) {

        // Get Row Header xml Layout
        View layout = LayoutInflater.from(mContext).inflate(R.layout.tableview_producto_row_header_layout,
                parent, false);

        // Create a Row Header ViewHolder
        return new ClienteRowHeaderViewHolder(layout);
    }

    @Override
    public void onBindRowHeaderViewHolder(AbstractViewHolder holder, Object p_jValue, int
            p_nYPosition) {

        RowHeaderCliente rowHeaderModel = (RowHeaderCliente) p_jValue;

        ClienteRowHeaderViewHolder rowHeaderViewHolder = (ClienteRowHeaderViewHolder) holder;
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
        return viewModelCliente.getCellItemViewType(position);
    }

    /**
     * This method is not a generic Adapter method. It helps to generate lists from single user
     * list for this adapter.
     */
    public void setUserList(List<Cliente> clientes) {
        // Generate the lists that are used to TableViewAdapter
        viewModelCliente.generateListForTableView(clientes);

        // Now we got what we need to show on TableView.
        setAllItems(viewModelCliente.getColumHeaderModeList(), viewModelCliente
                .getRowHeaderModelList(), viewModelCliente.getCellModelList());
    }
}
