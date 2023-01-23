package br.com.crudpai.ui.cliente

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import br.com.crudpai.data.db.AppDatabase
import br.com.crudpai.data.db.dao.ClienteDAO
import br.com.crudpai.repository.ClienteRepository
import br.com.crudpai.repository.DatabaseDataSourceCliente
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.google.android.material.snackbar.Snackbar
import br.com.crudpai.R
import br.com.crudpai.extensions.hideKeyboard
import kotlinx.android.synthetic.main.fragment_cliente.*

class ClienteFragment : Fragment(R.layout.fragment_cliente) {

    private val viewModel: ClienteViewModel by viewModels {
        object : ViewModelProvider.Factory {
            override fun <T : ViewModel> create(modelClass: Class<T>): T {
                val clienteDAO : ClienteDAO =
                    AppDatabase.getInstance(requireContext()).clienteDAO
                val repository : ClienteRepository = DatabaseDataSourceCliente(clienteDAO)
                return ClienteViewModel(repository) as T
            }
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        observeEvents()
        setListeners()
    }

    private fun observeEvents() {
        viewModel.subscriberStateEventData.observe(viewLifecycleOwner) { clienteState ->
            when (clienteState) {
                is ClienteViewModel.ClienteState.Inserted -> {
                    clearFields()
                    hideKeyboard()
                }
            }
        }
        viewModel.messageEventData.observe(viewLifecycleOwner) { stringResId ->
            Snackbar.make(requireView(), stringResId, Snackbar.LENGTH_LONG).show()
        }
    }

    private fun clearFields() {
        input_razao_social.text?.clear()
        input_email.text?.clear()
    }

    private fun hideKeyboard() {
        val parentActivity = requireActivity()
        if (parentActivity is AppCompatActivity) {
            parentActivity.hideKeyboard()
        }
    }

    private fun setListeners() {
        button_subscriber.setOnClickListener {
            val razaoSocial = input_razao_social.text.toString()
            val email = input_email.text.toString()
        }
    }
}