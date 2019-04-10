package com.firecod.avcm_android.core;

import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.VolleyLog;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.firecod.avcm_android.fragmentsVendedor.FormularioVendedor;
import com.firecod.avcm_android.model.Vendedor;
import com.google.gson.Gson;

import java.util.HashMap;
import java.util.Map;

public class ControllerVendedor {

    private Gson gson;
    private String urlGlobal ="http://192.168.0.5:8084/AVCM_WEB/restVendedor/";

    public void guardarVendedor(final FormularioVendedor act, final Vendedor vendedor)
    {

        Toast t = new Toast(act.requireContext());
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
                            act.getEtIdVendedor().setText("" + v.getId());
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
                params.put("nombre", vendedor.getPersona().getNombre());
                params.put("primerApellido", vendedor.getPersona().getApellidoPaterno());
                params.put("segundoApellido", vendedor.getPersona().getApellidoMaterno());
                params.put("rfc", vendedor.getPersona().getRfc());
                params.put("domicilio", vendedor.getPersona().getDomicilio());
                params.put("telefono", vendedor.getPersona().getTelefono());
                params.put("usuario", vendedor.getUsuario().getNombreUsuario());
                params.put("contrasenia", vendedor.getUsuario().getContrasenia());
                params.put("reputacion", ""+vendedor.getReputacion());
                params.put("almacen", ""+ vendedor.getAlmacen().getId());
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

}
