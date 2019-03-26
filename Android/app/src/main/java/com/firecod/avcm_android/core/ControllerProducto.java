package com.firecod.avcm_android.core;
import android.app.ProgressDialog;
import android.util.Log;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.VolleyLog;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.firecod.avcm_android.model.Producto;
import com.firecod.avcm_android.view.ActivityProducto;
import com.google.gson.Gson;
import java.util.HashMap;
import java.util.Map;

public class ControllerProducto {

    private Gson gson;
    private String url ="http://192.168.0.9:8084/AVCM_WEB/restProducto/";

    public void guardarProducto(final ActivityProducto act, final Producto producto)
    {
        Toast t = new Toast(act.getBaseContext());
        t.makeText(act.getBaseContext(), "Guardando...", Toast.LENGTH_LONG).show();
        url = url + "insertProducto";

        // Request a string response from the provided URL.
        StringRequest sr = new StringRequest(
                Request.Method.POST, //GET or POST
                url, //URL

                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try {
                            gson = new Gson();
                            Producto p = gson.fromJson(response, Producto.class);
                            act.getTxtIdProducto().setText("" + p.getId());
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
                return params;
            }

            @Override
            public String getBodyContentType() {
                return "application/x-www-form-urlencoded; charset=UTF-8";
            }
        };
        RequestQueue queue = Volley.newRequestQueue(act);
        queue.add(sr);

    }
}
