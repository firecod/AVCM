package com.firecod.avcm_android.fragmentsAlmacen.alertDialog;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.VolleyLog;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.firecod.avcm_android.R;
import com.firecod.avcm_android.model.Almacen;
import com.firecod.avcm_android.model.Producto;
import com.google.gson.Gson;

import java.util.HashMap;
import java.util.Map;

public class DialogAlmacen extends DialogFragment {

    EditText txtIdAlmacen;
    EditText txtNombreAlmacen;
    EditText txtDomicilioAlmacen;
    CheckBox cbEstatusAlmacen;
    private Gson gson;
    private String urlGlobal ="http://192.168.0.102:8084/AVCM_WEB/restProducto/";
    private  Almacen a;
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        // Use the Builder class for convenient dialog construction
        String[] datos = getArguments().getStringArray("valores");
        final View content = LayoutInflater.from(getContext()).inflate(R.layout.alertdialog_formulario_almacen, null);
        AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
        builder.setView(content);
        builder.setMessage("Modificar Almacén")
                .setPositiveButton("Actualizar", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        // Send the positive button event back to the host activity
                        actualizarAlmacen(content);

                    }
                })
                .setNegativeButton("Eliminar", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        eliminarAlmacen(content);
                        dialog.dismiss();

                    }
                });
        // Create the AlertDialog object and return it
        inicializar(content, datos);


        return builder.create();
    }

    public static DialogAlmacen newInstance(String[] valores) {
        DialogAlmacen f = new DialogAlmacen();
        Bundle args = new Bundle();
        args.putStringArray("valores", valores);
        f.setArguments(args);
        return f;
    }


    public void inicializar(View content, String[] datos){

        txtIdAlmacen = content.findViewById(R.id.txtIdAlmacen);
        txtIdAlmacen.setEnabled(false);
        txtNombreAlmacen = content.findViewById(R.id.txtNombreAlmacen);
        txtDomicilioAlmacen = content.findViewById(R.id.txtDomicilioAlmacen);
        cbEstatusAlmacen = content.findViewById(R.id.cbEstatusAlmacen);
        txtIdAlmacen.setText(datos[0]);
        txtNombreAlmacen.setText(datos[1]);
        txtDomicilioAlmacen.setText(datos[2]);

    }

    public void actualizarAlmacen(View content){
        a = new Almacen();
        a.setNombre(txtNombreAlmacen.getText().toString());
        a.setDomicilio(txtDomicilioAlmacen.getText().toString());
        a.setId(Integer.parseInt(txtIdAlmacen.getText().toString()));

        actualizarAlmacen(a);
    }

    public void actualizarAlmacen(final Almacen almacen)
    {
        final Context context= this.getContext();
        final Toast t = new Toast(this.getContext());
        t.makeText(this.getContext(), "Actualizando...", Toast.LENGTH_LONG).show();
        StringRequest sr = new StringRequest(
                Request.Method.POST, //GET or POST
                urlGlobal + "updateAlmacen", //URL
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try {
                            gson = new Gson();
                            Almacen a = gson.fromJson(response, Almacen.class);

                            t.makeText(context, "Modificación Exitosa, el almacén editado corresponde al ID " + a.getId(), Toast.LENGTH_LONG).show();

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
                params.put("nombre", almacen.getNombre());
                params.put("domicilio", almacen.getDomicilio());
                params.put("idAlmacen", String.valueOf(almacen.getId()));
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


    public void eliminarAlmacen(View v){
        eliminarAlmacen(Integer.parseInt(txtIdAlmacen.getText().toString()));

    }
    public void eliminarAlmacen(int id){
        final Context c = this.getContext();
        final Toast t = new Toast(this.requireContext());
        t.makeText(this.requireContext(), "Eliminando...", Toast.LENGTH_LONG).show();
        StringRequest sr = new StringRequest(
                Request.Method.GET, //GET or POST
                urlGlobal + "deleteAlmacen?idAlmacen=" + id, //URL

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
                t.makeText(c, "Ocurrió un error de conexión", Toast.LENGTH_LONG).show();
            }
        }
        ) ;
        RequestQueue queue = Volley.newRequestQueue(this.getContext());
        queue.add(sr);
    }
}
