package com.villarica.villarica.base

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.google.android.gms.ads.AdRequest
import com.google.android.gms.ads.AdView

open class BaseActivity: AppCompatActivity() {
    private lateinit var loadingDialog: LoadingDialog
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        loadingDialog = LoadingDialog(this)
    }

    fun showLoading(message: String = "Loading...") {
        loadingDialog.show(message)
    }

    fun hideLoading() {
        loadingDialog.dismiss()
    }

    fun loadBannerAd(adView: AdView){
        val adRequest = AdRequest.Builder().build()
        adView.loadAd(adRequest)
    }
}