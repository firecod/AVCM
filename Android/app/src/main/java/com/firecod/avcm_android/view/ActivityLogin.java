package com.firecod.avcm_android.view;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.firecod.avcm_android.R;
import com.firecod.avcm_android.core.ControllerLogin;
import com.firecod.avcm_android.model.Cliente;
import com.firecod.avcm_android.model.Vendedor;

public class ActivityLogin extends AppCompatActivity {

    EditText txtUsuario;
    EditText txtPassword;
    Button btnEntrar;

    ControllerLogin cl = new ControllerLogin();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        setTheme(R.style.AppTheme);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        inicializar();
    }

    public void inicializar(){
        txtUsuario = (EditText) findViewById(R.id.txtUsuario);
        txtPassword = (EditText) findViewById(R.id.txtPassword);
        btnEntrar = (Button) findViewById(R.id.btnEntrar);

        btnEntrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                entrar();
            }
        });
    }

    public EditText getTxtUsuario() {
        return txtUsuario;
    }

    public EditText getTxtPassword() {
        return txtPassword;
    }

    public Button getBtnEntrar() {
        return btnEntrar;
    }

    public void entrar(){
        cl.login(this);
    }

    public void ingresar(Vendedor v)
    {
        Intent i = new Intent(this, MainActivity.class);
        i.putExtra("nomVendedor", v.getPersona().getNombre());
        startActivity(i);
    }

    public void ingresar(Cliente c)
    {
        Intent i = new Intent(this, ActivityMainCliente.class);
        i.putExtra("nomCliente", c.getPersona().getNombre());
        startActivity(i);
    }
}
