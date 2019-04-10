package com.firecod.avcm_android.fragmentsProducto.alertDialog;


import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.VolleyLog;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.firecod.avcm_android.R;
import com.firecod.avcm_android.core.ControllerAlmacen;
import com.firecod.avcm_android.core.ControllerProducto;
import com.firecod.avcm_android.fragmentsProducto.FormularioProducto;
import com.firecod.avcm_android.model.Almacen;
import com.firecod.avcm_android.model.Producto;
import com.google.gson.Gson;

import java.util.HashMap;
import java.util.Map;

public class DialogProducto extends DialogFragment {
    EditText txtIdProducto;
    EditText txtNombreProducto;
    EditText txtMarcaProducto;
    EditText txtPrecioProducto;
    Spinner spCategoriaProducto;
    Spinner spAlmacenProducto;
    CheckBox cbEstatus;
    Button btnGuardar;
    Button btnEliminar;
    Producto p;
    Almacen a;
    ControllerProducto cp;
    private Gson gson;
    private String urlGlobal ="http://192.168.0.102:8084/AVCM_WEB/restProducto/";

    public Spinner getSpAlmacenProducto() {
        return spAlmacenProducto;
    }



    // Override the Fragment.onAttach() method to instantiate the NoticeDialogListener

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        // Use the Builder class for convenient dialog construction
        String[] datos = getArguments().getStringArray("valores");
        final View content = LayoutInflater.from(getContext()).inflate(R.layout.alertdialog_formulario_producto, null);
        AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
        builder.setView(content);
        builder.setMessage("Modificar Producto")
                .setPositiveButton("Actualizar", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        // Send the positive button event back to the host activity
                        actualizarProducto(content);

                    }
                })
                .setNegativeButton("Eliminar", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        eliminarProducto(content);
                        dialog.dismiss();

                    }
                });
        // Create the AlertDialog object and return it
        inicializar(content, datos);


        return builder.create();
    }

    public static DialogProducto newInstance(String[] valores) {
        DialogProducto f = new DialogProducto();
        Bundle args = new Bundle();
        args.putStringArray("valores", valores);
        f.setArguments(args);
        return f;
    }

    public void asignarAlmacen(String[] datos ){
        ControllerAlmacen ca = new ControllerAlmacen();
        ca.getAllSpinnerProductoEditar(this, datos);
    }

    public void agregarDatosSpinner(String[] datos){
        String[] categorias = new String[] {"Produtos de Temporada","Alimentos", "Línea Blanca", "Muebles", "Papeleria y Merceria",
                "Moda", "Bebes", "Entretenimiento", "Herramientas", "Limpieza"};
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this.getContext(),
                android.R.layout.simple_spinner_item, categorias);
        spCategoriaProducto.setAdapter(adapter);

        for (int i = 0; i < spCategoriaProducto.getAdapter().getCount(); i++) {
            if (spCategoriaProducto.getAdapter().getItem(i).toString().equals(datos[4])) {
                spCategoriaProducto.setSelection(i);
            }
        }
    }

    public void inicializar(View content, String[] datos){
        spAlmacenProducto = content.findViewById(R.id.spAlmacenProducto);
        asignarAlmacen(datos);
        cbEstatus = content.findViewById(R.id.cbEstatus);
        spCategoriaProducto = content.findViewById(R.id.spCategoriaProducto);
        txtIdProducto = content.findViewById(R.id.txtIdProducto);
        txtIdProducto.setEnabled(false);
        txtNombreProducto = content.findViewById(R.id.txtNombreProducto);
        txtMarcaProducto = content.findViewById(R.id.txtMarcaProducto);
        txtPrecioProducto = content.findViewById(R.id.txtPrecioProducto);
        btnGuardar = content.findViewById(R.id.btnGuardar);
        //btnEliminar = content.findViewById(R.id.btnEliminar);
        txtIdProducto.setText(datos[0]);
        txtNombreProducto.setText(datos[1]);
        txtMarcaProducto.setText(datos[2]);
        txtPrecioProducto.setText(datos[3]);
        agregarDatosSpinner(datos);
    }

    public void actualizarProducto(View content){
        p = new Producto();

        p.setPrecio(Float.parseFloat(txtPrecioProducto.getText().toString()));
        a = ((Almacen)spAlmacenProducto.getSelectedItem());
        p.setAlmacen(a);
        p.setNombre(txtNombreProducto.getText().toString());
        p.setMarca(txtMarcaProducto.getText().toString());
        p.setCategoria(spCategoriaProducto.getSelectedItem().toString());
        p.setId(Integer.parseInt(txtIdProducto.getText().toString()));
        actualizarProducto(p);
    }

    public void actualizarProducto(final Producto producto)
    {
      final Context context= this.getContext();
      final Toast t = new Toast(this.getContext());
           t.makeText(this.getContext(), "Actualizando...", Toast.LENGTH_LONG).show();
        StringRequest sr = new StringRequest(
                Request.Method.POST, //GET or POST
                urlGlobal + "updateProducto", //URL
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try {
                            gson = new Gson();
                            Producto p = gson.fromJson(response, Producto.class);
                            System.out.println("id cambio " + p.id);
                            t.makeText(context, "Modificación Exitosa, el producto editado corresponde al ID " + p.getId(), Toast.LENGTH_LONG).show();

                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                VolleyLog.d("Error: " + error.getMessage());
            }
        }
        ) {

            @Override
            public Map<String, String> getParams() {
                Map<String, String> params = new HashMap<String, String>();
                params.put("nombre", producto.getNombre());
                params.put("marca", producto.getMarca());
                params.put("precio", String.valueOf(producto.getPrecio()));
                params.put("categoria", producto.getCategoria());
                params.put("idAlmacen", String.valueOf(producto.getAlmacen().getId()));
                params.put("idProducto", String.valueOf(producto.getId()));
                return params;
            }

            @Override
            public String getBodyContentType() {
                return "application/x-www-form-urlencoded; charset=UTF-8";
            }
        };
        RequestQueue queue = Volley.newRequestQueue(this.getContext());
        queue.add(sr);

    }

    public void eliminarProducto(View content){
        eliminarProducto(Integer.parseInt(txtIdProducto.getText().toString()));
    }

    public void eliminarProducto(int id){
        final Context c = this.getContext();
        final Toast t = new Toast(this.requireContext());
        t.makeText(this.requireContext(), "Eliminando...", Toast.LENGTH_LONG).show();
        StringRequest sr = new StringRequest(
                Request.Method.GET, //GET or POST
                urlGlobal + "deleteProducto?idProducto=" + id, //URL

                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try {
                            if (response.contains("Eliminado")){
                                t.makeText(c, "Eliminado", Toast.LENGTH_LONG).show();
                            }
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                VolleyLog.d("Error: " + error.getMessage());
            }
        }
        ) ;
        RequestQueue queue = Volley.newRequestQueue(this.getContext());
        queue.add(sr);
    }

}
