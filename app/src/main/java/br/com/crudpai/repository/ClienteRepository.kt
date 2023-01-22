package br.com.crudpai.repository

import androidx.lifecycle.LiveData
import br.com.crudpai.data.db.entity.ClienteEntity
import br.com.crudpai.data.db.entity.TelefoneEntity

interface ClienteRepository {

    suspend fun insertCliente(razaoSocial : String, nomeFantasia : String, cnpj : String,
                              fones : List<TelefoneEntity>, email: String, municipioEndereco : String,
                              logradouroEndereco : String, numeroEndereco : String,
                              descricaoEndereco : String): Long

    suspend fun updateCliente(id : Long, razaoSocial : String, nomeFantasia : String, cnpj : String,
                              fones : List<TelefoneEntity>, email: String, municipioEndereco : String,
                              logradouroEndereco : String, numeroEndereco : String,
                              descricaoEndereco : String)

    suspend fun deleteCliente(id : Long)

    suspend fun deleteAllClientes()

    suspend fun getAllClientes(): LiveData<List<ClienteEntity>>
}