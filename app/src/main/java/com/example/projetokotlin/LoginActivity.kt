package com.example.projetokotlin

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.example.projetokotlin.databinding.ActivityLoginBinding
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase


class LoginActivity : AppCompatActivity() {

    private val binding: ActivityLoginBinding by lazy {
        ActivityLoginBinding.inflate(layoutInflater)
    }
    private lateinit var auth: FirebaseAuth



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        //Initialize Firebase Auth
        auth = Firebase.auth

        binding.btnLogin.setOnClickListener {
            val userEmail = binding.etEmail.text.toString()
            val password = binding.etPassword.text.toString()

            if(userEmail.isEmpty() || password.isEmpty()){
                Toast.makeText(this, "Please Fill All Fields", Toast.LENGTH_SHORT).show()
            }else{
                auth.signInWithEmailAndPassword(userEmail, password)
                    .addOnCompleteListener {task ->
                        if(task.isSuccessful){
                            Toast.makeText(this, "Sign-In Successful", Toast.LENGTH_SHORT).show()
                            startActivity(Intent(this, MainActivity::class.java))
                            finish()
                        }else{
                            Toast.makeText(this, "Sign-In Failed: ${task.exception?.message}", Toast.LENGTH_SHORT).show()
                        }
                    }
            }

        }

        binding.newAccountButton.setOnClickListener{
            startActivity(Intent(this, RegisterActivity::class.java))
            finish()
        }
    }
}