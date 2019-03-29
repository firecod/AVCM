package com.firecod.avcm_android.adapters;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import java.util.ArrayList;
import java.util.List;

public class SectionsAdapter extends FragmentStatePagerAdapter {

    private final List<Fragment> listaFragmentos = new ArrayList<>();
    private final List<String> listaTitulos = new ArrayList<>();

    public SectionsAdapter(FragmentManager fm) {
        super(fm);
    }

    public void addFragment (Fragment fragment, String titulo){
        listaFragmentos.add(fragment);
        listaTitulos.add(titulo);
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return listaTitulos.get(position);
    }

    @Override
    public Fragment getItem(int position) {
        return listaFragmentos.get(position);
    }

    @Override
    public int getCount() {
        return listaFragmentos.size();
    }
}
