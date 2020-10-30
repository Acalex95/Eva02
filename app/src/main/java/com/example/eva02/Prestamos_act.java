package com.example.eva02;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;

import java.util.ArrayList;

import Clases.Cliente;
import Clases.Creditos;

public class Prestamos_act extends AppCompatActivity {
    private Spinner spin, spin1;
    private Button boton, boton1;
    private TextView text;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_prestamos_act);

        spin = (Spinner)findViewById(R.id.spn);
        spin1 = (Spinner)findViewById(R.id.spn1);
        boton = (Button)findViewById(R.id.btn_iniciar);
        boton1 = (Button)findViewById(R.id.btn2);
        text = (TextView)findViewById(R.id.tv_con);

        ArrayList<String> listaClientes = (ArrayList<String>) getIntent().getSerializableExtra("listaClientes");
        ArrayAdapter<String> adapt = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1,listaClientes);
        spin.setAdapter(adapt);

        ArrayList<String> listaCreditos = (ArrayList<String>)getIntent().getSerializableExtra("listaCreditos");
        ArrayAdapter<String> adap = new ArrayAdapter<String>(this,  android.R.layout.simple_list_item_1,listaCreditos);
        spin1.setAdapter(adap);


    }
    public void CalcularPrestamo(View v){

        String clientes = spin.getSelectedItem().toString();
        String creditos = spin1.getSelectedItem().toString();

        Creditos cre = new Creditos();
        Cliente c = new Cliente();

        int resultHipoteca = c.getAxel() + cre.getCreditoHipotecario();
        int resultAutomotriz = c.getAxel() + cre.getCreditoAutomotriz();
        int resulHipot = c.getRoxana() + cre.getCreditoHipotecario();
        int resultAuto = c.getRoxana() + cre.getCreditoAutomotriz();

        if (clientes.equals("Axel") && creditos.equals("Credito Hipotecario")){

            text.setText("Saldo final: " + resultHipoteca);
        }
        if (clientes.equals("Axel") && creditos.equals("Credito Automotriz")){

            text.setText(("Saldo final: " + resultAutomotriz));
        }
        if (clientes.equals("Roxana") && creditos.equals("Credito Hipotecario")){

            text.setText(("Saldo final: " + resulHipot));
        }
        if (clientes.equals("Roxana") && creditos.equals("Credito Automotriz")){

            text.setText(("Saldo final: " + resultAuto));
        }

    }


    public void CalcularDeuda(View v){

        String clientes = spin.getSelectedItem().toString();
        String creditos = spin1.getSelectedItem().toString();

        Creditos cre = new Creditos();
        Cliente c = new Cliente();
        int n1 = 12;
        int n2 = 8;

        int resultHipoteca = (c.getAxel() + cre.getCreditoHipotecario()) / n1;
        int resultAutomotriz = (c.getAxel() + cre.getCreditoAutomotriz()) / n2;
        int resulHipot = (c.getRoxana() + cre.getCreditoHipotecario()) / n1;
        int resultAuto = (c.getRoxana() + cre.getCreditoAutomotriz()) / n2;


        if (clientes.equals("Axel") && creditos.equals("Credito Hipotecario")){

            text.setText("Cuotas a pagar: " + resultHipoteca);
        }
        if (clientes.equals("Axel") && creditos.equals("Credito Automotriz")){

            text.setText(("Cuotas a pagar: " + resultAutomotriz));
        }
        if (clientes.equals("Roxana") && creditos.equals("Credito Hipotecario")){

            text.setText(("Cuotas a pagar: " + resulHipot));
        }
        if (clientes.equals("Roxana") && creditos.equals("Credito Automotriz")){

            text.setText(("Cuotas a pagar: " + resultAuto));
        }

    }
}