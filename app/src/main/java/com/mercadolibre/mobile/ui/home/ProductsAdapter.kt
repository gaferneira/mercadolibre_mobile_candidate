package com.mercadolibre.mobile.ui.home

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.mercadolibre.domain.entities.Product
import com.mercadolibre.mobile.databinding.ItemHomeProductBinding
import com.mercadolibre.mobile.utils.view.GlideApp
import java.text.NumberFormat

class ProductsAdapter(
    private var listener: ProductsListener?
) : RecyclerView.Adapter<ProductsAdapter.ProductsViewHolder>() {

    private var list = listOf<Product>()

    interface ProductsListener {
        fun onClickProduct(product: Product, view: View)
    }

    override fun getItemCount(): Int = list.size

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ProductsViewHolder {
        val itemBinding =
            ItemHomeProductBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ProductsViewHolder(itemBinding)
    }

    override fun onBindViewHolder(holder: ProductsViewHolder, position: Int) {
        val item = list[position]
        holder.bind(item, listener)
    }

    fun setItems(items: List<Product>) {
        list = items
        notifyDataSetChanged()
    }

    class ProductsViewHolder(private val binding: ItemHomeProductBinding) :
        RecyclerView.ViewHolder(binding.root) {

        private val numberFormat = NumberFormat.getCurrencyInstance();

        fun bind(
            product: Product,
            listener: ProductsListener?
        ) {
            binding.textViewTitle.text = product.title
            binding.textViewPrice.text = numberFormat.format(product.price)
            GlideApp.with(itemView).load(product.thumbnail).into(binding.imageViewThumbnail)
            binding.imageViewThumbnail.transitionName = product.thumbnail

            binding.root.setOnClickListener {
                listener?.onClickProduct(product, binding.imageViewThumbnail)
            }
        }
    }
}
