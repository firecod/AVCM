package com.firecod.avcm_android.fragmentsCliente;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;

import com.firecod.avcm_android.R;
import com.firecod.avcm_android.core.ControllerCliente;
import com.firecod.avcm_android.model.Cliente;
import com.firecod.avcm_android.model.Persona;
import com.firecod.avcm_android.model.Usuario;

public class FormularioCliente extends Fragment {

    EditText txtIdCliente;
    EditText txtNombrePersona;
    EditText txtApellidoPaterno;
    EditText txtApellidoMaterno;
    EditText txtRFC;
    EditText txtDomicilio;
    EditText txtTelefono;
    EditText txtCorreoElectronico;
    EditText txtNombreUsuario;
    EditText txtContrasenia;
    CheckBox cbEstatus;
    Button btnGuardar;
    Button btnLimpiar;
    Cliente c;
    Persona p;
    Usuario u;
    ControllerCliente cc;

    public EditText getTxtIdCliente() {
        return txtIdCliente;
    }

    private OnFragmentInteractionListener mListener;

    public FormularioCliente() {
        // Required empty public constructor
    }

    // TODO: Rename and change types and number of parameters
    public static FormularioCliente newInstance() {
        FormularioCliente fragment = new FormularioCliente();
        Bundle args = new Bundle();

        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_formulario_cliente, container, false);
        inicializar(view);
        return view;
    }

    public void inicializar(View view){
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
        txtContrasenia = view.findViewById(R.id.txtContrasenia);
        cbEstatus = view.findViewById(R.id.cbEstatus);
        btnGuardar = view.findViewById(R.id.btnGuardar);
        btnLimpiar = view.findViewById(R.id.btnLimpiar);
        btnGuardar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                guardarCliente();
            }
        });
        btnLimpiar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                limpiarCampos();
            }
        });

    }

    public void guardarCliente(){
        if(txtContrasenia.getText().toString().length() > 0 && txtNombreUsuario.getText().toString().length() > 0) {
            c = new Cliente();
            p = new Persona();
            u = new Usuario();
            p.setNombre("" + txtNombrePersona.getText().toString());
            p.setApellidoMaterno("" + txtApellidoMaterno.getText().toString());
            p.setApellidoPaterno("" + txtApellidoPaterno.getText().toString());
            p.setDomicilio("" + txtDomicilio.getText().toString());
            p.setRfc("" + txtRFC.getText().toString());
            c.setPersona(p);
            c.setCorreoElectronico("" + txtCorreoElectronico.getText().toString());
            u.setContrasenia(txtContrasenia.getText().toString());
            u.setNombreUsuario(txtNombreUsuario.getText().toString());
            u.setContrasenia(txtContrasenia.getText().toString());
            u.setRol("Cliente");
            c.setUsuario(u);
            cc = new ControllerCliente();
            cc.guardarCliente(this, c);
        }else{
            Toast t = new Toast(this.getContext());
            t.makeText(this.getContext(), "Nombre de usuario y contraseña no deben de estar vacíos", Toast.LENGTH_SHORT).show();
        }
    }

    public void limpiarCampos(){
        txtIdCliente.setText("");
        txtNombreUsuario.setText("");
        txtContrasenia.setText("");
        txtCorreoElectronico.setText("");
        txtRFC.setText("");
        txtDomicilio.setText("");
        txtApellidoPaterno.setText("");
        txtApellidoMaterno.setText("");
        txtNombrePersona.setText("");
        txtTelefono.setText("");
        txtCorreoElectronico.setText("");
    }


    public void onButtonPressed(Uri uri) {
        if (mListener != null) {
            mListener.onFragmentInteraction(uri);
        }
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnFragmentInteractionListener) {
            mListener = (OnFragmentInteractionListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnFragmentInteractionListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }


    public interface OnFragmentInteractionListener {

        void onFragmentInteraction(Uri uri);
    }
}
