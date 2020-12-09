package br.edu.ifsp.scl.ads.s5.pdm.meuscontatos.model

// Podemos usar qualquer implementação de persistência mas esse é o comportamento esperado
interface ContatoDao {
    fun createContato(contato: Contato)
    fun readContato(nome: String): Contato
    fun readContatos(): MutableList<Contato>
    fun updateContato(contato: Contato)
    fun deleteContato(nome: String)
}
