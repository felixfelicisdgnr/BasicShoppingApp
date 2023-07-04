package com.doganur.shoppingapp.ui.home

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.widget.SearchView
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.doganur.shoppingapp.R
import com.doganur.shoppingapp.common.viewBinding
import com.doganur.shoppingapp.databinding.FragmentHomeBinding

class HomeFragment : Fragment(R.layout.fragment_home) {

    private val binding by viewBinding(FragmentHomeBinding::bind)

    private val productsAdapter by lazy { ProductsAdapter() }

    private val homeViewModel: HomeViewModel by viewModels()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        productsAdapter.onProductClick = {
            val action = HomeFragmentDirections.homeToDetail(it)
            findNavController().navigate(action)
        }

        binding.searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {

            override fun onQueryTextSubmit(query: String?): Boolean {
                return false
            }
            override fun onQueryTextChange(newText: String?): Boolean {
                if (newText.isNullOrEmpty()) {
                    homeViewModel.products()
                } else {
                    homeViewModel.searchProduct(newText)
                }
                return false
            }
        })

        homeViewModel.productList.observe(viewLifecycleOwner) { listProduct ->
            if (listProduct != null) {

               // val sortedPriceList = listProduct.sortedBy { it.price?.toDouble()}

                productsAdapter.submitList(listProduct.sortedBy { it.price })
                binding.rvProducts.adapter = productsAdapter

                } else {
                    Toast.makeText(requireContext(), "Empty List!", Toast.LENGTH_SHORT).show()
            }
        }
    }
}