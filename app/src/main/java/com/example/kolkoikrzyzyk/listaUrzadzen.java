package com.example.kolkoikrzyzyk;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.bluetooth.BluetoothServerSocket;
import android.bluetooth.BluetoothSocket;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.Set;
import java.util.UUID;

public class listaUrzadzen extends AppCompatActivity {

    public static BluetoothAdapter bluetoothAdapter;
    ListView scannList;
    ArrayList<String> nlist = new ArrayList<String>();
    public static ArrayList<BluetoothDevice> dlist = new ArrayList<BluetoothDevice>();
    ArrayAdapter<String> arrayAdapter;
    Button szukaj, a;
    TextView tl;
    boolean polonczone = false;

    private static String APP_NAME = "Kolko i Krzyzk";
    public static UUID MY_UUID = UUID.fromString("398a36e6-d5f7-4340-93af-589b39e28968");

    static SendRecive sendRecive;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lista_urzadzen);
        tl = findViewById(R.id.textView4);

        bluetoothAdapter = BluetoothAdapter.getDefaultAdapter();
        scannList = findViewById(R.id.listaUrzadzen);
        szukaj = findViewById(R.id.button8);
        a = findViewById(R.id.button9);

        Set<BluetoothDevice> bt = bluetoothAdapter.getBondedDevices();
        if(bt.size()>0)
        {
            for(BluetoothDevice device: bt)
            {
                nlist.add(device.getName());
                dlist.add(device);
            }
        }

        szukaj.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                tl.setVisibility(View.VISIBLE);
                scannList.setVisibility(View.VISIBLE);
                Toast.makeText(getApplicationContext(),"Szukam urządzeń",Toast.LENGTH_LONG).show();
                bluetoothAdapter.startDiscovery();

            }
        });

        IntentFilter intentFilter = new IntentFilter(BluetoothDevice.ACTION_FOUND);
        registerReceiver(broadcastReceiver,intentFilter);
        arrayAdapter = new ArrayAdapter<String>(getApplicationContext(),android.R.layout.simple_list_item_1, nlist);
        scannList.setAdapter(arrayAdapter);

        scannList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
               // BthGra(view,position);
                ClientClass clientClass = new ClientClass(listaUrzadzen.dlist.get(position),getApplicationContext());
                clientClass.start();

            }
        });
        a.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ServerClass serverClass = new ServerClass(getApplicationContext());
                serverClass.start();
                Toast.makeText(getApplicationContext(),"Czekam na połączenie",Toast.LENGTH_LONG).show();

            }
        });


    }

    BroadcastReceiver broadcastReceiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            String action = intent.getAction();
            if(BluetoothDevice.ACTION_FOUND.equals(action)){
                BluetoothDevice device = intent.getParcelableExtra(BluetoothDevice.EXTRA_DEVICE);
                String dname =device.getName();
                nlist.add(dname);
                dlist.add(device);
                arrayAdapter.notifyDataSetChanged();
            }
        }
    };

    @Override
    protected void onStop()
    {
        unregisterReceiver(broadcastReceiver);
        super.onStop();
    }

    public class ServerClass extends Thread {

        BluetoothServerSocket serverSocket;
        Context c;


        public ServerClass(Context c){
            this.c = c;
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
                    Intent intent = new Intent(c, BthGame.class);
                    intent.putExtra("typ",1);
                    startActivity(intent);

                } catch (IOException e) {
                    e.printStackTrace();
                }
                if(socket != null)
                {
                    sendRecive = new SendRecive(socket);
                    sendRecive.start();
                }
            }
        }

    }


    public class ClientClass extends Thread {

        BluetoothDevice device;
        BluetoothSocket socket;
        Context c;


        public ClientClass(BluetoothDevice device, Context c){
            this.device = device;
            this.c = c;
            try{
                socket = device.createRfcommSocketToServiceRecord(MY_UUID);
            } catch (IOException e){
                e.printStackTrace();
            }

        }
        public void run(){
            listaUrzadzen.bluetoothAdapter.cancelDiscovery();
            try {
                socket.connect();
                Log.d("Udało sie","udało");
                sendRecive = new SendRecive(socket);
                sendRecive.start();
                Intent intent = new Intent(c, BthGame.class);
                intent.putExtra("typ",2);
                startActivity(intent);

            } catch (IOException e)
            {
                e.printStackTrace();
            }

        }
    }


}
