package com.empower.beneficiaries

import android.app.Application
import android.content.res.AssetManager
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import org.json.JSONArray
import org.json.JSONObject

/**
 * Viewmodel class for the Beneficiaries List Fragment to drive the data flow
 * @param assetManager AssetManager to access the assets where the JSON data is stored
 */

class BeneficiariesListViewModel(assetManager: AssetManager): ViewModel() {

    // MutableStateFlow to hold the list of beneficiaries
    private val _beneficiaries = MutableStateFlow<List<Beneficiary>>(emptyList())
    // Publicly exposed immutable StateFlow for observing the list of beneficiaries
    val beneficiaries = _beneficiaries.asStateFlow()

    init {

        // Launch a coroutine to fetch the JSON data from the asset and parse it into a list of beneficiaries
        val beneficiariesListRepository = BeneficiariesListRepository(assetManager)
        viewModelScope.launch {
            val json = beneficiariesListRepository.getJsonDataFromAsset()
            val type = object : TypeToken<List<Beneficiary>>() {}.type
            val beneficiariesList: List<Beneficiary> = Gson().fromJson(json, type)
            _beneficiaries.emit(beneficiariesList)
        }
    }

    /**
     * Formats the beneficiary phone number to the desired format
     * @param phoneNumber The phone number to be formatted
     * @return The formatted phone number
     */
    fun formatPhoneNumber(phoneNumber: String): String {
        val regex = "(\\d{3})(\\d{3})(\\d{4})".toRegex()
        return regex.replace(phoneNumber, "($1) $2-$3")
    }

    /**
     * Formats the beneficiary date of birth to the desired format
     * @param dateOfBirth The date of birth to be formatted
     * @return The formatted date of birth
     */
    fun formatDateOfBirth(dateOfBirth: String): String {
        val regex = "(\\d{2})(\\d{2})(\\d{4})".toRegex()
        return regex.replace(dateOfBirth, "$1/$2/$3")
    }

    /**
     * Formats the beneficiary address to the desired format
     * @param address The address to be formatted
     * @param fullAddress the formatted and combined address
     */
    fun formatAddress(address: Address): String {
        val fullAddress = if (address.scndLineMailing.isNullOrEmpty()) {
            "${address.firstLineMailing}, ${address.city}, ${address.stateCode} ${address.zipCode}"
        } else {
            "${address.firstLineMailing}, ${address.scndLineMailing}, ${address.city}, ${address.stateCode} ${address.zipCode}"
        }

        return fullAddress
    }

    /**
     * Factory class for creating instances of BeneficiariesListViewModel
     * @param assetManager AssetManager to access the assets where the JSON data is stored
     * @return BeneficiariesListViewModelFactory instance
     */
    class BeneficiariesListViewModelFactory(val assetManager: AssetManager): ViewModelProvider.Factory {
        override fun <T : ViewModel> create(modelClass: Class<T>): T {
            return BeneficiariesListViewModel(assetManager) as T
        }
    }
}