package com.empower.beneficiaries

import android.content.res.AssetManager
import org.json.JSONArray
import org.json.JSONObject

/**
 * BeneficiariesListRepository class
 */

class BeneficiariesListRepository(val assetManager: AssetManager) {

    //Get the json data from the assets folder, and return it to the viewmodel for manipulation
    fun getJsonDataFromAsset(): String {
        return  assetManager.open("Beneficiaries.json").bufferedReader().use { it.readText() }
    }
}