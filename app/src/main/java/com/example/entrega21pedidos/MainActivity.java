package com.example.entrega21pedidos;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    int numeroCafeSolo = 0;
    int numeroCafeCortado = 0;
    int numeroCafeConLeche = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }



//  MENU
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    public void reset(MenuItem item) {
        numeroCafeSolo = 0;
        numeroCafeCortado = 0;
        numeroCafeConLeche = 0;

        TextView cantidadCafeSolo = findViewById(R.id.totalCafeSolo);
        cantidadCafeSolo.setText("0");

        TextView cantidadCafeCortado = findViewById(R.id.totalCafeCortado);
        cantidadCafeCortado.setText("0");

        TextView cantidadCafeConLeche = findViewById(R.id.totalCafeConLeche);
        cantidadCafeConLeche.setText("0");
    }

    public void pedir(MenuItem item) {

        TextView cafeSolo = findViewById(R.id.totalCafeSolo);
        TextView cafeCortado = findViewById(R.id.totalCafeCortado);
        TextView cafeConLeche = findViewById(R.id.totalCafeConLeche);



        Intent intent = new Intent(this, SegundaPantalla.class);
        intent.putExtra("cafeSolo", cafeSolo.getText().toString());
        intent.putExtra("cafeCortado", cafeCortado.getText().toString());
        intent.putExtra("cafeConLeche", cafeConLeche.getText().toString());
        startActivity(intent);
    }



//  CONTADOR
    // Cafe SOLO
    public void menosCafeSolo(View view) {
        if (numeroCafeSolo > 0) numeroCafeSolo--;
        TextView cantidadCafeSolo = findViewById(R.id.totalCafeSolo);
        cantidadCafeSolo.setText(String.valueOf(numeroCafeSolo));
    }

    public void masCafeSolo(View view) {
        if (numeroCafeSolo < 25) numeroCafeSolo++;

        TextView cantidadCafeSolo = findViewById(R.id.totalCafeSolo);
        cantidadCafeSolo.setText(String.valueOf(numeroCafeSolo));
    }

    // Cafe CORTADO
    public void menosCafeCortado(View view) {
        if (numeroCafeCortado > 0) numeroCafeCortado--;
        TextView cantidadCafeCortado = findViewById(R.id.totalCafeCortado);
        cantidadCafeCortado.setText(String.valueOf(numeroCafeCortado));
    }

    public void masCafeCortado(View view) {
        if (numeroCafeCortado < 25) numeroCafeCortado++;
        TextView cantidadCafeCortado = findViewById(R.id.totalCafeCortado);
        cantidadCafeCortado.setText(String.valueOf(numeroCafeCortado));
    }

    // Cafe CON LECHE
    public void menosCafeConLeche(View view) {
        if (numeroCafeConLeche > 0) numeroCafeConLeche--;
        TextView cantidadCafeConLeche = findViewById(R.id.totalCafeConLeche);
        cantidadCafeConLeche.setText(String.valueOf(numeroCafeConLeche));
    }

    public void masCafeConLeche(View view) {
        if (numeroCafeConLeche < 25) numeroCafeConLeche++;
        TextView cantidadCafeConLeche = findViewById(R.id.totalCafeConLeche);
        cantidadCafeConLeche.setText(String.valueOf(numeroCafeConLeche));
    }
}