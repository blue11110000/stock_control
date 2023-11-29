package com.example.stock_control.ui.stock_status

import android.os.Bundle
import android.widget.SearchView
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.stock_control.databinding.ActivityStockSearchBinding
import com.example.stock_control.common.AppDatabase
import com.example.stock_control.constants.CardType
import com.example.stock_control.common.RecyclerCardAdapter
import com.example.stock_control.common.ViewModelFactory
import com.example.stock_control.ui.stock_status.dao.StockListViewModel

class SaleTotalSearch : AppCompatActivity(){

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = ActivityStockSearchBinding.inflate(layoutInflater)
        setContentView(binding.root)
        val db = AppDatabase.getInstance(this)
        val viewModel = ViewModelFactory(db.stockListDao())
            .create(StockListViewModel::class.java)

        binding.recycleView.layoutManager = LinearLayoutManager(this@SaleTotalSearch)

        StockListViewModel.saleRankingList.observe(this) { stockList ->
            binding.recycleView.adapter =
                RecyclerCardAdapter(
                    stockList.toMutableList(),
                    CardType.SALE_TOTAL
                )
        }

        binding.searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener{
            override fun onQueryTextSubmit(productName: String?): Boolean {
                binding.searchView.clearFocus()

                if (productName == null){
                    return true
                }

                viewModel.findSaleTotalProductName(productName)

                return true
            }

            override fun onQueryTextChange(p0: String?): Boolean {
                return true
            }
        })

        binding.selectAll.setOnClickListener {
            viewModel.findSaleTotal()
        }

        binding.back.setOnClickListener {
            finish()
        }
    }
}