package com.example.stock_control.ui.home

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import androidx.fragment.app.Fragment
import com.example.stock_control.databinding.FragmentHomeBinding
import com.example.stock_control.ui.home.product.ProductDelete
import com.example.stock_control.ui.home.product.ProductRegister
import com.example.stock_control.ui.home.product.ProductSearch
import com.example.stock_control.ui.home.supplier.SupplierDelete
import com.example.stock_control.ui.home.supplier.SupplierRegister
import com.example.stock_control.ui.home.supplier.SupplierSearch

class HomeFragment : Fragment() {
    companion object{
        const val PRODUCT_REGISTER = 0
        const val PRODUCT_UPDATE = 1
        const val PRODUCT_DELETE = 2
        const val SUPPLIER_REGISTER = 0
        const val SUPPLIER_UPDATE = 1
        const val SUPPLIER_DELETE = 2
    }
    private var _binding: FragmentHomeBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        val root: View = binding.root
        val productProcess = binding.productProcess
        productProcess.onItemClickListener = ListItemClickListenerProductProcess()
        val supplierProcess = binding.supplierProcess
        supplierProcess.onItemClickListener = ListItemClickListenerSupplierProcess()
        return root
    }

    private inner class ListItemClickListenerProductProcess : AdapterView.OnItemClickListener {
        override fun onItemClick(parent: AdapterView<*>, view: View, position: Int, id: Long) {
            val productRegister = Intent(context, ProductRegister::class.java)
            val productSearch = Intent(context, ProductSearch::class.java)
            val productDelete = Intent(context, ProductDelete::class.java)

            when (position) {
                PRODUCT_REGISTER -> {
                    startActivity(productRegister)
                }
                PRODUCT_UPDATE -> {
                    startActivity(productSearch)
                }
                PRODUCT_DELETE -> {
                    startActivity(productDelete)
                }
            }
        }
    }
    private inner class ListItemClickListenerSupplierProcess : AdapterView.OnItemClickListener {
        override fun onItemClick(parent: AdapterView<*>, view: View, position: Int, id: Long) {

            val supplierRegister = Intent(context, SupplierRegister::class.java)
            val supplierSearch = Intent(context, SupplierSearch::class.java)
            val supplierDelete = Intent(context, SupplierDelete::class.java)

            when (position) {
                SUPPLIER_REGISTER -> {
                    startActivity(supplierRegister)
                }
                SUPPLIER_UPDATE -> {
                    startActivity(supplierSearch)
                }
                SUPPLIER_DELETE -> {
                    startActivity(supplierDelete)
                }
            }
        }
    }
    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}