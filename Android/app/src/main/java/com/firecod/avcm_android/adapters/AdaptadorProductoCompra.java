package com.firecod.avcm_android.adapters;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.firecod.avcm_android.R;
import com.firecod.avcm_android.clases.ProductoCompra;

import java.util.ArrayList;

public class AdaptadorProductoCompra extends
        RecyclerView.Adapter<AdaptadorProductoCompra.ViewHolderProductos>
        implements View.OnClickListener{

        ArrayList<ProductoCompra> listaProductos;
        private View.OnClickListener listener;


    public AdaptadorProductoCompra(ArrayList<ProductoCompra> listaProductos) {
            this.listaProductos = listaProductos;
        }

        @Override
        public void onClick(View v) {

        }

        public class ViewHolderProductos extends RecyclerView.ViewHolder {
            TextView nombreProducto;
            TextView marcaProducto;
            TextView precioProducto;
            ImageView fotoProducto;
            public ViewHolderProductos(View itemView) {
                super(itemView);
                nombreProducto=(TextView) itemView.findViewById(R.id.tv);
                marcaProducto=(TextView) itemView.findViewById(R.id.nombreProducto);
                precioProducto=(TextView) itemView.findViewById(R.id.marcaProducto);
                fotoProducto


            }
        }
        @Override
        public AdaptadorProductoCompra.ViewHolderProductos onCreateViewHolder(ViewGroup parent, int viewType) {
            View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.item_list_catalogo_producto_compra,null,false);
            return new AdaptadorProductoCompra.ViewHolderProductos(view);
        }

        @Override
        public void onBindViewHolder(AdaptadorProductoCompra.ViewHolderProductos holderProductos, int position) {
            holderProductos.idProducto.setText(listaProductos.get(position).getId());
            holderProductos.nombreProducto.setText(listaProductos.get(position).getNombre());
            holderProductos.marcaProducto.setText(listaProductos.get(position).getMarca());

        }


        @Override
        public int getItemCount() {
            return listaProductos.size();

        }


    }
