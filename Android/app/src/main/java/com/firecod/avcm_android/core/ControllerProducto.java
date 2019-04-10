package com.firecod.avcm_android.core;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.VolleyLog;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.evrencoskun.tableview.TableView;
import com.firecod.avcm_android.components.TableView.TableAdapterProducto;
import com.firecod.avcm_android.fragmentsProducto.CatalogoProducto;
import com.firecod.avcm_android.fragmentsProducto.CatalogoProductoCompra;
import com.firecod.avcm_android.fragmentsProducto.FormularioProducto;
import com.firecod.avcm_android.fragmentsProducto.alertDialog.DialogProducto;
import com.firecod.avcm_android.model.Producto;
import com.google.gson.Gson;

import org.json.JSONArray;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class ControllerProducto {

    private Gson gson;
    private String urlGlobal ="http://192.168.0.102:8084/AVCM_WEB/restProducto/";
    private CodificadorImagenes ci;

    public void guardarProducto(final FormularioProducto act, final Producto producto)
    {

        final Toast t = new Toast(act.requireContext());
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
                            if(p.getId() > 0){
                                act.getTxtIdProducto().setText("" + p.getId());
                                t.makeText(act.requireContext(), "Guardado!", Toast.LENGTH_SHORT).show();
                            }else{
                                t.makeText(act.requireContext(), "Error al guardar", Toast.LENGTH_SHORT).show();
                            }


                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                VolleyLog.d("Error: " + error.getMessage());
                t.makeText(act.requireContext(), "Error al conectar con el servidor, revise su conexión a internet", Toast.LENGTH_SHORT).show();
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
                params.put("foto", producto.getFoto());
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



    public void getAllProducto(final TableAdapterProducto pTableAdapter, final CatalogoProducto act, final ProgressBar mProgressBar, final TableView pTableView){
        final Toast t = new Toast(act.getContext());
        JsonArrayRequest sr = new JsonArrayRequest(
                Request.Method.GET, //GET or POST
                urlGlobal + "getAllProducto?estatus=1", //URL
                null,
                new Response.Listener<JSONArray>() {
                    @Override
                    public void onResponse(JSONArray response) {
                        try {
                            gson = new Gson();
                            ArrayList<Producto> productos = new ArrayList<>();
                            Producto p;

                            for(int i = 0; i<response.length(); i++){
                                p = gson.fromJson(response.get(i).toString(), Producto.class);
                                productos.add(p);

                            }
                            if(productos != null && productos.size()>0){
                                // set the list on TableViewModel
                                pTableAdapter.setUserList(productos);
                                hideProgressBar(mProgressBar, pTableView);
                            }else{
                                t.makeText(act.requireContext(), "Error al consultar productos!", Toast.LENGTH_SHORT).show();
                            }
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                VolleyLog.d("Error: " + error.getMessage());
                t.makeText(act.requireContext(), "Error al conectar con el servidor, revise su conexión a internet", Toast.LENGTH_SHORT).show();
            }
        }
        ) ;
        RequestQueue queue = Volley.newRequestQueue(act.getContext());
        queue.add(sr);
    }

    public ArrayAdapter<Producto> getAllProducto(final CatalogoProductoCompra act){
        final ArrayList<Producto> productos = new ArrayList<>();
        JsonArrayRequest sr = new JsonArrayRequest(
                Request.Method.GET, //GET or POST
                urlGlobal + "getAllProducto?estatus=1", //URL
                null,
                new Response.Listener<JSONArray>() {
                    @Override
                    public void onResponse(JSONArray response) {
                        try {
                            gson = new Gson();
                            Producto p;
                            for(int i = 0; i<response.length(); i++){
                                p = gson.fromJson(response.get(i).toString(), Producto.class);
                                productos.add(p);
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
        final ArrayAdapter<Producto> productosA = new ArrayAdapter<Producto>(
                act.getActivity(),
                android.R.layout.simple_list_item_1,
                productos);
        RequestQueue queue = Volley.newRequestQueue(act.getContext());
        queue.add(sr);
        return productosA;
    }

    public void hideProgressBar(ProgressBar mProgressBar, TableView pTableView) {
        mProgressBar.setVisibility(View.INVISIBLE);
        pTableView.setVisibility(View.VISIBLE);
    }




}
