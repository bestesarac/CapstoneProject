package com.bestesarac.capstoneproject

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.bestesarac.capstoneproject.databinding.DiscountedItemsBinding
import com.squareup.picasso.Picasso

class DiscounteditemsListAdapter (private val discounteditemsList: ArrayList<ProductsModel>):
    RecyclerView.Adapter<DiscounteditemsListAdapter.Discounteditems> () {

    var onFavoriteClick: (ProductsModel) -> Unit = {}

    inner class Discounteditems(private val discountedItemsBinding: DiscountedItemsBinding):RecyclerView.ViewHolder(discountedItemsBinding.root) {
        fun bind(discounteditemsModel: ProductsModel){

            discountedItemsBinding.apply {

                productModel = discounteditemsModel

                discounteditemsModel.image.let {
                    Picasso.get().load(it).into(itemImageView)
                }


            }
            discountedItemsBinding.productModel=discounteditemsModel

            }
        }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Discounteditems {
        val binding=DiscountedItemsBinding.inflate(LayoutInflater.from(parent.context),parent,false)
        return Discounteditems(binding)
    }

    override fun onBindViewHolder(holder: Discounteditems, position: Int) {
        holder.bind(discounteditemsList[position])
        holder.itemView.setOnClickListener{
            val action = HomeFragmentDirections.actionHomeFragmentToProductsDetailFragment(discounteditemsList[position])
            it.findNavController().navigate(action)
        }
    }

    override fun getItemCount(): Int {
        return discounteditemsList.count()
    }

    fun updateData(newList:List<ProductsModel>){
        discounteditemsList.clear()
        discounteditemsList.addAll(newList)
        notifyDataSetChanged()
    }

}