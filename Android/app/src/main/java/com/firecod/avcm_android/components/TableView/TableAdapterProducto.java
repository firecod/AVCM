package com.firecod.avcm_android.components.TableView;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.evrencoskun.tableview.adapter.AbstractTableAdapter;
import com.evrencoskun.tableview.adapter.recyclerview.holder.AbstractSorterViewHolder;
import com.evrencoskun.tableview.adapter.recyclerview.holder.AbstractViewHolder;
import com.firecod.avcm_android.R;
import com.firecod.avcm_android.components.TableView.holder.ProductoCellViewHolder;
import com.firecod.avcm_android.components.TableView.holder.ProductoColumnHeaderViewHolder;
import com.firecod.avcm_android.components.TableView.holder.ProductoRowHeaderViewHolder;
import com.firecod.avcm_android.components.TableView.model.CellProducto;
import com.firecod.avcm_android.components.TableView.model.ColumnHeaderProducto;
import com.firecod.avcm_android.components.TableView.model.RowHeaderProducto;
import com.firecod.avcm_android.model.Producto;

import java.util.List;

public class TableAdapterProducto extends AbstractTableAdapter<ColumnHeaderProducto, RowHeaderProducto, CellProducto>{

    private ProductoViewModel productoViewModel;

    public TableAdapterProducto(Context context) {
        super(context);
        this.productoViewModel = new ProductoViewModel();
    }

    @Override
    public AbstractViewHolder onCreateCellViewHolder(ViewGroup parent, int viewType) {

        View layout;
                // Get default Cell xml Layout
                layout = LayoutInflater.from(mContext).inflate(R.layout.tableview_producto_cell_layout,
                        parent, false);

                // Create a Cell ViewHolder
                return new ProductoCellViewHolder(layout);


    }

    @Override
    public void onBindCellViewHolder(AbstractViewHolder holder, Object p_jValue, int
            p_nXPosition, int p_nYPosition) {
        CellProducto cellProducto = (CellProducto) p_jValue;

        if (holder instanceof ProductoCellViewHolder) {
            // Get the holder to update cell item text
            ((ProductoCellViewHolder) holder).setCellModel(cellProducto, p_nXPosition);


        }
    }

        @Override
        public AbstractSorterViewHolder onCreateColumnHeaderViewHolder(ViewGroup parent, int viewType){

            View layout = LayoutInflater.from(mContext).inflate(R.layout
                    .tableview_producto_column_header_layout, parent, false);

            return new ProductoColumnHeaderViewHolder(layout, getTableView());

        }

    @Override
    public void onBindColumnHeaderViewHolder(AbstractViewHolder holder, Object p_jValue, int
            p_nXPosition) {
            ColumnHeaderProducto columnHeader = (ColumnHeaderProducto) p_jValue;

        // Get the holder to update cell item text
        ProductoColumnHeaderViewHolder columnHeaderViewHolder = (ProductoColumnHeaderViewHolder) holder;

        columnHeaderViewHolder.setColumnHeaderModel(columnHeader, p_nXPosition);
    }

    @Override
    public AbstractViewHolder onCreateRowHeaderViewHolder(ViewGroup parent, int viewType) {

        // Get Row Header xml Layout
        View layout = LayoutInflater.from(mContext).inflate(R.layout.tableview_producto_row_header_layout,
                parent, false);

        // Create a Row Header ViewHolder
        return new ProductoRowHeaderViewHolder(layout);
    }

    @Override
    public void onBindRowHeaderViewHolder(AbstractViewHolder holder, Object p_jValue, int
            p_nYPosition) {

        RowHeaderProducto rowHeaderModel = (RowHeaderProducto) p_jValue;

        ProductoRowHeaderViewHolder rowHeaderViewHolder = (ProductoRowHeaderViewHolder) holder;
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
        return productoViewModel.getCellItemViewType(position);
    }

    /**
     * This method is not a generic Adapter method. It helps to generate lists from single user
     * list for this adapter.
     */
    public void setUserList(List<Producto> productos) {
        // Generate the lists that are used to TableViewAdapter
        productoViewModel.generateListForTableView(productos);

        // Now we got what we need to show on TableView.
        setAllItems(productoViewModel.getColumHeaderModeList(), productoViewModel
                .getRowHeaderModelList(), productoViewModel.getCellModelList());
    }





}

