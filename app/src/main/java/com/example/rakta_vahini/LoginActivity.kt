package com.example.rakta_vahini

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.auth.FirebaseAuth

class LoginActivity : AppCompatActivity() {

    private lateinit var auth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_login)

        if (FirebaseAuth.getInstance().currentUser != null) {
            startActivity(Intent(this, MainActivity::class.java))
            finish()
        }

        auth = FirebaseAuth.getInstance()

        val etEmail =
            findViewById<EditText>(R.id.etEmail)

        val etPassword =
            findViewById<EditText>(R.id.etPassword)

        val btnLogin =
            findViewById<Button>(R.id.btnLogin)

        val btnSignup =
            findViewById<Button>(R.id.btnSignup)

        val tvGuest =
            findViewById<TextView>(R.id.tvGuest)

        // LOGIN

        btnLogin.setOnClickListener {

            val email =
                etEmail.text.toString().trim()

            val password =
                etPassword.text.toString().trim()

            if (email.isEmpty() ||
                password.isEmpty()
            ) {

                Toast.makeText(
                    this,
                    "Enter email and password",
                    Toast.LENGTH_SHORT
                ).show()

            } else {

                auth.signInWithEmailAndPassword(
                    email,
                    password
                )

                    .addOnSuccessListener {

                        Toast.makeText(
                            this,
                            "Login Successful ✅",
                            Toast.LENGTH_SHORT
                        ).show()

                        startActivity(
                            Intent(
                                this,
                                MainActivity::class.java
                            )
                        )

                        finish()
                    }

                    .addOnFailureListener {

                        Toast.makeText(
                            this,
                            "User not found. Create account first.",
                            Toast.LENGTH_LONG
                        ).show()
                    }
            }
        }

        // SIGNUP

        btnSignup.setOnClickListener {

            val email =
                etEmail.text.toString().trim()

            val password =
                etPassword.text.toString().trim()

            if (email.isEmpty() ||
                password.isEmpty()
            ) {

                Toast.makeText(
                    this,
                    "Enter email and password",
                    Toast.LENGTH_SHORT
                ).show()

            } else {

                auth.createUserWithEmailAndPassword(
                    email,
                    password
                )

                    .addOnSuccessListener {

                        Toast.makeText(
                            this,
                            "Account Created ✅",
                            Toast.LENGTH_SHORT
                        ).show()

                        // OPEN MAIN ACTIVITY

                        startActivity(
                            Intent(
                                this,
                                MainActivity::class.java
                            )
                        )

                        finish()
                    }

                    .addOnFailureListener {

                        Toast.makeText(
                            this,
                            "Signup Failed",
                            Toast.LENGTH_SHORT
                        ).show()
                    }
            }
        }

        // GUEST LOGIN

        tvGuest.setOnClickListener {

            startActivity(
                Intent(
                    this,
                    MainActivity::class.java
                )
            )

            finish()
        }
    }
}
