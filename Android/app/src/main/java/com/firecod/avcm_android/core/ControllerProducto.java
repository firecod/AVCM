package com.firecod.avcm_android.core;
import android.app.ProgressDialog;
import android.content.Intent;
import android.util.Log;
import android.widget.EditText;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.VolleyLog;
import com.android.volley.toolbox.StringRequest;
import com.firecod.avcm_android.model.Producto;
import com.firecod.avcm_android.view.ActivityProducto;
import com.google.gson.Gson;
import java.util.HashMap;
import java.util.Map;

public class ControllerProducto {

    private Gson gson;
    private static int id;
    private Intent i = new Intent();

    public void guardarProducto(final Producto producto, String url, RequestQueue queue, final ProgressDialog pDialog, final EditText etId) {

        pDialog.setMessage("Guardando...");
        pDialog.show();
        // Request a string response from the provided URL.
        StringRequest sr = new StringRequest(
                Request.Method.POST, //GET or POST
                url, //URL

                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try {
                            gson = new Gson();
<<<<<<< HEAD
                            ActivityProducto ap = new ActivityProducto();
                            Producto p = gson.fromJson(response, Producto.class);
                            ap.recuperarID(p.getId());
=======
                            Producto p = gson.fromJson(response.toString(), Producto.class);
                            id = p.getId();
                            etId.setText("" + id);
>>>>>>> 421255bc7774cb01fffffc34bc5fcbd46db3a8ae
                            Log.d("Bienvenido", "" + id);
                            pDialog.hide();
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                VolleyLog.d("Error: " + error.getMessage());
                pDialog.hide();
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
                return params;
            }

            @Override
            public String getBodyContentType() {
                return "application/x-www-form-urlencoded; charset=UTF-8";
            }
        };

        queue.add(sr);
<<<<<<< HEAD


    }
    public int construirParametros(Producto p, RequestQueue queue, ProgressDialog pDialog){
        String url ="http://192.168.0.105:8084/AVCM_WEB/restProducto/insertProducto";
=======
    }

    public void construirParametros(Producto p, RequestQueue queue, ProgressDialog pDialog, EditText etId){
        String url ="http://192.168.0.9:8084/AVCM_WEB/restProducto/insertProducto";
>>>>>>> 421255bc7774cb01fffffc34bc5fcbd46db3a8ae

        guardarProducto(p,url, queue, pDialog, etId);
    }
}
