package com.example.stock_control.ui.stock_status

import android.os.Bundle
import android.widget.SearchView
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.stock_control.common.AppDatabase
import com.example.stock_control.common.RecyclerCardAdapter
import com.example.stock_control.common.ViewModelFactory
import com.example.stock_control.constants.CardType
import com.example.stock_control.databinding.ActivitySupplierSearchBinding
import com.example.stock_control.ui.stock_status.dao.StockListViewModel


class SupplierSearch : AppCompatActivity(){

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = ActivitySupplierSearchBinding.inflate(layoutInflater)
        setContentView(binding.root)
        val db = AppDatabase.getInstance(this)
        val viewModel = ViewModelFactory(db.stockListDao())
            .create(StockListViewModel::class.java)
        binding.recycleView.layoutManager = LinearLayoutManager(this@SupplierSearch)

        StockListViewModel.supplierList.observe(this) { supplierList ->
            binding.recycleView.adapter =
                RecyclerCardAdapter(supplierList.toMutableList(),CardType.SUPPLIER_SEARCH)
        }

        binding.searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener{
            override fun onQueryTextSubmit(supplierName: String?): Boolean {
                binding.searchView.clearFocus()

                if (supplierName == null){
                    return true
                }

                viewModel.findSupplierName(supplierName)

                return true
            }

            override fun onQueryTextChange(p0: String?): Boolean {
                return true
            }
        })

        binding.selectAll.setOnClickListener {
            viewModel.findAllSupplier()
        }

        binding.back.setOnClickListener {
            finish()
        }
    }
}