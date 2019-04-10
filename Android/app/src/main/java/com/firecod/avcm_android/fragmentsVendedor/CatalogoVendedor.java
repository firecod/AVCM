package com.firecod.avcm_android.fragmentsVendedor;

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
import com.firecod.avcm_android.components.TableView.TableAdapterListenerProducto;
import com.firecod.avcm_android.components.TableView.TableAdapterListenerVendedor;
import com.firecod.avcm_android.components.TableView.TableAdapterProducto;
import com.firecod.avcm_android.core.ControllerProducto;
import com.firecod.avcm_android.core.ControllerVendedor;
import com.firecod.avcm_android.fragmentsProducto.CatalogoProducto;
import com.firecod.avcm_android.fragmentsProducto.alertDialog.DialogProducto;
import com.firecod.avcm_android.fragmentsVendedor.alertDialog.DialogVendedor;
import com.google.gson.Gson;

import java.util.List;


public class CatalogoVendedor extends Fragment {

    private TableView vTableView;
    private TableAdapterProducto vTableAdapter;
    private ProgressBar mProgressBar;
    private ControllerVendedor cv;
    private Gson gson;

    private static final String LOG_TAG = CatalogoVendedor.class.getSimpleName();


    public CatalogoVendedor()  {

    }


    @Override
    public void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle
            savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_catalogo_vendedor, container, false);

        mProgressBar = view.findViewById(R.id.progressBar);

        vTableView = view.findViewById(R.id.my_TableView);

        initializeTableView(vTableView);

        cv = new ControllerVendedor();
        //cv.getAllProducto(vTableAdapter, this , mProgressBar, vTableView);

        return view;
    }

    private void initializeTableView(TableView tableView){
        // Create TableView Adapter
        vTableAdapter = new TableAdapterProducto(getContext());
        tableView.setAdapter(vTableAdapter);
        // Create listener
        tableView.setTableViewListener(new TableAdapterListenerVendedor(tableView, this));
    }

    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        void onFragmentInteraction(Uri uri);
    }


    public void datos(List vendedores){
        String[] valores = new String[vendedores.size()];
        for(int i = 0; i<vendedores.size(); i++) {
            valores[i] = vendedores.get(i).toString();
        }
        DialogFragment newFragment = DialogVendedor.newInstance(valores);
        newFragment.show(getFragmentManager(), "dialogVendedor");
    }
}
