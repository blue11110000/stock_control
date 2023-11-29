package com.example.stock_control.ui.home.supplier


import androidx.recyclerview.widget.RecyclerView
import com.example.stock_control.databinding.ActivitySupplierDeleteRowBinding
import com.example.stock_control.ui.home.supplier.entities.SupplierCheck

class SupplierDeleteCardViewHolder(val binding: ActivitySupplierDeleteRowBinding) : RecyclerView.ViewHolder(binding.root) {

    fun bind(supplierCheck: SupplierCheck){
        binding.supplierId.text = supplierCheck.supplierId.toString()
        binding.supplierName.text = supplierCheck.supplierName
        binding.phoneNumber.text = supplierCheck.phoneNumber.toString()
        binding.email.text = supplierCheck.email
        binding.checkBox.isChecked = supplierCheck.isChecked

        binding.checkBox.setOnCheckedChangeListener { _, isChecked ->
            supplierCheck.isChecked = isChecked
        }
    }
}