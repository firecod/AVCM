package com.firecod.avcm_android.core;

import android.support.v4.app.DialogFragment;
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
import com.firecod.avcm_android.components.TableView.TableAdapterCliente;
import com.firecod.avcm_android.components.TableView.TableAdapterProducto;
import com.firecod.avcm_android.fragmentsCliente.CatalogoCliente;
import com.firecod.avcm_android.fragmentsCliente.FormularioCliente;
import com.firecod.avcm_android.fragmentsProducto.CatalogoProducto;
import com.firecod.avcm_android.fragmentsProducto.alertDialog.DialogProducto;
import com.firecod.avcm_android.model.Cliente;
import com.firecod.avcm_android.model.Producto;
import com.google.gson.Gson;

import org.json.JSONArray;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ControllerCliente {

    private Gson gson;
    private String urlGlobal ="http://192.168.0.102:8084/AVCM_WEB/restCliente/";

    public void guardarCliente (final FormularioCliente act, final Cliente c){
        final Toast t = new Toast(act.requireContext());
        t.makeText(act.requireContext(), "Guardando...", Toast.LENGTH_LONG).show();
        StringRequest sr = new StringRequest(
                Request.Method.POST, //GET or POST
                urlGlobal + "insertCliente", //URL
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try {
                            gson = new Gson();
                            Cliente c = gson.fromJson(response, Cliente.class);
                            act.getTxtIdCliente().setText("" + c.getId());
                            if(c.getId() > 0) {
                                t.makeText(act.requireContext(), "Guardado!!", Toast.LENGTH_SHORT).show();
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
                t.makeText(act.requireContext(), "Error al conectar con el servidor, revise su conexión a internet", Toast.LENGTH_SHORT).show();
            }
        }
        ) {

            @Override
            public Map<String, String> getParams() {
                Map<String, String> params = new HashMap<String, String>();
                params.put("nombre", c.getPersona().getNombre());
                params.put("apellidoPaterno", c.getPersona().getApellidoPaterno());
                params.put("apellidoMaterno", c.getPersona().getApellidoMaterno());
                params.put("rfc", String.valueOf(c.getPersona().getRfc()));
                params.put("domicilio", c.getPersona().getDomicilio());
                params.put("telefono", c.getPersona().getTelefono());
                params.put("nombreUsuario", c.getUsuario().getNombreUsuario());
                params.put("contrasenia", c.getUsuario().getContrasenia());
                params.put("rol", c.getUsuario().getRol());
                params.put("correoElectronico", c.getCorreoElectronico());
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

    public void getAllCliente(final TableAdapterCliente cTableAdapter, final CatalogoCliente act, final ProgressBar mProgressBar, final TableView cTableView){

        final Toast t = new Toast(act.getContext());

        JsonArrayRequest sr = new JsonArrayRequest(
                Request.Method.GET, //GET or POST
                urlGlobal + "getAllCliente?estatus=1", //URL
                null,
                new Response.Listener<JSONArray>() {
                    @Override
                    public void onResponse(JSONArray response) {
                        try {
                            gson = new Gson();
                            ArrayList<Cliente> clientes = new ArrayList<>();
                            Cliente c;

                            for(int i = 0; i<response.length(); i++){
                                c = gson.fromJson(response.get(i).toString(), Cliente.class);
                                clientes.add(c);
                            }
                            if(clientes != null && clientes.size()>0){
                                // set the list on TableViewModel
                                cTableAdapter.setUserList(clientes);
                                hideProgressBar(mProgressBar, cTableView);
                            }else{
                                t.makeText(act.requireContext(), "Error al Consultar", Toast.LENGTH_SHORT).show();
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

    public void hideProgressBar(ProgressBar mProgressBar, TableView pTableView) {
        mProgressBar.setVisibility(View.INVISIBLE);
        pTableView.setVisibility(View.VISIBLE);
    }



}
