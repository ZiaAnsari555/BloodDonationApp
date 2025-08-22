package com.villarica.villarica.base

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import com.villarica.villarica.local.DataStoreManager
import com.yodeck.models.getscreen.GetScreenResponse
import com.yodeck.network.BaseRepository

open class BaseViewModel(application: Application) : AndroidViewModel(application) {
    private val dataStoreManager = DataStoreManager(application) // ✅ Initialize DataStoreManager

    fun getCommonParams(): HashMap<String, Any> {
        val hashMap = HashMap<String, Any>()
        hashMap["device_token"] = "device_token"
        hashMap["device_type"] = "Android"
//        hashMap["login_device"] = AppConstant.DEVICE_TYPE
        return hashMap
    }
}