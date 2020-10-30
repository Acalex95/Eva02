package com.example.eva02;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private ProgressBar progreso;
    private Button boton;
    private EditText textnom, textcon;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        progreso = (ProgressBar)findViewById(R.id.pb);
        boton = (Button)findViewById(R.id.btn_iniciar);
        textnom = (EditText)findViewById(R.id.et1);
        textcon = (EditText)findViewById(R.id.etP);


        boton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                new Task().execute();
            }
        });


        progreso.setVisibility(View.INVISIBLE);
    }
    class Task extends AsyncTask<String, Void, String> {

        @Override
        protected void onPreExecute() {
            String usuario = textnom.getText().toString();
            String con = textcon.getText().toString();
            if (usuario != null && con != null && !usuario.isEmpty() && !con.isEmpty() && !usuario.equals("null") && !con.equals("null")) {
                if ((usuario.equals("Android") || usuario.toUpperCase().equals("ANDROID") || usuario.toLowerCase().equals("android")) && con.equals("123")) {
                    progreso.setVisibility(View.VISIBLE);
                    textnom.setText("");
                    textcon.setText("");
                } else {
                    Toast.makeText(getApplicationContext(),"Error de usuario o contraseña",Toast.LENGTH_SHORT).show();
                    cancel(true);
                }
            } else {
                Toast.makeText(getApplicationContext(),"Ingrese usuario y contraseña",Toast.LENGTH_SHORT).show();
                cancel(true);
            }
        }


        @Override
        protected String doInBackground(String... strings) {

            for(int i = 1; i < 5; i++)
            {
                try {
                    Thread.sleep(1000);

                }catch (Exception e)
                {
                    e.printStackTrace();
                }
            }
            return null;
        }


        @Override
        protected void onPostExecute(String s) {

            progreso.setVisibility(View.INVISIBLE);

            Intent i = new Intent(getBaseContext(), Home_act.class);
            startActivity(i);

        }
    }


}