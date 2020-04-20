package com.example.kolkoikrzyzyk;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class dzielonyEkran extends AppCompatActivity implements View.OnClickListener{

    Button pola[][] = new Button[3][3];

    Boolean kolko = false;
    Boolean krzyzyk = false;

    Boolean pierwszaPartia = true;

    int ruch = 0;
    int kolkoPunkty = 0;
    int krzyzykPunkty = 0;

    TextView wynikKolko;
    TextView wynikKrzyzyk;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dzielony_ekran);

        wynikKolko = findViewById(R.id.kolkoWynik);
        wynikKrzyzyk = findViewById(R.id.krzyzykWynik);

        String przycikID;
        int poleID;

        for(int i = 0; i < 3; i++)
        {
            for (int j = 0; j<3; j++)
            {
                przycikID = "pole"+i+j;
                poleID = this.getResources().getIdentifier(przycikID, "id",getPackageName());
                pola[i][j]=findViewById(poleID);
                pola[i][j].setOnClickListener(this);
            }
        }
        ktoZaczyna();
    }

    public void Powrot(View view)
    {
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }

    @Override
    public void onClick(View v) {
        if(!((Button)v).getText().toString().equals("")){
            Toast.makeText(getApplicationContext(),"Pole już zajęte",Toast.LENGTH_SHORT).show();
            return;
        }
        ruch++;

        if(kolko){
            ((Button) v).setText("O");
            if(czyWygrana()){
                nowaGra();
            }
            else {
                kolko = false;
                krzyzyk = true;
            }

        }else if(krzyzyk) {
            ((Button)v).setText("X");
            if(czyWygrana()){
                nowaGra();
            }
            else {
                kolko = true;
                krzyzyk = false;
            }

        }
    }

    public boolean czyWygrana() {
        //poziom
        for (int i = 0; i < 3; i++) {
            if(!pola[i][0].getText().toString().equals("")){
                if(pola[i][0].getText().toString().equals(pola[i][1].getText().toString()) && pola[i][0].getText().toString().equals(pola[i][2].getText().toString())){
                    if(pola[i][0].getText().toString().equals("O")){
                        kolkoPunkty++;
                        wynikKolko.setText(String.valueOf(kolkoPunkty));
                        Toast.makeText(getApplicationContext(),"Kółko wygrywa",Toast.LENGTH_SHORT).show();
                        return true;
                    }
                    if(pola[i][0].getText().toString().equals("X")){
                        krzyzykPunkty++;
                        wynikKrzyzyk.setText(String.valueOf(krzyzykPunkty));
                        Toast.makeText(getApplicationContext(),"Krzyżyk wygrywa",Toast.LENGTH_SHORT).show();
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
                        Toast.makeText(getApplicationContext(),"Kółko wygrywa",Toast.LENGTH_SHORT).show();
                        return true;
                    }
                    if(pola[0][i].getText().toString().equals("X")){
                        krzyzykPunkty++;
                        wynikKrzyzyk.setText(String.valueOf(krzyzykPunkty));
                        Toast.makeText(getApplicationContext(),"Krzyżyk wygrywa",Toast.LENGTH_SHORT).show();
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
                    Toast.makeText(getApplicationContext(),"Kółko wygrywa",Toast.LENGTH_SHORT).show();
                    return true;
                }
                if(pola[0][0].getText().toString().equals("X")){
                    krzyzykPunkty++;
                    wynikKrzyzyk.setText(String.valueOf(krzyzykPunkty));
                    Toast.makeText(getApplicationContext(),"Krzyżyk wygrywa",Toast.LENGTH_SHORT).show();
                    return true;
                }
            }
        }
        if(!pola[0][2].getText().toString().equals("")){
            if(pola[0][2].getText().toString().equals(pola[1][1].getText().toString())&&pola[0][2].getText().toString().equals(pola[2][0].getText().toString())){
                if(pola[0][2].getText().toString().equals("O")){
                    kolkoPunkty++;
                    wynikKolko.setText(String.valueOf(kolkoPunkty));
                    Toast.makeText(getApplicationContext(),"Kółko wygrywa",Toast.LENGTH_SHORT).show();
                    return true;
                }
                if(pola[0][2].getText().toString().equals("X")){
                    krzyzykPunkty++;
                    wynikKrzyzyk.setText(String.valueOf(krzyzykPunkty));
                    Toast.makeText(getApplicationContext(),"Krzyżyk wygrywa",Toast.LENGTH_SHORT).show();
                    return true;
                }
            }
        }
        if(ruch == 9){
            Toast.makeText(getApplicationContext(),"Remis",Toast.LENGTH_SHORT).show();
            ruch = 0;
            nowaGra();
            return false;
        }
        return false;

    }

    public void nowaGra()
    {
        ruch = 0;
        for (int i = 0; i < 3; i++) {
            for(int j = 0; j< 3; j++) {
                pola[i][j].setText("");
            }
        }
        ktoZaczyna();
    }
    public void ktoZaczyna()
    {
        if(pierwszaPartia){
            int rand = (int)Math.random()*1+1;
            if(rand == 1) {
                kolko = true;
                Toast.makeText(getApplicationContext(),"Kółko zaczyna",Toast.LENGTH_SHORT).show();
            }else  if (rand == 2){
                krzyzyk = true;
                Toast.makeText(getApplicationContext(),"Krzyżyk zaczyna",Toast.LENGTH_SHORT).show();
            }
             pierwszaPartia = false;
        }
        else {
            if(kolko){
                kolko = false;
                krzyzyk = true;
                Toast.makeText(getApplicationContext(),"Krzyżyk zaczyna",Toast.LENGTH_SHORT).show();
            }
            if(krzyzyk){
                krzyzyk = false;
                kolko = true;
                Toast.makeText(getApplicationContext(),"Kółko zaczyna",Toast.LENGTH_SHORT).show();
            }
        }

    }
}
