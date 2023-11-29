package com.example.stock_control.ui.stock

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
import com.example.stock_control.databinding.ActivityStockDeleteBinding
import com.example.stock_control.ui.stock.constants.DialogStockIn
import com.example.stock_control.ui.stock.constants.DialogStockOut
import com.example.stock_control.ui.stock.constants.StockConstants
import com.example.stock_control.ui.stock.dao.StockViewModel
import io.reactivex.disposables.Disposable

class StockDelete: AppCompatActivity(){

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        var disposable: Disposable? = null
        val binding = ActivityStockDeleteBinding.inflate(layoutInflater)
        setContentView(binding.root)
        val db = AppDatabase.getInstance(this)
        val viewModel = ViewModelFactory(db.stockDao())
            .create(StockViewModel::class.java)
        val stockBound = intent.getIntExtra("stockBound", StockConstants.STOCK_ERROR)

        binding.recycleView.layoutManager = LinearLayoutManager(this@StockDelete)

        if (stockBound == StockConstants.STOCK_IN_BOUND){
            StockViewModel.stockInCheckList.observe(this) { stockCheckList ->
                binding.recycleView.adapter =
                    RecyclerCardAdapter(stockCheckList.toMutableList(),CardType.STOCK_DELETE)
            }
        }else if (stockBound == StockConstants.STOCK_OUT_BOUND){
            StockViewModel.stockOutCheckList.observe(this) { stockCheckList ->
                binding.recycleView.adapter =
                    RecyclerCardAdapter(stockCheckList.toMutableList(),CardType.STOCK_DELETE)
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

                disposable =viewModel.findStockDeleteFlowable(productName,stockBound)

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
            disposable = viewModel.selectAllStockCheck(stockBound)
        }

        binding.delete.setOnClickListener {
            if (stockBound == StockConstants.STOCK_IN_BOUND){
                AlertDialog.Builder(this)
                    .setTitle(DialogStockIn.STOCK_IN_DELETE_TITLE)
                    .setMessage(DialogStockIn.STOCK_IN_DELETE_MSG)
                    .setPositiveButton(Dialog.BTN_OK) { _, _ ->
                        viewModel.deleteStockList(stockBound)
                    }.setNegativeButton(Dialog.BTN_NG,null).show()
            }else if (stockBound == StockConstants.STOCK_OUT_BOUND){
                AlertDialog.Builder(this)
                    .setTitle(DialogStockOut.STOCK_OUT_DELETE_TITLE)
                    .setMessage(DialogStockOut.STOCK_OUT_DELETE_MSG)
                    .setPositiveButton(Dialog.BTN_OK) { _, _ ->
                        viewModel.deleteStockList(stockBound)
                    }.setNegativeButton(Dialog.BTN_NG,null).show()
            }
        }

        binding.back.setOnClickListener {
            if (disposable != null){
                disposable!!.dispose()
            }
            finish()
        }
    }
}