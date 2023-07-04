package com.doganur.shoppingapp.ui.home

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.doganur.shoppingapp.data.model.Product
import com.doganur.shoppingapp.databinding.ItemProductBinding

class ProductsAdapter :
    ListAdapter<Product, ProductsAdapter.ProductViewHolder>(ProductDiffUtilCallback) {
    //kullandığım yerde içerisine bir adet Product model yerleştireceğim anlamına geliyor.
    var onProductClick : (Product) -> Unit = {}

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ProductViewHolder {
        val binding = ItemProductBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ProductViewHolder(binding, onProductClick)
    }

    override fun onBindViewHolder(holder: ProductsAdapter.ProductViewHolder, position: Int) =
        holder.bind(currentList[position])

    class ProductViewHolder(private val binding: ItemProductBinding, private val onProductClick : (Product) -> Unit ) : RecyclerView.ViewHolder(binding.root) {

        fun bind(product: Product) {
            with(binding) {

                tvProductTitle.text = product.title
                tvProductPrice.text = "${product.price}"


                Glide.with(binding.imgProduct).load(product.image).into(binding.imgProduct)

                root.setOnClickListener { onProductClick(product) }
            }
        }
    }
}

object ProductDiffUtilCallback : DiffUtil.ItemCallback<Product>() {
    override fun areItemsTheSame(oldItem: Product, newItem: Product) = oldItem.id == newItem.id

    override fun areContentsTheSame(oldItem: Product, newItem: Product) = oldItem == newItem
}