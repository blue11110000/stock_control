package com.example.stock_control.ui.home.supplier

import android.content.Context
import android.content.Intent
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.example.stock_control.databinding.ActivitySupplierSearchRowBinding
import com.example.stock_control.ui.home.supplier.entities.Supplier

class SupplierUpdateCardViewHolder(val binding: ActivitySupplierSearchRowBinding,
                                   private val context: Context) : RecyclerView.ViewHolder(binding.root) {

    fun bind(supplier: Supplier, supplierUpdate: Intent){
        binding.root.setOnClickListener {
            supplierUpdate.putExtra("supplierId",binding.supplierId.text)
            supplierUpdate.putExtra("supplierName",binding.supplierName.text)
            supplierUpdate.putExtra("phoneNumber",binding.phoneNumber.text)
            supplierUpdate.putExtra("email",binding.email.text)
            ContextCompat.startActivity(context, supplierUpdate, null)
        }
        binding.supplierId.text = supplier.supplierId.toString()
        binding.supplierName.text = supplier.supplierName
        binding.phoneNumber.text = supplier.phoneNumber.toString()
        binding.email.text = supplier.email
    }

}