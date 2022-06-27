package com.bestesarac.capstoneproject

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.navigation.Navigation
import androidx.recyclerview.widget.RecyclerView
import com.bestesarac.capstoneproject.databinding.FavoriteItemBinding
import com.squareup.picasso.Picasso

class FavoriteFragmentAdapter(val productList:ArrayList<ProductsModel>): RecyclerView.Adapter<FavoriteFragmentAdapter.FavoriteViewHolder>() {
    private lateinit var mListener:onItemClickListener

    interface onItemClickListener{
        fun onItemClick(product:ProductsModel)
    }

    fun setOnItemClickListener(listener:onItemClickListener){
        mListener=listener
    }
    class FavoriteViewHolder(val binding:FavoriteItemBinding):RecyclerView.ViewHolder(binding.root){
        fun bind(product:ProductsModel,listener:onItemClickListener){
            binding.itemPriceText.text=product.price.toString()+"$"
            binding.itemnameText.text=product.title
            binding.itemcategoryText.text=product.category
            Picasso.get().load(product.image).into(binding.favoriteImage)
            binding.deleteFavorite.setOnClickListener {
                listener.onItemClick(product)
            }
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FavoriteViewHolder {
        val binding=FavoriteItemBinding.inflate(LayoutInflater.from(parent.context),parent,false)
        return FavoriteViewHolder(binding)    }

    override fun onBindViewHolder(holder: FavoriteViewHolder, position: Int) {
        holder.bind(productList.get(position),mListener)
        holder.itemView.setOnClickListener {
            val action=FavoriteFragmentDirections.actionFavoriteFragmentToProductsDetailFragment(productList.get(position))
            Navigation.findNavController(it).navigate(action)
        }
    }

    override fun getItemCount(): Int {
        return productList.size
    }

    fun updateData(newProductList:List<ProductsModel>){
        productList.clear()
        productList.addAll(newProductList)
        notifyDataSetChanged()
    }
}