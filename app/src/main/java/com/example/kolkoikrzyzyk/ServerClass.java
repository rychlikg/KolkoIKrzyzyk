package com.example.kolkoikrzyzyk;

import android.app.Notification;
import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.bluetooth.BluetoothServerSocket;
import android.bluetooth.BluetoothSocket;
import android.content.BroadcastReceiver;
import android.os.Message;

import java.io.IOException;
import java.util.UUID;

/*class ServerClass extends Thread {

    BluetoothServerSocket serverSocket;
    private static String APP_NAME = "Kolko i Krzyzk";
    public static UUID MY_UUID = UUID.fromString("398a36e6-d5f7-4340-93af-589b39e28968");

    public ServerClass(){
        try{
            serverSocket = listaUrzadzen.bluetoothAdapter.listenUsingRfcommWithServiceRecord(APP_NAME,MY_UUID);
        } catch (IOException e){
            e.printStackTrace();
        }
    }

    public void  run()
    {
        BluetoothSocket socket = null;

        while (socket == null)
        {
            try{
                socket = serverSocket.accept();
            } catch (IOException e) {
                e.printStackTrace();
            }
            if(socket != null)
            {

            }
        }
    }

}*/
