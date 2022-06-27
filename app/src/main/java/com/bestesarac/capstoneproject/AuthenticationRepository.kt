package com.bestesarac.capstoneproject

import androidx.lifecycle.MutableLiveData
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase

class AuthenticationRepository {
    var signInResult = MutableLiveData<String>()
    var signUpResult = MutableLiveData<String>()
    var forgotResult = MutableLiveData<String>()
    var activeUserResult = MutableLiveData<Boolean>()
    private var auth = Firebase.auth

    fun forgot(eMail: String){
        auth.sendPasswordResetEmail(eMail)
        forgotResult.value="successful"
    }

    fun signUp(eMail: String,password: String) {
        auth.createUserWithEmailAndPassword(eMail, password).addOnSuccessListener {
            signUpResult.value = "successful"
        }
            .addOnFailureListener {
                signUpResult.value = it.message.toString()
            }
    }

    fun signIn(eMail: String, password: String) {

        auth.signInWithEmailAndPassword(eMail, password).addOnSuccessListener {
            signInResult.value = "successful"
        }
            .addOnFailureListener {
                signInResult.value = it.message.toString()

            }

    }
    fun activeUser(){
        val user=auth.currentUser
        user?.let {
            activeUserResult.value=true
        }
    }
}


