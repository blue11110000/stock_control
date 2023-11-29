package com.example.stock_control.ui.home.product

import android.content.Intent
import android.os.Bundle
import android.widget.SearchView
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.stock_control.databinding.ActivityProductSearchBinding
import com.example.stock_control.common.AppDatabase
import com.example.stock_control.constants.CardType
import com.example.stock_control.common.RecyclerCardAdapter
import com.example.stock_control.common.ViewModelFactory
import com.example.stock_control.ui.home.product.dao.ProductViewModel
import io.reactivex.disposables.Disposable

class ProductSearch : AppCompatActivity(){

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        var disposable: Disposable? = null
        val binding = ActivityProductSearchBinding.inflate(layoutInflater)
        setContentView(binding.root)
        val db = AppDatabase.getInstance(this)
        val viewModel = ViewModelFactory(db.productDao())
            .create(ProductViewModel::class.java)
        val productUpdate = Intent(this, ProductUpdate::class.java)
        binding.recycleView.layoutManager = LinearLayoutManager(this@ProductSearch)

        ProductViewModel.productList.observe(this) { productList ->
            binding.recycleView.adapter =
                RecyclerCardAdapter(productList.toMutableList(), productUpdate,CardType.PRODUCT_UPDATE)
        }

        binding.searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener{
            override fun onQueryTextSubmit(productName: String?): Boolean {
                binding.searchView.clearFocus()

                if (productName == null){
                    return true
                }
                if (disposable != null){
                    disposable!!.dispose()
                }
                disposable =viewModel.findProductNameFlowable(productName)

                return true
            }

            override fun onQueryTextChange(p0: String?): Boolean {
                return true
            }
        })

        binding.selectAll.setOnClickListener {
            if (disposable != null){
                disposable!!.dispose()
            }
            disposable = viewModel.selectAllProduct()
        }

        binding.back.setOnClickListener {
            if (disposable != null){
                disposable!!.dispose()
            }
            finish()
        }
    }
}