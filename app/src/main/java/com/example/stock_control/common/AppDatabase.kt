package com.example.stock_control.common

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.migration.Migration
import androidx.sqlite.db.SupportSQLiteDatabase
import com.example.stock_control.ui.home.product.entities.Product
import com.example.stock_control.ui.home.product.dao.ProductDao
import com.example.stock_control.ui.home.supplier.entities.Supplier
import com.example.stock_control.ui.home.supplier.dao.SupplierDao
import com.example.stock_control.ui.stock.entities.Stock
import com.example.stock_control.ui.stock.dao.StockDao
import com.example.stock_control.ui.stock_status.dao.StockListDao


@Database(entities = [Product::class, Supplier::class, Stock::class], version = 7 , exportSchema = false)
abstract class AppDatabase : RoomDatabase() {
    abstract fun productDao(): ProductDao
    abstract fun supplierDao(): SupplierDao
    abstract fun stockDao(): StockDao
    abstract fun stockListDao(): StockListDao

    companion object {

        private var database: AppDatabase? = null

        private val MIGRATION_4_5 = object : Migration(4, 5) {
            override fun migrate(database: SupportSQLiteDatabase) {
                database.execSQL("drop table supplier")
                database.execSQL("CREATE TABLE supplier (supplierId INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL, supplierName TEXT NOT NULL, phoneNumber INTEGER NOT NULL, email TEXT NOT NULL)")
            }
        }
        private val MIGRATION_5_6 = object : Migration(5, 6) {
            override fun migrate(database: SupportSQLiteDatabase) {
                database.execSQL("drop table product")
                database.execSQL("CREATE TABLE product (productId INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL, productNumber TEXT NOT NULL, productName TEXT NOT NULL, purchasePrice INTEGER NOT NULL, salePrice INTEGER NOT NULL,stockLow INTEGER NOT NULL,supplierId INTEGER NOT NULL)")
            }
        }
        private val MIGRATION_6_7 = object : Migration(6, 7) {
            override fun migrate(database: SupportSQLiteDatabase) {
                database.execSQL("drop table stock")
                database.execSQL("CREATE TABLE stock (stockId INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL, productId INTEGER NOT NULL,stockBound INTEGER NOT NULL,stockDate TEXT NOT NULL, stockQuantity INTEGER NOT NULL)")
            }
        }

        fun getInstance(context: Context): AppDatabase =
            database ?: synchronized(this) {
                database ?: buildDatabase(context)
                            .also { database = it }
                }

        private fun buildDatabase(context: Context) =
                Room.databaseBuilder(context.applicationContext,
                        AppDatabase::class.java, "stock_control_db")
                        .addMigrations(MIGRATION_4_5,MIGRATION_5_6,MIGRATION_6_7)
                        .allowMainThreadQueries()
                        .build()
    }
}