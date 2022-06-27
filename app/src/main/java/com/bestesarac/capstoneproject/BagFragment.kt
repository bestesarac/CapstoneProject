package com.bestesarac.capstoneproject

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.bestesarac.capstoneproject.databinding.FragmentBagBinding
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase

class BagFragment : Fragment() {
    private lateinit var binding:FragmentBagBinding
    private val bagViewModel by lazy { BagViewModel(requireContext()) }
    private var bagAdapter=BagAdapter(arrayListOf())
    private val auth=Firebase.auth

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding=FragmentBagBinding.inflate(inflater,container,false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        with(binding){
            bagRecycleView.layoutManager= LinearLayoutManager(context)
            bagRecycleView.adapter=bagAdapter
            bagRecycleView.setHasFixedSize(true)

            auth.currentUser?.let {
                bagViewModel.getBagProductsByUser(it.email.orEmpty())
                bagViewModel._productsFromBag.observe(viewLifecycleOwner, Observer {
                    if(it.isNotEmpty()){
                        bagAdapter.updateData(it)
                        this.payButton.setOnClickListener {
                            val action=BagFragmentDirections.actionBagFragmentToPaymentFragment()
                            findNavController().navigate(action)
                        }}
                        else {
                        bagAdapter.updateData(it)
                        this.payButton.setOnClickListener{
                            Toast.makeText(context, "Bag is empty.", Toast.LENGTH_SHORT).show()
                        }}
                })
            }

            bagAdapter.setOnItemClickListener(object : BagAdapter.onItemClickListener{
                override fun onItemClick(product: ProductsModel) {
                    bagViewModel.deleteProductFromBasket(product.id)
                    bagViewModel.crudResponse.observe(viewLifecycleOwner, Observer {
                        it?.let {
                            auth.currentUser.let { fbUser->
                                fbUser?.let {
                                    bagViewModel.getBagProductsByUser(fbUser.email.orEmpty())
                                    bagViewModel._productsFromBag.observe(viewLifecycleOwner,
                                        Observer {
                                            it?.let{
                                                bagAdapter.updateData(it)
                                            }
                                        })
                                }
                            }
                        }
                    })
                }

            })
        }

    }

}