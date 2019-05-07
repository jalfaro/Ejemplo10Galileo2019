package com.amalgamsoft.contatos;

import android.app.ProgressDialog;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.amalgamsoft.contatos.data.Contacto;
import com.amalgamsoft.contatos.utilities.APIUtility;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    private EditText nombre, numero;
    private Button guardar, consulta;
    private ImageView foto;
    private ProgressDialog dialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        nombre = findViewById(R.id.txtNombre);
        numero = findViewById(R.id.txtTelefono);
        guardar = findViewById(R.id.btnGrabar);
        consulta = findViewById(R.id.btnConsulta);
        foto = findViewById(R.id.foto);

        guardar.setOnClickListener(this);
        consulta.setOnClickListener(this);
        foto.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        Intent intent;
        switch (v.getId()) {
            case R.id.btnGrabar :
                Contacto item;
                dialog = new ProgressDialog(this);
                dialog.setMessage("Grabando item");
                dialog.show();
                item = new Contacto();
                item.setNombre(nombre.getText().toString());
                item.setTelefono(numero.getText().toString());
                APIUtility.grabarContacto(this, item);
                break;
            case R.id.btnConsulta :
                intent = new Intent(this, ListadoContactos.class);
                startActivity(intent);
                break;
            case R.id.foto :
                break;
        }
    }

    public void grabadoExitoso(String message) {
        dialog.dismiss();
        Toast.makeText(this, message, Toast.LENGTH_LONG).show();
    }
}
