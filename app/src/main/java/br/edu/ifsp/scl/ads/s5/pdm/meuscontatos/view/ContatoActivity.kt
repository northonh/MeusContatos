package br.edu.ifsp.scl.ads.s5.pdm.meuscontatos.view

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import br.edu.ifsp.scl.ads.s5.pdm.meuscontatos.databinding.ActivityContatoBinding
import br.edu.ifsp.scl.ads.s5.pdm.meuscontatos.model.Contato

class ContatoActivity : AppCompatActivity() {
    // Classe de ViewBinding
    private lateinit var activityContatoBinding: ActivityContatoBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        activityContatoBinding = ActivityContatoBinding.inflate(layoutInflater)
        setContentView(activityContatoBinding.root)

        activityContatoBinding.salvarBt.setOnClickListener {
            val novoContato = Contato(
                activityContatoBinding.nomeContatoEt.text.toString(),
                activityContatoBinding.telefoneContatoEt.text.toString(),
                activityContatoBinding.emailContatoEt.text.toString()
            )

            val retornoIntent = Intent()
            retornoIntent.putExtra(MainActivity.Extras.EXTRA_CONTATO, novoContato)
            setResult(RESULT_OK, retornoIntent)
            finish()
        }
    }
}