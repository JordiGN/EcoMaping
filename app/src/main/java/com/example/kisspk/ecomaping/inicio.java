package com.example.kisspk.ecomaping;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
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

import java.util.ArrayList;
import java.util.List;

public class inicio extends AppCompatActivity {
    ListView lista;
    View v;
    String dato;
    ODBC db;
    List<String> item=null;
    RequestQueue requestQueue;
    String showURL="http://webcolima.com/wsecomapping/verareas.php";
    /*String showURL="http://192.168.1.66:8080/wsecomapping/verareas.php";*/
    /*String showURL="http://192.168.79.187:8080/wsecomapping/verareas.php";*/
    ArrayList<String> listita= new ArrayList<String>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_inicio);
        lista=(ListView)findViewById(R.id.listView_lista);
        listita.add("Id, Área");
        db=new ODBC(this);
        db.BorrarDatosArea();
        db.BorrarDatosReporte();
        db.InsertArea(1,"Administrativa", "19.262586,-103.723911", 245, "19.263170,-103.724068", "19.263152,-103.723703", "19.261874,-103.723708", "19.261971,-103.724105");
        db.InsertArea(2,"Extraescolar", "19.262849,-103.723415", 52, "19.263450,-103.723675", "19.263429,-103.723106", "19.262301,-103.723663", "19.262282,-103.723148");
        db.InsertArea(3,"ISM", "19.261973,-103.723412", 450, "19.262305,-103.723696", "19.262286,-103.723152", "19.261577,-103.723723", "19.261491,-103.723121");
        db.InsertArea(4,"Laboratorios", "19.263650,-103.723023", 953, "19.263848,-103.724118", "19.263808,-103.722227", "19.263427,-103.724123", "19.263126,-103.722241");
        db.InsertArea(5,"Recursos Materiales", "19.262655,-103.722807", 240, "19.263126,-103.723080", "19.263097,-103.722053", "19.262147,-103.723096", "19.262155,-103.722214");
        db.InsertArea(6,"Sistemas", "19.261588,-103.722811", 356, "19.262136,-103.723104", "19.262145,-103.722347", "19.261066,-103.722638", "19.261116,-103.723152");

        db.InsertReporte(1, 2, 45, 654, 321, 456, 45, "2015-01-01", "YELLOW");
        db.InsertReporte(2, 5, 45, 200, 65, 45, 36, "2015-05-20", "RED");
        db.InsertReporte(3, 4, 85, 40, 654, 12, 46, "2015-11-02", "GREEN");
        db.InsertReporte(4, 3, 65, 965, 654, 65, 44, "2015-10-25", "YELLOW");

        verAreas();

        /*requestQueue = Volley.newRequestQueue(getApplicationContext());
        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.GET,showURL,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        try {
                            JSONArray areas = response.getJSONArray("areas");
                            for (int i = 0; i < areas.length(); i++) {
                                JSONObject area = areas.getJSONObject(i);
                                String id = area.getString("Id_Area");
                                String nom = area.getString("Nombre");
                                listita.add(id+","+nom);
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
        requestQueue.add(jsonObjectRequest);*/
        /*ArrayAdapter<String> adapter =
                new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1,listita);
        lista.setAdapter(adapter);

        lista.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterview, View view, int position, long id) {
                dato = lista.getItemAtPosition(position).toString();
                verArea(v);
            }
        });*/
        //Toast.makeText(this,"tamanio es= "+listita.size(),Toast.LENGTH_LONG).show();
    }
    public void verAreas(){

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
                Toast.makeText(getApplicationContext(), "Manda " + dato,Toast.LENGTH_LONG).show();
                verArea(v);
            }
        });
    };
    public void verArea(View v){
        Intent verarea=new Intent(this,mapatec.class);
        verarea.putExtra("dato", String.valueOf(dato));
        startActivity(verarea);
    }

}
