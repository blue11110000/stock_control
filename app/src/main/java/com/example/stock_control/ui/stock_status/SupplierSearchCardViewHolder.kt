package com.example.stock_control.ui.stock_status


import androidx.recyclerview.widget.RecyclerView
import com.example.stock_control.databinding.ActivitySupplierSearchRowBinding
import com.example.stock_control.ui.home.supplier.entities.Supplier

class SupplierSearchCardViewHolder(val binding: ActivitySupplierSearchRowBinding) : RecyclerView.ViewHolder(binding.root) {

    fun bind(supplier: Supplier){
        binding.supplierId.text = supplier.supplierId.toString()
        binding.supplierName.text = supplier.supplierName
        binding.phoneNumber.text = supplier.phoneNumber.toString()
        binding.email.text = supplier.email
    }

}