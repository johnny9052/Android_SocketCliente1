package com.example.johnny.socketcliente1;

import android.os.StrictMode;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.Socket;

public class MainActivity extends AppCompatActivity {

    TextView ip;
    TextView puerto;
    TextView mensaje;
    clsCliente cliente;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ip = (TextView) findViewById(R.id.txtIp);
        puerto = (TextView) findViewById(R.id.txtPuerto);
        mensaje = (TextView) findViewById(R.id.txtMensaje);
        /*StrictMode.setThreadPolicy(new StrictMode.ThreadPolicy.
                Builder().permitNetwork().build());*/
    }


    public void conexion(View view){
        int puerto = Integer.parseInt(this.puerto.getText().toString());
        String ip = this.ip.getText().toString();
        cliente = new clsCliente(this,puerto,ip);
        cliente.execute();
    }
}
