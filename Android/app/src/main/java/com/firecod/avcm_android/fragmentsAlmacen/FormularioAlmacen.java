package com.firecod.avcm_android.fragmentsAlmacen;

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
import android.widget.Spinner;

import com.firecod.avcm_android.R;
import com.firecod.avcm_android.core.ControllerAlmacen;
import com.firecod.avcm_android.fragmentsProducto.FormularioProducto;
import com.firecod.avcm_android.model.Almacen;

public class FormularioAlmacen extends Fragment {

    EditText txtIdAlmacen;
    EditText txtNombreAlmacen;
    EditText txtDomicilioAlmacen;
    CheckBox cbEstatusAlmacen;
    Button btnGuardarAlmacen;
    Button btnNuevoAlmacen;

    public EditText getTxtIdAlmacen() {
        return txtIdAlmacen;
    }

    private Almacen a;
    private ControllerAlmacen ca;


    private OnFragmentInteractionListener mListener;


    public static FormularioAlmacen newInstance(String param1, String param2) {
        FormularioAlmacen fragment = new FormularioAlmacen();
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
        View view = inflater.inflate(R.layout.fragment_formulario_almacen, container, false);
        inicializar(view);

        return view;
    }

    public void inicializar(View view){
        txtDomicilioAlmacen = view.findViewById(R.id.txtDomicilioAlmacen);
        txtIdAlmacen = view.findViewById(R.id.txtIdAlmacen);
        txtIdAlmacen.setEnabled(false);
        txtNombreAlmacen = view.findViewById(R.id.txtNombreAlmacen);
        cbEstatusAlmacen = view.findViewById(R.id.cbEstatusAlmacen);
        btnGuardarAlmacen = view.findViewById(R.id.btnGuardarAlmacen);
        btnNuevoAlmacen = view.findViewById(R.id.btnNuevoAlmacen);
        btnGuardarAlmacen.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                guardarAlmacen();
            }
        });
        btnNuevoAlmacen.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                limpiarCampos();
            }
        });
    }

    public void guardarAlmacen(){
        a = new Almacen();
        a.setDomicilio("" + txtDomicilioAlmacen.getText().toString());
        a.setNombre("" + txtNombreAlmacen.getText().toString());
        ca = new ControllerAlmacen();
        ca.guardarAlmacen(this, a);
    }

    public void limpiarCampos(){
        txtNombreAlmacen.setText("");
        txtIdAlmacen.setText("");
        txtDomicilioAlmacen.setText("");
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

    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        void onFragmentInteraction(Uri uri);
    }
}
