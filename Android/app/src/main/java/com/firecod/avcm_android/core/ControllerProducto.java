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
import com.android.volley.toolbox.StringRequest;
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

    public int guardarProducto(final Producto producto, String url, RequestQueue queue, final ProgressDialog pDialog) {

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
                            Log.d("Bienvenido", "" + p.getId());
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
            public Map getHeaders() throws AuthFailureError{
                Map <String, String> headers = new HashMap<>();
                headers.put("Content-Type", "application/json; charset=utf-8");
                return headers;
            }


            @Override
            public Map<String, String> getParams() {
                Map<String, String> params = new HashMap<String, String>();
                params.put("nombre", "Crema");//producto.getNombre());
                params.put("marca", String.valueOf(producto.getMarca()));
                params.put("precio", String.valueOf(producto.getPrecio()));
                params.put("categoria", String.valueOf(producto.getCategoria()));
                params.put("idAlmacen", String.valueOf(producto.getAlmacen().getId()));
                return params;
            }

    };

        queue.add(sr);
        return id;
    }

    public void construirParametros(Producto p, RequestQueue queue, ProgressDialog pDialog){
        String url ="http://192.168.43.16:8084/AVCM_WEB/restProducto/insertProducto";
        guardarProducto(p,url, queue, pDialog);
    }
}
