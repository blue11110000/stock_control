package com.example.stock_control.ui.stock_status

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import androidx.fragment.app.Fragment
import com.example.stock_control.databinding.FragmentStockListBinding


class StockListFragment : Fragment() {
    companion object{
        const val SUPPLIER_SEARCH = 0
        const val PRODUCT_SEARCH = 1
        const val STOCK_SEARCH = 2
        const val STOCK_REPLENISHMENT_SEARCH = 0
        const val STOCK_TOTAL_SEARCH = 1
        const val SALE_TOTAL_SEARCH = 2
    }

    private var _binding: FragmentStockListBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentStockListBinding.inflate(inflater, container, false)
        val root: View = binding.root
        val stockSearchProcess = binding.stockSearchProcess
        stockSearchProcess.onItemClickListener = ListItemClickListenerStockSearchProcess()
        val stockListProcess = binding.stockListProcess
        stockListProcess.onItemClickListener = ListItemClickListenerStockListProcess()
        return root
    }

    private inner class ListItemClickListenerStockSearchProcess : AdapterView.OnItemClickListener {
        override fun onItemClick(parent: AdapterView<*>, view: View, position: Int, id: Long) {

            val productSearch = Intent(context, ProductSearch::class.java)
            val supplierSearch = Intent(context, SupplierSearch::class.java)
            val stockSearch = Intent(context, StockSearch::class.java)

            when (position) {
                SUPPLIER_SEARCH -> {
                    startActivity(productSearch)
                }
                PRODUCT_SEARCH -> {
                    startActivity(supplierSearch)
                }
                STOCK_SEARCH -> {
                    startActivity(stockSearch)
                }
            }
        }
    }
    private inner class ListItemClickListenerStockListProcess : AdapterView.OnItemClickListener {
        override fun onItemClick(parent: AdapterView<*>, view: View, position: Int, id: Long) {

            val stockReplenishmentList = Intent(context, StockReplenishmentSearch::class.java)
            val stockQuantityRanking = Intent(context, StockTotalSearch::class.java)
            val salesRanking = Intent(context, SaleTotalSearch::class.java)

            when (position) {
                STOCK_REPLENISHMENT_SEARCH -> {
                    startActivity(stockReplenishmentList)
                }
                STOCK_TOTAL_SEARCH -> {
                    startActivity(stockQuantityRanking)
                }
                SALE_TOTAL_SEARCH -> {
                    startActivity(salesRanking)
                }
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}