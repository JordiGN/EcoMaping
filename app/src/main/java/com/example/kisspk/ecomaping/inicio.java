package com.example.kisspk.ecomaping;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.format.Time;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

public class inicio extends AppCompatActivity {
    ListView lista;
    View v;
    String dato;
    ODBC db;
    List<String> item=null;
    RequestQueue requestQueue;
    String showURL="http://webcolima.com/wsecomapping/nvoReporte.php";
    /*String showURL="http://192.168.1.66:8080/wsecomapping/verareas.php";*/
    /*String showURL="http://192.168.79.187:8080/wsecomapping/verareas.php";*/
    ArrayList<String> listita= new ArrayList<String>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_inicio);
        lista=(ListView)findViewById(R.id.listView_lista);
        listita.add("Id, Área");
        verAreas();
    }

    public void verAreas(){
        db=new ODBC(this);
        Cursor cur=db.VerAreas();
        item=new ArrayList<String>();
        if (cur.moveToFirst()){
            do {
                listita.add(cur.getString(0)+","+cur.getString(1));
            }while (cur.moveToNext());
        }
        ArrayAdapter<String> adapter =
                new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1,listita);
        lista.setAdapter(adapter);

        lista.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterview, View view, int position, long id) {
                dato = lista.getItemAtPosition(position).toString();
                verArea(v);
            }
        });
    }
    public void verArea(View v){
        Intent verarea=new Intent(this,mapatec.class);
        verarea.putExtra("dato", String.valueOf(dato));
        verarea.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(verarea);
    }
    public void Actualizar(View v){

        Toast.makeText(getApplicationContext(), "DATOS AGREGADOS CON ÉXITO",Toast.LENGTH_LONG).show();
        verAreas();


        requestQueue = Volley.newRequestQueue(getApplicationContext());
        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.GET,showURL,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        try {
                            JSONArray reportes = response.getJSONArray("reportes");
                            for (int i = 0; i < reportes.length(); i++) {
                                JSONObject reporte = reportes.getJSONObject(i);
                                /*String id = area.getString("Id_Area");
                                String nom = area.getString("Nombre");
                                String ubi = area.getString("Ubicacion");
                                String pob = area.getString("Poblacion");
                                String p1 = area.getString("P1");
                                String p2 = area.getString("P2");
                                String p3 = area.getString("P3");
                                String p4 = area.getString("P4");
                                db.InsertArea(nom,ubi,pob,p1,p2,p3,p4);*/

                                String idArea =reporte.getString("IdArea");
                                String rsu =reporte.getString("RSU");
                                String aire =reporte.getString("Aire");
                                String agua =reporte.getString("Agua");
                                String electricidad =reporte.getString("Electricidad");
                                String sonido =reporte.getString("Sonido");
                                String fecha =reporte.getString("Fecha");
                                String estadoReporte =reporte.getString("Estado");


                                db.InsertReporte(Integer.parseInt(idArea), Integer.parseInt(rsu),
                                        Integer.parseInt(aire), Integer.parseInt(agua), Integer.parseInt(electricidad),
                                        Integer.parseInt(sonido),fecha, estadoReporte);
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
        });
        requestQueue.add(jsonObjectRequest);
    }

}
