package com.example.stock_control.ui.home.product

import android.content.Context
import android.content.Intent
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.example.stock_control.databinding.ActivityProductSearchRowBinding
import com.example.stock_control.ui.home.product.entities.ProductDisplay

class ProductUpdateCardViewHolder(val binding: ActivityProductSearchRowBinding,
                                  private val context: Context) : RecyclerView.ViewHolder(binding.root) {

    fun bind(productDisplay: ProductDisplay, productUpdate: Intent){
        binding.root.setOnClickListener {
            productUpdate.putExtra("productId",binding.productId.text)
            productUpdate.putExtra("productNumber",binding.productNumber.text)
            productUpdate.putExtra("productName",binding.productName.text)
            productUpdate.putExtra("purchasePrice",binding.purchasePrice.text)
            productUpdate.putExtra("salePrice",binding.salePrice.text)
            productUpdate.putExtra("stockLow",binding.stockLow.text)
            productUpdate.putExtra("supplierId",binding.supplierId.text)
            productUpdate.putExtra("supplierName",binding.supplierName.text)
            ContextCompat.startActivity(context, productUpdate, null)
        }
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