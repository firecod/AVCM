package com.firecod.avcm_android.fragmentsProducto;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.Toast;

import com.firecod.avcm_android.R;
import com.firecod.avcm_android.core.CodificadorImagenes;
import com.firecod.avcm_android.core.ControllerAlmacen;
import com.firecod.avcm_android.core.ControllerProducto;
import com.firecod.avcm_android.model.Almacen;
import com.firecod.avcm_android.model.Producto;

import java.io.FileNotFoundException;
import java.io.InputStream;


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

    private static final int PICK_IMAGE=100;
    CodificadorImagenes ci;
    ImageView img;
    EditText txtIdProducto;
    EditText txtNombreProducto;
    EditText txtMarcaProducto;
    EditText txtPrecioProducto;
    Spinner spCategoriaProducto;
    Spinner spAlmacenProducto;
    CheckBox cbEstatus;
    Button btnGuardar;
    Button btnEliminar;
    Button btnNuevo;
    Button btnFoto;
    Producto p;
    Almacen a;
    ControllerProducto cp;
    public static String rutaURL;

    public EditText getTxtIdProducto() {
        return txtIdProducto;
    }
    public Spinner getSpAlmacenProducto() {
        return spAlmacenProducto;
    }


    private OnFragmentInteractionListener mListener;


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
        img = view.findViewById(R.id.imgFoto);
        spAlmacenProducto = view.findViewById(R.id.spAlmacenProducto);
        cbEstatus = view.findViewById(R.id.cbEstatus);
        spCategoriaProducto = view.findViewById(R.id.spCategoriaProducto);
        txtIdProducto = view.findViewById(R.id.txtIdProducto);
        txtIdProducto.setEnabled(false);
        txtNombreProducto = view.findViewById(R.id.txtNombreProducto);
        txtMarcaProducto = view.findViewById(R.id.txtMarcaProducto);
        txtPrecioProducto = view.findViewById(R.id.txtPrecioProducto);
        btnGuardar = view.findViewById(R.id.btnGuardar);
        btnNuevo = view.findViewById(R.id.btnNuevo);
        btnNuevo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                limpiarCampos();
            }
        });
        btnGuardar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                guardarProducto();
            }
        });
        btnFoto = view.findViewById(R.id.btnFoto);
        btnFoto.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                abrirGaleria();
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
        ci = new CodificadorImagenes();
        if(txtPrecioProducto.getText().toString().length() > 0) {
            p = new Producto();
            a = new Almacen();
            cp = new ControllerProducto();
            p.setCategoria(spCategoriaProducto.getSelectedItem().toString());
            p.setMarca("" + txtMarcaProducto.getText().toString());
            p.setNombre("" + txtNombreProducto.getText().toString());
            p.setPrecio(Float.parseFloat(txtPrecioProducto.getText().toString()));
            p.setFoto(ci.codificar(((BitmapDrawable) img.getDrawable()).getBitmap()));
            a = ((Almacen) spAlmacenProducto.getSelectedItem());
            p.setAlmacen(a);
            cp.guardarProducto(this, p);
        }else{
            Toast t = new Toast(this.getContext());
            t.makeText(this.getContext(), "Ingrese el precio del producto" , Toast.LENGTH_SHORT).show();
        }
    }

    public void limpiarCampos(){
        txtIdProducto.setText("");
        txtMarcaProducto.setText("");
        txtPrecioProducto.setText("");
        txtNombreProducto.setText("");
    }

    public void abrirGaleria(){
        Intent i = new Intent();
        i.setType("image/*");
        i.setAction(Intent.ACTION_GET_CONTENT);
        startActivityForResult(
                Intent.createChooser(i, "Seleccione una imagen"), PICK_IMAGE);
    }

    public void onActivityResult (int requestCode, int resultCode, Intent data){
        super.onActivityResult(requestCode, resultCode, data);

        Uri uriImage = null;
        Uri image;

        String filepath= null;
        switch (requestCode) {
            case PICK_IMAGE:
                if (resultCode == Activity.RESULT_OK) {
                    image = data.getData();
                    String selectedPath=image.getPath();
                    if (requestCode == PICK_IMAGE) {

                        if (selectedPath != null) {
                            InputStream imageStream = null;
                            try {
                                imageStream = getActivity().getApplicationContext().getContentResolver().openInputStream(image);
                            } catch (FileNotFoundException e) {
                                e.printStackTrace();
                            }

                            // Transformamos la URI de la imagen a inputStream y este a un Bitmap
                            Bitmap bmp = BitmapFactory.decodeStream(imageStream);

                            // Ponemos nuestro bitmap en un ImageView que tengamos en la vista

                            img.setImageBitmap(bmp);

                        }


                        try {
                            ci = new CodificadorImagenes();
                            final InputStream imageStream;
                            imageStream = getActivity().getApplicationContext().getContentResolver().openInputStream(image);
                            final Bitmap selectedImage = BitmapFactory.decodeStream(imageStream);
                            filepath= ci.codificar(selectedImage);
                            rutaURL = filepath;
                        } catch (FileNotFoundException e) {
                            e.printStackTrace();
                        }

                    }
                }
                break;
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
