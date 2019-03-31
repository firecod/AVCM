package com.firecod.avcm_android.components.TableView.holder;

import android.support.v4.content.ContextCompat;
import android.view.Gravity;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.evrencoskun.tableview.adapter.recyclerview.holder.AbstractSorterViewHolder;
import com.firecod.avcm_android.R;
import com.firecod.avcm_android.components.TableView.model.CellProducto;

public class ProductoViewCellHolder extends AbstractSorterViewHolder {
    public final TextView cell_textview;
    public final LinearLayout cell_container;

    public ProductoViewCellHolder(View itemView) {
        super(itemView);
        cell_textview = itemView.findViewById(R.id.cell_data);
        cell_container = itemView.findViewById(R.id.cell_container);
    }

    public void setCellModel(CellProducto p_jModel, int pColumnPosition) {

        // Change textView align by column
        cell_textview.setGravity(ProductoColumnHeaderViewHolder.COLUMN_TEXT_ALIGNS[pColumnPosition] |
                Gravity.CENTER_VERTICAL);

        // Set text
        cell_textview.setText(String.valueOf(p_jModel.getData()));

        // It is necessary to remeasure itself.
        cell_container.getLayoutParams().width = LinearLayout.LayoutParams.WRAP_CONTENT;
        cell_textview.requestLayout();
    }

    @Override
    public void setSelected(SelectionState p_nSelectionState) {
        super.setSelected(p_nSelectionState);

        if (p_nSelectionState == SelectionState.SELECTED) {
            cell_textview.setTextColor(ContextCompat.getColor(cell_textview.getContext(), R.color
                    .selected_text_color));
        } else {
            cell_textview.setTextColor(ContextCompat.getColor(cell_textview.getContext(), R.color
                    .unselected_text_color));
        }
    }
}
