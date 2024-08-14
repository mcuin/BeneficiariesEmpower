package com.empower.beneficiaries

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.TextView
import androidx.navigation.fragment.navArgs

/**
 * A fragment for displaying beneficiary details
 */
class BeneficiaryDetailFragment : Fragment() {

    // Arguments passed from BeneficiaryListFragment to BeneficiaryDetailFragment
    private val beneficaryDetailArgs: BeneficiaryDetailFragmentArgs by navArgs()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        // Create the layout for this fragment
        val linearLayout = LinearLayout(requireContext())
        linearLayout.orientation = LinearLayout.VERTICAL
        linearLayout.layoutParams = ViewGroup.LayoutParams(
            ViewGroup.LayoutParams.MATCH_PARENT,
            ViewGroup.LayoutParams.MATCH_PARENT
        )

        // Populate the layout with beneficiary details
        val socialSecurityDetail = TextView(requireContext())
        socialSecurityDetail.layoutParams = LinearLayout.LayoutParams(
            ViewGroup.LayoutParams.MATCH_PARENT,
            ViewGroup.LayoutParams.WRAP_CONTENT
        )
        socialSecurityDetail.text = String.format(getString(R.string.social_security_number), beneficaryDetailArgs.beneficiarySocialSecurityNumber)
        linearLayout.addView(socialSecurityDetail)

        val dateOfBirthDetail = TextView(requireContext())
        dateOfBirthDetail.layoutParams = LinearLayout.LayoutParams(
            ViewGroup.LayoutParams.MATCH_PARENT,
            ViewGroup.LayoutParams.WRAP_CONTENT
        )
        dateOfBirthDetail.text = String.format(getString(R.string.date_of_birth), beneficaryDetailArgs.beneficiaryDateOfBirth)
        linearLayout.addView(dateOfBirthDetail)

        val phoneNumberDetail = TextView(requireContext())
        phoneNumberDetail.layoutParams = LinearLayout.LayoutParams(
            ViewGroup.LayoutParams.MATCH_PARENT,
            ViewGroup.LayoutParams.WRAP_CONTENT
        )
        phoneNumberDetail.text = String.format(getString(R.string.phone_number), beneficaryDetailArgs.beneficiaryPhoneNumber)
        linearLayout.addView(phoneNumberDetail)

        val addressDetail = TextView(requireContext())
        addressDetail.layoutParams = LinearLayout.LayoutParams(
            ViewGroup.LayoutParams.MATCH_PARENT,
            ViewGroup.LayoutParams.WRAP_CONTENT
        )
        addressDetail.text = String.format(getString(R.string.address), beneficaryDetailArgs.beneficiaryAddress)
        linearLayout.addView(addressDetail)

        return linearLayout
    }
}