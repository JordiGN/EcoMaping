package com.example.kisspk.ecomaping;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.text.TextUtils;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

/**
 * Created by KissPK on 14/10/2015.
 */
public class ODBC extends SQLiteOpenHelper{
    //Base de datos
    public static String BASEDEDATOS="ecomapping";
    //Tabla
    public String AREA="area";
    //Campos de las tabla
    public String ID_AREA="Id_Area";
    public String NOMBRE="Nombre";
    public String POBLACION="Poblacion";
    public String UBUCACION="Ubicacion";
    public String P1="p1";
    public String P2="p2";
    public String P3="p3";
    public String P4="p4";

    public String REPORTE="reporte";
    //Campos de las tabla
    public String ID_REPORTE="Id_Reporte";
    public String IDAREA="IdArea";
    public String RSU="Rsu";
    public String AIRE="Aire";
    public String AGUA="Agua";
    public String ELECTRICIDAD= "Electricidad";
    public String SONIDO="Sonido";
    public String FECHA="Fecha";
    public String ESTATUS_REPORTE="EstadoReporte";


    public Context mContext;


    public ODBC(Context context/*, String name, SQLiteDatabase.CursorFactory factory, int version*/) {
        super(context, BASEDEDATOS, null, 1);
        mContext=context;
    }
    //Crear base de datos
    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL
                ("CREATE TABLE AREA (\n" +
                        "Id_Area INTEGER PRIMARY KEY AUTOINCREMENT,\n" +
                        "Nombre TEXT,\n" +
                        "Ubicacion TEXT,\n" +
                        "Poblacion NUMBER,\n" +
                        "P1 TEXT,\n" +
                        "P2 TEXT,\n" +
                        "P3 TEXT,\n" +
                        "P4 TEXT\n" +
                        ")");

        db.execSQL("CREATE TABLE  REPORTE (\n" +
                "        Id_Reporte INTEGER PRIMARY KEY AUTOINCREMENT,\n" +
                "        IdArea NUMBER,\n" +
                "        RSU NUMBER,\n" +
                "        Aire NUMBER,\n" +
                "        Agua NUMBER,\n" +
                "        Electricidad NUMBER,\n" +
                "        Sonido NUMBER,\n" +
                "        Fecha TEXT,\n" +
                "        Estado TEXT\n" +
                "        )");
    }
    //Actualización de tabla
    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {
        db.execSQL("DROP TABLE IF EXISTS" + AREA);
        db.execSQL("DROP TABLE IF EXISTS" + REPORTE);
        onCreate(db);

    }

    public Cursor VerAreas(){
        String renglon[]={ID_AREA,NOMBRE};
        Cursor c = this.getReadableDatabase().query(AREA, renglon, null, null, null, null, null);
        return c;
    }
    public Cursor VerReportes(String idArea){
        String renglon[]={ID_AREA,P1,P2,P3,P4,UBUCACION};
        Cursor c = this.getReadableDatabase().query(AREA, renglon, ID_AREA+"=?",
                new String[]{idArea}, null, null, null);
        return c;
    }
   /* public Cursor VerReportes(String idArea){
        String renglon[]={ID_AREA,P1,P2,P3,P4,ESTATUS_REPORTE};
        Cursor c = this.getReadableDatabase().query(AREA+"INNER JOIN"+REPORTE+"ON Id_Area=IdArea", renglon, ID_AREA+"=?",
                new String[]{idArea}, null, null, null);
        return c;
    }*/
   /* public Cursor VerReportes(String idArea){
        String query = "SELECT"+ID_AREA+","+P1+","+P2+","+P3+","+P4+"FROM AREA WHERE ID_AREA=?";
        Cursor cr = this.getReadableDatabase().rawQuery(query, new String[]{idArea});
        return cr;
    }*/
    //Metodos para las áreas
    //Metodo de inserción

     public void InsertArea(String Nombre, String Ubicacion,int Poblacion, String p1, String p2, String p3, String p4){
         ContentValues parametros = new ContentValues();
         SQLiteDatabase db = getWritableDatabase();
         if(db != null){
             parametros.put( NOMBRE,Nombre);
             parametros.put( UBUCACION,Ubicacion);
             parametros.put( POBLACION,Poblacion);
             parametros.put( P1,p1);
             parametros.put( P2,p2);
             parametros.put( P3,p3);
             parametros.put( P4,p4);
         }

         this.getWritableDatabase().insert(AREA, null, parametros);
        };
    public Integer BorrarDatosArea(){//Tabla, where, parametros
        int i=this.getWritableDatabase().delete(AREA, ID_AREA + ">" + 0, null);
        return i;
    }

    public void InsertReporte(int IdArea,int Rsu, int Aire, int Agua,int Electricidad, int Sonido,String Fecha, String EstadoReporte ){
        ContentValues parametros = new ContentValues();
        SQLiteDatabase db = getWritableDatabase();
        if(db != null){
            parametros.put(IDAREA,IdArea);
            parametros.put(RSU,Rsu);
            parametros.put(AIRE,Aire);
            parametros.put(AGUA,Agua);
            parametros.put(ELECTRICIDAD,Electricidad);
            parametros.put(SONIDO,Sonido);
            parametros.put(FECHA,Fecha);
            parametros.put(ESTATUS_REPORTE,EstadoReporte);
        }
        this.getWritableDatabase().insert(AREA, null, parametros);
    };
    public Integer BorrarDatosReporte(){//Tabla, where, parametros
        int i=this.getWritableDatabase().delete(REPORTE, ID_REPORTE + ">" + 0, null);
        return i;
    };
    /*
        public Integer ActualizarArea(String nombre, int poblacion){
        ContentValues parametros = new ContentValues();
        //poner en el campo NOMBRe, loque se trae del parámetro nombre
        parametros.put(NOMBRE, nombre);
        parametros.put(POBLACION,poblacion);
        parametros.put(ESTATUS_AREA,2);
        parametros.put("ENVIO",0);
        //    Tabla, valor que insertas, where
        int  i=this.getWritableDatabase().update(AREA, parametros, NOMBRE + "=" + nombre, null);
        return i;
    }

    //Metodo para borrar persona
    public Integer BorrarArea(String nombre){//Tabla, where, parametros
        ContentValues parametros = new ContentValues();
        parametros.put(NOMBRE, nombre);
        parametros.put(ESTATUS_AREA, 3);
        parametros.put("ENVIO", 0);
        int i=this.getWritableDatabase().delete(AREA, NOMBRE + "=" + nombre, null);
        return i;
    }*/
}
