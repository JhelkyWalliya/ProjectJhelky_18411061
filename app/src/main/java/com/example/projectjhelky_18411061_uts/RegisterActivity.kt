package com.example.projectjhelky_18411061_uts

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Patterns
import android.widget.Toast
import com.example.projectjhelky_18411061_uts.databinding.ActivityRegisterBinding
import com.google.firebase.auth.FirebaseAuth
import java.util.regex.Pattern

class RegisterActivity : AppCompatActivity() {
    private  lateinit var binding: ActivityRegisterBinding
    private lateinit var firebaseAuth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityRegisterBinding.inflate(layoutInflater)
        setContentView(binding.root)

        firebaseAuth = FirebaseAuth.getInstance()

        binding.btnRegister.setOnClickListener{
            val email : String = binding.edtEmail.text.toString().trim()
            val password : String = binding.edtPassword.text.toString().trim()
            val ConfirmPassword : String = binding.edtConfirmPassword.text.toString().trim()

            if (email.isEmpty()){
                binding.edtEmail.error = "input Email"
                binding.edtEmail.requestFocus()
                return@setOnClickListener
            }

            if (!Patterns.EMAIL_ADDRESS.matcher(email).matches()){
                binding.edtEmail.error = "invalid email"
                binding.edtEmail.requestFocus()
                return@setOnClickListener
            }

            if (password.isEmpty() || password.length < 6){
                binding.edtPassword.error = "password must be more than 6 characters"
                binding.edtPassword.requestFocus()
                return@setOnClickListener
            }

            if (password != ConfirmPassword){
                binding.edtConfirmPassword.error = "Password must be match"
                binding.edtConfirmPassword.requestFocus()
                return@setOnClickListener
            }
            registerUser(email, password)
        }

    }

    private fun registerUser(email: String, password: String) {

        firebaseAuth.createUserWithEmailAndPassword(email,password).addOnCompleteListener(){
            if (it.isSuccessful){
                Intent(this, MainActivity::class.java).also {
                    it.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
                    startActivity(it)
                }
            }
            else{
                Toast.makeText(this, it.exception?.message, Toast.LENGTH_SHORT).show()
            }
        }



    }


}