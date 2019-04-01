package com.firecod.avcm_android.fragmentsProducto;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;

import com.evrencoskun.tableview.TableView;
import com.firecod.avcm_android.R;
import com.firecod.avcm_android.components.TableView.ProductoViewModel;
import com.firecod.avcm_android.components.TableView.TableAdapterListenerProducto;
import com.firecod.avcm_android.components.TableView.TableAdapterProducto;
import com.firecod.avcm_android.model.Producto;


public class catalogo_producto extends Fragment {

    private TableView pTableView;
    private TableAdapterProducto pTableAdapter;
    private ProgressBar mProgressBar;

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
        /*
        MainViewModelFactory factory = InjectorUtils.getMainViewModelFactory(getActivity().getApplicationContext());
        vMainViewModel = ViewModelProviders.of(this, factory).get(MainViewModel.class);

        vMainViewModel.getUserList().observe(this, users -> {

            if(users != null && users.size()>0){
                // set the list on TableViewModel
                mTableAdapter.setUserList(users);

                hideProgressBar();
            }
        });

        // Let's post a request to get the User data from a web server.
        postRequest();
        */
        return view;
    }

    private void initializeTableView(TableView tableView){
       ProductoViewModel pvm = new ProductoViewModel();
        // Create TableView Adapter
        pTableAdapter = new TableAdapterProducto(getContext());
        tableView.setAdapter(pTableAdapter);
        pTableAdapter.setAllItems(pvm.getColumHeaderModeList(), pvm.getRowHeaderModelList(), pvm.getCellModelList());
        // Create listener
        tableView.setTableViewListener(new TableAdapterListenerProducto(tableView));
    }

    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        void onFragmentInteraction(Uri uri);
    }


}
