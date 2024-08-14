package com.empower.beneficiaries

import android.os.Bundle
import android.view.View.generateViewId
import android.view.ViewGroup.LayoutParams
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.FragmentContainerView
import androidx.navigation.fragment.NavHostFragment

/**
 * BeneficiariesMainActivity is the main activity for the Beneficiaries app.
 */
class BeneficiariesMainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // Create a FragmentContainerView to hold the NavHostFragment
        val fragmentContainerLayout = FragmentContainerView(this).apply {
            id = generateViewId()
        }
        fragmentContainerLayout.layoutParams = LayoutParams(
            LayoutParams.MATCH_PARENT,
            LayoutParams.MATCH_PARENT
        )

        // Create a NavHostFragment and set it as the primary navigation fragment
        val navHostFragment = NavHostFragment.create(R.navigation.beneficiaries_navigation)
        supportFragmentManager.beginTransaction()
            .replace(fragmentContainerLayout.id, navHostFragment)
            .setPrimaryNavigationFragment(navHostFragment)
            .commit()

        setContentView(fragmentContainerLayout)
    }
}