package com.firecod.avcm_android.core;

import android.widget.ArrayAdapter;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.VolleyLog;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;
import com.firecod.avcm_android.fragmentsProducto.FormularioProducto;
import com.firecod.avcm_android.model.Almacen;
import com.google.gson.Gson;
import org.json.JSONArray;
import java.util.ArrayList;

public class ControllerAlmacen {
    Gson gson;
    String urlGlobal = "http://192.168.137.159:8084/AVCM_WEB/restAlmacen/";
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