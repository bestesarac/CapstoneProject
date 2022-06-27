package com.bestesarac.capstoneproject

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.navigation.Navigation
import androidx.recyclerview.widget.RecyclerView
import com.bestesarac.capstoneproject.databinding.BagItemBinding
import com.squareup.picasso.Picasso

class BagAdapter(val productList:ArrayList<ProductsModel>): RecyclerView.Adapter<BagAdapter.ProductViewHolder>() {

    private lateinit var mListener:onItemClickListener

    interface onItemClickListener{
        fun onItemClick(product:ProductsModel)
    }

    fun setOnItemClickListener(listener:onItemClickListener){
        mListener=listener
    }
    class ProductViewHolder(val binding:BagItemBinding):RecyclerView.ViewHolder(binding.root){
        fun bind(product:ProductsModel,listener:onItemClickListener){
            binding.priceText.text=product.price.toString()+"$"
            binding.itemTitleText.text=product.title
            Picasso.get().load(product.image).into(binding.bagImageView)
            binding.bagDelete.setOnClickListener{
              listener.onItemClick(product)
            }

        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ProductViewHolder {
        val binding=BagItemBinding.inflate(LayoutInflater.from(parent.context),parent,false)
        return ProductViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ProductViewHolder, position: Int) {
        holder.bind(productList.get(position),mListener)
        holder.itemView.setOnClickListener {
            val action=BagFragmentDirections.actionBagFragmentToProductsDetailFragment(productList.get(position))
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