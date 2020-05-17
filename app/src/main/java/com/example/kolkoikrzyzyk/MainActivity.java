package com.example.kolkoikrzyzyk;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void DzielonyEkran(View view)
    {
        Intent intent = new Intent(this, dzielonyEkran.class);
        startActivity(intent);
    }
    public void GraZBotem(View view)
    {
        Intent intent = new Intent(this, wybor.class);
        startActivity(intent);
    }

    public void DwaUrzadzenia(View view)
    {
        Intent intent = new Intent(this,wyborPolaczenia.class);
        startActivity(intent);
    }
}
