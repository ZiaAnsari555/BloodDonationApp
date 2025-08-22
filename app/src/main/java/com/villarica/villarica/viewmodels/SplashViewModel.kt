package com.villarica.villarica.viewmodels

import android.app.Application
import androidx.lifecycle.MutableLiveData
import com.villarica.villarica.base.BaseViewModel
import com.yodeck.models.Data
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.withContext

class SplashViewModel(application: Application): BaseViewModel(application) {

    var delay = 3000L
    var userData: MutableLiveData<Data> = MutableLiveData()

    private var _isUserSignedIn: MutableLiveData<Boolean> = MutableLiveData()
    var isUserSignedIn: MutableLiveData<Boolean> = _isUserSignedIn

    suspend fun giveDelay() {
        withContext(Dispatchers.Main) {
            delay(delay)
            _isUserSignedIn.postValue(isSignedIn())
        }
    }

    private fun isSignedIn(): Boolean {
        return when {
            (userData.value != null) -> {
                true
            }
            else -> {
                false
            }
        }
    }
}