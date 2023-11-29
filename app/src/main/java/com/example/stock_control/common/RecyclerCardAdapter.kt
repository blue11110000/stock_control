package com.example.stock_control.common

import android.content.Intent
import android.view.ViewGroup


import androidx.recyclerview.widget.RecyclerView
import com.example.stock_control.constants.CardType
import com.example.stock_control.ui.home.product.entities.ProductCheck
import com.example.stock_control.ui.home.product.ProductDeleteCardViewHolder
import com.example.stock_control.ui.home.product.ProductUpdateCardViewHolder
import com.example.stock_control.ui.home.product.entities.ProductDisplay
import com.example.stock_control.ui.home.supplier.entities.Supplier
import com.example.stock_control.ui.home.supplier.entities.SupplierCheck
import com.example.stock_control.ui.home.supplier.SupplierDeleteCardViewHolder
import com.example.stock_control.ui.home.supplier.SupplierUpdateCardViewHolder
import com.example.stock_control.ui.stock.entities.StockCheck
import com.example.stock_control.ui.stock.StockDeleteCardViewHolder
import com.example.stock_control.ui.stock.entities.StockDisplay
import com.example.stock_control.ui.stock.StockUpdateCardViewHolder
import com.example.stock_control.ui.stock_status.ProductSearchCardViewHolder
import com.example.stock_control.ui.stock_status.SaleTotalSearchCardViewHolder
import com.example.stock_control.ui.stock_status.StockTotalSearchCardViewHolder
import com.example.stock_control.ui.stock_status.StockReplenishSearchCardViewHolder
import com.example.stock_control.ui.stock_status.StockSearchCardViewHolder
import com.example.stock_control.ui.stock_status.SupplierSearchCardViewHolder
import com.example.stock_control.ui.stock_status.entities.StockListDisplay
import com.example.stock_control.ui.stock_status.entities.StockListTotalDisplay

class RecyclerCardAdapter(private val listData: MutableList<Any>,
                          private val selectIntent: Intent,
                          private val cardType: CardType
): RecyclerView.Adapter<RecyclerView.ViewHolder>(){


    constructor(listData: MutableList<Any>,cardType: CardType): this(listData, Intent(),cardType)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return ViewHolderFactory.create(parent, cardType)
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        when(holder){
            is ProductUpdateCardViewHolder -> holder.bind(listData[position] as ProductDisplay,selectIntent)
            is ProductDeleteCardViewHolder -> holder.bind(listData[position] as ProductCheck)
            is SupplierUpdateCardViewHolder -> holder.bind(listData[position] as Supplier,selectIntent)
            is SupplierDeleteCardViewHolder -> holder.bind(listData[position] as SupplierCheck)
            is StockUpdateCardViewHolder -> holder.bind(listData[position] as StockDisplay,selectIntent)
            is StockDeleteCardViewHolder -> holder.bind(listData[position] as StockCheck)
            is ProductSearchCardViewHolder -> holder.bind(listData[position] as ProductDisplay)
            is SupplierSearchCardViewHolder -> holder.bind(listData[position] as Supplier)
            is StockSearchCardViewHolder -> holder.bind(listData[position] as StockDisplay)
            is StockReplenishSearchCardViewHolder -> holder.bind(listData[position] as StockListDisplay)
            is StockTotalSearchCardViewHolder -> holder.bind(listData[position] as StockListTotalDisplay)
            is SaleTotalSearchCardViewHolder -> holder.bind(listData[position] as StockListTotalDisplay)
        }
    }

    override fun getItemCount(): Int {
        return listData.size
    }
 }