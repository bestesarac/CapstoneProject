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
import com.bestesarac.capstoneproject.databinding.FragmentSignupBinding


class SignupFragment : Fragment() {
    private lateinit var binding:FragmentSignupBinding
    private lateinit var signupViewModel:SignupFragmentViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding= FragmentSignupBinding.inflate(inflater,container,false)

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.gosigninText.setOnClickListener {
            val action=SignupFragmentDirections.actionSignupFragmentToSigninFragment()
            findNavController().navigate(action)
        }
        signupViewModel = ViewModelProvider(this).get(SignupFragmentViewModel::class.java)

        signupViewModel.activeUser()
        signupViewModel.activeUserResult.observe(viewLifecycleOwner, Observer {
            if (it){
                val action = SignupFragmentDirections.actionSignupFragmentToMainnav()
                findNavController().navigate(action)
            }
        })
        binding.signUpButton.setOnClickListener {
            val email = binding.emailText.text.toString()
            val password = binding.passwordText.text.toString()

            signupViewModel.signUp(email, password)
            signupViewModel.signUpResult.observe(viewLifecycleOwner, Observer {
                if (it == "successful") {
                    val action = SignupFragmentDirections.actionSignupFragmentToMainnav()
                    findNavController().navigate(action)
                } else {
                    Toast.makeText(context, it, Toast.LENGTH_LONG).show()
                    println(it)
                }
            })
        }
    }
}