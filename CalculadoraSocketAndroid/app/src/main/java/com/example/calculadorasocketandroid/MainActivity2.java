package com.example.calculadorasocketandroid;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.net.Socket;

public class MainActivity2 extends AppCompatActivity {

    public static final int PUERTO = 2017;

    private String nuevo;

    EditText num1;
    EditText num2;
    TextView res;
    String numero1;
    String numero2;
    String operandos;
    Socket socketAlServidor = new Socket();
    PrintStream salida;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        num1 = findViewById(R.id.num1);
        num2 = findViewById(R.id.num2);
        res = findViewById(R.id.tvres);

        // Obtener la IP del intent
        nuevo = getIntent().getStringExtra("ip");

        // Conectar al servidor en un AsyncTask
        new ConnectToServerTask().execute();
    }



    public void sumar(View view) {
        numero1 = String.valueOf(num1.getText());
        numero2 = String.valueOf(num2.getText());
        operandos = numero1 + "_" + numero2;

        // Enviar la operación al servidor en un AsyncTask
        new SendOperationTask().execute(operandos);
    }
    public void restar(View view) {
        numero1 = String.valueOf(num1.getText());
        numero2 = String.valueOf(num2.getText());
        operandos = numero1 + "-" + numero2;

        // Enviar la operación al servidor en un AsyncTask
        new SendOperationTask().execute(operandos);
    }
    public void multiplicar(View view) {
        numero1 = String.valueOf(num1.getText());
        numero2 = String.valueOf(num2.getText());
        operandos = numero1 + "m" + numero2;

        // Enviar la operación al servidor en un AsyncTask
        new SendOperationTask().execute(operandos);
    }
    public void dividir(View view) {
        numero1 = String.valueOf(num1.getText());
        numero2 = String.valueOf(num2.getText());
        operandos = numero1 + "/" + numero2;

        // Enviar la operación al servidor en un AsyncTask
        new SendOperationTask().execute(operandos);
    }

    public void salir(View view) {
        // Cerrar la conexión al servidor en un AsyncTask
        new CloseConnectionTask().execute();
        finish();
    }

    private class ConnectToServerTask extends AsyncTask<Void, Void, Void> {

        @Override
        protected Void doInBackground(Void... voids) {
            try {
                // Conectar al servidor con la IP proporcionada
                socketAlServidor = new Socket(nuevo, PUERTO);
            } catch (IOException e) {
                e.printStackTrace();
            }
            return null;
        }
    }

    private class SendOperationTask extends AsyncTask<String, Void, String> {

        @Override
        protected String doInBackground(String... strings) {
            try {
                salida = new PrintStream(socketAlServidor.getOutputStream());
                salida.println(strings[0]);

                InputStreamReader entrada = new InputStreamReader(socketAlServidor.getInputStream());
                BufferedReader bf = new BufferedReader(entrada);

                System.out.println("CLIENTE: Esperando al resultado del servidor...");
                return bf.readLine(); // Espera al resultado del servidor
            } catch (IOException ex) {
                ex.printStackTrace();
                //mostrarErrorConexion();
            }
            return null;
        }

        @Override
        protected void onPostExecute(String resultado) {
            res.setText(resultado);
        }
    }

    private class CloseConnectionTask extends AsyncTask<Void, Void, Void> {

        @Override
        protected Void doInBackground(Void... voids) {
            try {
                salida = new PrintStream(socketAlServidor.getOutputStream());
                salida.println("salir");
                socketAlServidor.close();
            } catch (IOException e) {
                e.printStackTrace();
                //mostrarErrorConexion();
            }
            return null;
        }
    }
}
