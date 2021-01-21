package co.edu.unipiloto.labn5;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.Toast;

public class DatabaseHelper extends SQLiteOpenHelper {

    public static final String NOMBRE_BASE_DE_DATOS = "db";
    public static final String NOMBRE_TABLA = "Usuarios";
    public static final String COL_1 = "USUARIO";
    public static final String COL_2 = "NOMBRE";
    public static final String COL_3 = "CORREO";
    public static final String COL_4 = "CONTRASENA";
    public static final String COL_5 = "GENERO";

    public DatabaseHelper(Context context) {
        super(context, NOMBRE_BASE_DE_DATOS, null, 1);
    }

    public void iniciarDatos() {
        SQLiteDatabase db = this.getWritableDatabase();
        onUpgrade(db, 1, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE " + NOMBRE_TABLA + " (USUARIO TEXT PRIMARY KEY, " +
                "NOMBRE TEXT, CORREO TEXT, CONTRASENA TEXT, GENERO INTEGER);");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        onCreate(db);
    }

    public boolean insertar(String[] datos) {
        SQLiteDatabase db = this.getReadableDatabase();
        ContentValues info = new ContentValues();
        info.put(COL_1, datos[0]);
        info.put(COL_2, datos[1]);
        info.put(COL_3, datos[2]);
        info.put(COL_4, datos[3]);
        info.put(COL_5, datos[4]);

        Cursor res = db.rawQuery("SELECT * FROM " + NOMBRE_TABLA + " WHERE "
                + COL_1 + " = '" + datos[1] + "';", null);
        long resultado = -1;
        if(res.getCount() == 0){
            resultado = db.insert(NOMBRE_TABLA, null, info);
        }
        return (resultado != -1);
    }

    public String ingreso(String usuario, String contrasena) {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor res = db.rawQuery("SELECT * FROM " + NOMBRE_TABLA + " WHERE "
                + COL_1 + " = '" + usuario + "';", null);

        if (res.getCount() == 0) {
            return "Usuario Inexistente";
        } else {
            res.moveToNext();
            if (contrasena.equals(res.getString(3))) {
                return "Acceso Concedido\n " +
                        " Usuario: " + res.getString(0) +
                        " Nombre: " + res.getString(1) +
                        " Email: " + res.getString(2) +
                        " Contraseña: " + res.getString(3) +
                        " Genero: " + res.getString(4);
            } else {
                return "Contraseña Incorrecta";
            }
        }
    }



}
