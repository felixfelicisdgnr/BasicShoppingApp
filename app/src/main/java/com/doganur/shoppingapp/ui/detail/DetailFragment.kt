package com.doganur.shoppingapp.ui.detail

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.navArgs
import com.bumptech.glide.Glide
import com.doganur.shoppingapp.R
import com.doganur.shoppingapp.common.viewBinding
import com.doganur.shoppingapp.databinding.FragmentDetailBinding

class DetailFragment : Fragment(R.layout.fragment_detail) {

    private val binding by viewBinding(FragmentDetailBinding::bind)

    private val args : DetailFragmentArgs by navArgs()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

             with(binding) {

                 args.product.let {

                 tvDetailProductTitle.text = it.title
                 tvDetailProductPrice.text=  "${it.price}"
                 tvDetailProductDescription.text= it.description

                 Glide.with(imgvDetailProduct).load( it.image).into(imgvDetailProduct)
             }
        }
    }
}