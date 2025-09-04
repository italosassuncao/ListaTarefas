package com.example.listatarefas

import android.os.Bundle
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.listatarefas.database.TarefaDAO
import com.example.listatarefas.databinding.ActivityAdicionarTarefaBinding
import com.example.listatarefas.databinding.ActivityMainBinding
import com.example.listatarefas.model.Tarefa

class AdicionarTarefaActivity : AppCompatActivity() {

    private val binding by lazy {
        ActivityAdicionarTarefaBinding.inflate(layoutInflater)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        binding.buttonSalvar.setOnClickListener {
            if (binding.editTarefa.text.isNotEmpty()) {
                val descricao = binding.editTarefa.text.toString()
                val tarefa = Tarefa(
                    -1,
                    descricao,
                    "default"
                )

                val tarefaDAO = TarefaDAO(this)
                if(tarefaDAO.salvar(tarefa)){
                    Toast.makeText(
                        this,
                        "Tarefa salva com sucesso",
                        Toast.LENGTH_SHORT).show()
                    finish()
                }

            }else{
                Toast.makeText(
                    this,
                    "Preencha uma tarefa",
                    Toast.LENGTH_SHORT).show()
            }
        }
    }
}