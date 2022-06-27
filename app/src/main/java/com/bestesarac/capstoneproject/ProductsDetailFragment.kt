package com.bestesarac.capstoneproject

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.navArgs
import com.bestesarac.capstoneproject.databinding.FragmentProductsDetailBinding
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import com.squareup.picasso.Picasso


class ProductsDetailFragment : Fragment() {
    private lateinit var binding:FragmentProductsDetailBinding
    private val args:ProductsDetailFragmentArgs by navArgs()
    private lateinit var productModel:ProductsModel
    private val productsDetailViewModel by lazy { ProductsDetailViewModel(requireContext()) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding=FragmentProductsDetailBinding.inflate(inflater,container,false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        productModel=args.productModel

        val auth= Firebase.auth

        binding.description.text=productModel.description
        binding.title.text=productModel.title
        binding.category.text=productModel.category
        binding.price.text=productModel.price.toString()+"$"
        Picasso.get().load(productModel.image).into(binding.image)

        binding.addbagButton.setOnClickListener{
            productsDetailViewModel.addToBag(
                auth.currentUser!!.email!!,
                productModel.title,
                productModel.price,
                productModel.description,
                productModel.category,
                productModel.image,
                productModel.rate,
                productModel.count,
                productModel.sale_state
            )
            productsDetailViewModel.crudResponse.observe(viewLifecycleOwner, Observer {
                it?.let {
                    Toast.makeText(context,it.message, Toast.LENGTH_SHORT).show()
                }
            })
        }

        productsDetailViewModel.getProductByIdFromRoom(productModel.id)
        productsDetailViewModel.productById.observe(viewLifecycleOwner, Observer {
            if(it!=null){
                binding.favoriteButton.backgroundTintList=resources.getColorStateList(R.color.favorite_selected)
                binding.favoriteButton.isSelected=true
            }else{
                binding.favoriteButton.backgroundTintList=resources.getColorStateList(R.color.favorite_no_selected)
                binding.favoriteButton.isSelected=false
            }
        })

        binding.favoriteButton.setOnClickListener {
            if(it.isSelected){
                it.isSelected=false
                productsDetailViewModel.deleteProductFromRoomDb(productModel)
                binding.favoriteButton.backgroundTintList=resources.getColorStateList(R.color.favorite_no_selected)
            }else{
                it.isSelected=true
                productsDetailViewModel.addFavorite(productModel)
                binding.favoriteButton.backgroundTintList=resources.getColorStateList(R.color.favorite_selected)

            }
        }
    }

}

