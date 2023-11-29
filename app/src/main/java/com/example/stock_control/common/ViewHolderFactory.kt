package com.example.stock_control.common

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.stock_control.constants.CardType
import com.example.stock_control.databinding.ActivityProductDeleteRowBinding
import com.example.stock_control.databinding.ActivityProductSearchRowBinding
import com.example.stock_control.databinding.ActivitySaleTotalSearchRowBinding
import com.example.stock_control.databinding.ActivityStockDeleteRowBinding
import com.example.stock_control.databinding.ActivityStockInOutSearchRowBinding
import com.example.stock_control.databinding.ActivityStockReplenishmentSearchRowBinding
import com.example.stock_control.databinding.ActivityStockSearchRowBinding
import com.example.stock_control.databinding.ActivityStockTotalSearchRowBinding
import com.example.stock_control.databinding.ActivitySupplierDeleteRowBinding
import com.example.stock_control.databinding.ActivitySupplierSearchRowBinding
import com.example.stock_control.ui.home.product.ProductDeleteCardViewHolder
import com.example.stock_control.ui.home.product.ProductUpdateCardViewHolder
import com.example.stock_control.ui.home.supplier.SupplierDeleteCardViewHolder
import com.example.stock_control.ui.home.supplier.SupplierUpdateCardViewHolder
import com.example.stock_control.ui.stock.StockDeleteCardViewHolder
import com.example.stock_control.ui.stock.StockUpdateCardViewHolder
import com.example.stock_control.ui.stock_status.ProductSearchCardViewHolder
import com.example.stock_control.ui.stock_status.SaleTotalSearchCardViewHolder
import com.example.stock_control.ui.stock_status.StockTotalSearchCardViewHolder
import com.example.stock_control.ui.stock_status.StockReplenishSearchCardViewHolder
import com.example.stock_control.ui.stock_status.StockSearchCardViewHolder
import com.example.stock_control.ui.stock_status.SupplierSearchCardViewHolder

object ViewHolderFactory {

    fun create(parent: ViewGroup, cardType: CardType) :RecyclerView.ViewHolder{
        return when (cardType) {
            CardType.PRODUCT_UPDATE -> {
                val binding = ActivityProductSearchRowBinding.inflate(
                    LayoutInflater.from(parent.context),
                    parent,
                    false
                )
                ProductUpdateCardViewHolder(binding,parent.context)
            }
            CardType.PRODUCT_DELETE -> {
                val binding = ActivityProductDeleteRowBinding.inflate(
                    LayoutInflater.from(parent.context),
                    parent,
                    false
                )
                ProductDeleteCardViewHolder(binding)
            }
            CardType.SUPPLIER_UPDATE -> {
                val binding = ActivitySupplierSearchRowBinding.inflate(
                    LayoutInflater.from(parent.context),
                    parent,
                    false
                )
                SupplierUpdateCardViewHolder(binding,parent.context)
            }
            CardType.SUPPLIER_DELETE -> {
                val binding = ActivitySupplierDeleteRowBinding.inflate(
                    LayoutInflater.from(parent.context),
                    parent,
                    false
                )
                SupplierDeleteCardViewHolder(binding)
            }
            CardType.STOCK_UPDATE -> {
                val binding = ActivityStockSearchRowBinding.inflate(
                    LayoutInflater.from(parent.context),
                    parent,
                    false
                )
                StockUpdateCardViewHolder(binding,parent.context)
            }
            CardType.STOCK_DELETE -> {
                val binding = ActivityStockDeleteRowBinding.inflate(
                    LayoutInflater.from(parent.context),
                    parent,
                    false
                )
                StockDeleteCardViewHolder(binding)
            }
            CardType.PRODUCT_SEARCH -> {
                val binding = ActivityProductSearchRowBinding.inflate(
                    LayoutInflater.from(parent.context),
                    parent,
                    false
                )
                ProductSearchCardViewHolder(binding)
            }
            CardType.SUPPLIER_SEARCH -> {
                val binding = ActivitySupplierSearchRowBinding.inflate(
                    LayoutInflater.from(parent.context),
                    parent,
                    false
                )
                SupplierSearchCardViewHolder(binding)
            }
            CardType.STOCK_SEARCH -> {
                val binding = ActivityStockInOutSearchRowBinding.inflate(
                    LayoutInflater.from(parent.context),
                    parent,
                    false
                )
                StockSearchCardViewHolder(binding)
            }
            CardType.STOCK_REPLENISHMENT_LIST -> {
                val binding = ActivityStockReplenishmentSearchRowBinding.inflate(
                    LayoutInflater.from(parent.context),
                    parent,
                    false
                )
                StockReplenishSearchCardViewHolder(binding)
            }
            CardType.STOCK_TOTAL -> {
                val binding = ActivityStockTotalSearchRowBinding.inflate(
                    LayoutInflater.from(parent.context),
                    parent,
                    false
                )
                StockTotalSearchCardViewHolder(binding)
            }
            CardType.SALE_TOTAL -> {
                val binding = ActivitySaleTotalSearchRowBinding.inflate(
                    LayoutInflater.from(parent.context),
                    parent,
                    false
                )
                SaleTotalSearchCardViewHolder(binding)
            }
        }
    }
}
