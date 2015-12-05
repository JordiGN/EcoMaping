package com.example.kisspk.ecomaping;

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
    String lat;
    String longi;

    String rsu;
    String aire;
    String consumo;
    String sonido ;

    RequestQueue requestQueue;
    ;

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
        String showURL="http://webcolima.com/wsecomapping/verreporte.php?idarea="+id;


        requestQueue = Volley.newRequestQueue(getApplicationContext());
        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(/*Request.Method.GET,*/ showURL,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        try {
                            JSONArray areas = response.getJSONArray("areas");
                                JSONObject area = areas.getJSONObject(0);
                                nom = area.getString("Nombre");
                                lat = area.getString("Latitud");
                                longi = area.getString("Longitud");

                                rsu = area.getString("RSU");
                                aire = area.getString("Aire");
                                consumo = area.getString("Consumo");
                                sonido = area.getString("Sonido");
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                System.out.append(error.getMessage());
            }
        })/*{
            @Override
            protected Map<String, String> getParams() {
                Map<String, String> params = new HashMap<String, String>();
                params.put("idarea", id);
                return params;
            }
        }*/;
        Toast.makeText(getApplicationContext(), "Manda " + nombre+","+rsu+","+aire+","+sonido+","+consumo,
                Toast.LENGTH_LONG).show();
        requestQueue.add(jsonObjectRequest);
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

        CameraUpdate yourLocation = CameraUpdateFactory.newLatLngZoom(uno, 17);
        mMap.animateCamera(yourLocation);
        mMap.addMarker(new MarkerOptions().position(uno).title(nombre));

    }
}
