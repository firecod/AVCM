package com.firecod.avcm_android.fragmentsAlmacen;

import android.net.Uri;
import android.os.Bundle;
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
import com.firecod.avcm_android.components.TableView.TableAdapterAlmacen;
import com.firecod.avcm_android.core.ControllerAlmacen;
import com.firecod.avcm_android.model.Almacen;
import com.google.gson.Gson;

import org.json.JSONArray;

import java.util.ArrayList;

public class CatalogoAlmacen {

    private TableView pTableView;
    private TableAdapterAlmacen pTableAdapter;
    private ProgressBar mProgressBar;
    private ControllerAlmacen ca;
    private Gson gson;
    private String urlGlobal ="http://192.168.43.16:8084/AVCM_WEB/restAlmacen/";

    private static final String LOG_TAG = CatalogoAlmacen.class.getSimpleName();


    public CatalogoAlamacen(){

    }

    @Override
    public void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle
            savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_catalogo_almacen, container, false);

        mProgressBar = view.findViewById(R.id.progressBar);

        pTableView = view.findViewById(R.id.my_TableView);

        initializeTableView(pTableView);
        // initialize ViewModel
        JsonArrayRequest sr = new JsonArrayRequest(
                Request.Method.GET, //GET or POST
                urlGlobal + "getAllAlmacen?estatus=1", //URL
                null,
                new Response.Listener<JSONArray>() {
                    @Override
                    public void onResponse(JSONArray response) {
                        try {
                            gson = new Gson();
                            ArrayList<Almacen> almacenes = new ArrayList<>();
                            Almacen a;

                            for(int i = 0; i<response.length(); i++){
                                a = gson.fromJson(response.get(i).toString(), Almacen.class);
                               almacenes.add(a);

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

    private void initializeTableView(TableView tableView){

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
