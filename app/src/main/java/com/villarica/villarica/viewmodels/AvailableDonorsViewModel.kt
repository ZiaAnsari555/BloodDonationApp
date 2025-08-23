package com.villarica.villarica.viewmodels

import android.app.Application
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.villarica.villarica.base.BaseViewModel
import com.yodeck.models.donors_response.DonorsResponse
import com.yodeck.network.BaseRepository
import kotlinx.coroutines.launch

class AvailableDonorsViewModel(application: Application): BaseViewModel(application) {
    private val repo = BaseRepository()
    val response = MutableLiveData<DonorsResponse>()
    val error = MutableLiveData<String>()

    fun getDonorsData(bloodGroup: String, city: String, offset: Int, limit: Int){
        val hashMap = getCommonParams()
        hashMap["blood_group"] = bloodGroup
        hashMap["city"] = city
        hashMap["offset"] = offset
        hashMap["limit"] = limit
        viewModelScope.launch {
            try {
                val goalsData = repo.getDonors(hashMap)
                response.value = goalsData
            }catch (e: java.lang.Exception){
                error.value = e.toString()
            }
        }
    }
}