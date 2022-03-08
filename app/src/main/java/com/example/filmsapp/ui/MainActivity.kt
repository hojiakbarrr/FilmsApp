package com.example.filmsapp.ui

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.filmsapp.R
import com.firebase.ui.auth.FirebaseAuthUIActivityResultContract
import com.google.firebase.database.DatabaseReference

class MainActivity : AppCompatActivity() {


    // See: https://developer.android.com/training/basics/intents/result
    private val signInLauncher = registerForActivityResult(
        FirebaseAuthUIActivityResultContract()
    ) { res ->
//        this.onSignInResult(res)
    }

    private lateinit var database: DatabaseReference


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
//        database = Firebase.database.reference
//        database.child("hoji").child("userId2").setValue("user3")



        val intent = Intent(this, Movies_Activity::class.java)
        startActivity(intent)

//        database = Firebase.database.reference
//
//        // Choose authentication providers
//        val providers = arrayListOf(
//            AuthUI.IdpConfig.EmailBuilder().build())

// Create and launch sign-in intent
//        val signInIntent = AuthUI.getInstance()
//            .createSignInIntentBuilder()
//            .setAvailableProviders(providers)
//            .build()
//        signInLauncher.launch(signInIntent)

    }

//    public fun onSignInResult(result: FirebaseAuthUIAuthenticationResult) {
//        val response = result.idpResponse
//        if (result.resultCode == RESULT_OK) {
//            Log.d("MyTag1", "${response?.email}")
//            // Successfully signed in
//            val authUser = FirebaseAuth.getInstance().currentUser
//            authUser?.let {
//                val email = it.email
//                val uid = it.uid
//                val firebaseUser = User(email.toString(), uid)
//
//                Log.d("MyTag1", "$firebaseUser")
//                database.child("users").child(uid).setValue(firebaseUser)
////                onBackPressed()
////                val intent = Intent(this, Movies_Activity::class.java)
////                startActivity(intent)
//            }
//
//
//            // ...
//        } else {
//            // Sign in failed. If response is null the user canceled the
//            // sign-in flow using the back button. Otherwise check
//            // response.getError().getErrorCode() and handle the error.
//            // ...
//        //                        hoji_akbar@mail.ru   hojiakbar
//        }
//    }

}