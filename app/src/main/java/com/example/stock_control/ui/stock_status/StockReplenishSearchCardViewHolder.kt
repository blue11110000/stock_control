package com.example.stock_control.ui.stock_status

import androidx.recyclerview.widget.RecyclerView
import com.example.stock_control.databinding.ActivityStockReplenishmentSearchRowBinding
import com.example.stock_control.ui.stock_status.entities.StockListDisplay

class StockReplenishSearchCardViewHolder(val binding: ActivityStockReplenishmentSearchRowBinding) : RecyclerView.ViewHolder(binding.root) {

    fun bind(stock: StockListDisplay){
        binding.productId.text= stock.productId.toString()
        binding.productNumber.text= stock.productNumber
        binding.productName.text = stock.productName
        binding.stockTotalQuantity.text = stock.total.toString()
        binding.stockLow.text = stock.stockLow.toString()
    }
}