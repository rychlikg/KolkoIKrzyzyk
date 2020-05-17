package com.example.kolkoikrzyzyk;

import androidx.appcompat.app.AppCompatActivity;

import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Set;

public class listaUrzadzen extends AppCompatActivity {

    BluetoothAdapter bluetoothAdapter;
    ListView scannList;
    ArrayList<String> list = new ArrayList<String>();
    ArrayAdapter<String> arrayAdapter;
    Button szukaj;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lista_urzadzen);

        bluetoothAdapter = BluetoothAdapter.getDefaultAdapter();
        scannList = findViewById(R.id.listaUrzadzen);
        Toast.makeText(getApplicationContext(),"Początek poszukiwań",Toast.LENGTH_LONG).show();
        szukaj = findViewById(R.id.button8);

        szukaj.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                Toast.makeText(getApplicationContext(),"Początek 222 poszukiwań",Toast.LENGTH_LONG).show();
                bluetoothAdapter.startDiscovery();
            }
        });

        IntentFilter intentFilter = new IntentFilter(BluetoothDevice.ACTION_FOUND);
        registerReceiver(broadcastReceiver,intentFilter);
        arrayAdapter = new ArrayAdapter<String>(getApplicationContext(),android.R.layout.simple_list_item_1, list);
        scannList.setAdapter(arrayAdapter);
    }

    BroadcastReceiver broadcastReceiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            Toast.makeText(getApplicationContext(),"Dalej",Toast.LENGTH_LONG).show();
            String action = intent.getAction();
            if(BluetoothDevice.ACTION_FOUND.equals(action)){
                BluetoothDevice device = intent.getParcelableExtra(BluetoothDevice.EXTRA_DEVICE);
                Toast.makeText(getApplicationContext(),"mam",Toast.LENGTH_LONG).show();
                String dname =device.getName();
                list.add(dname);
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


}
