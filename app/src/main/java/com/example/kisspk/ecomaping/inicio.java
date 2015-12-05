package com.example.kisspk.ecomaping;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
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

public class inicio extends AppCompatActivity {
    ListView lista;
    View v;
    String dato;
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
        requestQueue = Volley.newRequestQueue(getApplicationContext());
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
        requestQueue.add(jsonObjectRequest);
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
        Toast.makeText(this,"tamanio es= "+listita.size(),Toast.LENGTH_LONG).show();
    }
    public void verArea(View v){
        Intent verarea=new Intent(this,mapatec.class);
        verarea.putExtra("dato", String.valueOf(dato));
        startActivity(verarea);
    }

}
