package com.firecod.avcm_android.fragmentsProducto.alertDialog;


import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Spinner;

import com.firecod.avcm_android.R;
import com.firecod.avcm_android.core.ControllerAlmacen;
import com.firecod.avcm_android.core.ControllerProducto;
import com.firecod.avcm_android.model.Almacen;
import com.firecod.avcm_android.model.Producto;

public class DialogProducto extends DialogFragment {
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

    public Spinner getSpAlmacenProducto() {
        return spAlmacenProducto;
    }

    public interface NoticeDialogListener {
        public void onDialogPositiveClick(DialogFragment dialog);
        public void onDialogNegativeClick(DialogFragment dialog);
    }

    // Use this instance of the interface to deliver action events
    NoticeDialogListener mListener;

    // Override the Fragment.onAttach() method to instantiate the NoticeDialogListener
    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        // Verify that the host activity implements the callback interface
        try {
            // Instantiate the NoticeDialogListener so we can send events to the host
            mListener = (NoticeDialogListener) activity;
        } catch (ClassCastException e) {
            // The activity doesn't implement the interface, throw exception
            throw new ClassCastException(activity.toString()
                    + " must implement NoticeDialogListener");
        }
    }
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        // Use the Builder class for convenient dialog construction
        String[] datos = getArguments().getStringArray("valores");

        View content = LayoutInflater.from(getContext()).inflate(R.layout.alertdialog_formulario_producto, null);
        AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
        builder.setView(content);
        builder.setMessage("Modificar Producto")
                .setPositiveButton("xD", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        // Send the positive button event back to the host activity
                        mListener.onDialogPositiveClick(DialogProducto.this);
                    }
                })
                .setNegativeButton(R.string.cancel, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        mListener.onDialogNegativeClick(DialogProducto.this);
                    }
                });
        // Create the AlertDialog object and return it
        //asignarAlmacen();
        inicializar(content, datos);
        return builder.create();
    }

    public static DialogProducto newInstance(String[] valores) {
        DialogProducto f = new DialogProducto();

        Bundle args = new Bundle();
        args.putStringArray("valores", valores);
        f.setArguments(args);
        return f;
    }

    public void asignarAlmacen(){
        ControllerAlmacen ca = new ControllerAlmacen();
        ca.getAllSpinnerProductoEditar(this);

    }

    public int[] agregarDatosSpinner(int p){
        int[] posiciones = new int[2];
        String[] categorias = new String[] {"Produtos de Temporada","Alimentos", "Línea Blanca", "Muebles", "Papeleria y Merceria",
                "Moda", "Bebes", "Entretenimiento", "Herramientas", "Limpieza"};
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this.getContext(),
                android.R.layout.simple_spinner_item, categorias);
        spCategoriaProducto.setAdapter(adapter);

        posiciones[0] = categorias.length;
        posiciones[1] = p;
        return posiciones;
    }

    public void inicializar(View content, String[] datos){
        spAlmacenProducto = content.findViewById(R.id.spAlmacenProducto);
        cbEstatus = content.findViewById(R.id.cbEstatus);
        spCategoriaProducto = content.findViewById(R.id.spCategoriaProducto);
        txtIdProducto = content.findViewById(R.id.txtIdProducto);
        txtIdProducto.setEnabled(false);
        txtNombreProducto = content.findViewById(R.id.txtNombreProducto);
        txtMarcaProducto = content.findViewById(R.id.txtMarcaProducto);
        txtPrecioProducto = content.findViewById(R.id.txtPrecioProducto);
        btnGuardar = content.findViewById(R.id.btnGuardar);
        btnEliminar = content.findViewById(R.id.btnEliminar);
        txtIdProducto.setText(datos[0]);
        txtNombreProducto.setText(datos[1]);
        txtMarcaProducto.setText(datos[2]);
        txtPrecioProducto.setText(datos[3]);

        System.out.println("que es eto " + spCategoriaProducto.getAdapter().getCount());
        //spAlmacenProducto
        //cbEstatus
    }
}
