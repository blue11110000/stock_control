package com.example.stock_control.ui.stock


import androidx.recyclerview.widget.RecyclerView
import com.example.stock_control.databinding.ActivityStockDeleteRowBinding
import com.example.stock_control.ui.stock.entities.StockCheck

class StockDeleteCardViewHolder(val binding: ActivityStockDeleteRowBinding) : RecyclerView.ViewHolder(binding.root) {

    fun bind(stockCheck: StockCheck){
        binding.productId.text = stockCheck.productId.toString()
        binding.productNumber.text= stockCheck.productNumber
        binding.productName.text = stockCheck.productName
        binding.stockDate.text = stockCheck.stockDate
        binding.stockQuantity.text = stockCheck.stockQuantity.toString()
        binding.checkBox.isChecked = stockCheck.isChecked

        binding.checkBox.setOnCheckedChangeListener { _, isChecked ->
            stockCheck.isChecked = isChecked
        }
    }
}