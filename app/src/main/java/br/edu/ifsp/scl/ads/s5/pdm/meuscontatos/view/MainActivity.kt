package br.edu.ifsp.scl.ads.s5.pdm.meuscontatos.view

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import br.edu.ifsp.scl.ads.s5.pdm.meuscontatos.adapter.ContatosAdapter
import br.edu.ifsp.scl.ads.s5.pdm.meuscontatos.adapter.OnContatoClickListener
import br.edu.ifsp.scl.ads.s5.pdm.meuscontatos.databinding.ActivityMainBinding
import br.edu.ifsp.scl.ads.s5.pdm.meuscontatos.model.Contato

class MainActivity : AppCompatActivity(), OnContatoClickListener {
    // Data source do Adapter
    private lateinit var contatosList: MutableList<Contato>
    // Adapter do RecyclerView
    private lateinit var contatosAdapter: ContatosAdapter
    // LayoutManager do RecyclerView
    private lateinit var contatosLayoutManager: LinearLayoutManager

    // Classe ViewBinding para evitar chamadas a findViewById
    private lateinit var activityMainBinding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // Instancia classe de ViewBinding e seta layout como sendo o root
        activityMainBinding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(activityMainBinding.root)

        // Inicializando lista de contatos para o Adapter
        contatosList = mutableListOf()
        for (i in 1..50) {
            contatosList.add(
                Contato(
                    "Nome $i",
                    "Telefone $i",
                    "Email $i"
                )
            )
        }

        // Instanciando o LayoutManager
        contatosLayoutManager = LinearLayoutManager(this)

        // Instanciando o Adapter
        contatosAdapter = ContatosAdapter(contatosList, this)

        // Associar o Adapter e o LayoutManager com o RecyclerView
        activityMainBinding.listaContatosRv.adapter = contatosAdapter
        activityMainBinding.listaContatosRv.layoutManager = contatosLayoutManager
    }

    override fun onContatoClick(position: Int) {
        // Recuperando o contato clicado
        val contato: Contato = contatosList[position]
        Toast.makeText(this, contato.nome, Toast.LENGTH_SHORT).show()
    }
}