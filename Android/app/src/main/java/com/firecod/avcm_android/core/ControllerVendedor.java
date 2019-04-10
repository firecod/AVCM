package com.firecod.avcm_android.core;

import android.view.View;
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
import com.firecod.avcm_android.components.TableView.TableAdapterVendedor;
import com.firecod.avcm_android.fragmentsVendedor.CatalogoVendedor;
import com.firecod.avcm_android.fragmentsVendedor.FormularioVendedor;
import com.firecod.avcm_android.fragmentsVendedor.alertDialog.DialogVendedor;
import com.firecod.avcm_android.model.Vendedor;
import com.google.gson.Gson;

import org.json.JSONArray;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class ControllerVendedor {

    private Gson gson;
    private String urlGlobal ="http://192.168.0.102:8084/AVCM_WEB/restVendedor/";

    public void guardarVendedor(final FormularioVendedor act, final Vendedor vendedor)
    {

        final Toast t = new Toast(act.requireContext());
        t.makeText(act.requireContext(), "Guardando...", Toast.LENGTH_LONG).show();
        StringRequest sr = new StringRequest(
                Request.Method.POST, //GET or POST
                urlGlobal + "insertVendedor", //URL
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try {
                            gson = new Gson();
                            Vendedor v = gson.fromJson(response, Vendedor.class);
                            if(v.getId() > 1) {
                                act.getEtIdVendedor().setText("" + v.getId());
                                t.makeText(act.requireContext(), "Guardado exitoso", Toast.LENGTH_SHORT).show();
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
                params.put("nombre", vendedor.getPersona().getNombre());
                params.put("primerApellido", vendedor.getPersona().getApellidoPaterno());
                params.put("segundoApellido", vendedor.getPersona().getApellidoMaterno());
                params.put("rfc", vendedor.getPersona().getRfc());
                params.put("domicilio", vendedor.getPersona().getDomicilio());
                params.put("telefono", vendedor.getPersona().getTelefono());
                params.put("usuario", vendedor.getUsuario().getNombreUsuario());
                params.put("contrasenia", vendedor.getUsuario().getContrasenia());
                params.put("reputacion", ""+vendedor.getReputacion());
                params.put("idAlmacen", ""+ vendedor.getAlmacen().getId());
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

    public void getAllVendedor(final TableAdapterVendedor pTableAdapter, final CatalogoVendedor act, final ProgressBar mProgressBar, final TableView vTableView){
        final Toast t = new Toast(act.getContext());
        JsonArrayRequest sr = new JsonArrayRequest(
                Request.Method.GET, //GET or POST
                urlGlobal + "getAllVendedor", //URL
                null,
                new Response.Listener<JSONArray>() {
                    @Override
                    public void onResponse(JSONArray response) {
                        try {
                            gson = new Gson();
                            ArrayList<Vendedor> vendedores = new ArrayList<>();
                            Vendedor v;

                            for(int i = 0; i<response.length(); i++){
                                v = gson.fromJson(response.get(i).toString(), Vendedor.class);
                                vendedores.add(v);
                            }
                            if(vendedores != null && vendedores.size()>0){
                                // set the list on TableViewModel
                                pTableAdapter.setUserList(vendedores);
                                hideProgressBar(mProgressBar, vTableView);
                            }else{
                                t.makeText(act.getContext(), "Error al consultar vendedores", Toast.LENGTH_SHORT).show();
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

    public void cambiarVendedor(final DialogVendedor act, final Vendedor vendedor)
    {

        final Toast t = new Toast(act.requireContext());
        t.makeText(act.requireContext(), "Actualizando...", Toast.LENGTH_LONG).show();
        StringRequest sr = new StringRequest(
                Request.Method.POST, //GET or POST
                urlGlobal + "updateVendedor", //URL
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try {
                            gson = new Gson();
                            Vendedor v = gson.fromJson(response, Vendedor.class);
                            if(v.getId() > 0){
                                t.makeText(act.requireContext(), "Modificación exitosa", Toast.LENGTH_SHORT).show();
                            }
                        } catch (Exception e) {
                            e.printStackTrace();
                            t.makeText(act.requireContext(), "Error al modificar", Toast.LENGTH_SHORT).show();
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
                params.put("idVendedor", ""+vendedor.getId());
                params.put("nombre", vendedor.getPersona().getNombre());
                params.put("primerApellido", vendedor.getPersona().getApellidoPaterno());
                params.put("segundoApellido", vendedor.getPersona().getApellidoMaterno());
                params.put("rfc", vendedor.getPersona().getRfc());
                params.put("domicilio", vendedor.getPersona().getDomicilio());
                params.put("telefono", vendedor.getPersona().getTelefono());
                params.put("usuario", vendedor.getUsuario().getNombreUsuario());
                params.put("numeroVendedor", ""+vendedor.getNumeroVendedor());
                params.put("reputacion", ""+vendedor.getReputacion());
                params.put("idAlmacen", ""+vendedor.getAlmacen().getId());
                params.put("idAlmacen", ""+vendedor.getPersona().getId());
                params.put("idAlmacen", ""+vendedor.getUsuario().getId());
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

    public void eliminarVendedor(final DialogVendedor act, final String id)
    {

        final Toast t = new Toast(act.requireContext());
        t.makeText(act.requireContext(), "Eliminando...", Toast.LENGTH_LONG).show();
        StringRequest sr = new StringRequest(
                Request.Method.POST, //GET or POST
                urlGlobal + "deleteVendedor?idVendedor=" + id , //URL
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try {
                            if (response.contains("Eliminado")){
                                t.makeText(act.getContext(), "Eliminado", Toast.LENGTH_SHORT).show();
                            }else{
                                t.makeText(act.getContext(), "Error al eliminar", Toast.LENGTH_SHORT).show();
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
        });

        RequestQueue queue = Volley.newRequestQueue(act.getContext());
        queue.add(sr);

    }

    public void hideProgressBar(ProgressBar mProgressBar, TableView pTableView) {
        mProgressBar.setVisibility(View.INVISIBLE);
        pTableView.setVisibility(View.VISIBLE);
    }

}
