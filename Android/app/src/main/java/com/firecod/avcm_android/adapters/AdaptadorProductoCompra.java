package com.firecod.avcm_android.adapters;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.firecod.avcm_android.R;
import com.firecod.avcm_android.clases.ProductoCompra;
import com.firecod.avcm_android.core.CodificadorImagenes;
import com.firecod.avcm_android.model.Producto;

import java.util.ArrayList;
import java.util.List;

public class AdaptadorProductoCompra extends
        ArrayAdapter<Producto>
        implements View.OnClickListener{

        ArrayAdapter<Producto> listaProductos;
        private View.OnClickListener listener;
        private CodificadorImagenes ci;


        public AdaptadorProductoCompra(Context context, List<Producto> productos) {
            super(context, 0, productos);
        }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        // Obtener inflater.
        LayoutInflater inflater = (LayoutInflater) getContext()
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        // ¿Existe el view actual?
        if (null == convertView) {
            convertView = inflater.inflate(
                    R.layout.item_list_catalogo_producto_compra,
                    parent,
                    false);
        }

        // Referencias UI.
        ImageView avatar = (ImageView) convertView.findViewById(R.id.iv_avatar);
        TextView name = (TextView) convertView.findViewById(R.id.tv_name);
        TextView title = (TextView) convertView.findViewById(R.id.tv_title);
        TextView company = (TextView) convertView.findViewById(R.id.tv_company);

        // Lead actual.
        Producto p = getItem(position);

        // Setup.
        Glide.with(getContext()).load(lead.getImage()).into(avatar);
        name.setText(lead.getName());
        title.setText(lead.getTitle());
        company.setText(lead.getCompany());

        return convertView;
    }

    @Override
    public void onClick(View v) {

    }
}
