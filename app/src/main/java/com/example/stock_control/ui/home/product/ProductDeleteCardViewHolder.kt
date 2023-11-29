package com.example.stock_control.ui.home.product


import androidx.recyclerview.widget.RecyclerView
import com.example.stock_control.databinding.ActivityProductDeleteRowBinding
import com.example.stock_control.ui.home.product.entities.ProductCheck

class ProductDeleteCardViewHolder(val binding: ActivityProductDeleteRowBinding) : RecyclerView.ViewHolder(binding.root) {

    fun bind(productCheck: ProductCheck){
        binding.productId.text = productCheck.productId.toString()
        binding.productNumber.text= productCheck.productNumber
        binding.productName.text = productCheck.productName
        binding.purchasePrice.text = productCheck.purchasePrice.toString()
        binding.salePrice.text = productCheck.salePrice.toString()
        binding.stockLow.text = productCheck.stockLow.toString()
        binding.supplierId.text = productCheck.supplierId.toString()
        binding.supplierName.text = productCheck.supplierName
        binding.checkBox.isChecked = productCheck.isChecked

        binding.checkBox.setOnCheckedChangeListener { _, isChecked ->
            productCheck.isChecked = isChecked
        }
    }
}