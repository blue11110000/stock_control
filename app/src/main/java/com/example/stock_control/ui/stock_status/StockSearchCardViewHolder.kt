package com.example.stock_control.ui.stock_status

import androidx.recyclerview.widget.RecyclerView
import com.example.stock_control.databinding.ActivityStockInOutSearchRowBinding
import com.example.stock_control.ui.stock.constants.StockConstants
import com.example.stock_control.ui.stock.entities.StockDisplay

class StockSearchCardViewHolder(val binding: ActivityStockInOutSearchRowBinding) : RecyclerView.ViewHolder(binding.root) {

    fun bind(stock: StockDisplay){
        binding.productId.text = stock.productId.toString()
        binding.productNumber.text= stock.productNumber
        binding.productName.text = stock.productName
        binding.stockDate.text = stock.stockDate
        binding.stockQuantity.text = stock.stockQuantity.toString()

        when(stock.stockBound){
            StockConstants.STOCK_IN_BOUND -> {
                binding.stockBound.text = StockConstants.STOCK_IN_BOUND_ITEM
            }
            StockConstants.STOCK_OUT_BOUND -> {
                binding.stockBound.text = StockConstants.STOCK_OUT_BOUND_ITEM
            }

        }
    }
}