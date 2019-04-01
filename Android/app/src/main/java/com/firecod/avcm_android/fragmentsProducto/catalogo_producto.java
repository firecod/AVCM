package com.firecod.avcm_android.fragmentsProducto;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.VolleyLog;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;
import com.evrencoskun.tableview.TableView;
import com.firecod.avcm_android.R;
import com.firecod.avcm_android.components.TableView.ProductoViewModel;
import com.firecod.avcm_android.components.TableView.TableAdapterListenerProducto;
import com.firecod.avcm_android.components.TableView.TableAdapterProducto;
import com.firecod.avcm_android.core.ControllerProducto;
import com.firecod.avcm_android.model.Producto;
import com.firecod.avcm_android.view.ActivityProducto;
import com.google.gson.Gson;

import org.json.JSONArray;

import java.util.ArrayList;
import java.util.List;


public class catalogo_producto extends Fragment {

    private TableView pTableView;
    private TableAdapterProducto pTableAdapter;
    private ProgressBar mProgressBar;
    private ControllerProducto cp;
    private Gson gson;
    private String url ="http://192.168.0.106:8084/AVCM_WEB/restProducto/";

    private static final String LOG_TAG = catalogo_producto.class.getSimpleName();


    public catalogo_producto(){

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
        // initialize ViewModel
        JsonArrayRequest sr = new JsonArrayRequest(
                Request.Method.GET, //GET or POST
                url + "getAllProducto?estatus=1", //URL
                null,
                new Response.Listener<JSONArray>() {
                    @Override
                    public void onResponse(JSONArray response) {
                        try {
                            gson = new Gson();
                            ArrayList<Producto> productos = new ArrayList<>();
                            Producto p;

                            for(int i = 0; i<response.length(); i++){
                                p = gson.fromJson(response.get(i).toString(), Producto.class);
                                productos.add(p);
                                System.out.println("valotes" + productos.get(i).getNombre());
                            }
                            if(productos != null && productos.size()>0){
                                // set the list on TableViewModel
                                pTableAdapter.setUserList(productos);
                                hideProgressBar();
                            }
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                VolleyLog.d("Error: " + error.getMessage());
            }
        }
        ) ;
        RequestQueue queue = Volley.newRequestQueue(this.getContext());
        queue.add(sr);





        return view;
    }

    public void getAll(  ) {


    }

    private void initializeTableView(TableView tableView){
       ProductoViewModel pvm = new ProductoViewModel();
        // Create TableView Adapter

        pTableAdapter = new TableAdapterProducto(getContext());
        tableView.setAdapter(pTableAdapter);

        // Create listener
        tableView.setTableViewListener(new TableAdapterListenerProducto(tableView));
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

}
