package com.example.stock_control.ui.stock_status


import androidx.recyclerview.widget.RecyclerView
import com.example.stock_control.databinding.ActivityProductSearchRowBinding
import com.example.stock_control.ui.home.product.entities.ProductDisplay


class ProductSearchCardViewHolder(val binding: ActivityProductSearchRowBinding) : RecyclerView.ViewHolder(binding.root) {

    fun bind(productDisplay: ProductDisplay){
        binding.productId.text = productDisplay.productId.toString()
        binding.productNumber.text= productDisplay.productNumber
        binding.productName.text = productDisplay.productName
        binding.purchasePrice.text = productDisplay.purchasePrice.toString()
        binding.salePrice.text = productDisplay.salePrice.toString()
        binding.stockLow.text = productDisplay.stockLow.toString()
        binding.supplierId.text = productDisplay.supplierId.toString()
        binding.supplierName.text = productDisplay.supplierName
    }

}