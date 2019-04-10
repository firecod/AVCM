package com.firecod.avcm_android.fragmentsCliente;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;

import com.evrencoskun.tableview.TableView;
import com.firecod.avcm_android.R;
import com.firecod.avcm_android.components.TableView.TableAdapterCliente;
import com.firecod.avcm_android.components.TableView.TableAdapterListenerCliente;
import com.firecod.avcm_android.components.TableView.TableAdapterListenerProducto;
import com.firecod.avcm_android.components.TableView.TableAdapterProducto;
import com.firecod.avcm_android.core.ControllerCliente;
import com.firecod.avcm_android.core.ControllerProducto;
import com.firecod.avcm_android.fragmentsCliente.alertDialog.DialogCliente;
import com.firecod.avcm_android.fragmentsProducto.CatalogoProducto;
import com.firecod.avcm_android.fragmentsProducto.alertDialog.DialogProducto;
import com.google.gson.Gson;

import java.util.List;

public class CatalogoCliente extends Fragment {

    private TableView pTableView;
    private TableAdapterCliente pTableAdapter;
    private ProgressBar mProgressBar;
    private ControllerCliente cp;
    private Gson gson;
    private String urlGlobal ="http://192.168.43.16:8084/AVCM_WEB/restCliente/";


    private static final String LOG_TAG = CatalogoCliente.class.getSimpleName();


    public CatalogoCliente()  {

    }


    @Override
    public void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle
            savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_catalogo_producto, container, false);

        mProgressBar = view.findViewById(R.id.progressBar);

        pTableView = view.findViewById(R.id.my_TableView);

        initializeTableView(pTableView);

        cp = new ControllerCliente();
        cp.getAllCliente(pTableAdapter, this , mProgressBar, pTableView);

        return view;
    }

    private void initializeTableView(TableView tableView){
        // Create TableView Adapter
        pTableAdapter = new TableAdapterCliente(getContext());
        tableView.setAdapter(pTableAdapter);
        // Create listener
        tableView.setTableViewListener(new TableAdapterListenerCliente(tableView, this));
    }

    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        void onFragmentInteraction(Uri uri);
    }


    public void datos(List clientes){
        String[] valores = new String[clientes.size()];
        System.out.println("lenght lista clientes " + clientes.size());
        for(int i = 0; i<clientes.size(); i++) {
            valores[i] = clientes.get(i).toString();
        }
        System.out.println("lenght valores" + valores.length);
        DialogFragment newFragment = DialogCliente.newInstance(valores);
        newFragment.show(getFragmentManager(), "dialogCliente");
    }

}
