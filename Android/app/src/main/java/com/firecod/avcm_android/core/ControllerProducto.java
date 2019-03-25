package com.firecod.avcm_android.core;
import android.app.ProgressDialog;
import android.util.Log;
import android.widget.EditText;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.VolleyLog;
import com.android.volley.toolbox.StringRequest;
import com.firecod.avcm_android.model.Producto;
import com.google.gson.Gson;
import java.util.HashMap;
import java.util.Map;

public class ControllerProducto {

    private Gson gson;

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
                            Producto p = gson.fromJson(response.toString(), Producto.class);
                            pDialog.hide();
                            etId.setText("" + p.getId());
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

    }
    public void construirParametros(Producto p, RequestQueue queue, ProgressDialog pDialog, EditText etId){
        String url ="http://192.168.0.9:8084/AVCM_WEB/restProducto/insertProducto";

        guardarProducto(p,url, queue, pDialog, etId);
    }
}
