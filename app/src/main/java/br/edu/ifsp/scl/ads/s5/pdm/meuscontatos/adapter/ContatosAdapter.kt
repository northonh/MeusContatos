package br.edu.ifsp.scl.ads.s5.pdm.meuscontatos.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import br.edu.ifsp.scl.ads.s5.pdm.meuscontatos.R
import br.edu.ifsp.scl.ads.s5.pdm.meuscontatos.model.Contato

class ContatosAdapter(
    private val contatosList: MutableList<Contato>,
    private val onContatoClickListener: OnContatoClickListener,
): RecyclerView.Adapter<ContatosAdapter.ContatoViewHolder>() {

    inner class ContatoViewHolder(layoutContatoView: View): RecyclerView.ViewHolder(layoutContatoView) {
        val nomeTv: TextView = layoutContatoView.findViewById(R.id.nomeTv)
        val telefoneTv: TextView = layoutContatoView.findViewById(R.id.telefoneTv)
    }

    // Chamado pelo LayoutManager para criar uma nova View
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ContatoViewHolder {
        // Cria uma nova View
        val layoutContatoView: View = LayoutInflater.from(parent.context).inflate(R.layout.layout_contato, parent, false)

        // Criar e retorna um ViewHolder associado a nova View
        return ContatoViewHolder(layoutContatoView)
    }

    // Chamado pelo ViewHolder para alterar o conteúdo de uma View
    override fun onBindViewHolder(holder: ContatoViewHolder, position: Int) {
        // Busca o contato para pegar os valores
        val contato = contatosList[position]

        // Seta os novos valores no ViewHolder
        holder.nomeTv.text = contato.nome
        holder.telefoneTv.text = contato.telefone
        // Seta o onClickListener da View associada ao ViewHolder como uma lambda que
        // chama a função definida na interface OnContatoClick, implementada na Activity e
        // recebida como parâmetro no construtor do Adapter. Ou seja, ao ser clicada a
        // View terá chamará a função definida na Activity passando a posição
        holder.itemView.setOnClickListener{
            onContatoClickListener.onContatoClick(position)
        }
    }

    // Chamado pelo LayoutManager para buscar a quantidade de dados e preparar a quanti
    // dade de Views e ViewHolders
    override fun getItemCount(): Int = contatosList.size
}
