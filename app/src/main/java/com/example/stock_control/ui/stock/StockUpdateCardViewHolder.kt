package com.example.stock_control.ui.stock

import android.content.Context
import android.content.Intent
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.example.stock_control.databinding.ActivityStockSearchRowBinding
import com.example.stock_control.ui.stock.entities.StockDisplay

class StockUpdateCardViewHolder(val binding: ActivityStockSearchRowBinding,
                                private val context: Context) : RecyclerView.ViewHolder(binding.root) {

    fun bind(stock: StockDisplay, stockUpdate: Intent){
        binding.root.setOnClickListener {
            stockUpdate.putExtra("stockId",stock.stockId.toString())
            stockUpdate.putExtra("productId",stock.productId.toString())
            stockUpdate.putExtra("productNumber",binding.productNumber.text)
            stockUpdate.putExtra("productName",binding.productName.text)
            stockUpdate.putExtra("stockBound",stock.stockBound.toString())
            stockUpdate.putExtra("stockDate",binding.stockDate.text)
            stockUpdate.putExtra("stockQuantity",binding.stockQuantity.text)
            ContextCompat.startActivity(context, stockUpdate, null)
        }
        binding.productId.text=stock.productId.toString()
        binding.productNumber.text= stock.productNumber
        binding.productName.text = stock.productName
        binding.stockDate.text = stock.stockDate
        binding.stockQuantity.text = stock.stockQuantity.toString()
    }

}