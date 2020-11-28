package com.example.entrega21pedidos;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.app.TimePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.text.format.DateFormat;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.DatePicker;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.DialogFragment;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

public class SegundaPantalla extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_segunda_pantalla);

        String tiempoActual = new SimpleDateFormat("HH:mm", Locale.getDefault()).format(new Date());
        String fechaActual = new SimpleDateFormat("dd/MM/yyyy", Locale.getDefault()).format(new Date());

        TextView tiempo = (TextView) findViewById(R.id.tiempo);
        tiempo.setText(tiempoActual);

        TextView fecha = (TextView) findViewById(R.id.fecha);
        fecha.setText(fechaActual);
    }

//  MENU
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_segunda_pantalla, menu);
        return true;
    }


    public void hacerPedido(MenuItem item) {
        Intent intentMain = getIntent();
        String cafeSolo = intentMain.getStringExtra("cafeSolo");
        String cafeCortado = intentMain.getStringExtra("cafeCortado");
        String cafeConLeche = intentMain.getStringExtra("cafeConLeche");

        TextView tiempo = findViewById(R.id.tiempo);
        TextView fecha = findViewById(R.id.fecha);

        RadioButton repartoADomicilio = findViewById(R.id.repartoADomicilio);
        RadioButton recogerEnLocal = findViewById(R.id.recogerEnLocal);

        if (!repartoADomicilio.isChecked() && !recogerEnLocal.isChecked()) {
            Toast myToast = Toast.makeText(this, "No has seleccionado Modo de entrega", Toast.LENGTH_LONG);
            myToast.show();
        }

        else if (repartoADomicilio.isChecked()) {
            Intent intent = new Intent(this, TerceraPantalla.class);
            intent.putExtra("cafeSolo", cafeSolo);
            intent.putExtra("cafeCortado", cafeCortado);
            intent.putExtra("cafeConLeche", cafeConLeche);
            intent.putExtra("entrega", "A Domicilio");
            intent.putExtra("tiempo", tiempo.getText().toString());
            intent.putExtra("fecha", fecha.getText().toString());
            startActivity(intent);
        }

        else if (recogerEnLocal.isChecked()) {
            Intent intent = new Intent(this, TerceraPantalla.class);
            intent.putExtra("cafeSolo", cafeSolo);
            intent.putExtra("cafeCortado", cafeCortado);
            intent.putExtra("cafeConLeche", cafeConLeche);
            intent.putExtra("entrega", "A Recoger");
            intent.putExtra("tiempo", tiempo.getText().toString());
            intent.putExtra("fecha", fecha.getText().toString());
            startActivity(intent);
        }
    }

    public void showTimePicker(View view) {
        DialogFragment newFragment = new TimePickerFragment();
        newFragment.show(getSupportFragmentManager(),
                getString(R.string.timepicker));
    }

    public void showDatePicker(View view) {
        DialogFragment newFragment = new DatePickerFragment();
        newFragment.show(getSupportFragmentManager(),
                getString(R.string.datepicker));
    }

    public void processTimePickerResult(int hourOfDay, int minute) {
        String hour_string = Integer.toString(hourOfDay);
        String minute_string = Integer.toString(minute);
        String timeMessage = "";
        if (hourOfDay < 10 && minute < 10) {
            timeMessage = ("0" + hour_string + ":" + "0" + minute_string);
        }

        else if(hourOfDay < 10 && minute > 9) {
            timeMessage = ("0" + hour_string + ":" + minute_string);
        }

        else if(hourOfDay > 9 && minute < 10) {
            timeMessage = (hour_string + ":" + "0" + minute_string);
        }

        else {
            timeMessage = (hour_string + ":" + minute_string);
        }

        TextView tiempo = findViewById(R.id.tiempo);
        tiempo.setText(timeMessage);
    }

    public void processDatePickerResult(int year, int month, int day) {
        String month_string = Integer.toString(month + 1);
        String day_string = Integer.toString(day);
        String year_string = Integer.toString(year);
        String dateMessage = "";

        if (month < 10 && day < 10) {
            dateMessage = "0" + day_string + "/" + "0" + month_string + "/" + year_string;
        }

        else if (month < 10) {
            dateMessage = day_string + "/" + "0" + month_string + "/" + year_string;
        }

        else if (day < 10) {
            dateMessage = "0" + day_string + "/" + month_string + "/" + year_string;
        }

        else {
            dateMessage = day_string + "/" + month_string + "/" + year_string;
        }

        TextView fecha = findViewById(R.id.fecha);
        fecha.setText(dateMessage);
    }


    public static class TimePickerFragment extends DialogFragment
            implements TimePickerDialog.OnTimeSetListener {

        public Dialog onCreateDialog(Bundle savedInstanceState) {
            final Calendar c = Calendar.getInstance();
            int hour = c.get(Calendar.HOUR_OF_DAY);
            int minute = c.get(Calendar.MINUTE);

            return new TimePickerDialog(getActivity(), this, hour, minute,
                    DateFormat.is24HourFormat(getActivity()));
        }

        public void onTimeSet(TimePicker timePicker, int hourOfDay, int minute) {
            SegundaPantalla activity = (SegundaPantalla) getActivity();
            activity.processTimePickerResult(hourOfDay, minute);
        }
    }

    public static class DatePickerFragment extends DialogFragment
            implements DatePickerDialog.OnDateSetListener {

        public Dialog onCreateDialog(Bundle savedInstanceState) {
            final Calendar c = Calendar.getInstance();
            int year = c.get(Calendar.YEAR);
            int month = c.get(Calendar.MONTH);
            int day = c.get(Calendar.DAY_OF_MONTH);

            // Create a new instance of DatePickerDialog and return it.
            return new DatePickerDialog(getActivity(), this, year, month, day);
        }

        public void onDateSet(DatePicker datePicker, int year, int month, int day) {
            SegundaPantalla activity = (SegundaPantalla) getActivity();
            activity.processDatePickerResult(year, month, day);
        }
    }
}