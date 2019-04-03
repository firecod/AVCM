package com.firecod.avcm_android.components.TableView.model;

import com.evrencoskun.tableview.sort.ISortableModel;
import com.firecod.avcm_android.model.Producto;

public class CellProducto implements ISortableModel {

    private String mId;
    private Object mData;

    public CellProducto(String mId, Object mData)
    {
        this.mId = mId;
        this.mData = mData;
    }

    public Object getData() {

        return mData;
    }

    @Override
    public String getId() {
        return mId;
    }


    @Override
    public Object getContent() {
        return mData;
    }


    @Override
    public String toString() {
        return mData.toString();

    }
}
