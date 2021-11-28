package com.example.agrolibre.view.ui.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.agrolibre.databinding.ProductsFragmentBinding
import com.example.agrolibre.view.adapter.AdapterProducts
import com.example.agrolibre.viewmodel.ProductsViewModel

class ProductsFragment : Fragment() {

    companion object {
        fun newInstance() = ProductsFragment()
    }

    private val viewModel by lazy{ViewModelProvider(this).get(ProductsViewModel::class.java)}
    private lateinit var adapter: AdapterProducts
    private var _binding: ProductsFragmentBinding? = null
    private val binding get() = _binding!!


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = ProductsFragmentBinding.inflate(inflater, container, false)
        val view = binding.root
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        adapter = AdapterProducts()
        binding.rvProducts.layoutManager = GridLayoutManager(activity,2)
        binding.rvProducts.adapter = adapter
        observeData()
    }
    //obtiene los datos del viewModel
    fun observeData(){
        binding.shimmerViewContainer.startShimmer()
        viewModel.fetchProductsData().observe(viewLifecycleOwner, Observer {
            binding.shimmerViewContainer.hideShimmer()
            binding.shimmerViewContainer.stopShimmer()
            binding.shimmerViewContainer.visibility = View.GONE
            adapter.setListData(it)
            adapter.notifyDataSetChanged()
        })
    }

    /*override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }*/

}