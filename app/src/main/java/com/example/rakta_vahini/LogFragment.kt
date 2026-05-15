package com.example.rakta_vahini

import android.app.DatePickerDialog
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.fragment.app.Fragment
import com.google.firebase.firestore.FirebaseFirestore
import java.util.Calendar

class LogFragment : Fragment() {

    private lateinit var listView: ListView

    private lateinit var donationList:
            ArrayList<String>

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        return inflater.inflate(
            R.layout.fragment_log,
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

        val etHospital =
            view.findViewById<EditText>(
                R.id.etHospital
            )

        val btnDate =
            view.findViewById<Button>(
                R.id.btnDate
            )

        val btnAdd =
            view.findViewById<Button>(
                R.id.btnAddDonation
            )

        listView =
            view.findViewById(
                R.id.listDonations
            )

        donationList = ArrayList()

        // DATE PICKER

        btnDate.setOnClickListener {

            val calendar =
                Calendar.getInstance()

            DatePickerDialog(

                requireContext(),

                { _, year, month, day ->

                    val selectedDate =

                        "$day/${month + 1}/$year"

                    btnDate.text = selectedDate
                },

                calendar.get(Calendar.YEAR),

                calendar.get(Calendar.MONTH),

                calendar.get(Calendar.DAY_OF_MONTH)

            ).show()
        }

        loadDonations()

        // ADD DONATION

        btnAdd.setOnClickListener {

            val hospital =
                etHospital.text.toString()

            val date =
                btnDate.text.toString()

            if (
                hospital.isEmpty() ||
                date == "Select Donation Date"
            ) {

                Toast.makeText(
                    requireContext(),
                    "Fill all fields",
                    Toast.LENGTH_SHORT
                ).show()

            } else {

                val donation = hashMapOf(

                    "hospital" to hospital,

                    "date" to date
                )

                FirebaseFirestore.getInstance()

                    .collection("donations")

                    .add(donation)

                    .addOnSuccessListener {

                        Toast.makeText(

                            requireContext(),

                            "Donation Added ✅",

                            Toast.LENGTH_SHORT

                        ).show()

                        etHospital.text.clear()

                        btnDate.text =
                            "Select Donation Date"

                        loadDonations()
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
    }

    private fun loadDonations() {

        FirebaseFirestore.getInstance()

            .collection("donations")

            .get()

            .addOnSuccessListener { result ->

                donationList.clear()

                for (document in result) {

                    val hospital =
                        document.getString("hospital")

                    val date =
                        document.getString("date")

                    donationList.add(

                        "🏥 $hospital\n📅 $date"
                    )
                }

                val adapter =
                    ArrayAdapter(

                        requireContext(),

                        android.R.layout.simple_list_item_1,

                        donationList
                    )

                listView.adapter = adapter
            }
    }
}