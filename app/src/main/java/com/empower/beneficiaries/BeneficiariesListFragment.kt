package com.empower.beneficiaries

import android.content.res.AssetManager
import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.TextView
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import kotlinx.coroutines.launch
import org.json.JSONObject

/**
 * A fragment representing a list of Beneficiaries by way of a recycler view.
 */

class BeneficiariesListFragment : Fragment() {

    //RecyclerView to display the list of Beneficiaries.
    private lateinit var beneficiariesRecyclerView: RecyclerView
    //Asset manager to access the JSON file, passed to ViewModel and then repository to keep context only in the fragment
    private lateinit var assetManager: AssetManager
    //View model for the fragment done by way of lazy initialization.
    private val beneficiariesListViewModel: BeneficiariesListViewModel by viewModels {
        BeneficiariesListViewModel.BeneficiariesListViewModelFactory(assetManager)
    }
    //Adapter for the RecyclerView.
    private lateinit var adapter: BeneficiariesRecyclerViewAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        //Get the asset manager from the context
        assetManager = requireContext().assets

        //Create the adapter
        adapter = BeneficiariesRecyclerViewAdapter {
            val phoneNumber = beneficiariesListViewModel.formatPhoneNumber(it.phoneNumber)
            val dateOfBirth = beneficiariesListViewModel.formatDateOfBirth(it.dateOfBirth)
            val beneficiaryAddress = beneficiariesListViewModel.formatAddress(it.beneficiaryAddress)
            findNavController().navigate(BeneficiariesListFragmentDirections.navigateToBeneficiaryDetailsFragment(
                it.socialSecurityNumber, dateOfBirth, phoneNumber, beneficiaryAddress)
            )
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        //Create the top level layout for the fragment
        val linearLayout = LinearLayout(requireContext())
        linearLayout.orientation = LinearLayout.VERTICAL
        linearLayout.layoutParams = ViewGroup.LayoutParams(
            ViewGroup.LayoutParams.MATCH_PARENT,
            ViewGroup.LayoutParams.MATCH_PARENT
        )

        //Initialize the recycler view and add adapter
        beneficiariesRecyclerView = RecyclerView(requireContext())
        beneficiariesRecyclerView.layoutParams = ViewGroup.LayoutParams(
            ViewGroup.LayoutParams.MATCH_PARENT,
            ViewGroup.LayoutParams.MATCH_PARENT
        )
        beneficiariesRecyclerView.layoutManager = LinearLayoutManager(requireContext())
        beneficiariesRecyclerView.adapter = adapter

        //Add the recycler view to the top level layout
        linearLayout.addView(beneficiariesRecyclerView)

        //Setup the observer for the list of Beneficiaries returned from the viewmodel, and pass the data to the recyclerview
        viewLifecycleOwner.lifecycleScope.launch {
            beneficiariesListViewModel.beneficiaries.collect {
                if (it.isNotEmpty()) {
                    println(beneficiariesRecyclerView.adapter)
                    adapter.setData(it)
                }
            }
        }

        return linearLayout
    }
}