package com.example.stock_control.ui.home.supplier

import android.content.Intent
import android.os.Bundle
import android.widget.SearchView
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.stock_control.common.AppDatabase
import com.example.stock_control.common.RecyclerCardAdapter
import com.example.stock_control.common.ViewModelFactory
import com.example.stock_control.constants.CardType
import com.example.stock_control.databinding.ActivitySupplierSearchBinding
import com.example.stock_control.ui.home.supplier.dao.SupplierViewModel

import io.reactivex.disposables.Disposable

class SupplierSearch : AppCompatActivity(){

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        var disposable: Disposable? = null
        val binding = ActivitySupplierSearchBinding.inflate(layoutInflater)
        setContentView(binding.root)
        val db = AppDatabase.getInstance(this)
        val viewModel = ViewModelFactory(db.supplierDao())
            .create(SupplierViewModel::class.java)
        val supplierUpdate = Intent(this, SupplierUpdate::class.java)
        binding.recycleView.layoutManager = LinearLayoutManager(this@SupplierSearch)

        SupplierViewModel.supplierList.observe(this) { supplierList ->
            binding.recycleView.adapter =
                RecyclerCardAdapter(supplierList.toMutableList(), supplierUpdate,CardType.SUPPLIER_UPDATE)
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
                disposable =viewModel.findSupplierNameFlowable(supplierName)

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
            disposable = viewModel.selectAllSupplier()
        }

        binding.back.setOnClickListener {
            if (disposable != null){
                disposable!!.dispose()
            }
            finish()
        }
    }
}