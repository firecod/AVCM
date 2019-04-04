package com.firecod.avcm_android.fragmentsProducto;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Spinner;

import com.firecod.avcm_android.R;
import com.firecod.avcm_android.core.ControllerAlmacen;
import com.firecod.avcm_android.core.ControllerProducto;
import com.firecod.avcm_android.fragmentsProducto.alertDialog.DialogProducto;
import com.firecod.avcm_android.model.Almacen;
import com.firecod.avcm_android.model.Producto;
import com.firecod.avcm_android.view.MainActivity;

import java.util.List;


/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link FormularioProducto.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link FormularioProducto#newInstance} factory method to
 * create an instance of this fragment.
 */
public class FormularioProducto extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER

    EditText txtIdProducto;
    EditText txtNombreProducto;
    EditText txtMarcaProducto;
    EditText txtPrecioProducto;
    Spinner spCategoriaProducto;
    Spinner spAlmacenProducto;
    CheckBox cbEstatus;
    Button btnGuardar;
    Button btnEliminar;
    Producto p;
    Almacen a;
    ControllerProducto cp;

    public EditText getTxtIdProducto() {
        return txtIdProducto;
    }

    public EditText getTxtNombreProducto() {
        return txtNombreProducto;
    }

    public EditText getTxtMarcaProducto() {
        return txtMarcaProducto;
    }

    public EditText getTxtPrecioProducto() {
        return txtPrecioProducto;
    }

    public Spinner getSpCategoriaProducto() {
        return spCategoriaProducto;
    }

    public Spinner getSpAlmacenProducto() {
        return spAlmacenProducto;
    }

    public CheckBox getCbEstatus() {
        return cbEstatus;
    }

    public Button getBtnGuardar() {
        return btnGuardar;
    }

    public Button getBtnConsultar() {
        return btnEliminar;
    }

    private OnFragmentInteractionListener mListener;

    private FormularioProducto myContext;
    public FormularioProducto() {
        // Required empty public constructor

    }


    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment FormularioProducto.
     */
    // TODO: Rename and change types and number of parameters
    public static FormularioProducto newInstance(String param1, String param2) {
        FormularioProducto fragment = new FormularioProducto();
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
        View view = inflater.inflate(R.layout.fragment_formulario_producto, container, false);
        inicializar(view);

        return view;
    }

    // TODO: Rename method, update argument and hook method into UI event
    public void onButtonPressed(Uri uri) {
        if (mListener != null) {
            mListener.onFragmentInteraction(uri);
        }
    }

    public void inicializar(View view){

        spAlmacenProducto = view.findViewById(R.id.spAlmacenProducto);
        cbEstatus = view.findViewById(R.id.cbEstatus);
        spCategoriaProducto = view.findViewById(R.id.spCategoriaProducto);
        txtIdProducto = view.findViewById(R.id.txtIdProducto);
        txtIdProducto.setEnabled(false);
        txtNombreProducto = view.findViewById(R.id.txtNombreProducto);
        txtMarcaProducto = view.findViewById(R.id.txtMarcaProducto);
        txtPrecioProducto = view.findViewById(R.id.txtPrecioProducto);
        btnGuardar = view.findViewById(R.id.btnGuardar);
        btnGuardar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                guardarProducto();
            }
        });
        btnEliminar = view.findViewById(R.id.btnEliminar);
        btnEliminar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                llamarDialog();
            }
        });
        asignarAlmacen();
        asignarCategorias();



    }

    public void asignarAlmacen(){
        ControllerAlmacen ca = new ControllerAlmacen();
        ca.getAllSpinnerProducto(this);
    }

    public void asignarCategorias(){
        String[] categorias = new String[] {"Produtos de Temporada","Alimentos", "Línea Blanca", "Muebles", "Papeleria y Merceria",
                "Moda", "Bebes", "Entretenimiento", "Herramientas", "Limpieza"};
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this.getContext(),
                android.R.layout.simple_spinner_item, categorias);
        spCategoriaProducto.setAdapter(adapter);
    }



    public void guardarProducto(){
        p = new Producto();
        a = new Almacen();
        cp = new ControllerProducto();
        p.setCategoria(spCategoriaProducto.getSelectedItem().toString());
        p.setEstatus(1);
        p.setMarca(txtMarcaProducto.getText().toString());
        p.setNombre(txtNombreProducto.getText().toString());
        p.setPrecio(Float.parseFloat(txtPrecioProducto.getText().toString()));
        a = ((Almacen)spAlmacenProducto.getSelectedItem());
        p.setAlmacen(a);
        cp.guardarProducto(this,p);
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
    public void llamarDialog(){
        DialogProducto dp = new DialogProducto();
        dp.show( getFragmentManager(), "missiles");
    }
}
