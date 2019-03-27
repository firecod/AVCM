package com.firecod.avcm_android.view;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;

import com.firecod.avcm_android.R;
import com.firecod.avcm_android.model.Vendedor;

public class ActivityLogin extends AppCompatActivity {

    EditText txtUsuario;
    EditText txtPassword;
    Button btnEntrar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        inicializar();
    }

    public void inicializar(){
        txtUsuario = (EditText) findViewById(R.id.txtUsuario);
        txtPassword = (EditText) findViewById(R.id.txtPassword);
        btnEntrar = (Button) findViewById(R.id.btnEntrar);
    }

    public EditText getTxtUsuario() {
        return txtUsuario;
    }

    public void setTxtUsuario(EditText txtUsuario) {
        this.txtUsuario = txtUsuario;
    }

    public EditText getTxtPassword() {
        return txtPassword;
    }

    public void setTxtPassword(EditText txtPassword) {
        this.txtPassword = txtPassword;
    }

    public Button getBtnEntrar() {
        return btnEntrar;
    }

    public void setBtnEntrar(Button btnEntrar) {
        this.btnEntrar = btnEntrar;
    }

    public void login(){

    }

    public void ingresar(Vendedor v)
    {

    }
    public void ingresar(Cliente c){
        Intent i = new Intent(this, ActivityMainVendedor.class);
        i.putExtra("cliente", o);
        startActivity(i);
    }
}
