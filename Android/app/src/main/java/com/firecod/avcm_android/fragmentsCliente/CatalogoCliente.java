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
import com.google.gson.Gson;

import java.util.List;

public class CatalogoCliente extends Fragment {

    private TableView cTableView;
    private TableAdapterCliente cTableAdapter;
    private ProgressBar mProgressBar;
    private ControllerCliente cc;
    private Gson gson;
    private String urlGlobal ="http://192.168.0.102:8084/AVCM_WEB/restCliente/";


    private OnFragmentInteractionListener mListener;

    public CatalogoCliente() {
        // Required empty public constructor
    }

    public static CatalogoCliente newInstance(String param1, String param2) {
        CatalogoCliente fragment = new CatalogoCliente();
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


        View view = inflater.inflate(R.layout.fragment_catalogo_cliente, container, false);

        mProgressBar = view.findViewById(R.id.progressBar);

        cTableView = view.findViewById(R.id.my_TableView);
        cc = new ControllerCliente();
        //cc.getAllCliente(cTableAdapter, this , mProgressBar, cTableView);
        initializeTableView(cTableView);
        return view;
    }


    private void initializeTableView(TableView tableView){
        // Create TableView Adapter
        cTableAdapter = new TableAdapterCliente(getContext());
        tableView.setAdapter(cTableAdapter);
        // Create listener
        tableView.setTableViewListener(new TableAdapterListenerCliente(tableView, this));
    }

    public void datos(List clientes){
        String[] valores = new String[clientes.size()];
        for(int i = 0; i<clientes.size(); i++) {
            valores[i] = clientes.get(i).toString();
        }
        DialogFragment newFragment = DialogCliente.newInstance(valores);
        newFragment.show(getFragmentManager(), "dialogCliente");
    }

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
