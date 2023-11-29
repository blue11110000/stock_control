package com.example.stock_control.ui.stock

import android.content.Intent
import android.os.Bundle
import android.widget.SearchView
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.stock_control.databinding.ActivityStockSearchBinding
import com.example.stock_control.common.AppDatabase
import com.example.stock_control.constants.CardType
import com.example.stock_control.common.RecyclerCardAdapter
import com.example.stock_control.common.ViewModelFactory
import com.example.stock_control.ui.stock.constants.StockConstants
import com.example.stock_control.ui.stock.dao.StockViewModel
import io.reactivex.disposables.Disposable

class StockSearch : AppCompatActivity(){

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        var disposable: Disposable? = null
        val binding = ActivityStockSearchBinding.inflate(layoutInflater)
        setContentView(binding.root)
        val db = AppDatabase.getInstance(this)
        val viewModel = ViewModelFactory(db.stockDao())
            .create(StockViewModel::class.java)

        val stockBound = intent.getIntExtra("stockBound", StockConstants.STOCK_ERROR)
        val stockUpdate = Intent(this, StockUpdate::class.java)

        binding.recycleView.layoutManager = LinearLayoutManager(this@StockSearch)

        if (stockBound == StockConstants.STOCK_IN_BOUND){
            StockViewModel.stockInList.observe(this) { stockList ->
                binding.recycleView.adapter =
                    RecyclerCardAdapter(stockList.toMutableList(),stockUpdate,CardType.STOCK_UPDATE)
            }
        }else if (stockBound == StockConstants.STOCK_OUT_BOUND){
            StockViewModel.stockOutList.observe(this) { stockList ->
                binding.recycleView.adapter =
                    RecyclerCardAdapter(stockList.toMutableList(), stockUpdate,CardType.STOCK_UPDATE)
            }
        }

        binding.searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener{
            override fun onQueryTextSubmit(productName: String?): Boolean {
                binding.searchView.clearFocus()

                if (productName == null){
                    return true
                }
                if (disposable != null){
                    disposable!!.dispose()
                }
                disposable =viewModel.findStockNameFlowable(productName,stockBound)

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
            disposable = viewModel.selectAllStock(stockBound)
        }

        binding.back.setOnClickListener {
            if (disposable != null){
                disposable!!.dispose()
            }
            finish()
        }
    }
}