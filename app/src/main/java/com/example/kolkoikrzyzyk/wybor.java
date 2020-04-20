package com.example.kolkoikrzyzyk;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class wybor extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_wybor);
    }
    public void Kolko(View view)
    {
        Intent intent = new Intent(this, GraZBotem.class);
        intent.putExtra("wybor", 1);
        startActivity(intent);
    }
    public void Krzyzyk(View view)
    {
        Intent intent = new Intent(this, GraZBotem.class);
        intent.putExtra("wybor", 2);
        startActivity(intent);
    }
}
