package com.example.crud_sqlite

import android.annotation.SuppressLint
import android.content.ContentValues
import android.content.Context;
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper;

var BD="baseDatos";
var tabla ="Usuario";

class BaseDatos(contexto: Context):SQLiteOpenHelper(contexto, BD, null, 1) {
    override fun onCreate(db: SQLiteDatabase?) {
        val sql="CREATE TABLE " + tabla +
                "( id INTEGER PRIMARY KEY AUTOINCREMENT, " +
                "nombre VARCHAR(25), " +
                "edad Integer)";
        db?.execSQL(sql)
    }

    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {
        TODO("Not yet implemented")
    }

    fun insertarDatos(usuario:Usuario):String{
        val db = this.writableDatabase
        var contenedor = ContentValues();

        contenedor.put("nombre", usuario.nombre);
        contenedor.put("edad", usuario.edad);

        var resultado = db.insert("Usuario", null, contenedor)
        if (resultado == -1.toLong()) {
            return "Falla en la base de datos"
        } else {
            return "Insertado con éxito"
        }
    }

    @SuppressLint("Range") // error por posibilidad de int = -1
    fun listarDatos():MutableList<Usuario> {
        var lista:MutableList<Usuario> = ArrayList()
        val db = this.readableDatabase
        val sql = "SELECT * FROM Usuario"
        val resultado = db.rawQuery(sql, null)
        if (resultado.moveToFirst()) {
            do {
                var usuario = Usuario()
                usuario.id = resultado.getString(resultado.getColumnIndex("id")).toInt()
                usuario.nombre = resultado.getString(resultado.getColumnIndex("nombre"))
                usuario.edad = resultado.getString(resultado.getColumnIndex("edad")).toInt()
                lista.add(usuario)
            } while (resultado.moveToNext())
                resultado.close()
                db.close()
            return(lista)
            }
        return lista
    }

    fun actualizarDatos(id:String, nombre:String, edad:Int):String{
        val db = this.writableDatabase
        var contenedorDeValores = ContentValues();
        contenedorDeValores.put("nombre", nombre)
        contenedorDeValores.put("edad", edad)
        var resultado = db.update(tabla, contenedorDeValores, "id=?", arrayOf(id))
        if (resultado > 0) {
            return "Actualizado con éxito"
        }else {
            return  "No se puedo actualizar"
        }
    }

    fun borrarDatos(id: String) {
        val db = this.writableDatabase
        if (id.length>0) {
            db.delete("Usuario","id=?", arrayOf(id))
        }
    }

}
