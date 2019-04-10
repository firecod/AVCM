package com.firecod.avcm_android.fragmentsAlmacen;

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
import com.firecod.avcm_android.components.TableView.TableAdapterAlmacen;
import com.firecod.avcm_android.components.TableView.TableAdapterListenerAlmacen;
import com.firecod.avcm_android.components.TableView.TableAdapterListenerProducto;
import com.firecod.avcm_android.components.TableView.TableAdapterProducto;
import com.firecod.avcm_android.core.ControllerAlmacen;
import com.firecod.avcm_android.core.ControllerProducto;
import com.firecod.avcm_android.fragmentsAlmacen.alertDialog.DialogAlmacen;
import com.firecod.avcm_android.fragmentsProducto.CatalogoProducto;
import com.firecod.avcm_android.fragmentsProducto.alertDialog.DialogProducto;
import com.google.gson.Gson;

import java.util.List;

public class CatalogoAlmacen extends Fragment {
    private TableView aTableView;
    private TableAdapterAlmacen aTableAdapter;
    private ProgressBar mProgressBar;
    private ControllerAlmacen ca;
    private Gson gson;
    private String urlGlobal ="http://192.168.43.16:8084/AVCM_WEB/restAlmacen/";


    private static final String LOG_TAG = CatalogoProducto.class.getSimpleName();


    public CatalogoAlmacen()  {

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

        aTableView = view.findViewById(R.id.my_TableView);

        initializeTableView(aTableView);

        ca = new ControllerAlmacen();
        ca.getAllAlmacen(aTableAdapter, this , mProgressBar, aTableView);

        return view;
    }

    private void initializeTableView(TableView tableView){
        // Create TableView Adapter
        aTableAdapter = new TableAdapterAlmacen(getContext());
        tableView.setAdapter(aTableAdapter);
        // Create listener
        tableView.setTableViewListener(new TableAdapterListenerAlmacen(tableView, this));
    }


    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        void onFragmentInteraction(Uri uri);
    }
    public void showProgressBar() {
        mProgressBar.setVisibility(View.VISIBLE);
        aTableView.setVisibility(View.INVISIBLE);
    }

    public  void datos(List almacenes){
        String[] valores = new String[almacenes.size()];
        for(int i = 0; i<almacenes.size(); i++) {
            valores[i] = almacenes.get(i).toString();

        }

        DialogFragment newFragment = DialogAlmacen.newInstance(valores);
        newFragment.show(getFragmentManager(), "dialogAlmacen");

    }
}
