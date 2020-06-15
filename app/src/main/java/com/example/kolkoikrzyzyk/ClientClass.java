package com.example.kolkoikrzyzyk;

import android.bluetooth.BluetoothDevice;
import android.bluetooth.BluetoothSocket;
import android.content.Context;
import android.content.Intent;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.widget.Toast;

import java.io.IOException;

/*public class ClientClass extends Thread {

    BluetoothDevice device;
    BluetoothSocket socket;


    public ClientClass(BluetoothDevice device){
        this.device = device;
        try{
            socket = device.createRfcommSocketToServiceRecord(ServerClass.MY_UUID);
        } catch (IOException e){
            e.printStackTrace();
        }

    }
    public void run(){
        listaUrzadzen.bluetoothAdapter.cancelDiscovery();
        try {
            socket.connect();
           // Toast.makeText(c,,Toast.LENGTH_SHORT).show();
            Log.d("Udało sie","udało");

        } catch (IOException e)
        {
            e.printStackTrace();
        }

    }
}
*/