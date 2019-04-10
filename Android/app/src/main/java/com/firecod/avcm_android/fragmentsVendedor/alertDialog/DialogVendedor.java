package com.firecod.avcm_android.fragmentsVendedor.alertDialog;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Spinner;

import com.firecod.avcm_android.R;
import com.firecod.avcm_android.core.ControllerAlmacen;
import com.firecod.avcm_android.core.ControllerVendedor;
import com.firecod.avcm_android.model.Almacen;
import com.firecod.avcm_android.model.Persona;
import com.firecod.avcm_android.model.Usuario;
import com.firecod.avcm_android.model.Vendedor;


public class DialogVendedor extends DialogFragment {

    EditText etIdVendedor;
    EditText etNombre;
    EditText etPApellido;
    EditText etSApellido;
    EditText etRFC;
    EditText etDomicilio;
    EditText etTelefono;
    EditText etUsuario;
    EditText etReputacion;
    Spinner etAlmacen;
    CheckBox chbActivo;
    ControllerVendedor cv;
    Vendedor v;
    Persona p;
    Usuario u;
    Almacen a;
    int idPersona;
    int idUsuario;

    public Spinner getEtAlmacen() {
        return etAlmacen;
    }

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        // Use the Builder class for convenient dialog construction
        String[] datos = getArguments().getStringArray("valores");
        final View content = LayoutInflater.from(getContext()).inflate(R.layout.alertdialog_formulario_vendedor, null);
        AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
        builder.setView(content);
        builder.setMessage("Modificar Vendedor")
                .setPositiveButton("Actualizar", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        // Send the positive button event back to the host activity
                        actualizarVendedor(content);

                    }
                })
                .setNegativeButton("Eliminar", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        eliminarVendedor(content);
                        dialog.dismiss();

                    }
                });
        inicializar(content, datos);


        return builder.create();
    }

    public void asignarAlmacen(String[] datos ){
        ControllerAlmacen ca = new ControllerAlmacen();
        ca.getAllSpinnerVendedorEditar(this, datos);
    }

    public void inicializar(View view, String[] d){
        etIdVendedor = view.findViewById(R.id.etIdVendedor);
        etNombre = view.findViewById(R.id.etNombre);
        etDomicilio = view.findViewById(R.id.etDomicilio);
        etPApellido = view.findViewById(R.id.etPApellido);
        etSApellido = view.findViewById(R.id.etSApellido);
        etReputacion = view.findViewById(R.id.etReputacion);
        etRFC = view.findViewById(R.id.etRFC);
        etTelefono = view.findViewById(R.id.etTelefono);
        chbActivo = view.findViewById(R.id.cbEstatus);
        etUsuario = view.findViewById(R.id.etUsuario);
        etAlmacen = view.findViewById(R.id.spAlmacenVendedor);
        asignarAlmacen(d);
        etIdVendedor.setText(d[0]);
        etNombre.setText(d[1]);
        etPApellido.setText(d[2]);
        etSApellido.setText(d[3]);
        etRFC.setText(d[4]);
        etDomicilio.setText(d[5]);
        etTelefono.setText(d[6]);
        etUsuario.setText(d[7]);
        etReputacion.setText(d[8]);
        idPersona = Integer.parseInt(d[9]);
        idUsuario = Integer.parseInt(d[10]);
    }

    public void actualizarVendedor(View view){
        v = new Vendedor();
        p = new Persona();
        u = new Usuario();
        a = new Almacen();
        p.setId(idPersona);
        p.setNombre(etNombre.getText().toString());
        p.setApellidoPaterno(etPApellido.getText().toString());
        p.setApellidoMaterno(etSApellido.getText().toString());
        p.setRfc(etRFC.getText().toString());
        p.setDomicilio(etDomicilio.getText().toString());
        p.setTelefono(etTelefono.getText().toString());
        u.setNombreUsuario(etUsuario.getText().toString());
        u.setId(idUsuario);
        v.setPersona(p);
        v.setUsuario(u);
        v.setReputacion(Integer.valueOf(etReputacion.getText().toString()));
        a = ((Almacen)etAlmacen.getSelectedItem());
        v.setAlmacen(a);
        actualizarVendedor(v);
    }

    public void actualizarVendedor(Vendedor v){
        cv = new ControllerVendedor();
        cv.cambiarVendedor(this, v);
    }
    public void eliminarVendedor(View v){
        cv = new ControllerVendedor();
        String id = etIdVendedor.getText().toString();
        cv.eliminarVendedor(this, id);
    }

    public static DialogVendedor newInstance(String[] valores) {
        DialogVendedor f = new DialogVendedor();
        Bundle args = new Bundle();
        args.putStringArray("valores", valores);
        f.setArguments(args);
        return f;
    }
}
