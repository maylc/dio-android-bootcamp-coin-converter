package io.github.maylcf.coinconverter.ui.history

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import io.github.maylcf.coinconverter.core.extensions.formatCurrency
import io.github.maylcf.coinconverter.data.model.Coin
import io.github.maylcf.coinconverter.data.model.ExchangeResponseValue
import io.github.maylcf.coinconverter.databinding.ItemHistoryBinding

class HistoryListAdapter :
    ListAdapter<ExchangeResponseValue, HistoryListAdapter.ViewHolder>(DiffCallback()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = ItemHistoryBinding.inflate(inflater, parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    inner class ViewHolder(
        private val binding: ItemHistoryBinding
    ) : RecyclerView.ViewHolder(binding.root) {

        fun bind(item: ExchangeResponseValue) {
            binding.tvName.text = item.name
            val coin = Coin.getByName(item.codein)
            binding.tvValue.text = item.bid.formatCurrency(coin.locale)
        }
    }
}

class DiffCallback : DiffUtil.ItemCallback<ExchangeResponseValue>() {
    override fun areItemsTheSame(
        oldItem: ExchangeResponseValue,
        newItem: ExchangeResponseValue
    ) = oldItem == newItem

    override fun areContentsTheSame(
        oldItem: ExchangeResponseValue,
        newItem: ExchangeResponseValue
    ) = oldItem.id == newItem.id

}