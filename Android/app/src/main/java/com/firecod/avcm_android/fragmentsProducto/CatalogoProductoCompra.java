package com.firecod.avcm_android.fragmentsProducto;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.ProgressBar;

import com.evrencoskun.tableview.TableView;
import com.firecod.avcm_android.R;
import com.firecod.avcm_android.adapters.AdaptadorProductoCompra;
import com.firecod.avcm_android.components.TableView.TableAdapterListenerProducto;
import com.firecod.avcm_android.components.TableView.TableAdapterProducto;
import com.firecod.avcm_android.core.ControllerProducto;
import com.firecod.avcm_android.fragmentsProducto.alertDialog.DialogProducto;
import com.firecod.avcm_android.model.Producto;
import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link CatalogoProductoCompra.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link CatalogoProductoCompra#newInstance} factory method to
 * create an instance of this fragment.
 */
public class CatalogoProductoCompra extends Fragment {
    // TODO: Rename parameter arguments, choose names that match

    private AdaptadorProductoCompra adapter;
    private ListView lvProductos;
    private ArrayAdapter<Producto> productos;
    private ControllerProducto cp;
    private Gson gson;
    private String urlGlobal ="http://192.168.0.5:8084/AVCM_WEB/restProducto/";

    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private OnFragmentInteractionListener mListener;

    public CatalogoProductoCompra() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment CatalogoProductoCompra.
     */
    // TODO: Rename and change types and number of parameters
    public static CatalogoProductoCompra newInstance(String param1, String param2) {
        CatalogoProductoCompra fragment = new CatalogoProductoCompra();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        ControllerProducto cp = new ControllerProducto();
        View root = inflater.inflate(R.layout.fragment_catalogo_producto_compra, container, false);

        // Instancia del ListView.
        lvProductos = (ListView) root.findViewById(R.id.productos_list);

        // Inicializar el adaptador con la fuente de datos.
        //adapter = new AdaptadorProductoCompra(cp.getAllProducto(this));

        //Relacionando la lista con el adaptador
        lvProductos.setAdapter(cp.getAllProducto(this));

        return root;
    }

    private void initializeListView(ListView listView){
        cp = new ControllerProducto();
        productos =  cp.getAllProducto(this);
    }

    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        void onFragmentInteraction(Uri uri);
    }


    public void datos(List productos){
        String[] valores = new String[productos.size()];
        for(int i = 0; i<productos.size(); i++) {
            valores[i] = productos.get(i).toString();
        }
        DialogFragment newFragment = DialogProducto.newInstance(valores);
        newFragment.show(getFragmentManager(), "dialog");
    }
}
