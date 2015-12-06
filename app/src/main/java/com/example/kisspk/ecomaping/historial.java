package com.example.kisspk.ecomaping;

import android.app.Activity;
import android.database.Cursor;
import android.graphics.Color;
import android.graphics.LinearGradient;
import android.os.Bundle;

import android.app.Activity;
import android.os.Bundle;
import com.androidplot.xy.SimpleXYSeries;
import com.androidplot.xy.XYSeries;
import com.androidplot.xy.*;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Arrays;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Toast;

public class historial extends AppCompatActivity {
    private XYPlot mySimpleXYPlot;
    //Variable tipo OpenHelper de la base de datos
    ODBC db;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_historial);
        Bundle datos = this.getIntent().getExtras();
        String id = datos.getString("id");
        Toast.makeText(getApplicationContext(), "El ID es: " + id, Toast.LENGTH_LONG).show();

        // Inicializamos el objeto XYPlot búscandolo desde el layout:
        mySimpleXYPlot = (XYPlot) findViewById(R.id.mySimpleXYPlot);

        // Creamos dos arrays de prueba. En el caso real debemos reemplazar
        // estos datos por los que realmente queremos mostrar
        Number[] series1Numbers = {1, 8, 5, 2, 7, 4};
        Number[] series2Numbers = {5, 6, 7, 2, 8, 9};
        Number[] series3Numbers = {4, 6, 3, 8, 2, 10};
        Number[] series4Numbers = {6, 9, 2, 2, 5, 7};
        Number[] series5Numbers = {8, 4, 3, 2, 9, 6};

/*
        String aux="";
        db=new ODBC(this);
        Cursor cur=db.VerReportes(id);
        if (cur.moveToFirst()){
            */
/*Toast.makeText(getApplicationContext(), "Entro al cursor " + cur.getString(0),Toast.LENGTH_LONG).show();*//*

            do {
                aux=aux+cur.getString(0)+",";
            }while (cur.moveToNext());
        }
*/



        // Añadimos Línea Número UNO:
        XYSeries series1 = new SimpleXYSeries(
                Arrays.asList(series1Numbers),  // Array de datos
                SimpleXYSeries.ArrayFormat.Y_VALS_ONLY, // Sólo valores verticales
                "RSU"); // Nombre de la primera serie

        // Repetimos para la segunda serie

        XYSeries series2 = new SimpleXYSeries(
                Arrays.asList(series2Numbers),  // Array de datos
                SimpleXYSeries.ArrayFormat.Y_VALS_ONLY, // Sólo valores verticales
                "Aire"); // Nombre de la primera serie

        XYSeries series3 = new SimpleXYSeries(
                Arrays.asList(series3Numbers),  // Array de datos
                SimpleXYSeries.ArrayFormat.Y_VALS_ONLY, // Sólo valores verticales
                "Agua"); // Nombre de la primera serie

        XYSeries series4 = new SimpleXYSeries(
                Arrays.asList(series4Numbers),  // Array de datos
                SimpleXYSeries.ArrayFormat.Y_VALS_ONLY, // Sólo valores verticales
                "Electricidad"); // Nombre de la primera serie

        XYSeries series5 = new SimpleXYSeries(
                Arrays.asList(series5Numbers),  // Array de datos
                SimpleXYSeries.ArrayFormat.Y_VALS_ONLY, // Sólo valores verticales
                "Sonido"); // Nombre de la primera serie


        // Modificamos los colores de la primera serie

       /* LineAndPointFormatter series1Format = new LineAndPointFormatter(
                Color.rgb(0, 200, 0),                   // Color de la línea
                Color.rgb(0, 100, 0),                   // Color del punto
                Color.rgb(150, 190, 150));// Relleno*/

        LineAndPointFormatter series1Format = new LineAndPointFormatter(Color.RED, Color.GREEN, Color.CYAN, null);
        // Una vez definida la serie (datos y estilo), la añadimos al panel
        mySimpleXYPlot.addSeries(series1, series1Format);

        LineAndPointFormatter series2Format = new LineAndPointFormatter(Color.RED, Color.GREEN, Color.CYAN, null);
        // Una vez definida la serie (datos y estilo), la añadimos al panel
        mySimpleXYPlot.addSeries(series2, series2Format);

        LineAndPointFormatter series3Format = new LineAndPointFormatter(Color.RED, Color.GREEN, Color.CYAN, null);
        // Una vez definida la serie (datos y estilo), la añadimos al panel
        mySimpleXYPlot.addSeries(series3, series3Format);

        LineAndPointFormatter series4Format = new LineAndPointFormatter(Color.RED, Color.GREEN, Color.CYAN, null);
        // Una vez definida la serie (datos y estilo), la añadimos al panel
        mySimpleXYPlot.addSeries(series4, series4Format);

        LineAndPointFormatter series5Format = new LineAndPointFormatter(Color.RED, Color.GREEN, Color.CYAN, null);
        // Una vez definida la serie (datos y estilo), la añadimos al panel
        mySimpleXYPlot.addSeries(series5, series5Format);
    }
}
