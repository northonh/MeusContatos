package br.edu.ifsp.scl.ads.s5.pdm.meuscontatos.model

import com.google.firebase.database.ChildEventListener
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.ktx.database
import com.google.firebase.database.ktx.getValue
import com.google.firebase.ktx.Firebase

class ContatoFirebase: ContatoDao {
    private val CONTATOS_LIST_REALTIME_DATABASE = "contatosList"
    // Referência para o nó principal que é a lista de contatos do RtDb
    private val contatosListRtDb = Firebase.database.getReference(CONTATOS_LIST_REALTIME_DATABASE)

    private val contatosList: MutableList<Contato> = mutableListOf()
    init {
        contatosListRtDb.addChildEventListener(object: ChildEventListener{
            override fun onChildAdded(snapshot: DataSnapshot, previousChildName: String?) {
                val novoContato: Contato = snapshot.getValue<Contato>() ?: Contato()

                if (contatosList.indexOfFirst { it.nome.equals(novoContato.nome) } == -1) {
                    contatosList.add(novoContato)
                }
            }

            override fun onChildChanged(snapshot: DataSnapshot, previousChildName: String?) {
                val contatoEditado: Contato = snapshot.getValue<Contato>() ?: Contato()

                val posicao = contatosList.indexOfFirst { it.nome.equals(contatoEditado.nome) }
                contatosList[posicao] = contatoEditado
            }

            override fun onChildRemoved(snapshot: DataSnapshot) {
                val contatoRemovido: Contato = snapshot.getValue<Contato>() ?: Contato()
                contatosList.remove(contatoRemovido)
            }

            override fun onChildMoved(snapshot: DataSnapshot, previousChildName: String?) {
                // Não se aplica
            }

            override fun onCancelled(error: DatabaseError) {
                // Não se aplica
            }
        })
    }

    override fun createContato(contato: Contato) = createOrUpdateContato(contato)

    override fun readContato(nome: String): Contato = contatosList[contatosList.indexOfFirst { it.nome.equals(nome) }]

    override fun readContatos(): MutableList<Contato> = contatosList

    override fun updateContato(contato: Contato) = createOrUpdateContato(contato)

    override fun deleteContato(nome: String) {
        contatosListRtDb.child(nome).removeValue()
    }

    private fun createOrUpdateContato(contato: Contato) {
        contatosListRtDb.child(contato.nome).setValue(contato)
    }
}