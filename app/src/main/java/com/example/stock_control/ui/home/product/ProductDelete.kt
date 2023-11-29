package com.example.stock_control.ui.home.product

import android.os.Bundle
import android.widget.SearchView
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.stock_control.common.AppDatabase
import com.example.stock_control.common.RecyclerCardAdapter
import com.example.stock_control.common.ViewModelFactory
import com.example.stock_control.constants.CardType
import com.example.stock_control.constants.Dialog
import com.example.stock_control.databinding.ActivityProductDeleteBinding
import com.example.stock_control.ui.home.product.constants.DialogProduct
import com.example.stock_control.ui.home.product.dao.ProductViewModel
import io.reactivex.disposables.Disposable

class ProductDelete: AppCompatActivity(){

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        var disposable: Disposable? = null
        val binding = ActivityProductDeleteBinding.inflate(layoutInflater)
        setContentView(binding.root)
        val db = AppDatabase.getInstance(this)
        val viewModel = ViewModelFactory(db.productDao())
            .create(ProductViewModel::class.java)
        binding.recycleView.layoutManager = LinearLayoutManager(this@ProductDelete)

        ProductViewModel.productCheckList.observe(this) { productCheckList ->
            binding.recycleView.adapter =
                RecyclerCardAdapter(productCheckList.toMutableList(),CardType.PRODUCT_DELETE)
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
                disposable =viewModel.findProductDeleteFlowable(productName)

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
            disposable = viewModel.selectAllProductCheck()
        }

        binding.delete.setOnClickListener {
            AlertDialog.Builder(this)
                .setTitle(DialogProduct.DELETE_TITLE)
                .setMessage(DialogProduct.DELETE_MSG)
                .setPositiveButton(Dialog.BTN_OK) { _, _ ->
                    viewModel.deleteProductList()
                }.setNegativeButton(Dialog.BTN_NG,null).show()
        }

        binding.back.setOnClickListener {
            if (disposable != null){
                disposable!!.dispose()
            }
            finish()
        }
    }
}