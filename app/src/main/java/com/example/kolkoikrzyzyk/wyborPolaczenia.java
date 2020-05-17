package com.example.kolkoikrzyzyk;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.bluetooth.BluetoothAdapter;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class wyborPolaczenia extends AppCompatActivity {

    Button bluetooth;
    BluetoothAdapter bluetoothAdapter;
    Intent btEnableIntent;
    Intent intent;
    int requestCodeForEnable;
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_wybor_polaczenia);

        bluetooth = findViewById(R.id.button6);
        bluetoothAdapter = BluetoothAdapter.getDefaultAdapter();
        btEnableIntent = new Intent(BluetoothAdapter.ACTION_REQUEST_ENABLE);
        requestCodeForEnable = 1;
        bluetoothON();

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        if(requestCode == requestCodeForEnable){
            if(resultCode == RESULT_OK)
            {
                Toast.makeText(getApplicationContext(),"Bluetooth aktywne",Toast.LENGTH_SHORT).show();
                intent = new Intent(this,listaUrzadzen.class);
                startActivity(intent);

            }
            else{
                Toast.makeText(getApplicationContext(),"Bluetooth nieaktywne",Toast.LENGTH_SHORT).show();
            }
        }
    }

    private void bluetoothON() {
        bluetooth.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(bluetoothAdapter == null) {
                    Toast.makeText(getApplicationContext(),"Bluetooth nie wspierany na tym urządzeniu",Toast.LENGTH_LONG).show();
                }
                else{
                    if(!bluetoothAdapter.isEnabled()) {
                        startActivityForResult(btEnableIntent,requestCodeForEnable);
                    }
                    else {
                        tolist();
                    }
                }
            }

        });
    }
    private void tolist() {
        intent = new Intent(this,listaUrzadzen.class);
        startActivity(intent);
    }
    public void Powrót(View view)
    {
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }
}
