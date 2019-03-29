package com.firecod.avcm_android.core;

import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.VolleyLog;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.firecod.avcm_android.model.Cliente;
import com.firecod.avcm_android.model.Vendedor;
import com.firecod.avcm_android.view.ActivityLogin;
import com.google.gson.Gson;

import java.util.HashMap;
import java.util.Map;

public class ControllerLogin {

    private Gson gson;
    private String url ="http://192.168.43.152:8084/AVCM_WEB/restLogin/login";

    public void login(final ActivityLogin act)
    {
        Toast t = new Toast(act.getBaseContext());
        t.makeText(act.getBaseContext(), "Ingresando...", Toast.LENGTH_LONG).show();

        StringRequest sr = new StringRequest(
                Request.Method.POST, //GET or POST
                url, //URL

                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try {
                            gson = new Gson();
                            if(response.toLowerCase().contains("error")){
                                Toast t = new Toast(act);
                                t.makeText(act, "Usuario y/o contraseña inválidos", Toast.LENGTH_LONG).show();
                            } else if(response.toLowerCase().contains("vendedor")) {
                                Vendedor v = gson.fromJson(response, Vendedor.class);
                                Toast t = new Toast(act);
                                t.makeText(act, "Ingresando...", Toast.LENGTH_LONG).show();
                                act.ingresar(v);
                            }else{
                                Cliente c = gson.fromJson(response, Cliente.class);
                                Toast t = new Toast(act);
                                t.makeText(act, "Ingresando...", Toast.LENGTH_LONG).show();
                                act.ingresar(c);
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
        ) {

            @Override
            public Map<String, String> getParams() {
                Map<String, String> params = new HashMap<String, String>();
                params.put("usuario", act.getTxtUsuario().getText().toString());
                params.put("password", act.getTxtPassword().getText().toString());
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
