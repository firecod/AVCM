package com.firecod.avcm_android.adapters;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.firecod.avcm_android.R;
import com.firecod.avcm_android.clases.ProductoVo;

import java.util.ArrayList;

public class AdaptadorProducto extends RecyclerView.Adapter<AdaptadorProducto.ViewHolderProductos>
        implements View.OnClickListener{
    ArrayList<ProductoVo> listaProductos;
    private View.OnClickListener listener;


    public AdaptadorProducto(ArrayList<ProductoVo> listaProductos) {
        this.listaProductos = listaProductos;
    }

    @Override
    public void onClick(View v) {

    }

    public class ViewHolderProductos extends RecyclerView.ViewHolder {
        TextView idProducto;
        TextView nombreProducto;
        TextView marcaProducto;
        public ViewHolderProductos(View itemView) {
            super(itemView);
            idProducto=(TextView) itemView.findViewById(R.id.idProducto);
            nombreProducto=(TextView) itemView.findViewById(R.id.nombreProducto);
            marcaProducto=(TextView) itemView.findViewById(R.id.marcaProducto);

        }
    }
    @Override
    public ViewHolderProductos onCreateViewHolder(ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.item_list,null,false);
        return new ViewHolderProductos(view);
    }

    @Override
    public void onBindViewHolder(ViewHolderProductos holderProductos, int position) {
        holderProductos.idProducto.setText(listaProductos.get(position).getId());
        holderProductos.nombreProducto.setText(listaProductos.get(position).getNombre());
        holderProductos.marcaProducto.setText(listaProductos.get(position).getMarca());

    }


    @Override
    public int getItemCount() {
        return listaProductos.size();

    }


}
