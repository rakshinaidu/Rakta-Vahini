package com.example.rakta_vahini

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import com.google.firebase.firestore.FirebaseFirestore

class StatsFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        return inflater.inflate(
            R.layout.fragment_stats,
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

        val tvDonationCount =
            view.findViewById<TextView>(
                R.id.tvDonationCount
            )

        val tvLivesSaved =
            view.findViewById<TextView>(
                R.id.tvLivesSaved
            )

        val tvDonorLevel =
            view.findViewById<TextView>(
                R.id.tvDonorLevel
            )

        FirebaseFirestore.getInstance()

            .collection("donations")

            .get()

            .addOnSuccessListener { result ->

                val donationCount =
                    result.size()

                val livesSaved =
                    donationCount * 3

                tvDonationCount.text =
                    donationCount.toString()

                tvLivesSaved.text =
                    livesSaved.toString()

                // DONOR LEVEL

                val level = when {

                    donationCount >= 10 ->
                        "🏆 Platinum Donor"

                    donationCount >= 5 ->
                        "🥇 Gold Donor"

                    donationCount >= 3 ->
                        "🥈 Silver Donor"

                    else ->
                        "🥉 Bronze Donor"
                }

                tvDonorLevel.text = level
            }
    }
}