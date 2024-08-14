package com.empower.beneficiaries

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

/**
 * A class to represent a beneficiary
 */
@Parcelize
data class Beneficiary(val lastName: String, val firstName: String, val designationCode: String, val socialSecurityNumber: String,
val dateOfBirth: String, val middleName: String?, val phoneNumber: String, val beneficiaryAddress: Address, val beneType: String):
    Parcelable

/**
 * A class to represent an address of a beneficiary
 */
@Parcelize
data class Address (val firstLineMailing: String, val scndLineMailing: String?, val city: String, val stateCode: String, val zipCode: String, val country: String): Parcelable
