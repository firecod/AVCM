package com.firecod.avcm_android.fragmentsAlmacen;

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
import com.firecod.avcm_android.fragmentsProducto.CatalogoProducto;
import com.firecod.avcm_android.fragmentsProducto.ContenedorProducto;
import com.firecod.avcm_android.fragmentsProducto.FormularioProducto;

public class ContenedorAlmacen extends Fragment {
    View vista;
    private AppBarLayout appBar;
    private TabLayout pestañas;
    private ViewPager viewPager;
    private ContenedorAlmacen.OnFragmentInteractionListener mListener;

    public ContenedorAlmacen() {
        // Required empty public constructor
    }

    public static ContenedorAlmacen newInstance(String param1, String param2) {
        ContenedorAlmacen fragment = new ContenedorAlmacen();
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
        vista = inflater.inflate(R.layout.fragment_contenedor_almacen, container, false);

        if (Utilidades.rotacion==0){
            View parent= (View) container.getParent();

            if(appBar == null){
                appBar = (AppBarLayout) parent.findViewById(R.id.appBar);
                pestañas = new TabLayout(getActivity());
                appBar.addView(pestañas);

                viewPager = (ViewPager) vista.findViewById(R.id.idViewPagerInfoAlmacen);
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
        adapter.addFragment(new FormularioAlmacen(), "Formulario Almacén");
        adapter.addFragment(new CatalogoAlmacen(), "Catálogo Almacén");


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
