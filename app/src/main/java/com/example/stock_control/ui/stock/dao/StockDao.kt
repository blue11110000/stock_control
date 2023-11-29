package com.example.stock_control.ui.stock.dao

import androidx.room.*
import com.example.stock_control.ui.home.product.entities.ProductNameNumber
import com.example.stock_control.ui.stock.entities.Stock
import com.example.stock_control.ui.stock.entities.StockDisplay
import io.reactivex.Flowable

@Dao
interface StockDao {

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun insertStock(stock: Stock)

    @Query("select s.stockId,p.productId,p.productNumber,p.productName,s.stockBound,s.stockDate,s.stockQuantity from stock S " +
            "inner join product P ON s.productId = p.productId " +
            "where p.productName like '%' || :productName || '%' and s.stockBound = :stockBound")
    fun selectStockNameFlowable(productName:String,stockBound:Int): Flowable<MutableList<StockDisplay>>

    @Query("select s.stockId,p.productId,p.productNumber,p.productName,s.stockBound,s.stockDate,s.stockQuantity from stock S " +
            "inner join product P ON s.productId = p.productId " +
            "where p.productName like '%' || :productName || '%'")
    fun selectNameFlowable(productName:String): Flowable<MutableList<StockDisplay>>

    @Query("select s.stockId,p.productId,p.productNumber,p.productName,s.stockBound,s.stockDate,s.stockQuantity from stock S " +
            "inner join product P ON s.productId = p.productId where  s.stockBound = :stockBound")
    fun selectAllStockFlowable(stockBound:Int): Flowable<MutableList<StockDisplay>>

    @Query("select s.stockId,p.productId,p.productNumber,p.productName,s.stockBound,s.stockDate,s.stockQuantity from stock S " +
            "inner join product P ON s.productId = p.productId")
    fun selectAllFlowable(): Flowable<MutableList<StockDisplay>>

    @Query("SELECT * FROM stock where stockId = :stockId")
    fun getStockById(stockId:Int): Stock

    @Query("SELECT * FROM stock where productId = :productId")
    fun getStockByNumber(productId:String): Stock?

    @Query("SELECT productNumber,productName FROM product where productId = :productId")
    fun getProductIdByProduct(productId:Int): ProductNameNumber?

    @Query("SELECT i.sum_quantity- ifNull(o.sum_quantity,0) as total FROM" +
            " (SELECT SUM(stockQuantity) as sum_quantity , productId FROM stock WHERE stockBound =0 " +
            "GROUP BY productId) i left OUTER JOIN (SELECT SUM(stockQuantity) as sum_quantity , productId " +
            "FROM stock WHERE stockBound = 1 GROUP BY productId) o ON o.productId = i.productId " +
            "where i.productId = :productId")
    fun selectStockTotal(productId: Int): Int

    @Update
    fun updateStock(stock: Stock)

    @Delete
    fun deleteStock(stockList: MutableList<Stock>)

}