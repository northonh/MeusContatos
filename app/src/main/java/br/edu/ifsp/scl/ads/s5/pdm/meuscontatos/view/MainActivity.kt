package br.edu.ifsp.scl.ads.s5.pdm.meuscontatos.view

import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import br.edu.ifsp.scl.ads.s5.pdm.meuscontatos.R
import br.edu.ifsp.scl.ads.s5.pdm.meuscontatos.adapter.ContatosAdapter
import br.edu.ifsp.scl.ads.s5.pdm.meuscontatos.adapter.OnContatoClickListener
import br.edu.ifsp.scl.ads.s5.pdm.meuscontatos.controller.ContatoController
import br.edu.ifsp.scl.ads.s5.pdm.meuscontatos.databinding.ActivityMainBinding
import br.edu.ifsp.scl.ads.s5.pdm.meuscontatos.model.Contato
import br.edu.ifsp.scl.ads.s5.pdm.meuscontatos.view.MainActivity.Extras.EXTRA_CONTATO

class MainActivity : AppCompatActivity(), OnContatoClickListener {
    // Data source do Adapter
    private lateinit var contatosList: MutableList<Contato>
    // Adapter do RecyclerView
    private lateinit var contatosAdapter: ContatosAdapter
    // LayoutManager do RecyclerView
    private lateinit var contatosLayoutManager: LinearLayoutManager

    // Classe ViewBinding para evitar chamadas a findViewById
    private lateinit var activityMainBinding: ActivityMainBinding

    // Constantes para a ContatoActivity
    private val NOVO_CONTATO_REQUEST_CODE = 0
    object Extras {
        val EXTRA_CONTATO = "EXTRA_CONTATO"
    }

    // Controller
    private lateinit var contatoController: ContatoController

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // Instancia classe de ViewBinding e seta layout como sendo o root
        activityMainBinding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(activityMainBinding.root)

        // Instanciando Controller
        contatoController = ContatoController(this)

        // Inicializando lista de contatos para o Adapter
        contatosList = contatoController.buscaContatos()

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

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_main, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean =
        if (item.itemId == R.id.novoContatoMi) {
            val novoContantoIntent = Intent(this, ContatoActivity::class.java)
            startActivityForResult(novoContantoIntent, NOVO_CONTATO_REQUEST_CODE)
            true
        }
        else
            false

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == NOVO_CONTATO_REQUEST_CODE && resultCode == RESULT_OK && data != null) {
            val novoContato = data.getParcelableExtra<Contato>(EXTRA_CONTATO)
            if (novoContato != null){
                // Salvando no banco
                contatoController.insereContato(novoContato)

                contatosList.add(novoContato)
                contatosAdapter.notifyDataSetChanged()
            }
        }
    }
}