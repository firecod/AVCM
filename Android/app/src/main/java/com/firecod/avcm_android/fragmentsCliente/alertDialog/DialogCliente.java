package com.firecod.avcm_android.fragmentsCliente.alertDialog;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.VolleyLog;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.firecod.avcm_android.R;
import com.firecod.avcm_android.fragmentsAlmacen.alertDialog.DialogAlmacen;
import com.firecod.avcm_android.model.Almacen;
import com.firecod.avcm_android.model.Cliente;
import com.firecod.avcm_android.model.Persona;
import com.firecod.avcm_android.model.Usuario;
import com.google.gson.Gson;

import java.util.HashMap;
import java.util.Map;

public class DialogCliente extends DialogFragment {
    private EditText txtIdCliente;
    private EditText txtNombrePersona;
    private EditText txtApellidoPaterno;
    private EditText txtApellidoMaterno;
    private EditText txtRFC;
    private EditText txtDomicilio;
    private EditText txtTelefono;
    private EditText txtCorreoElectronico;
    private EditText txtNombreUsuario;
    private CheckBox cbEstatus;
    int idPersona;
    int idUsuario;
    private Gson gson;
    private String urlGlobal ="http://192.168.0.102:8084/AVCM_WEB/restCliente/";
    private Cliente c;
    private Persona p;
    private Usuario u;


    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        // Use the Builder class for convenient dialog construction
        String[] datos = getArguments().getStringArray("valores");
        final View content = LayoutInflater.from(getContext()).inflate(R.layout.alertdialog_formulario_cliente, null);
        AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
        builder.setView(content);
        builder.setMessage("Modificar Cliente")
                .setPositiveButton("Actualizar", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        // Send the positive button event back to the host activity
                        actualizarCliente(content);
                        dialog.dismiss();

                    }
                })
                .setNegativeButton("Eliminar", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        eliminarCliente(content);
                        dialog.dismiss();

                    }
                });
        // Create the AlertDialog object and return it
        inicializar(content, datos);


        return builder.create();
    }

    public static DialogCliente newInstance(String[] valores) {
        DialogCliente f = new DialogCliente();
        Bundle args = new Bundle();
        args.putStringArray("valores", valores);
        f.setArguments(args);
        return f;
    }


    public void inicializar(View view, String[] datos){
        txtNombrePersona = view.findViewById(R.id.txtNombrePersona);
        txtApellidoPaterno = view.findViewById(R.id.txtApellidoPaterno);
        txtApellidoMaterno = view.findViewById(R.id.txtApellidoMaterno);
        txtCorreoElectronico = view.findViewById(R.id.txtCorreoElectronico);
        txtRFC = view.findViewById(R.id.txtRFC);
        txtDomicilio = view.findViewById(R.id.txtDomicilio);
        txtTelefono = view.findViewById(R.id.txtTelefono);
        txtIdCliente = view.findViewById(R.id.txtIdCliente);
        txtIdCliente.setEnabled(false);
        txtNombreUsuario = view.findViewById(R.id.txtNombreUsuario);
        cbEstatus = view.findViewById(R.id.cbEstatus);
        txtIdCliente.setText(datos[0]);
        txtNombrePersona.setText(datos[1]);
        txtApellidoPaterno.setText(datos[2]);
        txtApellidoMaterno.setText(datos[3]);
        txtRFC.setText(datos[4]);
        txtDomicilio.setText(datos[5]);
        txtTelefono.setText(datos[6]);
        txtNombreUsuario.setText(datos[7]);
        txtCorreoElectronico.setText(datos[9]);
        idPersona = Integer.parseInt(datos[10]);
        idUsuario = Integer.parseInt(datos[11]);
    }

    public void actualizarCliente(View content){

        c = new Cliente();
        p = new Persona();
        u = new Usuario();
        p.setNombre("" + txtNombrePersona.getText().toString());
        p.setApellidoMaterno("" + txtApellidoMaterno.getText().toString());
        p.setApellidoPaterno("" + txtApellidoPaterno.getText().toString());
        p.setDomicilio("" + txtDomicilio.getText().toString());
        p.setRfc("" + txtRFC.getText().toString());
        p.setTelefono("" + txtTelefono.getText().toString());
        p.setId(idPersona);
        c.setPersona(p);
        u.setId(idUsuario);
        c.setUsuario(u);
        c.setId(Integer.parseInt(txtIdCliente.getText().toString()));
        c.setCorreoElectronico("" + txtCorreoElectronico.getText().toString());
        actualizarCliente(c);
    }

    public void actualizarCliente(final Cliente c)
    {
        final Context context= this.getContext();
        final Toast t = new Toast(this.getContext());
        t.makeText(this.getContext(), "Actualizando...", Toast.LENGTH_LONG).show();
        StringRequest sr = new StringRequest(
                Request.Method.POST, //GET or POST
                urlGlobal + "updateCliente", //URL
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try {
                            gson = new Gson();
                            Cliente c = gson.fromJson(response, Cliente.class);
                            t.makeText(context, "Modificación Exitosa, el cliente editado corresponde al ID " + c.getId(), Toast.LENGTH_LONG).show();

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
                params.put("nombre", c.getPersona().getNombre());
                params.put("apellidoPaterno", c.getPersona().getApellidoPaterno());
                params.put("apellidoMaterno", c.getPersona().getApellidoMaterno());
                params.put("rfc", String.valueOf(c.getPersona().getRfc()));
                params.put("domicilio", c.getPersona().getDomicilio());
                params.put("telefono", c.getPersona().getTelefono());
                params.put("correoElectronico", c.getCorreoElectronico());
                params.put("idPersona", String.valueOf(c.getPersona().getId()));
                params.put("idCliente", String.valueOf(c.getId()));
                params.put("idUsuario", String.valueOf(c.getUsuario().getId()));
                return params;
            }

            @Override
            public String getBodyContentType() {
                return "application/x-www-form-urlencoded; charset=UTF-8";
            }
        };
        RequestQueue queue = Volley.newRequestQueue(this.getContext());
        queue.add(sr);

    }


    public void eliminarCliente(View v){
        eliminarCliente(Integer.parseInt(txtIdCliente.getText().toString()));

    }
    public void eliminarCliente(int id){
        final Context c = this.getContext();
        final Toast t = new Toast(this.requireContext());
        t.makeText(this.requireContext(), "Eliminando...", Toast.LENGTH_LONG).show();
        StringRequest sr = new StringRequest(
                Request.Method.GET, //GET or POST
                urlGlobal + "deleteCliente?idCliente=" + id, //URL

                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try {
                            if (response.contains("Eliminado")){
                                t.makeText(c, "Eliminado", Toast.LENGTH_LONG).show();
                            }else{
                                t.makeText(c, "Error al eliminar", Toast.LENGTH_LONG).show();
                            }
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                VolleyLog.d("Error: " + error.getMessage());
                t.makeText(c, "Ocurrió un error de conexión", Toast.LENGTH_LONG).show();
            }
        }
        ) ;
        RequestQueue queue = Volley.newRequestQueue(this.getContext());
        queue.add(sr);
    }
}
