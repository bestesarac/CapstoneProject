package com.bestesarac.capstoneproject

import android.view.Gravity.apply
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.GravityCompat.apply
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.bestesarac.capstoneproject.databinding.ProductsBinding
import com.squareup.picasso.Picasso

class ProductsListAdapter(private val productsList: ArrayList<ProductsModel>) : RecyclerView.Adapter<ProductsListAdapter.Products>() {

    var onFavoriteClick: (ProductsModel) -> Unit= {}

    inner class Products(private var productsBinding: ProductsBinding) : RecyclerView.ViewHolder(productsBinding.root) {
        fun bind(productsModel: ProductsModel) {

            productsBinding.apply {

                productModel = productsModel

                productsModel.image.let {
                    Picasso.get().load(it).into(itemImageView)
                }

            }
            productsBinding.productModel=productsModel

        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Products {
        val binding = ProductsBinding.inflate(LayoutInflater.from(parent.context),parent,false)
        return Products(binding)
    }

    override fun onBindViewHolder(holder: Products, position: Int) {
        holder.bind(productsList[position])
        holder.itemView.setOnClickListener{
            val action= HomeFragmentDirections.actionHomeFragmentToProductsDetailFragment(productsList[position])
            it.findNavController().navigate(action)
        }
    }

    override fun getItemCount(): Int {
        return productsList.count()
    }

    fun updateData(newList:List<ProductsModel>){
        productsList.clear()
        productsList.addAll(newList)
        notifyDataSetChanged()
    }

}