package com.example.kolkoikrzyzyk;

import android.bluetooth.BluetoothSocket;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

class SendRecive extends Thread {
    BluetoothSocket bluetoothSocket;
    InputStream inputStream;
    OutputStream outputStream;

    public SendRecive(BluetoothSocket socket) {
        bluetoothSocket = socket;
        InputStream tempIn = null;
        OutputStream tempOut = null;

        try {
            tempIn = bluetoothSocket.getInputStream();
        } catch (IOException e) {
            e.printStackTrace();
        }

        try {
            tempOut = bluetoothSocket.getOutputStream();
        } catch (IOException e) {
            e.printStackTrace();
        }
        inputStream = tempIn;
        outputStream = tempOut;
    }

    public void run() {
        byte[] buffer = new byte[1024];
        int bytes;

        while (true) {
            try {
                bytes = inputStream.read(buffer);
                BthGame.handler.obtainMessage(1,bytes,-1,buffer).sendToTarget();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public void write(byte[] bytes) {
        try {
            outputStream.write(bytes);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

