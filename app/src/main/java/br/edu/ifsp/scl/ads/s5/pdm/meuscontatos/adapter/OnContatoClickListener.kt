package br.edu.ifsp.scl.ads.s5.pdm.meuscontatos.adapter

// Interface que será implementada na Activity para tratar os eventos de clique
// e usada no Adapter para tratar os eventos de clique
interface OnContatoClickListener {
    fun onContatoClick(position: Int)
}