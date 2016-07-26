package com.example.johnny.socketcliente1;

import android.app.Activity;
import android.os.AsyncTask;
import android.widget.ProgressBar;
import android.widget.Toast;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * Created by Johnny on 21/07/2016.
 */
public class clsCliente extends AsyncTask<Void, String, Boolean> {


    private Activity activity;
    private int puerto;
    private String ip;

    public clsCliente(Activity activity, int puerto, String ip) {
        this.activity = activity;
        this.puerto = puerto;
        this.ip = ip;
    }




    /*Antes de iniciar el proceso, reiniciamos la barra de progreso*/
    @Override
    protected void onPreExecute() {

    }


    /*
    * El método doInBackground() se ejecuta en un hilo secundario (por tanto no podremos interactuar
    * con la interfaz), pero sin embargo todos los demás se ejecutan en el hilo principal, lo que
    * quiere decir que dentro de ellos podremos hacer referencia directa a nuestros controles de
    * usuario para actualizar la interfaz. Ademas si se llama al metodo publishProgress() este
    * automáticamente ejecuta el onProgressUpdate() que actualizara la interfaz si es necesario
    * */
    @Override
    protected Boolean doInBackground(Void... params) {
        try {
            //Se inicia el cliente
            publishProgress(ip+"-"+puerto );
            Socket cliente = new Socket(ip, puerto);
            publishProgress("Esperando datos del servidor");
            //Recepcion de datos desde el servidor

            //DATAINPUT STREAM permite la lectura de líneas de texto y tipos de datos
            //primitivos de Java de un modo altamente portable
            DataInputStream flujo = new DataInputStream(cliente.getInputStream());
            publishProgress("Se recibio dato");
            //Se interpreta el dato que mando el server
            publishProgress("Respuesta server: "+flujo.readUTF());
            cliente.close();//Se desconecta el cliente
        } catch (Exception e) {
            publishProgress("Error: "+e.getMessage());
        }



        /*Se le retorna true a la funcion onPostExecute*/
        return true;
    }

    @Override
    protected void onProgressUpdate(String... values) {
        Toast.makeText(activity,values[0], Toast.LENGTH_SHORT).show();
    }


    @Override
    protected void onPostExecute(Boolean result) {
        /*Tan pronto termine el proceso, se muestra un toast en la activity indincando que termino
        * el proceso*/
        if(result)
            Toast.makeText(activity, "Finalizo la comunicacion",
                    Toast.LENGTH_SHORT).show();
    }

    @Override
    protected void onCancelled() {
        /*Si se cancela el proceso se le indica al usuario*/
        Toast.makeText(activity, "Tarea cancelada!",
                Toast.LENGTH_SHORT).show();
    }

}
