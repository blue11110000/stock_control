package com.example.stock_control.ui.stock.dao


import android.content.Context
import androidx.appcompat.app.AlertDialog
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.stock_control.constants.Dialog
import com.example.stock_control.ui.home.product.entities.ProductNameNumber
import com.example.stock_control.ui.stock.constants.DialogStockOut
import com.example.stock_control.ui.stock.constants.StockConstants
import com.example.stock_control.ui.stock.entities.Stock
import com.example.stock_control.ui.stock.entities.StockCheck
import com.example.stock_control.ui.stock.entities.StockDisplay
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers
import kotlinx.coroutines.launch


class StockViewModel(private val dao: StockDao) : ViewModel() {
    companion object{
        val stockInList = MutableLiveData<MutableList<StockDisplay>>()
        val stockOutList = MutableLiveData<MutableList<StockDisplay>>()
        val stockInCheckList = MutableLiveData<MutableList<StockCheck>>()
        val stockOutCheckList = MutableLiveData<MutableList<StockCheck>>()
    }

    fun findStockNameFlowable(productNameFlowable:String,stockBound: Int): Disposable {
        val disposable =dao.selectStockNameFlowable(productNameFlowable,stockBound)
            .subscribeOn(Schedulers.io())
            .subscribe { stocks ->
                viewModelScope.launch {
                    if (stockBound == StockConstants.STOCK_IN_BOUND){
                        stockInList.value = stocks
                    }else if (stockBound == StockConstants.STOCK_OUT_BOUND){
                        stockOutList.value = stocks
                    }
                }
            }
        return disposable
    }

    fun findStockDeleteFlowable(productNameFlowable:String,stockBound: Int): Disposable {
        val disposable =dao.selectStockNameFlowable(productNameFlowable,stockBound)
            .subscribeOn(Schedulers.io())
            .subscribe { stocks ->
                val stockCheckListTemp = mutableListOf<StockCheck>()
                stocks.forEach {
                    stockCheckListTemp.add(StockCheck(it))
                }
                viewModelScope.launch {
                    if (stockBound == StockConstants.STOCK_IN_BOUND){
                        stockInCheckList.value = stockCheckListTemp
                    }else if (stockBound == StockConstants.STOCK_OUT_BOUND){
                        stockOutCheckList.value = stockCheckListTemp
                    }
                }
            }
        return disposable
    }

    fun selectAllStockCheck(stockBound: Int): Disposable{
        val disposable = dao.selectAllStockFlowable(stockBound)
            .subscribeOn(Schedulers.io())
            .subscribe { stocks ->
                val stockCheckListTemp = mutableListOf<StockCheck>()
                stocks.forEach {
                    stockCheckListTemp.add(StockCheck(it))
                }
                viewModelScope.launch {
                    if (stockBound == StockConstants.STOCK_IN_BOUND){
                        stockInCheckList.value = stockCheckListTemp
                    }else if (stockBound == StockConstants.STOCK_OUT_BOUND){
                        stockOutCheckList.value = stockCheckListTemp
                    }
                }
            }
        return disposable
    }

    fun selectAllStock(stockBound: Int): Disposable{
        val disposable =dao.selectAllStockFlowable(stockBound)
            .subscribeOn(Schedulers.io())
            .subscribe { stocks ->
                viewModelScope.launch {
                    if (stockBound == StockConstants.STOCK_IN_BOUND){
                        stockInList.value = stocks
                    }else if (stockBound == StockConstants.STOCK_OUT_BOUND){
                        stockOutList.value = stocks
                    }
                }
            }
        return disposable
    }

    fun selectAll(): Disposable{
        val disposable =dao.selectAllFlowable()
            .subscribeOn(Schedulers.io())
            .subscribe { stocks ->
                viewModelScope.launch {
                    stockOutList.value = stocks
                }
            }
        return disposable
    }

    fun getProduct(productId: Int): ProductNameNumber? {
        return dao.getProductIdByProduct(productId)
    }

    fun addStockIn(productId:Int,stockBound: Int,stockDate:String,stockQuantity:Int) {
        val stock = Stock(productId, stockBound,stockDate, stockQuantity)
        dao.insertStock(stock)
    }

    fun addStockOut(productId:Int,stockBound: Int,stockDate:String,stockQuantity:Int,context: Context) {
        if (dao.selectStockTotal(productId) < stockQuantity){
            AlertDialog.Builder(context)
                .setTitle(DialogStockOut.STOCK_OUT_REGISTER_ERROR_TITLE)
                .setMessage(DialogStockOut.STOCK_OUT_REGISTER_ERROR_MSG)
                .setPositiveButton(Dialog.BTN_OK,null).setNegativeButton(Dialog.BTN_NG,null).show()
            return
        }
        val stock = Stock(productId, stockBound,stockDate, stockQuantity)
        dao.insertStock(stock)
    }

    fun updateStockIn(stockId:Int, productId:Int,stockBound: Int,
                    stockDate:String,stockQuantity:Int){
        val stock = dao.getStockById(stockId)
        stock.productId = productId
        stock.stockBound = stockBound
        stock.stockDate = stockDate
        stock.stockQuantity = stockQuantity

        dao.updateStock(stock)
    }

    fun updateStockOut(
        stockId: Int, productId: Int, stockBound: Int,
        stockDate: String, stockQuantity: Int){
            val stock = dao.getStockById(stockId)
            stock.productId = productId
            stock.stockBound = stockBound
            stock.stockDate = stockDate
            stock.stockQuantity = stockQuantity

            dao.updateStock(stock)
    }

    fun isUpdateRequired(productId: Int,additionalStockOUtQuantity:Int):Boolean{
        if (dao.selectStockTotal(productId) < additionalStockOUtQuantity){
            return true
        }
        return false
    }

    fun deleteStockList(stockBound: Int){
        val deletedStockList = mutableListOf<Stock>()
        val updateStockList = mutableListOf<StockDisplay>()
        lateinit var checkedStocks : List<StockCheck>
        lateinit var uncheckedStocks :List<StockCheck>

        if (stockBound == StockConstants.STOCK_IN_BOUND){
            checkedStocks = stockInCheckList.value?.filter { it.isChecked } ?: return
            uncheckedStocks = stockInCheckList.value?.filterNot { it.isChecked } ?: return
        }else if (stockBound == StockConstants.STOCK_OUT_BOUND){
            checkedStocks = stockOutCheckList.value?.filter { it.isChecked } ?: return
            uncheckedStocks = stockOutCheckList.value?.filterNot { it.isChecked } ?: return
        }

        for (stockCheck in checkedStocks){
            val stock = Stock(stockCheck.productId,stockBound,stockCheck.stockDate,stockCheck.stockQuantity)
            stock.stockId = stockCheck.stockId
            deletedStockList.add(stock)
        }
        dao.deleteStock(deletedStockList)

        for (uncheckedStock in uncheckedStocks){

            updateStockList.add(uncheckedStock.stockDisplay)
        }
        stockInList.value = updateStockList
    }
}