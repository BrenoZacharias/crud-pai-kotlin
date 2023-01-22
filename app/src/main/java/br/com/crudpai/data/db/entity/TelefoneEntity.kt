package br.com.crudpai.data.db.entity

import androidx.room.ForeignKey

data class TelefoneEntity(
    val numero: String,
    val descricao: String?
)