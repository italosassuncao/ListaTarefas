package com.example.listatarefas

import android.content.Intent
import android.os.Bundle
import android.util.Log
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
        tarefaAdapter = TarefaAdapter()
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
}