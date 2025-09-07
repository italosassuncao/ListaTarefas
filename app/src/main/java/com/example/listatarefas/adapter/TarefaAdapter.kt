package com.example.listatarefas.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.listatarefas.databinding.ItemTarefaBinding
import com.example.listatarefas.model.Tarefa

class TarefaAdapter(
    val onClickExcluir: (Int) -> Unit
) : RecyclerView.Adapter<TarefaAdapter.TarefaViewHolder>() {

    private var listaTarefas: List<Tarefa> = emptyList()

    fun carregarLista(lista: List<Tarefa>) {
        this.listaTarefas = lista
        notifyDataSetChanged()
    }

    inner class TarefaViewHolder(itemBinding: ItemTarefaBinding)
        : RecyclerView.ViewHolder(itemBinding.root) {

        private val binding: ItemTarefaBinding

        init {
            binding = itemBinding
        }
        fun binding(tarefa: Tarefa) {
            binding.textDescricao.text = tarefa.descricao
            binding.textData.text = tarefa.data

            binding.buttonExcluir.setOnClickListener {
                onClickExcluir(tarefa.idTarefa)
            }
        }
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): TarefaViewHolder {
        val itemTarefaBinding = ItemTarefaBinding.inflate(
            LayoutInflater.from(parent.context), parent, false
        )
        return TarefaViewHolder(itemTarefaBinding)
    }

    override fun onBindViewHolder(holder: TarefaViewHolder, position: Int) {
        val tarefa = listaTarefas[position]
        holder.binding(tarefa)
    }

    override fun getItemCount(): Int {
        return listaTarefas.size
    }
}