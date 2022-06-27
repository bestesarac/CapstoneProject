package com.bestesarac.capstoneproject

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.GridLayoutManager
import com.bestesarac.capstoneproject.databinding.FragmentFavoriteBinding

class FavoriteFragment : Fragment() {

    private lateinit var binding:FragmentFavoriteBinding
    private val favoriteViewModel by lazy { FavoriteFragmentViewModel(requireContext()) }
    private var favoriteFragmentAdapter= FavoriteFragmentAdapter(arrayListOf())


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding= FragmentFavoriteBinding.inflate(inflater,container,false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        with(binding){
            favoriteRecycleView.layoutManager= GridLayoutManager(view.context,2)
            favoriteRecycleView.setHasFixedSize(true)
            favoriteRecycleView.adapter=favoriteFragmentAdapter
            favoriteViewModel.getAllFavorites()
            favoriteViewModel.favoriteList.observe(viewLifecycleOwner, Observer {
                it?.let{
                    favoriteFragmentAdapter.updateData(it)
                }
            })

            favoriteFragmentAdapter.setOnItemClickListener(object:FavoriteFragmentAdapter.onItemClickListener{
                override fun onItemClick(product: ProductsModel) {
                    favoriteViewModel.deleteProductFromRoomDb(product)
                    favoriteViewModel.favoriteList.observe(viewLifecycleOwner, Observer {
                        it?.let {
                            favoriteFragmentAdapter.updateData(it)
                        }
                    })
                }
            })
        }

    }

}

