package com.example.rakta_vahini

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.fragment.app.Fragment
import com.google.firebase.firestore.FirebaseFirestore
import android.content.Intent
import com.google.firebase.auth.FirebaseAuth

class ProfileFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        return inflater.inflate(
            R.layout.fragment_profile,
            container,
            false
        )
    }

    override fun onViewCreated(
        view: View,
        savedInstanceState: Bundle?
    ) {

        super.onViewCreated(
            view,
            savedInstanceState
        )

        val etName =
            view.findViewById<EditText>(
                R.id.etName
            )

        val etPhone =
            view.findViewById<EditText>(
                R.id.etPhone
            )

        val etCity =
            view.findViewById<EditText>(
                R.id.etCity
            )

        val spinnerBlood =
            view.findViewById<Spinner>(
                R.id.spinnerBlood
            )

        val switchEligible =
            view.findViewById<Switch>(
                R.id.switchEligible
            )

        val btnSave =
            view.findViewById<Button>(
                R.id.btnSave
            )

        val btnLogout =
            view.findViewById<Button>(
                R.id.btnLogout
            )

        val db =
            FirebaseFirestore.getInstance()

        val bloodGroups = arrayOf(
            "A+",
            "A-",
            "B+",
            "B-",
            "AB+",
            "AB-",
            "O+",
            "O-"
        )

        spinnerBlood.adapter =
            ArrayAdapter(
                requireContext(),
                android.R.layout.simple_spinner_dropdown_item,
                bloodGroups
            )

        btnSave.setOnClickListener {

            val name =
                etName.text.toString()

            val phone =
                etPhone.text.toString()

            val city =
                etCity.text.toString()

            val blood =
                spinnerBlood.selectedItem.toString()

            val eligible =
                switchEligible.isChecked

            if (
                name.isEmpty() ||
                phone.isEmpty() ||
                city.isEmpty()
            ) {

                Toast.makeText(
                    requireContext(),
                    "Fill all fields",
                    Toast.LENGTH_SHORT
                ).show()

            } else {

                val donor = hashMapOf(

                    "name" to name,

                    "phone" to phone,

                    "city" to city,

                    "bloodGroup" to blood,

                    "eligible" to eligible
                )

                db.collection("donors")
                    .add(donor)

                    .addOnSuccessListener {

                        Toast.makeText(
                            requireContext(),
                            "Profile Saved ✅",
                            Toast.LENGTH_SHORT
                        ).show()
                    }

                    .addOnFailureListener {

                        Toast.makeText(
                            requireContext(),
                            "Failed ❌",
                            Toast.LENGTH_SHORT
                        ).show()
                    }
            }


        }
        btnLogout.setOnClickListener {

            FirebaseAuth.getInstance()
                .signOut()

            startActivity(

                Intent(
                    requireContext(),
                    LoginActivity::class.java
                )
            )

            requireActivity().finish()
        }
    }
}