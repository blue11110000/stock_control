package com.example.stock_control.ui.stock_status.dao

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

import com.example.stock_control.ui.home.product.entities.ProductDisplay
import com.example.stock_control.ui.home.supplier.entities.Supplier
import com.example.stock_control.ui.stock.entities.StockDisplay
import com.example.stock_control.ui.stock_status.entities.StockListDisplay
import com.example.stock_control.ui.stock_status.entities.StockListTotalDisplay

class StockListViewModel(private val dao: StockListDao) : ViewModel() {

    companion object{
        val productList = MutableLiveData<MutableList<ProductDisplay>>()
        val supplierList = MutableLiveData<MutableList<Supplier>>()
        val stockList = MutableLiveData<MutableList<StockDisplay>>()
        val stockReplenishmentList = MutableLiveData<MutableList<StockListDisplay>>()
        val stockRankingList = MutableLiveData<MutableList<StockListTotalDisplay>>()
        val saleRankingList = MutableLiveData<MutableList<StockListTotalDisplay>>()
    }

    fun findProductName(productName:String){
        productList.value =dao.selectProductName(productName)
    }

    fun findAllProduct(){
        productList.value =dao.selectAllProduct()
    }

    fun findSupplierName(supplierName:String){
        supplierList.value = dao.selectSupplierName(supplierName)
    }

    fun findAllSupplier(){
        supplierList.value =dao.selectAllSupplier()
    }

    fun findStockName(productName:String) {
        stockList.value =dao.selectStock(productName)
    }

    fun findStockAll(){
        stockList.value =dao.selectStockAll()
    }

    fun findStockReplenishment(){
        stockReplenishmentList.value  = dao.selectReplenish()

    }

    fun findStockReplenishmentProductName(productName: String){
        stockReplenishmentList.value  = dao.selectReplenishProductName(productName)

    }

    fun findStockTotal(){
        stockRankingList.value = dao.selectStockTotal()
    }

    fun findStockTotalProductName(productName: String){
        stockRankingList.value = dao.selectStockTotalProductName(productName)
    }

    fun findSaleTotal(){
        saleRankingList.value = dao.selectSaleTotal()
    }

    fun findSaleTotalProductName(productName: String){
        saleRankingList.value = dao.selectSaleTotalProductName(productName)
    }
}