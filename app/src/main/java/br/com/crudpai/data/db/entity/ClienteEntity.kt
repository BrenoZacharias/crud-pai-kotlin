package br.com.crudpai.data.db.entity

import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "cliente")
data class ClienteEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Long = 0,
    val cnpj: String,
    val razaoSocial: String,
    val nomeFantasia: String,
    val email: String,
    val fones: List<TelefoneEntity>,
    @Embedded
    val endereco: EnderecoEntity
)


