package co.edu.unipiloto.labn5;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class Registro extends AppCompatActivity implements View.OnClickListener {

    private EditText txtUsuario, txtNombre, txtCorreo, txtContrasena, txtConfirmarV;
    private Button btnRegistro;
    private RadioButton btmM, btnM;
    private DatabaseHelper db;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.registro);

        this.txtUsuario = (EditText) findViewById(R.id.txt_usuario);
        this.txtNombre = (EditText) findViewById(R.id.txt_nombre_completo);
        this.txtCorreo = (EditText) findViewById(R.id.txt_email);
        this.txtContrasena = (EditText) findViewById(R.id.txt_contrasena);
        this.txtConfirmarV = (EditText) findViewById(R.id.txt_confirmar_contrasena);



        this.btmM = (RadioButton) findViewById(R.id.rBtn_masculino);
        this.btnM = (RadioButton) findViewById(R.id.rBtn_femenino);



        this.btnRegistro = (Button) findViewById(R.id.btn_registro);
        this.db = new DatabaseHelper(this);
        this.btnRegistro.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        if(v.getId() == btnRegistro.getId()){
            if(txtContrasena.getText().toString().equals(txtConfirmarV.getText().toString())) {
                String nombre = txtNombre.getText().toString();
                String usuario = txtUsuario.getText().toString();
                String correo = txtCorreo.getText().toString();
                String genero= "X";
                if(btmM.isChecked()){ genero = "M"; }else if (btnM.isChecked()){ genero = "F"; }
                String contrasena = txtContrasena.getText().toString();
                if(this.db.insertar(new String[]{usuario, nombre, correo,contrasena, genero})){
                    Toast.makeText(co.edu.unipiloto.labn5.Registro.this, "Registrado con exito", Toast.LENGTH_SHORT).show();
                }else{
                    Toast.makeText(co.edu.unipiloto.labn5.Registro.this, "Error", Toast.LENGTH_SHORT).show();
                }
            }
        }

    }
}