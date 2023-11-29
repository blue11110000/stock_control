package com.example.stock_control.ui.stock_status

import androidx.recyclerview.widget.RecyclerView
import com.example.stock_control.databinding.ActivityStockTotalSearchRowBinding
import com.example.stock_control.ui.stock_status.entities.StockListTotalDisplay

class StockTotalSearchCardViewHolder(val binding: ActivityStockTotalSearchRowBinding) : RecyclerView.ViewHolder(binding.root) {

    fun bind(stock: StockListTotalDisplay){
        binding.productId.text=stock.productId.toString()
        binding.productNumber.text= stock.productNumber
        binding.productName.text = stock.productName
        binding.stockTotalQuantity.text = stock.total.toString()
    }
}