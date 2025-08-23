package com.villarica.villarica.base

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity

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

}