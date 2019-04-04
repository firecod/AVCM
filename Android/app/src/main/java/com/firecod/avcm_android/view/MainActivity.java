package com.firecod.avcm_android.view;

import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;

import com.firecod.avcm_android.R;
import com.firecod.avcm_android.clases.Utilidades;
import com.firecod.avcm_android.fragmentsAlmacen.CatalogoAlmacen;
import com.firecod.avcm_android.fragmentsAlmacen.ContenedorAlmacen;
import com.firecod.avcm_android.fragmentsAlmacen.FormularioAlmacen;
import com.firecod.avcm_android.fragmentsCliente.CatalogoCliente;
import com.firecod.avcm_android.fragmentsCliente.ContenedorCliente;
import com.firecod.avcm_android.fragmentsCliente.DescripcionCliente;
import com.firecod.avcm_android.fragmentsCliente.FormularioCliente;
import com.firecod.avcm_android.fragmentsProducto.ContenedorProducto;
import com.firecod.avcm_android.fragmentsProducto.FormularioProducto;
import com.firecod.avcm_android.fragmentsProducto.CatalogoProducto;


public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener,
                    ContenedorCliente.OnFragmentInteractionListener,
                    FormularioCliente.OnFragmentInteractionListener,
                    CatalogoCliente.OnFragmentInteractionListener,
                    DescripcionCliente.OnFragmentInteractionListener,
                    ContenedorProducto.OnFragmentInteractionListener,
                    CatalogoProducto.OnFragmentInteractionListener,
                    FormularioProducto.OnFragmentInteractionListener,
                    FormularioAlmacen.OnFragmentInteractionListener,
                    CatalogoAlmacen.OnFragmentInteractionListener,
                    ContenedorAlmacen.OnFragmentInteractionListener


                    {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        Bundle bundle = getIntent().getExtras();
        String valorRecibido= getIntent().getStringExtra("dato_bundle");
        /*
        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
*/
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        if (Utilidades.pantalla==true){
            Fragment fragment = new ContenedorCliente();
            getSupportFragmentManager().beginTransaction().replace(R.id.contant_main, fragment).commit();
        }else{
            Utilidades.pantalla=false;
        }

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        Fragment fragmento = null;
        boolean fragSeleccionado = false;

        if (id == R.id.nav_Cliente) {
            fragmento = new ContenedorCliente();
            fragSeleccionado = true;
        } else if (id == R.id.nav_Vendedor) {

        } else if (id == R.id.nav_Producto) {
            fragmento = new ContenedorProducto();
            fragSeleccionado = true;

        } else if (id == R.id.nav_Almacen) {
            fragmento = new ContenedorAlmacen();
            fragSeleccionado = true;

        } else if (id == R.id.nav_share) {

        } else if (id == R.id.nav_send) {

        }

        if(fragSeleccionado == true){
            getSupportFragmentManager().beginTransaction().replace(R.id.contant_main, fragmento).commit();
        }


        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    @Override
    public void onFragmentInteraction(Uri uri) {

    }

    /**
     * Called when pointer capture is enabled or disabled for the current window.
     *
     * @param hasCapture True if the window has pointer capture.
     */
    @Override
    public void onPointerCaptureChanged(boolean hasCapture) {

    }



}
