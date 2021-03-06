package com.firecod.avcm_android.core;

import android.app.Activity;
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
import com.firecod.avcm_android.components.TableView.TableAdapterAlmacen;
import com.firecod.avcm_android.components.TableView.TableAdapterProducto;
import com.firecod.avcm_android.fragmentsAlmacen.CatalogoAlmacen;
import com.firecod.avcm_android.fragmentsAlmacen.FormularioAlmacen;
import com.firecod.avcm_android.fragmentsProducto.CatalogoProducto;
import com.firecod.avcm_android.fragmentsProducto.FormularioProducto;
import com.firecod.avcm_android.fragmentsProducto.alertDialog.DialogProducto;
import com.firecod.avcm_android.fragmentsVendedor.FormularioVendedor;
import com.firecod.avcm_android.fragmentsVendedor.alertDialog.DialogVendedor;
import com.firecod.avcm_android.model.Almacen;
import com.firecod.avcm_android.model.Producto;
import com.google.gson.Gson;
import org.json.JSONArray;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class ControllerAlmacen {
    Gson gson;
    String urlGlobal = "http://192.168.0.102:8084/AVCM_WEB/restAlmacen/";

    public void guardarAlmacen(final FormularioAlmacen act, final Almacen almacen)
    {

        final Toast t = new Toast(act.requireContext());
        t.makeText(act.requireContext(), "Guardando...", Toast.LENGTH_LONG).show();
        StringRequest sr = new StringRequest(
                Request.Method.POST, //GET or POST
                urlGlobal + "insertAlmacen", //URL
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try {
                            gson = new Gson();
                            Almacen a = gson.fromJson(response, Almacen.class);
                            act.getTxtIdAlmacen().setText("" + a.getId());
                            if(a.getId() > 0) {
                                t.makeText(act.requireContext(), "Guardado Correctamente", Toast.LENGTH_SHORT).show();
                            }else{
                                t.makeText(act.requireContext(), "Error al Guardar", Toast.LENGTH_SHORT).show();
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
                params.put("nombre", almacen.getNombre());
                params.put("domicilio", almacen.getDomicilio());
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

        final Toast t =new Toast(act.getContext());
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
                                a = gson.fromJson(response.get(i).toString(), Almacen.class);
                                almacenes.add(a);
                            }
                            if(almacenes.get(0).getId() > 0) {
                                ArrayAdapter<Almacen> adapter = new ArrayAdapter<Almacen>(act.getContext(),
                                        android.R.layout.simple_spinner_item, almacenes);
                                act.getSpAlmacenProducto().setAdapter(adapter);
                            }else{
                                t.makeText(act.getContext(), "Error al consultar almacenes", Toast.LENGTH_SHORT).show();
                            }
                           // Almacen acd;
                            //acd = ((Almacen) act.getSpAlmacenProducto().getSelectedItem());
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                VolleyLog.d("Error: " + error.getMessage());
                t.makeText(act.getContext(), "Error al conectar con el servidor, revise su conexion a internet", Toast.LENGTH_SHORT).show();
            }
        }
        ) ;
        RequestQueue queue = Volley.newRequestQueue(act.getContext());
        queue.add(sr);
    }

    public void getAllSpinnerVendedor(final FormularioVendedor act) {

        final Toast t =new Toast(act.getContext());
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
                                a = gson.fromJson(response.get(i).toString(), Almacen.class);
                                almacenes.add(a);
                            }
                            if(almacenes.get(0).getId() > 0) {
                                ArrayAdapter<Almacen> adapter = new ArrayAdapter<Almacen>(act.getContext(),
                                        android.R.layout.simple_spinner_item, almacenes);
                                act.getEtAlmacen().setAdapter(adapter);
                            }else{
                                t.makeText(act.getContext(), "Error al consultar almacenes", Toast.LENGTH_SHORT).show();
                            }
                            // Almacen acd;

                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                VolleyLog.d("Error: " + error.getMessage());
                t.makeText(act.getContext(), "Error al conectar con el servidor, revise su conexion a internet", Toast.LENGTH_SHORT).show();
            }
        }
        ) ;
        RequestQueue queue = Volley.newRequestQueue(act.getContext());
        queue.add(sr);
    }

    public void getAllSpinnerProductoEditar(final DialogProducto act , final String[] datos) {

        final Toast t =new Toast(act.getContext());
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
                                a = gson.fromJson(response.get(i).toString(), Almacen.class);
                                almacenes.add(a);
                            }
                            if(almacenes.get(0).getId() > 0) {
                                ArrayAdapter<Almacen> adapter = new ArrayAdapter<Almacen>(act.getContext(),
                                        android.R.layout.simple_spinner_item, almacenes);
                                act.getSpAlmacenProducto().setAdapter(adapter);
                                for (int i = 0; i < act.getSpAlmacenProducto().getAdapter().getCount(); i++) {
                                    Almacen acd;
                                    acd = ((Almacen) act.getSpAlmacenProducto().getAdapter().getItem(i));
                                    if (acd.getNombre().equals(datos[5])) {
                                        act.getSpAlmacenProducto().setSelection(i);
                                    }
                                }
                            }else{
                                t.makeText(act.getContext(), "Error al consultar almacenes" , Toast.LENGTH_SHORT).show();
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
        RequestQueue queue = Volley.newRequestQueue(act.getContext());
        queue.add(sr);
    }

    public void getAllSpinnerVendedorEditar(final DialogVendedor act , final String[] datos) {

        final Toast t =new Toast(act.getContext());
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
                                a = gson.fromJson(response.get(i).toString(), Almacen.class);
                                almacenes.add(a);
                            }
                            if(almacenes.get(0).getId() > 0) {
                                ArrayAdapter<Almacen> adapter = new ArrayAdapter<Almacen>(act.getContext(),
                                        android.R.layout.simple_spinner_item, almacenes);
                                act.getEtAlmacen().setAdapter(adapter);
                                for (int i = 0; i < act.getEtAlmacen().getAdapter().getCount(); i++) {
                                    Almacen acd;
                                    acd = ((Almacen) act.getEtAlmacen().getAdapter().getItem(i));
                                    if (acd.getNombre().equals(datos[5])) {
                                        act.getEtAlmacen().setSelection(i);
                                    }
                                }
                            }else{
                                t.makeText(act.getContext(), "Error al consultar almacenes" , Toast.LENGTH_SHORT).show();
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

    public void getAllAlmacen(final TableAdapterAlmacen aTableAdapter, final CatalogoAlmacen act, final ProgressBar mProgressBar, final TableView aTableView){
        final Toast t =new Toast(act.getContext());
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
                                a = gson.fromJson(response.get(i).toString(), Almacen.class);
                                almacenes.add(a);
                            }
                            if(almacenes != null && almacenes.size()>0){
                                // set the list on TableViewModel
                                aTableAdapter.setUserList(almacenes);
                                hideProgressBar(mProgressBar, aTableView);
                            }else{
                                t.makeText(act.getContext(), "Error al consultar almacenes" , Toast.LENGTH_SHORT).show();
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

    public void hideProgressBar(ProgressBar mProgressBar, TableView aTableView) {
        mProgressBar.setVisibility(View.INVISIBLE);
        aTableView.setVisibility(View.VISIBLE);
    }
}