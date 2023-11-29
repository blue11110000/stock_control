package com.example.stock_control.ui.home.product.dao

import androidx.room.*
import com.example.stock_control.ui.home.product.entities.Product
import com.example.stock_control.ui.home.product.entities.ProductDisplay
import io.reactivex.Flowable

@Dao
interface ProductDao {

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun insertProduct(product: Product)

    @Query("select p.productId,p.productNumber,p.productName,p.purchasePrice,p.salePrice,p.stockLow,s.supplierId,s.supplierName " +
            "from product p inner join supplier s ON p.supplierId = s.supplierId " +
            "where p.productName like '%' || :productName || '%'")
    fun selectProductNameFlowable(productName:String): Flowable<MutableList<ProductDisplay>>

    @Query("select p.productId,p.productNumber,p.productName,p.purchasePrice,p.salePrice,p.stockLow,s.supplierId,s.supplierName " +
            "from product p inner join supplier s ON p.supplierId = s.supplierId ")
    fun selectAllProductFlowable(): Flowable<MutableList<ProductDisplay>>

    @Query("SELECT * FROM product where productId = :productId")
    fun getProductById(productId:Int): Product?

    @Query("SELECT supplierName FROM supplier where supplierId = :supplierId")
    fun getSupplierNameById(supplierId:Int): String?

    @Update
    fun updateProduct(product: Product)

    @Delete
    fun deleteProduct(productList: MutableList<Product>)

}