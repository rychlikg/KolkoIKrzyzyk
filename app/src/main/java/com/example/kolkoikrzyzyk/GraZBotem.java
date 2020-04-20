package com.example.kolkoikrzyzyk;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.BoringLayout;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Random;

public class GraZBotem extends AppCompatActivity implements View.OnClickListener{
    Button pola[][] = new Button[3][3];

    Boolean kolko = false;
    Boolean krzyzyk = false;
    Boolean graczKolko = false;

    Boolean pierwszaPartia = true;

    int ruch = 0;
    int kolkoPunkty = 0;
    int krzyzykPunkty = 0;

    TextView wynikKolko;
    TextView wynikKrzyzyk;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gra_zbotem);
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

        int wybor = getIntent().getIntExtra("wybor",1);
        if(wybor == 1) {
            graczKolko = true;
        }else if (wybor == 2) {
            graczKolko = false;
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

        if(graczKolko){
            ((Button) v).setText("O");
            if(czyWygrana()){
                wynikKolko.setText(String.valueOf(kolkoPunkty));
                nowaGra();
            } else {
                ruchBota();
            }
        } else {
            ((Button)v).setText("X");
            if(czyWygrana()){
                wynikKolko.setText(String.valueOf(krzyzykPunkty));
                nowaGra();
            }else {
                ruchBota();
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
                        Toast.makeText(getApplicationContext(),"Kółko wygrywa",Toast.LENGTH_SHORT).show();
                        return true;
                    }
                    if(pola[i][0].getText().toString().equals("X")){
                        krzyzykPunkty++;
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
                        Toast.makeText(getApplicationContext(),"Kółko wygrywa",Toast.LENGTH_SHORT).show();
                        return true;
                    }
                    if(pola[0][i].getText().toString().equals("X")){
                        krzyzykPunkty++;
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
                    Toast.makeText(getApplicationContext(),"Kółko wygrywa",Toast.LENGTH_SHORT).show();
                    return true;
                }
                if(pola[0][0].getText().toString().equals("X")){
                    krzyzykPunkty++;
                    Toast.makeText(getApplicationContext(),"Krzyżyk wygrywa",Toast.LENGTH_SHORT).show();
                    return true;
                }
            }
        }
        if(!pola[0][2].getText().toString().equals("")){
            if(pola[0][2].getText().toString().equals(pola[1][1].getText().toString())&&pola[0][2].getText().toString().equals(pola[2][0].getText().toString())){
                if(pola[0][2].getText().toString().equals("O")){
                    kolkoPunkty++;
                    Toast.makeText(getApplicationContext(),"Kółko wygrywa",Toast.LENGTH_SHORT).show();
                    return true;
                }
                if(pola[0][2].getText().toString().equals("X")){
                    krzyzykPunkty++;
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
                if(!graczKolko) ruchBota();

            }else  if (rand == 2){
                krzyzyk = true;
                Toast.makeText(getApplicationContext(),"Krzyżyk zaczyna",Toast.LENGTH_SHORT).show();
                if(graczKolko) ruchBota();
            }
            pierwszaPartia = false;
        }
        else {
            if(kolko){
                kolko = false;
                krzyzyk = true;
                Toast.makeText(getApplicationContext(),"Krzyżyk zaczyna",Toast.LENGTH_SHORT).show();
                if(!graczKolko) ruchBota();
            }
            if(krzyzyk){
                krzyzyk = false;
                kolko = true;
                Toast.makeText(getApplicationContext(),"Kółko zaczyna",Toast.LENGTH_SHORT).show();
                if(graczKolko) ruchBota();
            }
        }

    }

    public void ruchBota(){
        ruch++;
        boolean wstawione = false;
        String znak;
        if(graczKolko) znak = "X";
        else  znak = "O";
        int x = 0;
        int y = 0;
        Random r = new Random();
        Random r2 = new Random();
        if(ruch < 4){
            while(!wstawione){
                x = r.nextInt(2);
                y = r.nextInt(2);
                //Toast.makeText(getApplicationContext(),x,Toast.LENGTH_SHORT).show();
                if(pola[x][y].getText().toString().equals("")){
                    pola[x][y].setText(znak);
                    if(czyWygrana()){
                        if(znak == "X")
                            wynikKrzyzyk.setText(String.valueOf(krzyzykPunkty));
                        else  wynikKrzyzyk.setText(String.valueOf(kolkoPunkty));
                        nowaGra();
                    }
                    else wstawione = true;
                }
            }
        }
        else {
            //skosy
            if(pola[0][0].getText().toString().equals(znak)){
                if (pola[1][1].getText().toString().equals(znak)){
                    if(pola[2][2].getText().toString().equals("")){
                        pola[2][2].setText(znak);
                        if(czyWygrana()){
                            if(znak == "X")
                                wynikKrzyzyk.setText(String.valueOf(krzyzykPunkty));
                            else  wynikKrzyzyk.setText(String.valueOf(kolkoPunkty));
                            nowaGra();
                        }
                        return;
                    }
                }
                if (pola[2][2].getText().toString().equals(znak)){
                    if(pola[1][1].getText().toString().equals("")){
                        pola[1][1].setText(znak);
                        if(czyWygrana()){
                            if(znak == "X")
                                wynikKrzyzyk.setText(String.valueOf(krzyzykPunkty));
                            else  wynikKrzyzyk.setText(String.valueOf(kolkoPunkty));
                            nowaGra();
                        }
                        return;
                    }
                }
            }
            if(pola[0][2].getText().toString().equals(znak)){
                if (pola[1][1].getText().toString().equals(znak)){
                    if(pola[2][0].getText().toString().equals("")){
                        pola[2][0].setText(znak);
                        if(czyWygrana()){
                            if(znak == "X")
                                wynikKrzyzyk.setText(String.valueOf(krzyzykPunkty));
                            else  wynikKrzyzyk.setText(String.valueOf(kolkoPunkty));
                            nowaGra();
                        }
                        return;
                    }
                }
                if (pola[2][0].getText().toString().equals(znak)){
                    if(pola[1][1].getText().toString().equals("")){
                        pola[1][1].setText(znak);
                        if(czyWygrana()){
                            if(znak == "X")
                                wynikKrzyzyk.setText(String.valueOf(krzyzykPunkty));
                            else  wynikKrzyzyk.setText(String.valueOf(kolkoPunkty));
                            nowaGra();
                        }
                        return;
                    }
                }
            }
            //pion
            for (int i = 0; i < 3; i++){
                for (int j =0; j < 3; j++) {
                    if(pola[i][j].getText().toString().equals(znak)) {
                        if(j == 0) {
                            if(pola[i][j+1].getText().toString().equals(znak)){
                                if(pola[i][j+2].getText().toString().equals("")){
                                    pola[i][j+2].setText(znak);
                                    if(czyWygrana()){
                                        if(znak == "X")
                                            wynikKrzyzyk.setText(String.valueOf(krzyzykPunkty));
                                        else  wynikKrzyzyk.setText(String.valueOf(kolkoPunkty));
                                        nowaGra();
                                    }
                                    return;
                                }
                            }
                            if(pola[i][j+2].getText().toString().equals(znak)){
                                if(pola[i][j+1].getText().toString().equals("")){
                                    pola[i][j+1].setText(znak);
                                    if(czyWygrana()){
                                        if(znak == "X")
                                            wynikKrzyzyk.setText(String.valueOf(krzyzykPunkty));
                                        else  wynikKrzyzyk.setText(String.valueOf(kolkoPunkty));
                                        nowaGra();
                                    }
                                    return;
                                }
                            }
                        }
                        if(j == 1){
                            if(pola[i][j-1].getText().toString().equals(znak)){
                                if(pola[i][j+1].getText().toString().equals("")){
                                    pola[i][j+1].setText(znak);
                                    if(czyWygrana()){
                                        if(znak == "X")
                                            wynikKrzyzyk.setText(String.valueOf(krzyzykPunkty));
                                        else  wynikKrzyzyk.setText(String.valueOf(kolkoPunkty));
                                        nowaGra();
                                    }
                                    return;
                                }
                            }
                             if(pola[i][j+1].getText().toString().equals(znak)){
                                if(pola[i][j-1].getText().toString().equals("")){
                                    pola[i][j-1].setText(znak);
                                    if(czyWygrana()){
                                        if(znak == "X")
                                            wynikKrzyzyk.setText(String.valueOf(krzyzykPunkty));
                                        else  wynikKrzyzyk.setText(String.valueOf(kolkoPunkty));
                                        nowaGra();
                                    }
                                    return;
                                }
                            }
                        }
                        if(j == 2){
                            if(pola[i][j-1].getText().toString().equals(znak)){
                                if(pola[i][j-2].getText().toString().equals("")){
                                    pola[i][j-2].setText(znak);
                                    if(czyWygrana()){
                                        if(znak == "X")
                                            wynikKrzyzyk.setText(String.valueOf(krzyzykPunkty));
                                        else  wynikKrzyzyk.setText(String.valueOf(kolkoPunkty));
                                        nowaGra();
                                    }
                                    return;
                                }
                            }
                            if(pola[i][j-2].getText().toString().equals(znak)){
                                if(pola[i][j-1].getText().toString().equals("")){
                                    pola[i][j-1].setText(znak);
                                    if(czyWygrana()){
                                        if(znak == "X")
                                            wynikKrzyzyk.setText(String.valueOf(krzyzykPunkty));
                                        else  wynikKrzyzyk.setText(String.valueOf(kolkoPunkty));
                                        nowaGra();
                                    }
                                    return;
                                }
                            }
                        }
                    }
                }
            }
            for (int i = 0; i < 3; i++){
                for (int j =0; j < 3; j++) {
                    if(pola[j][i].getText().toString().equals(znak)) {
                        if(j == 0) {
                            if(pola[j+1][i].getText().toString().equals(znak)){
                                if(pola[j+2][i].getText().toString().equals("")){
                                    pola[j+2][i].setText(znak);
                                    if(czyWygrana()){
                                        if(znak == "X")
                                            wynikKrzyzyk.setText(String.valueOf(krzyzykPunkty));
                                        else  wynikKrzyzyk.setText(String.valueOf(kolkoPunkty));
                                        nowaGra();
                                    }
                                    return;
                                }
                            }
                            if(pola[j+2][i].getText().toString().equals(znak)){
                                if(pola[j+1][i].getText().toString().equals("")){
                                    pola[j+1][i].setText(znak);
                                    if(czyWygrana()){
                                        if(znak == "X")
                                            wynikKrzyzyk.setText(String.valueOf(krzyzykPunkty));
                                        else  wynikKrzyzyk.setText(String.valueOf(kolkoPunkty));
                                        nowaGra();
                                    }
                                    return;
                                }
                            }
                        }
                        if(j == 1){
                            if(pola[j-1][i].getText().toString().equals(znak)){
                                if(pola[j+1][i].getText().toString().equals("")){
                                    pola[j+1][i].setText(znak);
                                    if(czyWygrana()){
                                        if(znak == "X")
                                            wynikKrzyzyk.setText(String.valueOf(krzyzykPunkty));
                                        else  wynikKrzyzyk.setText(String.valueOf(kolkoPunkty));
                                        nowaGra();
                                    }
                                    return;
                                }
                            }
                            if(pola[j+1][i].getText().toString().equals(znak)){
                                if(pola[j-1][i].getText().toString().equals("")){
                                    pola[j-1][i].setText(znak);
                                    if(czyWygrana()){
                                        if(znak == "X")
                                            wynikKrzyzyk.setText(String.valueOf(krzyzykPunkty));
                                        else  wynikKrzyzyk.setText(String.valueOf(kolkoPunkty));
                                        nowaGra();
                                    }
                                    return;
                                }
                            }
                        }
                        if(j == 2){
                            if(pola[j-1][i].getText().toString().equals(znak)){
                                if(pola[i][j-2].getText().toString().equals("")){
                                    pola[i][j-2].setText(znak);
                                    if(czyWygrana()){
                                        if(znak == "X")
                                            wynikKrzyzyk.setText(String.valueOf(krzyzykPunkty));
                                        else  wynikKrzyzyk.setText(String.valueOf(kolkoPunkty));
                                        nowaGra();
                                    }
                                    return;
                                }
                            }
                            if(pola[j-2][i].getText().toString().equals(znak)){
                                if(pola[j-1][i].getText().toString().equals("")){
                                    pola[j-1][i].setText(znak);
                                    if(czyWygrana()){
                                        if(znak == "X")
                                            wynikKrzyzyk.setText(String.valueOf(krzyzykPunkty));
                                        else  wynikKrzyzyk.setText(String.valueOf(kolkoPunkty));
                                        nowaGra();
                                    }
                                    return;
                                }
                            }
                        }
                    }
                }
            }
            while(!wstawione){
                x = r.nextInt(2);
                y = r.nextInt(2);
                //Toast.makeText(getApplicationContext(),x,Toast.LENGTH_SHORT).show();
                if(pola[x][y].getText().toString().equals("")){
                    pola[x][y].setText(znak);
                    if(czyWygrana()){
                        if(znak == "X")
                            wynikKrzyzyk.setText(String.valueOf(krzyzykPunkty));
                        else  wynikKrzyzyk.setText(String.valueOf(kolkoPunkty));
                        nowaGra();
                    }
                    else wstawione = true;
                }
            }
        }

    }
}


