package com.example.kisspk.ecomaping;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

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
    public String ESTATUS_AREA="EstadoArea";

    public String REPORTE="reporte";
    //Campos de las tabla
    public String ID_REPORTE="Id_Reporte";
    public String IDAREA="IdArea";
    public String RSU="RSU";
    public String AIRE="Aire";
    public String CONSUMO="Consumo";
    public String SONIDO="Sonido";
    public String FECHA="Fecha";
    public String ESTATUS_REPORTE="EstadoReporte";


    public ODBC(Context context/*, String name, SQLiteDatabase.CursorFactory factory, int version*/) {
        super(context, BASEDEDATOS, null, 1);
    }
    //Crear base de datos
    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE " + AREA + " ( " + ID_AREA+ " INTEGER PRIMARY KEY AUTOINCREMENT, " + NOMBRE +
                " TEXT "+POBLACION+"TEXT"+ ESTATUS_AREA+"NUMBER "+" )");
        db.execSQL("CREATE TABLE " + REPORTE + " ( " + ID_REPORTE + " INTEGER PRIMARY KEY AUTOINCREMENT, " + IDAREA +
                " NUMBER " + RSU + "NUMBER" + AIRE + "NUMBER" + CONSUMO + "NUMBER" + SONIDO + "NUMBER" + FECHA + "TEXT" + ESTATUS_REPORTE + "NUMBER " + " )");

    }
    //Actualización de tabla
    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {
        db.execSQL("DROP TABLE IF EXISTS" + AREA);
        db.execSQL("DROP TABLE IF EXISTS" + REPORTE);
        onCreate(db);

    }
    //Metodos para las áreas
    //Metodo de inserción
    public void InsertArea(String Nombre, int Poblacion){
        ContentValues parametros = new ContentValues();
        parametros.put(NOMBRE, Nombre);
        parametros.put(POBLACION,Poblacion);
        parametros.put(ESTATUS_AREA, 1);
        parametros.put("ENVIO", 0);
        this.getWritableDatabase().insert(AREA, null, parametros);
    }
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
        int i=this.getWritableDatabase().delete(AREA,NOMBRE + "=" + nombre ,null);
        return i;
    }

    //Metodos para los reportes
    //Metodo de inserción para los reportes
    public void InsertReporte(int area, int rsu, int aire, int consumo,int sonido, String fecha ){
        ContentValues parametros = new ContentValues();
        parametros.put(IDAREA, area);
        parametros.put(RSU,rsu);
        parametros.put(CONSUMO,consumo);
        parametros.put(SONIDO,sonido);
        parametros.put(FECHA,fecha);
        parametros.put(ESTATUS_REPORTE,1);
        parametros.put("ENVIO",0);
        this.getWritableDatabase().insert(AREA, null, parametros);
    }

    //Metodo para ectualizar datos
    public Integer ActualizarReporte(int area, int rsu, int aire, int consumo,int sonido, String fecha ){
        ContentValues parametros = new ContentValues();
        parametros.put(IDAREA, area);
        parametros.put(RSU,rsu);
        parametros.put(CONSUMO,consumo);
        parametros.put(SONIDO,sonido);
        parametros.put(FECHA,fecha);
        parametros.put(ESTATUS_REPORTE,2);
        parametros.put("ENVIO",0);
        //    Tabla, valor que insertas, where
        int  i=this.getWritableDatabase().update(AREA, parametros, ID_AREA + "=" + area, null);
        return i;
    }
    public void webservice_insert(String Nombre, String Apellido){

    }

}
