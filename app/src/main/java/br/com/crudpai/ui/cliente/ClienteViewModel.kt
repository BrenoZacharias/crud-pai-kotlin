package br.com.crudpai.ui.cliente

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import br.com.crudpai.R
import br.com.crudpai.data.db.entity.TelefoneEntity
import br.com.crudpai.repository.ClienteRepository
import kotlinx.coroutines.launch

class ClienteViewModel(
    private val repository: ClienteRepository
) : ViewModel() {

    private val _clienteStateEventData = MutableLiveData<ClienteState>()
    val subscriberStateEventData: LiveData<ClienteState>
        get() = _clienteStateEventData

    private val _messageEventData = MutableLiveData<Int>()
    val messageEventData: LiveData<Int>
        get() = _messageEventData

    fun addCliente(razaoSocial : String, nomeFantasia : String, cnpj : String,
                   fones : List<TelefoneEntity>, email: String, municipioEndereco : String,
                   logradouroEndereco : String, numeroEndereco : String,
                   descricaoEndereco : String) = viewModelScope.launch {
        try {
            val id = repository.insertCliente(razaoSocial, nomeFantasia, cnpj,
                fones, email, municipioEndereco,
            logradouroEndereco, numeroEndereco,
            descricaoEndereco)
            if (id > 0) {
                _clienteStateEventData.value = ClienteState.Inserted
                _messageEventData.value = R.string.cliente_inserted_successfully
            }
        } catch (ex : Exception){
            _messageEventData.value = R.string.cliente_error_to_insert
            Log.e(TAG, ex.toString())
        }
    }

    sealed class ClienteState {
        object Inserted : ClienteState()
    }

    companion object {
        private val TAG = ClienteViewModel::class.java.simpleName
    }
}