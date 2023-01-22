package br.com.crudpai.repository

import androidx.lifecycle.LiveData
import br.com.crudpai.data.db.dao.ClienteDAO
import br.com.crudpai.data.db.entity.ClienteEntity
import br.com.crudpai.data.db.entity.ContatoEntity

class DatabaseDataSourceCliente(private val clienteDAO: ClienteDAO) : ClienteRepository{

    override suspend fun insertCliente(razaoSocial : String, nomeFantasia : String, cnpj : String,
                              fones : List<ContatoEntity>, email: String, municipioEndereco : String,
                                       logradouroEndereco : String, numeroEndereco : String,
                                       descricaoEndereco : String): Long {
        val cliente = ClienteEntity(
            razaoSocial = razaoSocial,
            nomeFantasia = nomeFantasia,
            cnpj = cnpj,
            fones = fones,
            email = email,
            municipioEndereco = municipioEndereco,
            logradouroEndereco = logradouroEndereco,
            numeroEndereco = numeroEndereco,
            descricaoEndereco = descricaoEndereco
        )
        return clienteDAO.insert(cliente)
    }

    override suspend fun updateCliente(id : Long, razaoSocial : String, nomeFantasia : String, cnpj : String,
                              fones : List<ContatoEntity>, email: String, municipioEndereco : String,
                                       logradouroEndereco : String, numeroEndereco : String,
                                       descricaoEndereco : String){
        val cliente = ClienteEntity(
            razaoSocial = razaoSocial,
            nomeFantasia = nomeFantasia,
            cnpj = cnpj,
            fones = fones,
            email = email,
            municipioEndereco = municipioEndereco,
            logradouroEndereco = logradouroEndereco,
            numeroEndereco = numeroEndereco,
            descricaoEndereco = descricaoEndereco
        )
        return clienteDAO.update(cliente)

    }

    override suspend fun deleteCliente(id : Long){
        clienteDAO.delete(id)
    }

    override suspend fun deleteAllClientes(){
        clienteDAO.deleteAll()
    }

    override suspend fun getAllClientes(): LiveData<List<ClienteEntity>> {
        return clienteDAO.getAll()
    }
}