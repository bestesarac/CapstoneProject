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
import com.bestesarac.capstoneproject.databinding.FragmentSigninBinding

class SigninFragment:Fragment(){
    private lateinit var binding:FragmentSigninBinding
    private lateinit var signInViewModel:SignInFragmentViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)}

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding= FragmentSigninBinding.inflate(inflater,container,false)
        binding.forgotpasswordText.setOnClickListener {
            val action=SigninFragmentDirections.actionSigninFragmentToForgotpasswordFragment()
            findNavController().navigate(action)
        }
        return binding.root
    }

    override fun onViewCreated(view: View,savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

       signInViewModel = ViewModelProvider(this).get(SignInFragmentViewModel::class.java)
        binding.LoginButton.setOnClickListener {
            val email = binding.emailText.text.toString()
            val password = binding.passwordText.text.toString()

            signInViewModel.signIn(email, password)
            signInViewModel.signInResult.observe(viewLifecycleOwner, Observer {
                if (it == "successful") {
                    val action = SigninFragmentDirections.actionSigninFragmentToMainnav()
                    findNavController().navigate(action)
                } else {
                    Toast.makeText(context, it, Toast.LENGTH_LONG).show()
                    println(it)
                }
            })
        }
    }
}
