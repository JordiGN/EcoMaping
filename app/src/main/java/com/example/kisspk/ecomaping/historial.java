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
import java.util.List;

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
        //Toast.makeText(getApplicationContext(), "El ID es: " + id, Toast.LENGTH_LONG).show();

        // Inicializamos el objeto XYPlot búscandolo desde el layout:
        mySimpleXYPlot = (XYPlot) findViewById(R.id.mySimpleXYPlot);

        // Creamos dos arrays de prueba. En el caso real debemos reemplazar
        // estos datos por los que realmente queremos mostrar
        ArrayList ListRSU = new ArrayList<Integer>();
        ArrayList ListAIRE= new ArrayList<Integer>();
        ArrayList ListAGUA= new ArrayList<Integer>();
        ArrayList ListELECTRICIDAD = new ArrayList<Integer>();
        ArrayList ListSONIDO = new ArrayList<Integer>();
        db=new ODBC(this);
        Cursor cur=db.LlenarDATOS(id);//FALTA FECHA
        if (cur.moveToFirst()){
            /*Toast.makeText(getApplicationContext(), "Entro al cursor " + cur.getString(0),Toast.LENGTH_LONG).show();*/
            do {
                ListRSU.add(Integer.parseInt(cur.getString(0)));
                Toast.makeText(getApplicationContext(), "RSU " + cur.getString(0),Toast.LENGTH_LONG).show();
                ListAIRE.add(Integer.parseInt(cur.getString(1)));
                ListAGUA.add(Integer.parseInt(cur.getString(2)));
                ListELECTRICIDAD.add(Integer.parseInt(cur.getString(3)));
                ListSONIDO.add(Integer.parseInt(cur.getString(4)));
            }while (cur.moveToNext());
        }
        Number[] series1Numbers = new Number[ListRSU.size()];
        ListRSU.toArray(series1Numbers);
        Number[] series2Numbers = new Number[ListAIRE.size()];
        ListAIRE.toArray(series2Numbers);
        Number[] series3Numbers = new Number[ListAGUA.size()];
        ListAGUA.toArray(series3Numbers);
        Number[] series4Numbers = new Number[ListELECTRICIDAD.size()];
        ListELECTRICIDAD.toArray(series4Numbers);
        Number[] series5Numbers = new Number[ListSONIDO.size()];
        ListSONIDO.toArray(series5Numbers);


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

        LineAndPointFormatter series1Format = new LineAndPointFormatter(Color.BLUE, Color.GREEN, null, null);
        // Una vez definida la serie (datos y estilo), la añadimos al panel
        mySimpleXYPlot.addSeries(series1, series1Format);

        LineAndPointFormatter series2Format = new LineAndPointFormatter(Color.RED, Color.GREEN, null, null);
        // Una vez definida la serie (datos y estilo), la añadimos al panel
        mySimpleXYPlot.addSeries(series2, series2Format);

        LineAndPointFormatter series3Format = new LineAndPointFormatter(Color.YELLOW, Color.GREEN, null, null);
        // Una vez definida la serie (datos y estilo), la añadimos al panel
        mySimpleXYPlot.addSeries(series3, series3Format);

        LineAndPointFormatter series4Format = new LineAndPointFormatter(Color.GREEN, Color.GREEN, null, null);
        // Una vez definida la serie (datos y estilo), la añadimos al panel
        mySimpleXYPlot.addSeries(series4, series4Format);

        LineAndPointFormatter series5Format = new LineAndPointFormatter(Color.CYAN, Color.GREEN, null, null);
        // Una vez definida la serie (datos y estilo), la añadimos al panel
        mySimpleXYPlot.addSeries(series5, series5Format);
    }
}
