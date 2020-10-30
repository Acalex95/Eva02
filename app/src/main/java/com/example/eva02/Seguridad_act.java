package com.example.eva02;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.os.MessageQueue;
import android.util.Base64;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.security.MessageDigest;
import java.util.concurrent.ExecutionException;

import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;

public class Seguridad_act extends AppCompatActivity {

    private EditText texto;
    private TextView textV;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_seguridad_act);

        texto = (EditText)findViewById(R.id.et_con);
        textV = (TextView)findViewById(R.id.tv_con);

    }
    private SecretKeySpec generarClave (String password)throws Exception{

        MessageDigest md = MessageDigest.getInstance("SHA-256");
        byte[] clave = password.getBytes("UTF-8");
        clave = md.digest();

        SecretKeySpec claveSecreta = new SecretKeySpec(clave, "AES");
        return claveSecreta;
    }
    public String encriptar(String dato, String password)throws Exception{

        if (!texto.getText().toString().isEmpty()) {

            SecretKeySpec claveSecreta = generarClave(password);
            Cipher c = Cipher.getInstance("AES");
            c.init(Cipher.ENCRYPT_MODE, claveSecreta);

            byte[] claveEncriptadaByte = c.doFinal(dato.getBytes());
            String claveEncriptadaString = Base64.encodeToString(claveEncriptadaByte, Base64.DEFAULT);

            return claveEncriptadaString;

        } else {
            Toast.makeText(this, "Ingrese Contraseña", Toast.LENGTH_LONG).show();
            return null;

        }

    }

    public void Encriptacion(View v){
        try {
            String m = encriptar(texto.getText().toString(), textV.getText().toString());
            textV.setText(m);

        } catch (Exception e) {

            e.printStackTrace();
        }

    }
}