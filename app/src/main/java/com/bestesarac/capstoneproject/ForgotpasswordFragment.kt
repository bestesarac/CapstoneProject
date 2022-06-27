package com.bestesarac.capstoneproject

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.bestesarac.capstoneproject.databinding.FragmentForgotpasswordBinding


class ForgotpasswordFragment : Fragment() {
    private lateinit var binding: FragmentForgotpasswordBinding
    private lateinit var forgotpasswordViewModel: ForgotpasswordViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding=FragmentForgotpasswordBinding.inflate(inflater,container,false)
        return binding.root
    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        forgotpasswordViewModel = ViewModelProvider(this).get(ForgotpasswordViewModel::class.java)
        binding.sendButton.setOnClickListener {
            val email = binding.emailText.text.toString()

            forgotpasswordViewModel.forgot(email)
            forgotpasswordViewModel.forgotResult.observe(viewLifecycleOwner, Observer {
                if (it == "successful") {
                    val action = ForgotpasswordFragmentDirections.actionForgotpasswordFragmentToSigninFragment()
                    findNavController().navigate(action)
                } else {
                    Toast.makeText(context, it, Toast.LENGTH_LONG).show()
                    println(it)
                }
            })
        }
    }}


