package com.example.crud_sqlite

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.EditText
import android.widget.ListView
import android.widget.TextView

class MainActivity : AppCompatActivity() {
    lateinit var nombre: EditText
    lateinit var edad: EditText
    lateinit var codigo:EditText
    lateinit var mensaje:TextView


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        nombre = findViewById(R.id.txt_Nombre)
        edad = findViewById(R.id.txt_Edad)
        codigo = findViewById(R.id.txt_Codigo)
        mensaje = findViewById(R.id.txt_Lista)
    }

    fun GuardarDatos(view:View) {
        var bd = BaseDatos(this)
        var usuario = Usuario()
        if (nombre.text.toString().length>0 && edad.toString().length>0) {
            usuario.nombre = nombre.text.toString()
            usuario.edad = edad.text.toString().toInt()
            bd.insertarDatos(usuario)
        }
    }

    fun ListarDatos(view: View) {
        var bd = BaseDatos(this)
        if (codigo.text.toString().length>0) {
            mensaje.text = ""
            var listaDatos = bd.listarDatos()
            for (i in 0..listaDatos.size - 1) {
                mensaje.append("Código " + listaDatos.get(i).id + " Nombre " +  listaDatos.get(i).nombre + " Edad " +  listaDatos.get(i).edad + "\n")
            }
        }
    }

    fun BorrarDatos(view: View) {
        var bd = BaseDatos(this)
        if (codigo.text.toString().length>0){
            bd.borrarDatos(codigo.text.toString())
        }
    }

    fun ActualizarDatos(view: View) {
        var bd = BaseDatos(this)
        if (nombre.text.toString().length>0 && edad.toString().length>0 && codigo.text.toString().length>0) {
            bd.actualizarDatos(codigo.text.toString(), nombre.text.toString(), edad.text.toString().toInt())
        }
    }
}