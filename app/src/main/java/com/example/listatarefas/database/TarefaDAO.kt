package com.example.listatarefas.database

import android.content.ContentValues
import android.content.Context
import android.util.Log
import com.example.listatarefas.model.Tarefa

class TarefaDAO(context: Context): ITarefaDAO {

    private val escrita = DatabaseHelper(context).writableDatabase
    private val leitura = DatabaseHelper(context).readableDatabase

    override fun salvar(tarefa: Tarefa): Boolean {

        val conteudo = ContentValues()
        conteudo.put("${DatabaseHelper.DESCRICAO}", tarefa.descricao)

        try {
            escrita.insert(
                DatabaseHelper.TABELA_TAREFAS,
                null,
                conteudo
            )
            Log.i("INFO_DB", "Sucesso ao criar a tabela")
        }catch (e: Exception){
            e.printStackTrace()
            Log.i("INFO_DB", "Erro ao criar a tabela")
            return false
        }
        return true
    }

    override fun atualizar(tarefa: Tarefa): Boolean {
        TODO("Not yet implemented")
    }

    override fun excluir(idTarefa: Int): Boolean {
        val args = arrayOf(idTarefa.toString())
        try {
            escrita.delete(
                DatabaseHelper.TABELA_TAREFAS,
                "${DatabaseHelper.ID_TAREFA} = ?",
                args
            )
            Log.i("INFO_DB", "Sucesso ao excluir tarefa")
        }catch (e: Exception){
            Log.i("INFO_DB", "Erro ao excluir tarefa")
            return false
        }
        return true
    }

    override fun listar(): List<Tarefa> {

        val listaTarefas = mutableListOf<Tarefa>()

        val sql = "SELECT ${DatabaseHelper.ID_TAREFA}, ${DatabaseHelper.DESCRICAO}," +
                " strftime('%d/%m/%Y %H:%M', ${DatabaseHelper.DATA}) ${DatabaseHelper.DATA}" +
                " FROM ${DatabaseHelper.TABELA_TAREFAS}"

        val cursor = leitura.rawQuery(sql, null)
        val indexId = cursor.getColumnIndex(DatabaseHelper.ID_TAREFA)
        val indexDescricao = cursor.getColumnIndex(DatabaseHelper.DESCRICAO)
        val indexData = cursor.getColumnIndex(DatabaseHelper.DATA)

        while (cursor.moveToNext()){
            val id = cursor.getInt(indexId)
            val descricao = cursor.getString(indexDescricao)
            val data = cursor.getString(indexData)

            listaTarefas.add(Tarefa(id, descricao, data))
        }

        return listaTarefas

    }

}