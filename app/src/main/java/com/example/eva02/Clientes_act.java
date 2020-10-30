package com.example.eva02;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import Clases.AdminSQLiteOpenHelper;

public class Clientes_act extends AppCompatActivity {

    private EditText text, text1, text2;
    private Button boton, boton1, boton2, boton3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_clientes_act);
        text = (EditText)findViewById(R.id.et_cod);
        text1 = (EditText)findViewById(R.id.et_nom);
        text2 = (EditText)findViewById(R.id.et_saldo);
        boton = (Button)findViewById(R.id.btn_iniciar);
        boton1 = (Button)findViewById(R.id.btn_iniciar);
        boton2 = (Button)findViewById(R.id.btn2);
        boton3 = (Button)findViewById(R.id.btn3);
    }
    public void AñadirCliente(View v){
        AdminSQLiteOpenHelper admin = new AdminSQLiteOpenHelper(this, "Fichero", null, 1);
        SQLiteDatabase bd = admin.getWritableDatabase();

        if(!text.getText().toString().isEmpty()){

            ContentValues registro = new ContentValues();

            registro.put("codigo", text.getText().toString());
            registro.put("nombre", text1.getText().toString());
            registro.put("saldo", text2.getText().toString());

            bd.insert("clientes", null, registro);
            bd.close();

            Toast.makeText(this,"Se ha guardado el Cliente", Toast.LENGTH_LONG).show();

        }else{
            Toast.makeText(this,"Complete los campos", Toast.LENGTH_LONG).show();
        }
    }
    public void MostrarClliente(View v){
        AdminSQLiteOpenHelper admin = new AdminSQLiteOpenHelper(this, "Fichero", null, 1);
        SQLiteDatabase bd = admin.getWritableDatabase();

        String Codigo = text.getText().toString();
        if (!Codigo.isEmpty()){

            Cursor fila  =  bd.rawQuery("SELECT nombre, saldo From clientes Where codigo=" + Codigo, null);

            if (fila.moveToFirst()) {

                text1.setText(fila.getString(0));
                text2.setText(fila.getString(1));
            }else{
                Toast.makeText(this,"No existe el cliente", Toast.LENGTH_LONG).show();
            }
        }else{

            Toast.makeText(this,"No existe el cliente", Toast.LENGTH_LONG).show();
        }

    }
    public void EliminarCliente(View v){
        AdminSQLiteOpenHelper admin = new AdminSQLiteOpenHelper(this, "Fichero", null, 1);
        SQLiteDatabase bd = admin.getWritableDatabase();

        String Codigo = text.getText().toString();

        bd.delete("clientes", "codigo =" + Codigo, null);
        bd.close();

        Toast.makeText(this,"Se ha eliminado el cliente", Toast.LENGTH_LONG).show();
    }
    public void ActualizarCliente(View v){

        AdminSQLiteOpenHelper admin = new AdminSQLiteOpenHelper(this, "Fichero", null, 1);
        SQLiteDatabase bd = admin.getWritableDatabase();

        String Codigo = text.getText().toString();

        ContentValues cont =  new ContentValues();

        cont.put("codigo", text.getText().toString());
        cont.put("nombre", text1.getText().toString());
        cont.put("saldo", text2.getText().toString());

        bd.update("clientes", cont, "codigo =" +Codigo, null);
        bd.close();

        Toast.makeText(this,"Se ha Actualizado el cliente", Toast.LENGTH_LONG).show();

    }
}