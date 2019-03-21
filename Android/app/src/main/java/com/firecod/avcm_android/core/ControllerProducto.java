package com.firecod.avcm_android.core;
import android.app.Activity;
import android.app.ProgressDialog;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.VolleyLog;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.firecod.avcm_android.model.Producto;
import com.firecod.avcm_android.view.ActivityProducto;
import com.google.gson.Gson;

import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class ControllerProducto {

    private Gson gson;
    public int id;

    public int guardarProducto(final Producto producto, String url) {
        RequestQueue queue = Volley.newRequestQueue( new ActivityProducto());
        final ProgressDialog pDialog = new ProgressDialog(new ActivityProducto());
        pDialog.setMessage("Guardando...");
        pDialog.setCanceledOnTouchOutside(false);
        pDialog.show();
        // Request a string response from the provided URL.
        JsonObjectRequest request = new JsonObjectRequest(
                Request.Method.POST, //GET or POST
                url, //URL
                null, //Parameters
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        try {
                            gson = new Gson();
                            Producto p = gson.fromJson(response.toString(), Producto.class);
                            Log.d("Bienvenido", p.getNombre());
                            pDialog.hide();
                            id = p.getId();
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
            protected Map<String, String> getParams() {
                Map<String, String> params = new HashMap<String, String>();
                params.put("nombre", producto.getNombre());
                params.put("marca", producto.getMarca());
                params.put("precio", String.valueOf(producto.getPrecio()));
                params.put("categoria", producto.getCategoria());
                params.put("idAlmacen", String.valueOf(producto.getAlmacen().getId()));
                return params;
            }

            @Override
            public Map<String, String> getHeaders() throws AuthFailureError {
                HashMap<String, String> headers = new HashMap<String, String>();
                headers.put("Content-Type", "application/json; charset=utf-8");
                return headers;
            }
        };

        queue.add(request);
        return id;
    }


    public void construirParametros(Producto p){
        String url ="http://192.168.137.223:8084/AVCM/servicioProducto/guardarProducto";
        guardarProducto(p,url);
    }
}
