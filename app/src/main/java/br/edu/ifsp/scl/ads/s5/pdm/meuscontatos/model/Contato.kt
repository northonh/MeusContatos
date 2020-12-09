package br.edu.ifsp.scl.ads.s5.pdm.meuscontatos.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Contato(
    val nome: String = "",
    var telefone: String = "",
    var email: String = ""
): Parcelable