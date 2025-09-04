package com.example.listatarefas.database

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import android.util.Log

class DatabaseHelper(context: Context): SQLiteOpenHelper(
    context,
    NOME_TABELA,
    null,
    1
) {
    override fun onCreate(db: SQLiteDatabase?) {
        val sql = "CREATE TABLE IF NOT EXISTS $TABELA_TAREFAS(" +
                "$ID_TAREFA INTEGER NOT NULL PRIMARY KEY AUTOINCREMENT," +
                "$DESCRICAO VARCHAR(100)," +
                "$DATA DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP" +
                ");"

        try {
            db?.execSQL(sql)
            Log.i("INFO_DB", "Sucesso ao criar a tabela")
        }catch (e: Exception){
            e.printStackTrace()
            Log.i("INFO_DB", "Erro ao criar a tabela")
        }
    }

    override fun onUpgrade(
        db: SQLiteDatabase?,
        oldVersion: Int,
        newVersion: Int
    ) {
        TODO("Not yet implemented")
    }

    companion object{
        const val NOME_TABELA = "ListaTarefas.db"
        const val VERSION = 1
        const val TABELA_TAREFAS = "tarefas"
        const val ID_TAREFA = "id_tarefa"
        const val DESCRICAO = "descricao"
        const val DATA = "data"
    }
}