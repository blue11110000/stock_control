package com.example.stock_control.common

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.stock_control.ui.home.product.dao.ProductDao
import com.example.stock_control.ui.home.product.dao.ProductViewModel
import com.example.stock_control.ui.home.supplier.dao.SupplierDao
import com.example.stock_control.ui.home.supplier.dao.SupplierViewModel
import com.example.stock_control.ui.stock.dao.StockDao
import com.example.stock_control.ui.stock.dao.StockViewModel
import com.example.stock_control.ui.stock_status.dao.StockListDao
import com.example.stock_control.ui.stock_status.dao.StockListViewModel

//
class ViewModelFactory(private val dataSource: Any)
    : ViewModelProvider.Factory {

        @Suppress("UNCHECKED_CAST")
        override fun <T : ViewModel> create(modelClass: Class<T>): T {

            if (modelClass.isAssignableFrom(ProductViewModel::class.java)) {
                return ProductViewModel(dataSource as ProductDao) as T
            }
            if (modelClass.isAssignableFrom(SupplierViewModel::class.java)) {
                return SupplierViewModel(dataSource as SupplierDao) as T
            }
            if (modelClass.isAssignableFrom(StockViewModel::class.java)) {
                return StockViewModel(dataSource as StockDao) as T
            }
            if (modelClass.isAssignableFrom(StockListViewModel::class.java)) {
                return StockListViewModel(dataSource as StockListDao) as T
            }

            throw IllegalArgumentException("Unknown ViewModel class: ${modelClass.name}")
        }
}
