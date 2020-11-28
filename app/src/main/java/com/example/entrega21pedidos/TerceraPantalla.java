package com.example.entrega21pedidos;


import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;


public class TerceraPantalla extends AppCompatActivity implements AdapterView.OnItemSelectedListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tercera_pantalla);

        Spinner spinner = findViewById(R.id.spinner);
        if (spinner != null) {
            spinner.setOnItemSelectedListener(this);
        }

        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
                R.array.telefonos, android.R.layout.simple_spinner_item);

        adapter.setDropDownViewResource
                (android.R.layout.simple_spinner_dropdown_item);

        if (spinner != null) {
            spinner.setAdapter(adapter);
        }
    }


    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {}

    public void onNothingSelected(AdapterView<?> parent) {}



//  MENU
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_tercera_pantalla, menu);
        return true;
    }

    public void enviarEmail(MenuItem item) {
        Intent intent = getIntent();

        String cafeSolo = intent.getStringExtra("cafeSolo");
        String cafeCortado = intent.getStringExtra("cafeCortado");
        String cafeConLeche = intent.getStringExtra("cafeConLeche");

        String entrega = intent.getStringExtra("entrega");

        String tiempo = intent.getStringExtra("tiempo");
        String fecha = intent.getStringExtra("fecha");

        final EditText nombre = findViewById(R.id.editTextNombre);
        EditText direccion = findViewById(R.id.editTextDireccion);
        EditText telefono = findViewById(R.id.editTextTelefono);
        Spinner tipoTelefono = findViewById(R.id.spinner);

        final String message = "Mi pedido es: \n" + cafeConLeche + " Cafés con Leche\n" + cafeSolo + " Café Solos\n" + cafeCortado + " Café Cortados\n"
                        + "La fecha de entrega es " + fecha + " y la hora de entrega " + tiempo + "\nModo de entrega: " + entrega
                        + "\nDirección: " + direccion.getText().toString() + "\nNúmero de teléfono: " + telefono.getText().toString() + " (" + tipoTelefono.getSelectedItem().toString() + ")";


        AlertDialog.Builder myAlertBuilder = new AlertDialog.Builder(TerceraPantalla.this);

        myAlertBuilder.setMessage("¿Está seguro de que quiere hacer el pedido?");

        myAlertBuilder.setPositiveButton("SÍ", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int which) {
                Intent sendIntent = new Intent();
                String email = "pedidos@mitienda.com";

                sendIntent.setAction(sendIntent.ACTION_SENDTO);
                sendIntent.setData(Uri.parse("mailto:"));
                sendIntent.putExtra(Intent.EXTRA_TEXT, message);
                sendIntent.putExtra(Intent.EXTRA_EMAIL, new String[] {email});
                sendIntent.putExtra(Intent.EXTRA_SUBJECT, "Pedido de " + nombre.getText().toString());

                if (sendIntent.resolveActivity(getPackageManager()) != null) {
                    startActivity(sendIntent);
                } else {
                    Toast.makeText(TerceraPantalla.this, "There is no email client installed", Toast.LENGTH_SHORT).show();
                }
            }
        });

        myAlertBuilder.setNegativeButton("NO", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int which) {
                // User cancelled the dialog.
                Toast.makeText(getApplicationContext(), "Revise el pedido",
                        Toast.LENGTH_SHORT).show();
            }
        });

        myAlertBuilder.show();
    }
}