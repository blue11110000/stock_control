package com.example.stock_control.ui.stock

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import androidx.fragment.app.Fragment
import com.example.stock_control.databinding.FragmentStockBinding
import com.example.stock_control.ui.stock.constants.StockConstants


class StockFragment : Fragment() {
    companion object{
        const val STOCK_IN_REGISTER = 0
        const val STOCK_IN_UPDATE = 1
        const val STOCK_IN_DELETE = 2
        const val STOCK_OUT_REGISTER = 0
        const val STOCK_OUT_UPDATE = 1
        const val STOCK_OUT_DELETE = 2
    }

    private var _binding: FragmentStockBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentStockBinding.inflate(inflater, container, false)
        val root: View = binding.root
        val stockInProcess = binding.stockInProcess
        stockInProcess.onItemClickListener = ListItemClickListenerStockInProcess()
        val stockOutProcess = binding.stockOutProcess
        stockOutProcess.onItemClickListener = ListItemClickListenerStockOutProcess()

        return root
    }
    private inner class ListItemClickListenerStockInProcess : AdapterView.OnItemClickListener {
        override fun onItemClick(parent: AdapterView<*>, view: View, position: Int, id: Long) {

            val stockInRegister = Intent(context, StockRegister::class.java)
            val stockInSearch = Intent(context, StockSearch::class.java)
            val stockInDelete = Intent(context, StockDelete::class.java)

            when (position) {
                STOCK_IN_REGISTER -> {
                    stockInRegister.putExtra("stockBound", StockConstants.STOCK_IN_BOUND)
                    startActivity(stockInRegister)
                }
                STOCK_IN_UPDATE -> {
                    stockInSearch.putExtra("stockBound",StockConstants.STOCK_IN_BOUND)
                    startActivity(stockInSearch)
                }
                STOCK_IN_DELETE -> {
                    stockInDelete.putExtra("stockBound",StockConstants.STOCK_IN_BOUND)
                    startActivity(stockInDelete)
                }
            }
        }
    }
    private inner class ListItemClickListenerStockOutProcess : AdapterView.OnItemClickListener {
        override fun onItemClick(parent: AdapterView<*>, view: View, position: Int, id: Long) {

            val stockOutRegister = Intent(context, StockRegister::class.java)
            val stockOutSearch = Intent(context, StockSearch::class.java)
            val stockOutDelete = Intent(context, StockDelete::class.java)

            when (position) {
                STOCK_OUT_REGISTER -> {
                    stockOutRegister.putExtra("stockBound",StockConstants.STOCK_OUT_BOUND)
                    startActivity(stockOutRegister)
                }
                STOCK_OUT_UPDATE -> {
                    stockOutSearch.putExtra("stockBound",StockConstants.STOCK_OUT_BOUND)
                    startActivity(stockOutSearch)
                }
                STOCK_OUT_DELETE -> {
                    stockOutDelete.putExtra("stockBound",StockConstants.STOCK_OUT_BOUND)
                    startActivity(stockOutDelete)

                }
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}