package com.bestesarac.capstoneproject

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class SignInFragmentViewModel : ViewModel() {
    var signInResult=MutableLiveData<String>()
    var authenticationRepository= AuthenticationRepository()
    fun signIn(eMail:String,password:String){
        authenticationRepository.signIn(eMail,password)
        signInResult=authenticationRepository.signInResult}
}


