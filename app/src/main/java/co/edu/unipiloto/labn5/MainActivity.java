package co.edu.unipiloto.labn5;


import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;


public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private EditText txtUsuario, txtContrasena;
    private Button btnIngreso, btnRegistro;
    private DatabaseHelper db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        txtUsuario = (EditText) findViewById(R.id.txt_usuario);
        txtContrasena = (EditText) findViewById(R.id.txt_contrasena);
        btnIngreso = (Button) findViewById(R.id.boton_ingreso);
        btnRegistro = (Button) findViewById(R.id.boton_registro);
        btnIngreso.setOnClickListener(this);
        btnRegistro.setOnClickListener(this);
        db = new DatabaseHelper(this);

    }

    @Override
    public void onClick(View v) {
        if (v.getId() == btnIngreso.getId()) {
            String respuesta = this.db.ingreso(txtUsuario.getText().toString(), txtContrasena.getText().toString());
            if (respuesta.equals("Usuario Inexistente")) {
                Toast.makeText(MainActivity.this, "Usuario Inexistente", Toast.LENGTH_SHORT).show();
            } else if (respuesta.equals("Contraseña Incorrecta")) {
                Toast.makeText(MainActivity.this, "Contraseña Incorrecta", Toast.LENGTH_SHORT).show();
            } else {
                Toast.makeText(MainActivity.this, respuesta, Toast.LENGTH_SHORT).show();
            }

        } else if (v.getId() == btnRegistro.getId()) {
            Intent registro = new Intent(MainActivity.this, Registro.class);
            startActivity(registro);
        }
    }
}