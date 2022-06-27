package com.bestesarac.capstoneproject

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class ForgotpasswordViewModel :ViewModel(){
    var forgotResult= MutableLiveData<String>()
    var authenticationRepository= AuthenticationRepository()
    fun forgot(eMail:String){
        authenticationRepository.forgot(eMail)
        forgotResult=authenticationRepository.forgotResult}
}