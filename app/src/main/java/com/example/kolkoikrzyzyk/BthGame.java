package com.example.kolkoikrzyzyk;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothSocket;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.text.BoringLayout;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class BthGame extends AppCompatActivity {

    static Button pola[][] = new Button[3][3];

    static Boolean kolko = false;
    static Boolean krzyzyk = false;

    static Boolean kolkoWygrywa = false;
    static Boolean krzyzykWygrywa = false;

    static Boolean twojRuch = false;

    static int ruch = 0;
    static int kolkoPunkty = 0;
    static int krzyzykPunkty = 0;
    static int partia = 1;

    static TextView wynikKolko;
    static TextView wynikKrzyzyk;
    static String massage = "";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bth_game);

        wynikKolko = findViewById(R.id.kolkoWynik);
        wynikKrzyzyk = findViewById(R.id.krzyzykWynik);

        int typ = getIntent().getIntExtra("typ",1);
        if(typ == 1)
        {
            kolko = true;
            krzyzyk = false;
            twojRuch = true;
            Toast.makeText(getApplicationContext(),"Twoja figuta to O - zaczynasz",Toast.LENGTH_LONG).show();
        } else if (typ == 2)
        {
            kolko = false;
            krzyzyk = true;
            twojRuch = false;
            Toast.makeText(getApplicationContext(),"Twoja figuta to X - zaczyna przeciwnik",Toast.LENGTH_LONG).show();
        }

        String przycikID;
        int poleID;

        for(int i = 0; i < 3; i++)
        {
            for (int j = 0; j<3; j++)
            {
                przycikID = "pole"+i+j;
                poleID = this.getResources().getIdentifier(przycikID, "id",getPackageName());
                pola[i][j]=findViewById(poleID);
            }
        }

        pola[0][0].setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(twojRuch)
                {
                    if(pola[0][0].getText().toString().equals("")){
                        if(kolko){
                            pola[0][0].setText("O");
                            twojRuch = false;
                            massage = "0,0,O";
                            ruch++;
                            sendMgs();
                            if(czyWygrana()){

                                Toast.makeText(getApplicationContext(),"Kolko wygrywa",Toast.LENGTH_SHORT).show();
                                nowaGra();
                            }
                        } else if(krzyzyk){
                            pola[0][0].setText("X");
                            twojRuch = false;
                            massage = "0,0,X";
                            ruch++;
                            sendMgs();
                            if(czyWygrana()){
                                Toast.makeText(getApplicationContext(),"Krzyżyk wygrywa",Toast.LENGTH_SHORT).show();
                                nowaGra();
                            }
                        }
                    } else Toast.makeText(getApplicationContext(),"Pole już zajęte",Toast.LENGTH_SHORT).show();
                }else Toast.makeText(getApplicationContext(),"Ruch przeciwnika",Toast.LENGTH_SHORT).show();

            }
        });
        pola[0][1].setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(twojRuch)
                {
                    if(pola[0][1].getText().toString().equals("")){
                        if(kolko){
                            pola[0][1].setText("O");
                            twojRuch = false;
                            massage = "0,1,O";
                            ruch++;
                            sendMgs();
                            if(czyWygrana()){

                                Toast.makeText(getApplicationContext(),"Kolko wygrywa",Toast.LENGTH_SHORT).show();
                                nowaGra();
                            }
                        } else if(krzyzyk){
                            pola[0][1].setText("X");
                            twojRuch = false;
                            massage = "0,1,X";
                            ruch++;
                            sendMgs();
                            if(czyWygrana()){
                                Toast.makeText(getApplicationContext(),"Krzyżyk wygrywa",Toast.LENGTH_SHORT).show();
                                nowaGra();
                            }
                        }
                    } else Toast.makeText(getApplicationContext(),"Pole już zajęte",Toast.LENGTH_SHORT).show();
                }else Toast.makeText(getApplicationContext(),"Ruch przeciwnika",Toast.LENGTH_SHORT).show();

            }
        });

        pola[0][2].setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(twojRuch)
                {
                    if(pola[0][2].getText().toString().equals("")){
                        if(kolko){
                            pola[0][2].setText("O");
                            twojRuch = false;
                            massage = "0,2,O";;
                            ruch++;
                            sendMgs();
                            if(czyWygrana()){

                                Toast.makeText(getApplicationContext(),"Kolko wygrywa",Toast.LENGTH_SHORT).show();
                                nowaGra();
                            }
                        } else if(krzyzyk){
                            pola[0][2].setText("X");
                            twojRuch = false;
                            massage = "0,2,X";
                            ruch++;
                            sendMgs();
                            if(czyWygrana()){
                                Toast.makeText(getApplicationContext(),"Krzyżyk wygrywa",Toast.LENGTH_SHORT).show();
                                nowaGra();
                            }
                        }
                    } else Toast.makeText(getApplicationContext(),"Pole już zajęte",Toast.LENGTH_SHORT).show();
                }else Toast.makeText(getApplicationContext(),"Ruch przeciwnika",Toast.LENGTH_SHORT).show();

            }
        });

        pola[1][0].setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(twojRuch)
                {
                    if(pola[1][0].getText().toString().equals("")){
                        if(kolko){
                            pola[1][0].setText("O");
                            twojRuch = false;
                            massage = "1,0,O";
                            ruch++;
                            sendMgs();
                            if(czyWygrana()){

                                Toast.makeText(getApplicationContext(),"Kolko wygrywa",Toast.LENGTH_SHORT).show();
                                nowaGra();
                            } else if (ruch == 9)
                            {
                                Toast.makeText(getApplicationContext(),"Remis",Toast.LENGTH_SHORT).show();
                                ruch = 0;
                                nowaGra();
                            }
                        } else if(krzyzyk){
                            pola[1][0].setText("X");
                            twojRuch = false;
                            massage = "1,0,X";
                            ruch++;
                            sendMgs();
                            if(czyWygrana()){
                                Toast.makeText(getApplicationContext(),"Krzyżyk wygrywa",Toast.LENGTH_SHORT).show();
                                nowaGra();
                            } else if (ruch == 9)
                            {
                                Toast.makeText(getApplicationContext(),"Remis",Toast.LENGTH_SHORT).show();
                                ruch = 0;
                                nowaGra();
                            }
                        }
                    } else Toast.makeText(getApplicationContext(),"Pole już zajęte",Toast.LENGTH_SHORT).show();
                }else Toast.makeText(getApplicationContext(),"Ruch przeciwnika",Toast.LENGTH_SHORT).show();

            }
        });

        pola[1][1].setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(twojRuch)
                {
                    if(pola[1][1].getText().toString().equals("")){
                        if(kolko){
                            pola[1][1].setText("O");
                            twojRuch = false;
                            massage = "1,1,O";
                            ruch++;
                            sendMgs();
                            if(czyWygrana()){

                                Toast.makeText(getApplicationContext(),"Kolko wygrywa",Toast.LENGTH_SHORT).show();
                                nowaGra();
                            } else if (ruch == 9)
                            {
                                Toast.makeText(getApplicationContext(),"Remis",Toast.LENGTH_SHORT).show();
                                ruch = 0;
                                nowaGra();
                            }
                        } else if(krzyzyk){
                            pola[1][1].setText("X");
                            twojRuch = false;
                            massage = "1,1,X";
                            ruch++;
                            sendMgs();
                            if(czyWygrana()){
                                Toast.makeText(getApplicationContext(),"Krzyżyk wygrywa",Toast.LENGTH_SHORT).show();
                                nowaGra();
                            }
                            else if (ruch == 9)
                            {
                                Toast.makeText(getApplicationContext(),"Remis",Toast.LENGTH_SHORT).show();
                                ruch = 0;
                                nowaGra();
                            }
                        }
                    } else Toast.makeText(getApplicationContext(),"Pole już zajęte",Toast.LENGTH_SHORT).show();
                }else Toast.makeText(getApplicationContext(),"Ruch przeciwnika",Toast.LENGTH_SHORT).show();

            }
        });

        pola[1][2].setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(twojRuch)
                {
                    if(pola[1][2].getText().toString().equals("")){
                        if(kolko){
                            pola[1][2].setText("O");
                            twojRuch = false;
                            massage = "1,2,O";
                            ruch++;
                            sendMgs();
                            if(czyWygrana()){

                                Toast.makeText(getApplicationContext(),"Kolko wygrywa",Toast.LENGTH_SHORT).show();
                                nowaGra();
                            }  else if (ruch == 9)
                            {
                                Toast.makeText(getApplicationContext(),"Remis",Toast.LENGTH_SHORT).show();
                                ruch = 0;
                                nowaGra();
                            }
                        } else if(krzyzyk){
                            pola[1][2].setText("X");
                            twojRuch = false;
                            massage = "1,2,X";
                            ruch++;
                            sendMgs();
                            if(czyWygrana()){
                                Toast.makeText(getApplicationContext(),"Krzyżyk wygrywa",Toast.LENGTH_SHORT).show();
                                nowaGra();
                            }  else if (ruch == 9)
                            {
                                Toast.makeText(getApplicationContext(),"Remis",Toast.LENGTH_SHORT).show();
                                ruch = 0;
                                nowaGra();
                            }
                        }
                    } else Toast.makeText(getApplicationContext(),"Pole już zajęte",Toast.LENGTH_SHORT).show();
                }else Toast.makeText(getApplicationContext(),"Ruch przeciwnika",Toast.LENGTH_SHORT).show();

            }
        });

        pola[2][0].setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(twojRuch)
                {
                    if(pola[2][0].getText().toString().equals("")){
                        if(kolko){
                            pola[2][0].setText("O");
                            twojRuch = false;
                            massage = "2,0,O";
                            ruch++;
                            sendMgs();
                            if(czyWygrana()){

                                Toast.makeText(getApplicationContext(),"Kolko wygrywa",Toast.LENGTH_SHORT).show();
                                nowaGra();
                            }  else if (ruch == 9)
                            {
                                Toast.makeText(getApplicationContext(),"Remis",Toast.LENGTH_SHORT).show();
                                ruch = 0;
                                nowaGra();
                            }
                        } else if(krzyzyk){
                            pola[2][0].setText("X");
                            twojRuch = false;
                            massage = "2,0,X";
                            ruch++;
                            sendMgs();
                            if(czyWygrana()){
                                Toast.makeText(getApplicationContext(),"Krzyżyk wygrywa",Toast.LENGTH_SHORT).show();
                                nowaGra();
                            }  else if (ruch == 9)
                            {
                                Toast.makeText(getApplicationContext(),"Remis",Toast.LENGTH_SHORT).show();
                                ruch = 0;
                                nowaGra();
                            }
                        }
                    } else Toast.makeText(getApplicationContext(),"Pole już zajęte",Toast.LENGTH_SHORT).show();
                }else Toast.makeText(getApplicationContext(),"Ruch przeciwnika",Toast.LENGTH_SHORT).show();

            }
        });

        pola[2][1].setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(twojRuch)
                {
                    if(pola[2][1].getText().toString().equals("")){
                        if(kolko){
                            pola[2][1].setText("O");
                            twojRuch = false;
                            massage = "2,1,O";
                            ruch++;
                            sendMgs();
                            if(czyWygrana()){

                                Toast.makeText(getApplicationContext(),"Kolko wygrywa",Toast.LENGTH_SHORT).show();
                                nowaGra();
                            }  else if (ruch == 9)
                            {
                                Toast.makeText(getApplicationContext(),"Remis",Toast.LENGTH_SHORT).show();
                                ruch = 0;
                                nowaGra();
                            }
                        } else if(krzyzyk){
                            pola[2][1].setText("X");
                            twojRuch = false;
                            massage = "2,1,X";
                            ruch++;
                            sendMgs();
                            if(czyWygrana()){
                                Toast.makeText(getApplicationContext(),"Krzyżyk wygrywa",Toast.LENGTH_SHORT).show();
                                nowaGra();
                            }  else if (ruch == 9)
                            {
                                Toast.makeText(getApplicationContext(),"Remis",Toast.LENGTH_SHORT).show();
                                ruch = 0;
                                nowaGra();
                            }
                        }
                    } else Toast.makeText(getApplicationContext(),"Pole już zajęte",Toast.LENGTH_SHORT).show();
                }else Toast.makeText(getApplicationContext(),"Ruch przeciwnika",Toast.LENGTH_SHORT).show();

            }
        });

        pola[2][2].setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(twojRuch)
                {
                    if(pola[2][2].getText().toString().equals("")){
                        if(kolko){
                            pola[2][2].setText("O");
                            twojRuch = false;
                            massage = "2,2,O";
                            ruch++;
                            sendMgs();
                            if(czyWygrana()){

                                Toast.makeText(getApplicationContext(),"Kolko wygrywa",Toast.LENGTH_SHORT).show();
                                nowaGra();
                            }  else if (ruch == 9)
                            {
                                Toast.makeText(getApplicationContext(),"Remis",Toast.LENGTH_SHORT).show();
                                ruch = 0;
                                nowaGra();
                            }
                        } else if(krzyzyk){
                            pola[2][2].setText("X");
                            twojRuch = false;
                            massage = "2,2,X";
                            ruch++;
                            sendMgs();
                            if(czyWygrana()){
                                Toast.makeText(getApplicationContext(),"Krzyżyk wygrywa",Toast.LENGTH_SHORT).show();
                                nowaGra();
                            }  else if (ruch == 9)
                            {
                                Toast.makeText(getApplicationContext(),"Remis",Toast.LENGTH_SHORT).show();
                                ruch = 0;
                                nowaGra();
                            }
                        }
                    } else Toast.makeText(getApplicationContext(),"Pole już zajęte",Toast.LENGTH_SHORT).show();
                }else Toast.makeText(getApplicationContext(),"Ruch przeciwnika",Toast.LENGTH_SHORT).show();

            }
        });
    }
    public void Powrot(View view){
        listaUrzadzen.bluetoothAdapter.disable();
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);

    }


    public void nowaGra()
    {

        partia++;
        ruch = 0;
        for (int i = 0; i < 3; i++) {
            for(int j = 0; j< 3; j++) {
                pola[i][j].setText("");
            }
        }
        if(kolkoWygrywa){
            if(kolko) {
                twojRuch = false;
                Toast.makeText(getApplicationContext(),"Przeciwnik zaczyna",Toast.LENGTH_SHORT).show();
            } else {
                twojRuch = true;
                Toast.makeText(getApplicationContext(),"Zaczynasz",Toast.LENGTH_SHORT).show();
            }
        } else if (krzyzykWygrywa){
            if(krzyzyk) {
                twojRuch = false;
                Toast.makeText(getApplicationContext(),"Przeciwnik zaczyna",Toast.LENGTH_SHORT).show();
            } else {
                twojRuch = true;
                Toast.makeText(getApplicationContext(),"Zaczynasz",Toast.LENGTH_SHORT).show();
            }
        }
        if(!krzyzykWygrywa){
            if(!kolkoWygrywa){
                if(partia%2 == 0) {
                    if(kolko) {
                        twojRuch = false;
                        Toast.makeText(getApplicationContext(),"Przeciwnik zaczyna",Toast.LENGTH_SHORT).show();
                    } else {
                        twojRuch = true;
                        Toast.makeText(getApplicationContext(),"Zaczynasz",Toast.LENGTH_SHORT).show();
                    }
                } else{
                    if(krzyzyk) {
                        twojRuch = false;
                        Toast.makeText(getApplicationContext(),"Przeciwnik zaczyna",Toast.LENGTH_SHORT).show();
                    } else {
                        twojRuch = true;
                        Toast.makeText(getApplicationContext(),"Zaczynasz",Toast.LENGTH_SHORT).show();
                    }

                }
            }
        }

        kolkoWygrywa = false;
        krzyzykWygrywa = false;

    }

    public void sendMgs()
    {
        listaUrzadzen.sendRecive.write(massage.getBytes());
        Log.d("sendMgs", "sendMgs:  send");
        Log.d("sengMgs", "sendMgs: " + massage);
    }

    static Handler handler = new Handler(new Handler.Callback() {
        @Override
        public boolean handleMessage(@NonNull Message msg) {

            switch (msg.what)
            {
                case 1:
                    byte[] readBuffer = (byte[]) msg.obj;
                    String temp = new String(readBuffer,0,msg.arg1);
                    Log.d("wiadomosc", "handleMessage: odebrana");
                   odebranaWiadomosc(temp);
                    break;
            }
            return true;
        }
    });

    static public void odebranaWiadomosc(String wiadomosc){

        String[] temp = wiadomosc.split(",");
        int x = Integer.parseInt(temp[0]);
        int y = Integer.parseInt(temp[1]);
        String z = temp[2];
        Log.d("wiadomosc", wiadomosc);

        pola[x][y].setText(z);

        twojRuch = true;
        ruch++;
        if(czyWygrana())
        {
            partia++;
            ruch = 0;
            for (int i = 0; i < 3; i++) {
                for(int j = 0; j< 3; j++) {
                    pola[i][j].setText("");
                }
            }

            if(kolkoWygrywa){
                if(kolko) {
                    twojRuch = false;
                } else {
                    twojRuch = true;
                }
            } else if (krzyzykWygrywa){
                if(krzyzyk) {
                    twojRuch = false;
                } else {
                    twojRuch = true;
                }
            }
            if(!krzyzykWygrywa){
                if(!kolkoWygrywa){
                    if(partia%2 == 0) {
                        if(kolko) {
                            twojRuch = false;
                        } else {
                            twojRuch = true;
                        }
                    } else{
                        if(krzyzyk) {
                            twojRuch = false;
                        } else {
                            twojRuch = true;
                        }

                    }
                }
            }

            kolkoWygrywa = false;
            krzyzykWygrywa = false;

        }

    }
    public static boolean czyWygrana() {
        //poziom
        for (int i = 0; i < 3; i++) {
            if(!pola[i][0].getText().toString().equals("")){
                if(pola[i][0].getText().toString().equals(pola[i][1].getText().toString()) && pola[i][0].getText().toString().equals(pola[i][2].getText().toString())){
                    if(pola[i][0].getText().toString().equals("O")){
                        kolkoPunkty++;
                        wynikKolko.setText(String.valueOf(kolkoPunkty));
                        kolkoWygrywa = true;
                        return true;
                    }
                    if(pola[i][0].getText().toString().equals("X")){
                        krzyzykPunkty++;
                        wynikKrzyzyk.setText(String.valueOf(krzyzykPunkty));
                        krzyzykWygrywa = true;
                        return true;
                    }
                }
            }
        }

        //pion
        for (int i = 0; i < 3; i++) {
            if(!pola[0][i].getText().toString().equals("")){
                if(pola[0][i].getText().toString().equals(pola[1][i].getText().toString()) && pola[0][i].getText().toString().equals(pola[2][i].getText().toString())){
                    if(pola[0][i].getText().toString().equals("O")){
                        kolkoPunkty++;
                        wynikKolko.setText(String.valueOf(kolkoPunkty));
                        kolkoWygrywa = true;
                        return true;
                    }
                    if(pola[0][i].getText().toString().equals("X")){
                        krzyzykPunkty++;
                        wynikKrzyzyk.setText(String.valueOf(krzyzykPunkty));
                        krzyzykWygrywa = true;
                        return true;
                    }
                }
            }
        }

        //skosy
        if(!pola[0][0].getText().toString().equals("")){
            if(pola[0][0].getText().toString().equals(pola[1][1].getText().toString())&&pola[0][0].getText().toString().equals(pola[2][2].getText().toString())){
                if(pola[0][0].getText().toString().equals("O")){
                    kolkoPunkty++;
                    wynikKolko.setText(String.valueOf(kolkoPunkty));
                    kolkoWygrywa = true;
                    return true;
                }
                if(pola[0][0].getText().toString().equals("X")){
                    krzyzykPunkty++;
                    wynikKrzyzyk.setText(String.valueOf(krzyzykPunkty));
                    krzyzykWygrywa = true;
                    return true;
                }
            }
        }
        if(!pola[0][2].getText().toString().equals("")){
            if(pola[0][2].getText().toString().equals(pola[1][1].getText().toString())&&pola[0][2].getText().toString().equals(pola[2][0].getText().toString())){
                if(pola[0][2].getText().toString().equals("O")){
                    kolkoPunkty++;
                    wynikKolko.setText(String.valueOf(kolkoPunkty));
                    kolkoWygrywa = true;
                    return true;
                }
                if(pola[0][2].getText().toString().equals("X")){
                    krzyzykPunkty++;
                    wynikKrzyzyk.setText(String.valueOf(krzyzykPunkty));
                    krzyzykWygrywa = true;
                    return true;
                }
            }
        }

        return false;

    }

}
