package com.fup.jennyferlopez.proyectokitetkiwe.gestorbd;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.Toast;

import com.fup.jennyferlopez.proyectokitetkiwe.models.Puntos;
import com.fup.jennyferlopez.proyectokitetkiwe.models.Usuario;

import java.util.ArrayList;
import java.util.List;

public class

GestorBd extends SQLiteOpenHelper {

    Context context;

    private static final String BDNAME = "BDREGISTRAR";
    private static final int BDVERSION = 1;

    private static final String TBLUSUARIO = "TABLAUSUARIO";
    private static final String IDUSUARIO = "IDUSUARIO";
    private static final String NOMUSUARIO = "NOMBREUSUARIO";
    private static final String CONTRASEÑAUSUARIO = "CONTRASEÑAUSUARIO";
    private static final String PATHIMG = "PATHIMG";
    private static final String SESION = "SESION";

    private static final String TBLPUNTOS = "TBLPUNTOS";
    private static final String IDPUNTOS = "IDPUNTOS";
    private static final String IDUSUARIOR = "IDUSUARIOR";
    private static final String PUNTOSOROS = "PUNTOSOROS";


    public GestorBd(Context context) {
        super(context, BDNAME, null, BDVERSION);
        this.context = context;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String tblUsuario = "create table " + TBLUSUARIO + "(" + IDUSUARIO + " INTEGER PRIMARY KEY AUTOINCREMENT, " + NOMUSUARIO + " TEXT, " +
                CONTRASEÑAUSUARIO + " TEXT, " + PATHIMG + " TEXT, " + SESION +" TEXT);";

        String tblPuntos="create table " + TBLPUNTOS + "(" + IDPUNTOS + " INTEGER PRIMARY KEY AUTOINCREMENT, " + PUNTOSOROS + " INTEGER, " + IDUSUARIOR + " INTEGER, FOREIGN KEY ( " + IDUSUARIOR + " ) REFERENCES " + TBLUSUARIO + "(" + IDUSUARIO +"))";

        db.execSQL(tblUsuario);
        db.execSQL(tblPuntos);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i2) {
        String sql2 = "drop table if exists" + TBLUSUARIO;
        String sqlPuntos = "drop table if exist" +TBLPUNTOS;

        db.execSQL(sqlPuntos);
        db.execSQL(sql2);
        onCreate(db);
    }

    public void agregarUsuario(Usuario usuario) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(NOMUSUARIO, usuario.getNomUsuario());
        values.put(CONTRASEÑAUSUARIO, usuario.getContraUsuario());
        values.put(PATHIMG, usuario.getPathImg());
        values.put(SESION, usuario.getSesion());
        long pos = db.insert(TBLUSUARIO, null, values);
        db.close();
    }

    public String validacioUser(String user) {
        String temp = null;
        SQLiteDatabase db = this.getWritableDatabase();
        String sql = ("select " + CONTRASEÑAUSUARIO + " FROM " + TBLUSUARIO + " WHERE " + NOMUSUARIO + " ='" + user + "'");
        Cursor cursor = db.rawQuery(sql, null);

        if (cursor.moveToFirst()) {
            temp = String.valueOf(cursor.getInt(0));
        }
        cursor.close();
        db.close();
        return temp;
    }
    public int obtenerId(String name_user) {
        int id_u = 0;
        SQLiteDatabase db = this.getWritableDatabase();
        String query = ("select " + IDUSUARIO + " from " + TBLUSUARIO + " where " + NOMUSUARIO + " ='" + name_user + "'");
        Cursor cursor = db.rawQuery(query, null);
        if (cursor.moveToFirst())

        {
            id_u = (cursor.getInt(0));
        }

        db.close();
        return id_u;
    }

    public void insertarPuntos(Puntos p) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(IDUSUARIOR, p.getId_usuario_r());
        values.put(PUNTOSOROS, p.getPuntos());
        db.insert(TBLPUNTOS, null, values);
        db.close();
    }

    public List<Puntos> sumaPuntos(int id) {
        SQLiteDatabase db = this.getWritableDatabase();
        String query = ("select SUM(PUNTOSOROS) FROM TBLPUNTOS where IDUSUARIOR ='"+id+"'");
        Cursor cursor = db.rawQuery(query, null);
        List<Puntos> puntos = new ArrayList<Puntos>();
        if (cursor.moveToFirst()) {
            Puntos p = new Puntos();
            p.setPuntos(cursor.getInt(0));
            puntos.add(p);
            cursor.moveToNext();
        }
        cursor.close();

        db.close();
        return puntos;
    }

    public void eliminarPuntaje(int idUsuario) {
        SQLiteDatabase db = this.getWritableDatabase();
        db.execSQL("delete from " + TBLPUNTOS + " where " + IDUSUARIOR + " = '" + idUsuario + "'");
        db.close();
    }

    public void actualizarActivity(String nombre, String contra, String pathimg, String activity, int id){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(NOMUSUARIO, nombre);
        values.put(CONTRASEÑAUSUARIO, contra);
        values.put(PATHIMG, pathimg);
        values.put(SESION, activity);
        db.update(TBLUSUARIO, values, IDUSUARIO + "='" + id + "'", null);
        db.close();
    }

    public String buscarActivity(int id){
        String sesion ="";
        SQLiteDatabase db = this.getWritableDatabase();
        String query = ("select " + SESION + " from " + TBLUSUARIO + " where " + IDUSUARIO + " ='" + id + "'");
        Cursor cursor = db.rawQuery(query, null);
        if (cursor.moveToFirst())

        {
            sesion = (cursor.getString(0));
        }

        db.close();
        return sesion;
    }

    public String buscarAvatar(int id){
        String avatar ="";
        SQLiteDatabase db = this.getWritableDatabase();
        String query = ("select " + PATHIMG + " from " + TBLUSUARIO + " where " + IDUSUARIO + " ='" + id + "'");
        Cursor cursor = db.rawQuery(query, null);
        if (cursor.moveToFirst())

        {
            avatar = (cursor.getString(0));
        }

        db.close();
        return avatar;
    }

    public List<Usuario> datosUsuario(int id) {
        SQLiteDatabase db = this.getWritableDatabase();
        String query = ("select * from "+ TBLUSUARIO + " WHERE " + IDUSUARIO + " ='"+id+"'");
        Cursor cursor = db.rawQuery(query, null);
        List<Usuario> usuarios = new ArrayList<Usuario>();
        if (cursor.moveToFirst()) {
            Usuario usu = new Usuario();
            usu.setNomUsuario(cursor.getString(1));
            usu.setContraUsuario(cursor.getString(2));
            usu.setPathImg(cursor.getString(3));
            usu.setSesion(cursor.getString(4));
            usuarios.add(usu);
            cursor.moveToNext();
        }
        cursor.close();

        db.close();
        return usuarios;
    }

}
