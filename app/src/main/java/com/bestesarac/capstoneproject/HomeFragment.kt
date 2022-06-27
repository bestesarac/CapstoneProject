package com.bestesarac.capstoneproject

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.bestesarac.capstoneproject.databinding.FragmentHomeBinding


class HomeFragment : Fragment() {

    private lateinit var binding:FragmentHomeBinding
    private val homeViewModel by lazy { HomeViewModel(requireContext()) }
    private var productsListAdapter= ProductsListAdapter(arrayListOf())
    private var discounteditemsListAdapter= DiscounteditemsListAdapter(arrayListOf())

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding= FragmentHomeBinding.inflate(inflater,container,false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


        //initObservers()
        binding.productsRecyclerView.setHasFixedSize(true)
        binding.discountedItemsRecycler.setHasFixedSize(true)

        homeViewModel.getProducts()
        homeViewModel._productslist.observe(viewLifecycleOwner) {
            it.let {
                binding.productsRecyclerView.adapter = productsListAdapter
                productsListAdapter.updateData(it)
            }
        }
        homeViewModel.getDiscounteditems()
        homeViewModel._discounteditemslist.observe(viewLifecycleOwner) {
            it.let {
                binding.discountedItemsRecycler.adapter = discounteditemsListAdapter
                discounteditemsListAdapter.updateData(it)
            }
        }

        discounteditemsListAdapter.onFavoriteClick = {

        }
    }

    /*private fun initObservers() {
        with(binding) {
            with(homeViewModel){
                _productslist.observe(viewLifecycleOwner) { list ->
                    productsRecyclerView.apply {
                        setHasFixedSize(true)
                        adapter = productsListAdapter.also { adapter ->
                            adapter.updateData(list)
                            adapter.onFavoriteClick = {
                                homeViewModel.addFavorite(it)
                            }
                        }
                    }
                }
                isItemAddedFavorite.observe(viewLifecycleOwner){

                }
            }
        }
    }*/
}

