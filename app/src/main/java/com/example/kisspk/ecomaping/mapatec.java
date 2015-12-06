package com.example.kisspk.ecomaping;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Color;
import android.support.v4.app.FragmentActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.android.volley.RequestQueue;

import com.google.android.gms.maps.CameraUpdate;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.maps.model.Polygon;
import com.google.android.gms.maps.model.PolygonOptions;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class mapatec extends FragmentActivity implements OnMapReadyCallback {

    private GoogleMap mMap;
//Variable tipo OpenHelper de la base de datos
    ODBC db;
//Datos area seleccionada
    String id;
    String nombre;
//Datos del poligono
    String[] p1;
    String[] p2;
    String[] p3;
    String[] p4;
    String[] ubicacion;
    String color;
//Datos de los residos
    String poblacion;
    String rsu;
    String aire;
    String agua;
    String electricidad;
    String sonido;
    RequestQueue requestQueue;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mapatec);
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);

        Bundle datos = this.getIntent().getExtras();
        String[] datoarea = datos.getString("dato").split(",");
        id=datoarea[0];
        nombre = datoarea[1];
        db=new ODBC(this);
        Cursor cur=db.VerReportes(id);
        if (cur.moveToFirst()){
            /*Toast.makeText(getApplicationContext(), "Entro al cursor " + cur.getString(0),Toast.LENGTH_LONG).show();*/
            do {
                    p1=cur.getString(1).split(",");
                    p2=cur.getString(2).split(",");
                    p3=cur.getString(3).split(",");
                    p4=cur.getString(4).split(",");
                    ubicacion=cur.getString(5).split(",");
                    color=cur.getString(6);
                    rsu=cur.getString(7);
                    aire=cur.getString(8);
                    agua=cur.getString(9);
                    electricidad=cur.getString(10);
                    sonido=cur.getString(11);
                    poblacion=cur.getString(12);

            }while (cur.moveToNext());
        }
    }
    /**
     * Manipulates the map once available.
     * This callback is triggered when the map is ready to be used.
     * This is where we can add markers or lines, add listeners or move the camera. In this case,
     * we just add a marker near Sydney, Australia.
     * If Google Play services is not installed on the device, the user will be prompted to install
     * it inside the SupportMapFragment. This method will only be triggered once the user has
     * installed Google Play services and returned to the app.
     */
    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;

        // Add a marker in Sydney and move the camera

        /*LatLng sydney = new LatLng(-34, 151);*/
        /*LatLng uno = new LatLng(Double.parseDouble(lat),Double.parseDouble(longi));*/
        LatLng uno = new LatLng(Double.parseDouble(ubicacion[0]), Double.parseDouble(ubicacion[1]));


        Toast.makeText(getApplicationContext(), "Estado Reporte "+color ,Toast.LENGTH_LONG).show();
        Polygon polygon= mMap.addPolygon(new PolygonOptions()
               .add(new LatLng(Double.parseDouble(p1[0]), Double.parseDouble(p1[1])),
                       new LatLng(Double.parseDouble(p2[0]), Double.parseDouble(p2[1])),
                       new LatLng(Double.parseDouble(p3[0]), Double.parseDouble(p3[1])),
                       new LatLng(Double.parseDouble(p4[0]), Double.parseDouble(p4[1]))
               )
               /*.strokeColor(Color.RED)
               .fillColor(Color.BLUE)*/);

        switch(color) {
            case "GREEN":
                polygon.setStrokeColor(Color.GREEN);
                polygon.setFillColor(Color.GREEN);
                break;
            case "YELLOW":
                polygon.setStrokeColor(Color.YELLOW);
                polygon.setFillColor(Color.YELLOW);
                break;
            case "RED":
                polygon.setStrokeColor(Color.RED);
                polygon.setFillColor(Color.RED);
                break;
            default:
        }

        CameraUpdate yourLocation = CameraUpdateFactory.newLatLngZoom(uno,17);
        mMap.animateCamera(yourLocation);
        mMap.addMarker(new MarkerOptions().position(uno).title(nombre)
                        .snippet("Población: " + poblacion)
        );
        mMap.setOnInfoWindowClickListener(new GoogleMap.OnInfoWindowClickListener() {
            @Override
            public void onInfoWindowClick(Marker marker) {
                new AlertDialog.Builder(mapatec.this)
                        .setTitle("Datos de área")
                        .setMessage("RSU: " + rsu + "\n" +
                                "Aire: " + aire + "\n" +
                                "Agua: " + agua + "\n" +
                                "Electricidad: " + electricidad + "\n" +
                                "Sonido: " + sonido)
                        .setPositiveButton("Aceptar", new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int which) {
                                Intent intent = new Intent(mapatec.this, inicio.class);
                                startActivity(intent);
                            }
                        })
                        .setNegativeButton("Ver Historial", new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int which) {
                                 /*Intent intent = new Intent(mapatec.this, historial.class);
                        intent.putExtra("id", String.valueOf(id));
                        startActivity(intent);*/
                            }
                        })
                        .setIcon(android.R.drawable.ic_dialog_alert)
                        .show();

            }
        });

    }
}
