package com.firecod.avcm_android.fragmentsVendedor;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.Button;
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

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link FormularioVendedor.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link FormularioVendedor#newInstance} factory method to
 * create an instance of this fragment.
 */
public class FormularioVendedor extends Fragment {

    EditText etIdVendedor;
    EditText etNombre;
    EditText etPApellido;
    EditText etSApellido;
    EditText etRFC;
    EditText etDomicilio;
    EditText etTelefono;
    EditText etUsuario;
    EditText etContrasenia;
    EditText etReputacion;
    Spinner etAlmacen;
    CheckBox chbActivo;
    Button btnGuardar;
    Button btnNuevo;
    Vendedor v;
    Persona p;
    Usuario u;
    Almacen a;
    ControllerVendedor cv;

    public EditText getEtIdVendedor() {
        return etIdVendedor;
    }

    public EditText getEtNombre() {
        return etNombre;
    }

    public EditText getEtPApellido() {
        return etPApellido;
    }

    public EditText getEtSApellido() {
        return etSApellido;
    }

    public EditText getEtRFC() {
        return etRFC;
    }

    public EditText getEtDomicilio() {
        return etDomicilio;
    }

    public EditText getEtTelefono() {
        return etTelefono;
    }

    public EditText getEtUsuario() {
        return etUsuario;
    }

    public EditText getEtContrasenia() {
        return etContrasenia;
    }

    public EditText getEtReputacion() {
        return etReputacion;
    }

    public Spinner getEtAlmacen() {
        return etAlmacen;
    }

    public CheckBox getChbActivo() {
        return chbActivo;
    }

    public Button getBtnGuardar() {
        return btnGuardar;
    }

    public Button getBtnNuevo() {
        return btnNuevo;
    }

    private OnFragmentInteractionListener mListener;

    public FormularioVendedor() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment FormularioVendedor.
     */
    // TODO: Rename and change types and number of parameters
    public static FormularioVendedor newInstance(String param1, String param2) {
        FormularioVendedor fragment = new FormularioVendedor();
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
        // Inflate the layout for this fragment

        View view = inflater.inflate(R.layout.fragment_formulario_vendedor, container, false);

        inicializar(view);

        return view;
    }

    public void inicializar(View view){
        etIdVendedor = view.findViewById(R.id.etIdVendedor);
        etIdVendedor.setEnabled(false);
        etNombre = view.findViewById(R.id.etNombre);
        etPApellido = view.findViewById(R.id.etPApellido);
        etSApellido = view.findViewById(R.id.etSApellido);
        etRFC = view.findViewById(R.id.etRFC);
        etDomicilio = view.findViewById(R.id.etDomicilio);
        etTelefono = view.findViewById(R.id.etTelefono);
        etUsuario = view.findViewById(R.id.etUsuario);
        etContrasenia = view.findViewById(R.id.etContrasenia);
        etReputacion = view.findViewById(R.id.etReputacion);
        etAlmacen = view.findViewById(R.id.etAlmacen);
        btnGuardar = view.findViewById(R.id.btnGuardar);
        btnNuevo = view.findViewById(R.id.btnNuevo);
        btnGuardar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                guardar();
            }
        });
        asignarAlmacen();
    }

    public void asignarAlmacen(){
        ControllerAlmacen ca = new ControllerAlmacen();
        ca.getAllSpinnerVendedor(this);
    }

    public void guardar(){
        u = new Usuario();
        p = new Persona();
        v = new Vendedor();
        a = new Almacen();
        cv = new ControllerVendedor();
        p.setNombre(etNombre.getText().toString());
        p.setApellidoPaterno(etPApellido.getText().toString());
        p.setApellidoMaterno(etSApellido.getText().toString());
        p.setRfc(etRFC.getText().toString());
        p.setDomicilio(etDomicilio.getText().toString());
        p.setTelefono(etTelefono.getText().toString());
        u.setNombreUsuario(etUsuario.getText().toString());
        u.setContrasenia(etContrasenia.getText().toString());
        v.setPersona(p);
        v.setUsuario(u);
        v.setReputacion(Integer.valueOf(etReputacion.getText().toString()));
        a = ((Almacen)etAlmacen.getSelectedItem());
        v.setAlmacen(a);
        cv.guardarVendedor(this, v);
    }

    // TODO: Rename method, update argument and hook method into UI event
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

    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     * <p>
     * See the Android Training lesson <a href=
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.
     */
    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        void onFragmentInteraction(Uri uri);
    }
}
