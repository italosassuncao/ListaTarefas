package com.example.listatarefas

import android.app.AlertDialog
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.listatarefas.adapter.TarefaAdapter
import com.example.listatarefas.database.TarefaDAO
import com.example.listatarefas.databinding.ActivityMainBinding
import com.example.listatarefas.model.Tarefa

class MainActivity : AppCompatActivity() {

    private val binding by lazy {
        ActivityMainBinding.inflate(layoutInflater)
    }

    private var listaTarefas = emptyList<Tarefa>()

    private var tarefaAdapter: TarefaAdapter? = null

    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(binding.root)

        binding.fabAdicionar.setOnClickListener {
            val intent = Intent(this, AdicionarTarefaActivity::class.java)
            startActivity(intent)
        }

        //Recycler View
        tarefaAdapter = TarefaAdapter ({ id -> confirmarExclusao(id)},
            { tarefa -> editar(tarefa)})

        binding.rvTarefas.adapter = tarefaAdapter
        binding.rvTarefas.layoutManager = LinearLayoutManager(this)

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
    }

    private fun carregarListaTarefas() {
        val tarefaDAO = TarefaDAO(this)
        listaTarefas = tarefaDAO.listar()
        tarefaAdapter?.carregarLista(listaTarefas)
    }

    override fun onStart() {
        super.onStart()
        carregarListaTarefas()
    }

    private fun confirmarExclusao(id: Int) {

        val alertBuilder = AlertDialog.Builder(this)
        alertBuilder.setTitle("Confirmação")
        alertBuilder.setMessage("Deseja realmente excluir a tarefa?")

        alertBuilder.setPositiveButton("Sim") { _, _ ->
            val tarefaDAO = TarefaDAO(this)
            if (tarefaDAO.excluir(id)) {
                carregarListaTarefas()
                Toast.makeText(this,
                    "Sucesso ao excluir tarefa!",
                    Toast.LENGTH_SHORT).show()
            } else {
                Toast.makeText(this,
                    "Erro ao remover tarefa!",
                    Toast.LENGTH_SHORT).show()
            }
        }

        alertBuilder.setNegativeButton("Não") { _, _ ->
            Toast.makeText(this,
                "Exclusão cancelada!",
                Toast.LENGTH_SHORT).show()
        }
        alertBuilder.create().show()
    }

    private fun editar(tarefa: Tarefa) {
        val intent = Intent(this, AdicionarTarefaActivity::class.java)
        intent.putExtra("tarefa", tarefa)
        startActivity(intent)
    }
}
