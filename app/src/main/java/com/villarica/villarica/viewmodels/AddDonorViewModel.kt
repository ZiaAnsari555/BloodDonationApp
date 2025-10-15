package com.villarica.villarica.viewmodels

import android.app.Application
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.villarica.villarica.base.BaseViewModel
import com.yodeck.models.AddDonorResponse
import com.yodeck.models.donors_response.DonorsResponse
import com.yodeck.network.BaseRepository
import com.yodeck.network.RetrofitInstance.prepareFilePart
import com.yodeck.network.RetrofitInstance.preparePartMap
import kotlinx.coroutines.launch
import java.io.File

class AddDonorViewModel(application: Application): BaseViewModel(application) {

    private val repo = BaseRepository()
    val response = MutableLiveData<AddDonorResponse>()
    val error = MutableLiveData<String>()

    fun addDonor(file: File?,name: String, bloodGroup:String, phoneNumber:String, city: String, dob: String){

        val params = hashMapOf<String, Any>(
            "name" to name,
            "blood_group" to bloodGroup,
            "phone_number" to phoneNumber,
            "city" to city,
            "dob" to dob
        )

        val partMap = preparePartMap(params)

        viewModelScope.launch {
            try {
                var respone = repo.addDonor(partMap)
                if (file != null){
                    val imagePart = prepareFilePart("picture", file)
                    respone = repo.addDonor(imagePart, partMap)
                }
                if (respone.isSuccessful) {
                    response.value = respone.body()
                } else {
                    // Parse the error JSON into AddDonorResponse
                    val gson = Gson()
                    val type = object : TypeToken<AddDonorResponse>() {}.type
                    val errorResponse: AddDonorResponse? =
                        gson.fromJson(respone.errorBody()?.charStream(), type)

                    error.value = errorResponse?.error ?: "Unknown error"
                }
            }catch (e: java.lang.Exception){
                error.value = e.toString()
            }
        }
    }



}