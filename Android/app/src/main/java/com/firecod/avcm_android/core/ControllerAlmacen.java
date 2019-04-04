package com.firecod.avcm_android.core;

import android.widget.ArrayAdapter;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.VolleyLog;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.firecod.avcm_android.fragmentsProducto.FormularioProducto;
import com.firecod.avcm_android.model.Almacen;
import com.firecod.avcm_android.model.Producto;
import com.google.gson.Gson;
import org.json.JSONArray;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class ControllerAlmacen {
    private Gson gson;
    private String urlGlobal ="http://192.168.43.16:8084/AVCM_WEB/restAlmacen/";

    public void guardarAlmacen(final FormularioAlmacen act, final Producto producto)
    {
        Toast t = new Toast(act.requireContext());
        t.makeText(act.requireContext(), "Guardando...", Toast.LENGTH_LONG).show();
        StringRequest sr = new StringRequest(
                Request.Method.POST, //GET or POST
                urlGlobal + "insertProducto", //URL
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
        RequestQueue queue = Volley.newRequestQueue(act.getContext());
        queue.add(sr);

    }

    public void getAllSpinnerProducto(final FormularioProducto act) {

        JsonArrayRequest sr = new JsonArrayRequest(
                Request.Method.GET, //GET or POST
                    urlGlobal + "getAllAlmacen?estatus=1", //URL
                null,
                new Response.Listener<JSONArray>() {
                    @Override
                    public void onResponse(JSONArray response) {
                        try {
                            gson = new Gson();
                            ArrayList<Almacen> almacenes = new ArrayList<>();
                            Almacen a;

                            for(int i = 0; i<response.length(); i++){
                                a = new Almacen();
                                a = gson.fromJson(response.get(i).toString(), Almacen.class);
                                almacenes.add(a);
                            }

                            ArrayAdapter<Almacen> adapter = new ArrayAdapter<Almacen>(act.getContext(),
                                    android.R.layout.simple_spinner_item, almacenes);
                            act.getSpAlmacenProducto().setAdapter(adapter);
                            Almacen acd;
                            acd = ((Almacen) act.getSpAlmacenProducto().getSelectedItem());
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
        RequestQueue queue = Volley.newRequestQueue(act.getContext());
        queue.add(sr);
    }
}