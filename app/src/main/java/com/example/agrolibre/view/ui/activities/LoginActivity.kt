package com.example.agrolibre.view.ui.activities

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.agrolibre.R
import com.example.agrolibre.databinding.ActivityLoginBinding
import com.firebase.ui.auth.AuthUI
import com.google.android.gms.auth.api.Auth
import com.google.firebase.auth.FirebaseAuth

class LoginActivity : AppCompatActivity() {
    private lateinit var binding: ActivityLoginBinding
    val AUTH_REQUEST_CODE = 1234
    lateinit var firebaseAuth: FirebaseAuth
    lateinit var listener:FirebaseAuth.AuthStateListener
    lateinit var providers: List<AuthUI.IdpConfig>


    override fun onStart() {
        super.onStart()
        firebaseAuth.addAuthStateListener { listener }
    }

    override fun onStop() {
        if(listener != null){
            firebaseAuth.removeAuthStateListener { listener }
        }
        super.onStop()

    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLoginBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        providers = arrayListOf(
            AuthUI.IdpConfig.EmailBuilder().build(),
            AuthUI.IdpConfig.GoogleBuilder().build()
        )
        //            AuthUI.IdpConfig.PhoneBuilder().build()
        val intent = Intent(this, MainActivity::class.java)
        firebaseAuth = FirebaseAuth.getInstance()
        listener = FirebaseAuth.AuthStateListener { pO ->
            val  user = pO.currentUser
            if(user != null){
                startActivity(intent)
            }else{
                startActivityForResult(AuthUI.getInstance()
                    .createSignInIntentBuilder()
                    .setAvailableProviders(providers)
                    .setLogo(R.mipmap.ic_launcher_libre)
                    .setTheme(R.style.LoginTheme)
                    .build(), AUTH_REQUEST_CODE
                )
            }

        }
    }
}