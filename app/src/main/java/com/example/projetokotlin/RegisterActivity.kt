package com.example.projetokotlin

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast

import com.example.projetokotlin.databinding.ActivityRegisterBinding
import com.google.firebase.auth.FirebaseAuth

class RegisterActivity : AppCompatActivity() {
    private val binding: ActivityRegisterBinding by lazy {
        ActivityRegisterBinding.inflate(layoutInflater)
    }

    private lateinit var auth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        //Initialize Firebase Auth
        auth = FirebaseAuth.getInstance()

        binding.tvHaveAnAccount.setOnClickListener{
            startActivity(Intent(this, LoginActivity::class.java))
            finish()
        }

        binding.btnRegister.setOnClickListener {
            //Get text from edit text
            val email = binding.etEmail.text.toString()
            val username = binding.etName.text.toString()
            val password = binding.etPassword.text.toString()
            val passwordConfirmation = binding.etConfirmPassword.text.toString()

            //Check if any field is blank
            if(email.isEmpty() || username.isEmpty() || password.isEmpty() || passwordConfirmation.isEmpty()){

                Toast.makeText(this, "Please Fill All The Details", Toast.LENGTH_SHORT).show()
            }else if(password != passwordConfirmation){
                Toast.makeText(this, "The Confirmation is Wrong", Toast.LENGTH_SHORT).show()
            }else{
                auth.createUserWithEmailAndPassword(email, password)
                    .addOnCompleteListener(this) { task ->
                        if(task.isSuccessful){
                            Toast.makeText(this, "Registration Successful", Toast.LENGTH_SHORT).show()
                            startActivity(Intent(this, LoginActivity::class.java))
                            finish()
                        }else{
                            Toast.makeText(this, "Registration Failed : ${task.exception?.message}", Toast.LENGTH_SHORT).show()
                        }
                    }
            }
        }
    }
}