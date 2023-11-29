package com.example.stock_control.ui.stock_status.dao

import androidx.room.Dao
import androidx.room.Query
import com.example.stock_control.ui.home.product.entities.ProductDisplay
import com.example.stock_control.ui.home.supplier.entities.Supplier
import com.example.stock_control.ui.stock.entities.StockDisplay
import com.example.stock_control.ui.stock_status.entities.StockListDisplay
import com.example.stock_control.ui.stock_status.entities.StockListTotalDisplay


@Dao
interface StockListDao {

    @Query("select p.productId,p.productNumber,p.productName,p.purchasePrice,p.salePrice,p.stockLow,s.supplierId,s.supplierName " +
            "from product p inner join supplier s ON p.supplierId = s.supplierId " +
            "where p.productName like '%' || :productName || '%'")
    fun selectProductName(productName:String): MutableList<ProductDisplay>

    @Query("select p.productId,p.productNumber,p.productName,p.purchasePrice,p.salePrice,p.stockLow,s.supplierId,s.supplierName " +
            "from product p inner join supplier s ON p.supplierId = s.supplierId ")
    fun selectAllProduct(): MutableList<ProductDisplay>

    @Query("SELECT * FROM supplier where supplierName like '%' || :supplierName || '%'")
    fun selectSupplierName(supplierName:String): MutableList<Supplier>

    @Query("SELECT * FROM supplier")
    fun selectAllSupplier(): MutableList<Supplier>

    @Query("select s.stockId,p.productId,p.productNumber,p.productName,s.stockBound,s.stockDate,s.stockQuantity from stock S " +
            "inner join product P ON s.productId = p.productId " +
            "where p.productName like '%' || :productName || '%'")
    fun selectStock(productName:String): MutableList<StockDisplay>

    @Query("select s.stockId,p.productId,p.productNumber,p.productName,s.stockBound,s.stockDate,s.stockQuantity from stock S " +
            "inner join product P ON s.productId = p.productId")
    fun selectStockAll(): MutableList<StockDisplay>

    @Query("SELECT p.productId,p.productNumber,p.productName,i.sum_quantity- ifNull(o.sum_quantity,0) as total ,p.stockLow " +
            "FROM (SELECT SUM(stockQuantity) as sum_quantity , productId FROM stock WHERE stockBound =0 " +
            "GROUP BY productId) i left OUTER JOIN (SELECT SUM(stockQuantity) as sum_quantity , productId " +
            "FROM stock WHERE stockBound = 1 GROUP BY productId) o  ON o.productId = i.productId " +
            "left outer join product p on p.productId = i.productId " +
            "where i.sum_quantity- ifNull(o.sum_quantity,0) < p.stockLow order by total desc")
    fun selectReplenish(): MutableList<StockListDisplay>

    @Query("SELECT p.productId,p.productNumber,p.productName,i.sum_quantity- ifNull(o.sum_quantity,0) as total ,p.stockLow " +
            "FROM (SELECT SUM(stockQuantity) as sum_quantity , productId FROM stock WHERE stockBound =0 " +
            "GROUP BY productId) i left OUTER JOIN (SELECT SUM(stockQuantity) as sum_quantity , productId " +
            "FROM stock WHERE stockBound = 1 GROUP BY productId) o  ON o.productId = i.productId " +
            "left outer join product p on p.productId = i.productId " +
            "where i.sum_quantity- ifNull(o.sum_quantity,0) < p.stockLow and  p.productName like '%' || :productName || '%' order by total desc")
    fun selectReplenishProductName(productName:String): MutableList<StockListDisplay>

    @Query("SELECT p.productId,p.productNumber,p.productName,i.sum_quantity- ifNull(o.sum_quantity,0) as total FROM" +
            " (SELECT SUM(stockQuantity) as sum_quantity , productId FROM stock WHERE stockBound =0 " +
            "GROUP BY productId) i left OUTER JOIN (SELECT SUM(stockQuantity) as sum_quantity , productId " +
            "FROM stock WHERE stockBound = 1 GROUP BY productId) o ON o.productId = i.productId " +
            "left outer join product p on p.productId = i.productId order by total DESC")
    fun selectStockTotal(): MutableList<StockListTotalDisplay>

    @Query("SELECT p.productId,p.productNumber,p.productName,i.sum_quantity- ifNull(o.sum_quantity,0) as total FROM" +
            " (SELECT SUM(stockQuantity) as sum_quantity , productId FROM stock WHERE stockBound =0 " +
            "GROUP BY productId) i left OUTER JOIN (SELECT SUM(stockQuantity) as sum_quantity , productId " +
            "FROM stock WHERE stockBound = 1 GROUP BY productId) o ON o.productId = i.productId " +
            "left outer join product p on p.productId = i.productId where p.productName like '%' || :productName || '%' order by total DESC")
    fun selectStockTotalProductName(productName:String): MutableList<StockListTotalDisplay>

    @Query("select product.productId,product.productNumber,product.productName,sumSaleQuery.sumQuantity*product.salePrice as total from " +
            "(select SUM(stockQuantity) sumQuantity ,productId FROM stock WHERE stockBound = 1 GROUP BY productId) sumSaleQuery " +
            "INNER JOIN product on sumSaleQuery.productId = product.productId order by total DESC")
    fun selectSaleTotal(): MutableList<StockListTotalDisplay>

    @Query("select product.productId,product.productNumber,product.productName,sumSaleQuery.sumQuantity*product.salePrice as total from " +
            "(select SUM(stockQuantity) sumQuantity ,productId FROM stock WHERE stockBound = 1 GROUP BY productId) sumSaleQuery " +
            "INNER JOIN product on sumSaleQuery.productId = product.productId where product.productName like '%' || :productName || '%' order by total DESC")
    fun selectSaleTotalProductName(productName:String): MutableList<StockListTotalDisplay>
}