package com.example.rakta_vahini

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.fragment.app.Fragment
import com.google.firebase.firestore.FirebaseFirestore

class FindFragment : Fragment() {

    private lateinit var listView: ListView

    private val donorList =
        ArrayList<Donor>()

    private val displayList =
        ArrayList<String>()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        return inflater.inflate(
            R.layout.fragment_find,
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

        val spinnerBlood =
            view.findViewById<Spinner>(
                R.id.spinnerBloodFilter
            )

        val etCity =
            view.findViewById<EditText>(
                R.id.etCityFilter
            )

        val btnSearch =
            view.findViewById<Button>(
                R.id.btnSearch
            )

        listView =
            view.findViewById(
                R.id.listDonors
            )

        val bloodGroups = arrayOf(
            "All",
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

        loadDonors("All", "")

        btnSearch.setOnClickListener {

            val blood =
                spinnerBlood.selectedItem.toString()

            val city =
                etCity.text.toString()

            loadDonors(
                blood,
                city
            )
        }
    }

    private fun loadDonors(
        bloodFilter: String,
        cityFilter: String
    ) {

        FirebaseFirestore.getInstance()

            .collection("donors")

            .get()

            .addOnSuccessListener { result ->

                donorList.clear()

                displayList.clear()

                for (document in result) {

                    val donor =
                        document.toObject(
                            Donor::class.java
                        )

                    val matchesBlood =

                        bloodFilter == "All" ||

                                donor.bloodGroup ==
                                bloodFilter

                    val matchesCity =

                        donor.city.lowercase()
                            .contains(
                                cityFilter.lowercase()
                            )

                    if (
                        matchesBlood &&
                        matchesCity
                    ) {

                        donorList.add(donor)

                        val text =

                            "👤 ${donor.name}\n" +

                                    "📍 ${donor.city}\n" +

                                    "🩸 ${donor.bloodGroup}\n" +

                                    if (donor.eligible)

                                        "✅ Eligible"

                                    else

                                        "❌ Not Eligible"

                        displayList.add(text)
                    }
                }

                // NO DONORS FOUND

                if (displayList.isEmpty()) {

                    displayList.add(
                        "❌ No donors found"
                    )
                }

                val adapter =
                    ArrayAdapter(
                        requireContext(),

                        android.R.layout.simple_list_item_1,

                        displayList
                    )

                listView.adapter = adapter

                // CALL FEATURE

                listView.setOnItemClickListener {

                        _, _, position, _ ->

                    // prevent clicking no donor message

                    if (donorList.isEmpty())
                        return@setOnItemClickListener

                    val donor =
                        donorList[position]

                    val intent =
                        Intent(
                            Intent.ACTION_DIAL
                        )

                    intent.data =
                        Uri.parse(
                            "tel:${donor.phone}"
                        )

                    startActivity(intent)
                }
            }
    }
}