package com.villarica.villarica.activities

import android.os.Bundle
import androidx.activity.viewModels
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.lifecycleScope
import com.villarica.villarica.R
import com.villarica.villarica.base.BaseActivity
import com.villarica.villarica.local.SharedPrefs
import com.villarica.villarica.viewmodels.SplashViewModel
import kotlinx.coroutines.launch

class SplashScreen: BaseActivity() {

    private val viewModel: SplashViewModel by viewModels()

    private fun SplashViewModel.init(lifecycleOwner: LifecycleOwner){
        viewModel.isUserSignedIn.observe(lifecycleOwner) {
            when (it) {
                true -> {
                    moveToMainDashboard()
                }
                false -> {
                    moveToMainDashboard()
                }
                else -> {
                    moveToMainDashboard()
                }
            }

        }
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.splash_screen)
        viewModel.init(this)
//        SharedPrefs.getInstance(this).userData?.let {
//            viewModel.userData.value = it
//        }
        lifecycleScope.launch {
            viewModel.giveDelay()
        }
    }

    private fun moveToMainDashboard(){
        SelectActivity.startThisActivity(this)
        finish()
    }

    private fun moveToLogin(){
        LoginActivity.startThisActivity(this)
        finish()
    }
}