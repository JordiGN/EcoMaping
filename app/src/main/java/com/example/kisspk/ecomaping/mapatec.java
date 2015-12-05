package com.example.kisspk.ecomaping;

import android.graphics.Color;
import android.support.v4.app.FragmentActivity;
import android.os.Bundle;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.google.android.gms.maps.CameraUpdate;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.maps.model.Polygon;
import com.google.android.gms.maps.model.PolygonOptions;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;


public class mapatec extends FragmentActivity implements OnMapReadyCallback {

    private GoogleMap mMap;
    String id;
    String nombre;
    String nom;

    String rsu;
    String aire;
    String agua;
    String electricidad;
    String sonido;
    String[] p1;
    String[] p2;
    String[] p3;
    String[] p4;
    String color="Color.";
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
        String[] datosevento = datos.getString("dato").split(",");
        id=datosevento[0];
        nombre = datosevento[1];
        /*Toast.makeText(getApplicationContext(), "Manda " + nombre+" e "+id, Toast.LENGTH_LONG).show();*/
        /*String showURL="http://192.168.1.66:8080/wsecomapping/verreporte.php?idarea="+id;*/
        Toast.makeText(getApplicationContext(), "Manda " + nombre+"id "+id,Toast.LENGTH_LONG).show();

        String showURL="http://webcolima.com/wsecomapping/verreporte.php?idarea="+id;
        /*Toast.makeText(getApplicationContext(), "URL " + showURL,Toast.LENGTH_LONG).show();

        requestQueue = Volley.newRequestQueue(getApplicationContext());
        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.GET, showURL,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        try {
                            JSONArray areas = response.getJSONArray("areas");
                            for (int i = 0; i < areas.length(); i++) {
                                JSONObject area = areas.getJSONObject(i);

                                rsu = area.getString("RSU");
                                aire = area.getString("Aire");
                                agua = area.getString("Agua");
                                electricidad = area.getString("Electricidad");
                                sonido = area.getString("Sonido");

                                p1=area.getString("P1").split(",");
                                p2=area.getString("P2").split(",");
                                p3=area.getString("P3").split(",");
                                p4=area.getString("P4").split(",");
                                color=color+area.getString("Estado");

                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                System.out.append(error.getMessage());
            }
        })*//*{
            @Override
            protected Map<String, String> getParams() {
                Map<String, String> params = new HashMap<String, String>();
                params.put("idarea", id);
                return params;
            }*//**//*
        }*//*;
        Toast.makeText(getApplicationContext(), "Datos punto 1 " + p1[0]+" , "+p1[1],Toast.LENGTH_LONG).show();

        requestQueue.add(jsonObjectRequest);*/
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
        LatLng uno = new LatLng(19.2622897,-103.7233931);

       /*mMap.addPolygon(new PolygonOptions()
                .add(new LatLng(Double.parseDouble(p1[0]), Double.parseDouble(p1[1])),
                        new LatLng(Double.parseDouble(p2[0]), Double.parseDouble(p2[1])),
                        new LatLng(Double.parseDouble(p3[0]), Double.parseDouble(p3[1])),
                        new LatLng(Double.parseDouble(p4[0]), Double.parseDouble(p4[1])))
                .strokeColor(Color.RED)
                .fillColor(Color.BLUE));*/


        CameraUpdate yourLocation = CameraUpdateFactory.newLatLngZoom(uno, 17);
        mMap.animateCamera(yourLocation);
        mMap.addMarker(new MarkerOptions().position(uno).title(nombre));


    }
}
