package com.firecod.avcm_android.core;
import android.app.ProgressDialog;
import android.util.Log;
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
    private int id;

    public void guardarProducto(final Producto producto, String url, RequestQueue queue, final ProgressDialog pDialog) {

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
                            setId(p.getId());
                            final int  id = p.getId();
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

    }
    private  void setId (int id){
        this.id = id;
    }

    public int getId (){
        System.out.println("el id" + this.id);
        return this.id;
    }
    public int construirParametros(Producto p, RequestQueue queue, ProgressDialog pDialog){
        String url ="http://192.168.0.9:8084/AVCM_WEB/restProducto/insertProducto";

        guardarProducto(p,url, queue, pDialog);
        System.out.println("el id we"+ id);
        return id;
    }
}
