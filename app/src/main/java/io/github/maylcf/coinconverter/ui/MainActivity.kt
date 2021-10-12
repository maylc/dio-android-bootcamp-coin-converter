package io.github.maylcf.coinconverter.ui

import android.os.Bundle
import android.widget.ArrayAdapter
import androidx.appcompat.app.AppCompatActivity
import androidx.core.widget.doAfterTextChanged
import io.github.maylcf.coinconverter.core.extensions.*
import io.github.maylcf.coinconverter.data.model.Coin
import io.github.maylcf.coinconverter.data.model.ExchangeResponseValue
import io.github.maylcf.coinconverter.databinding.ActivityMainBinding
import io.github.maylcf.coinconverter.presentation.MainViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel

class MainActivity : AppCompatActivity() {

    private val dialog by lazy { createProgressDialog() }
    private val binding by lazy { ActivityMainBinding.inflate(layoutInflater) }
    private val viewModel by viewModel<MainViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        bindAdapters()
        bindListeners()
        bindObserve()
    }

    private fun bindAdapters() {
        val list = Coin.values()
        val adapter = ArrayAdapter(this, android.R.layout.simple_list_item_1, list)

        binding.tvFrom.setAdapter(adapter)
        binding.tvTo.setAdapter(adapter)

        binding.tvFrom.setText(Coin.BRL.name, false)
        binding.tvTo.setText(Coin.USD.name, false)
    }

    private fun bindListeners() {
        binding.tilValue.editText?.doAfterTextChanged {
            binding.btnConverter.isEnabled = it != null && it.toString().isNotEmpty()
        }

        binding.btnConverter.setOnClickListener {
            it.hideSoftKeyboard()
            val search = "${binding.tilFrom.text}-${binding.tilTo.text}"
            viewModel.getExchangeValue(search)
        }
    }

    private fun bindObserve() {
        viewModel.state.observe(this) {
            when (it) {
                is MainViewModel.State.Loading -> dialog.show()
                is MainViewModel.State.Error -> {
                    dialog.dismiss()
                    createDialog { setMessage(it.throwable.message) }.show()
                }
                is MainViewModel.State.Success -> {
                    dialog.dismiss()
                    updateResult(it.value)
                }
            }
        }
    }

    private fun updateResult(exchangeResult: ExchangeResponseValue) {
        val selectedCoin = binding.tilTo.text
        val coin = Coin.values().find { it.name == selectedCoin } ?: Coin.BRL

        val result = exchangeResult.bid * binding.tilValue.text.toDouble()
        binding.tvResult.text = result.formatCurrency(coin.locale)
    }
}