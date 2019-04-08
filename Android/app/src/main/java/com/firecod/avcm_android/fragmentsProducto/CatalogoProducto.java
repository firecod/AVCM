package com.firecod.avcm_android.fragmentsProducto;

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
import com.firecod.avcm_android.components.TableView.TableAdapterProducto;
import com.firecod.avcm_android.core.ControllerProducto;
import com.firecod.avcm_android.fragmentsProducto.alertDialog.DialogProducto;
import com.google.gson.Gson;

import java.util.List;


public class CatalogoProducto extends Fragment{

    private TableView pTableView;
    private TableAdapterProducto pTableAdapter;
    private ProgressBar mProgressBar;
    private ControllerProducto cp;
    private Gson gson;
    private String urlGlobal ="http://192.168.43.16:8084/AVCM_WEB/restProducto/";


    private static final String LOG_TAG = CatalogoProducto.class.getSimpleName();


    public CatalogoProducto()  {

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

        cp = new ControllerProducto();
        cp.getAllProducto(pTableAdapter, this , mProgressBar, pTableView);

        return view;
    }

    private void initializeTableView(TableView tableView){
        // Create TableView Adapter
        pTableAdapter = new TableAdapterProducto(getContext());
        tableView.setAdapter(pTableAdapter);
        // Create listener
        tableView.setTableViewListener(new TableAdapterListenerProducto(tableView, this));
    }




    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        void onFragmentInteraction(Uri uri);
    }
    public void showProgressBar() {
        mProgressBar.setVisibility(View.VISIBLE);
        pTableView.setVisibility(View.INVISIBLE);
    }

    public void hideProgressBar() {
        mProgressBar.setVisibility(View.INVISIBLE);
        pTableView.setVisibility(View.VISIBLE);
    }


    public  void datos(List productos){
        String[] valores = new String[productos.size()];
        for(int i = 0; i<productos.size(); i++) {
            valores[i] = productos.get(i).toString();

        }
        DialogFragment newFragment = DialogProducto.newInstance(valores);
        newFragment.show(getFragmentManager(), "dialog");
    }

}
