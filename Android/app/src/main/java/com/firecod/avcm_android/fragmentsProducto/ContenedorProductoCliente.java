package com.firecod.avcm_android.fragmentsProducto;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.firecod.avcm_android.R;
import com.firecod.avcm_android.adapters.SectionsAdapter;
import com.firecod.avcm_android.clases.Utilidades;

public class ContenedorProductoCliente extends Fragment {

    View vista;
    private AppBarLayout appBar;
    private TabLayout pestañas;
    private ViewPager viewPager;
    private OnFragmentInteractionListener mListener;

    public ContenedorProductoCliente() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment ContenedorProducto.
     */
    // TODO: Rename and change types and number of parameters
    public static ContenedorProductoCliente newInstance(String param1, String param2) {
        ContenedorProductoCliente fragment = new ContenedorProductoCliente();
        Bundle args = new Bundle();

        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        vista = inflater.inflate(R.layout.fragment_contenedor_producto, container, false);

        if (Utilidades.rotacion==0){
            View parent= (View) container.getParent();

            if(appBar == null){
                appBar = (AppBarLayout) parent.findViewById(R.id.appBar);
                pestañas = new TabLayout(getActivity());
                appBar.addView(pestañas);

                viewPager = (ViewPager) vista.findViewById(R.id.idViewPagerInfoProducto);
                llenarViewPager(viewPager);

                viewPager.addOnPageChangeListener(new ViewPager.SimpleOnPageChangeListener(){
                    @Override
                    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
                        super.onPageScrolled(position, positionOffset, positionOffsetPixels);
                    }
                });
                pestañas.setupWithViewPager(viewPager);
            }
            pestañas.setTabGravity(TabLayout.GRAVITY_FILL);
        }else{
            Utilidades.rotacion=1;
        }

        return vista;
    }

    private void llenarViewPager(ViewPager viewPager) {
        SectionsAdapter adapter = new SectionsAdapter(getFragmentManager());
        adapter.addFragment(new CatalogoProductoCompra(), "Catálogo Producto");


        viewPager.setAdapter(adapter);
    }

    // TODO: Rename method, update argument and hook method into UI event
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
    public void onDestroyView() {
        super.onDestroyView();
        if(Utilidades.rotacion==0){
            appBar.removeView(pestañas);
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
