package com.bestesarac.capstoneproject

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class SignupFragmentViewModel : ViewModel (){

    var signUpResult= MutableLiveData <String>()
    var activeUserResult= MutableLiveData <Boolean>()
    var authenticationRepository= AuthenticationRepository()

    fun signUp(eMail:String,password:String,) {
        authenticationRepository.signUp(eMail,password)
        signUpResult=authenticationRepository.signUpResult

    }
    fun activeUser(){
        authenticationRepository.activeUser()
        activeUserResult=authenticationRepository.activeUserResult
    }
}