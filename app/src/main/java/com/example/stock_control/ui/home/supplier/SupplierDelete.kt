package com.example.stock_control.ui.home.supplier

import android.os.Bundle
import android.widget.SearchView
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.stock_control.common.AppDatabase
import com.example.stock_control.common.RecyclerCardAdapter
import com.example.stock_control.common.ViewModelFactory
import com.example.stock_control.constants.CardType
import com.example.stock_control.constants.Dialog
import com.example.stock_control.databinding.ActivitySupplierDeleteBinding
import com.example.stock_control.ui.home.supplier.constants.DialogSupplier
import com.example.stock_control.ui.home.supplier.dao.SupplierViewModel
import io.reactivex.disposables.Disposable

class SupplierDelete: AppCompatActivity(){

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        var disposable: Disposable? = null
        val binding = ActivitySupplierDeleteBinding.inflate(layoutInflater)
        setContentView(binding.root)
        val db = AppDatabase.getInstance(this)
        val viewModel = ViewModelFactory(db.supplierDao())
            .create(SupplierViewModel::class.java)
        binding.recycleView.layoutManager = LinearLayoutManager(this@SupplierDelete)

        SupplierViewModel.supplierCheckList.observe(this) { supplierCheckList ->
            binding.recycleView.adapter =
                RecyclerCardAdapter(supplierCheckList.toMutableList(),CardType.SUPPLIER_DELETE)

        }

        binding.searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener{
            override fun onQueryTextSubmit(supplierName: String?): Boolean {
                binding.searchView.clearFocus()
                if (supplierName == null){
                    return true
                }
                if (disposable != null){
                    disposable!!.dispose()
                }
                disposable =viewModel.findSupplierDeleteFlowable(supplierName)

                return true
            }

            override fun onQueryTextChange(p0: String?): Boolean {
                return true
            }
        })

        binding.selectAll.setOnClickListener {
            if (disposable != null){
                disposable!!.dispose()
            }
            disposable = viewModel.selectAllSupplierCheck()
        }

        binding.delete.setOnClickListener {
            AlertDialog.Builder(this)
                .setTitle(DialogSupplier.DELETE_TITLE)
                .setMessage(DialogSupplier.DELETE_MSG)
                .setPositiveButton(Dialog.BTN_OK) { _, _ ->
                    viewModel.deleteSupplierList()
                }.setNegativeButton(Dialog.BTN_NG,null).show()
        }

        binding.back.setOnClickListener {
            if (disposable != null){
                disposable!!.dispose()
            }
            finish()
        }
    }
}