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
        TODO("Not yet implemented")
    }

    override fun listar(): List<Tarefa> {
        TODO("Not yet implemented")
    }

}