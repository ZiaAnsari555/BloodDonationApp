package com.villarica.villarica.activities

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.core.view.GravityCompat
import com.villarica.villarica.adapters.ProductsAdapter
import com.villarica.villarica.utils.NetworkUtils
import com.villarica.villarica.databinding.ActivityMainBinding // <-- import your util
import androidx.appcompat.app.AlertDialog

class MainActivity : ComponentActivity() {
    private var binding : ActivityMainBinding? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding?.root)

        binding?.toolbar?.ivMenu?.setOnClickListener {
            openSideMenu()
        }
        binding?.rcvProducts?.adapter = ProductsAdapter()
    }

    // ✅ Check internet when the activity becomes visible
    override fun onResume() {
        super.onResume()
        if (!NetworkUtils.isNetworkAvailable(this)) {
            showNoInternetDialog()
        }
    }

    private fun openSideMenu() {
        if (binding?.drawerLayout?.isDrawerOpen(GravityCompat.START) == true)
            binding?.drawerLayout?.closeDrawer(GravityCompat.START)
        else
            binding?.drawerLayout?.openDrawer(GravityCompat.START)
    }

    private fun showNoInternetDialog() {
        AlertDialog.Builder(this)
            .setTitle("No Internet Connection")
            .setMessage("Please enable Wi-Fi or Mobile Data to continue.")
            .setCancelable(false)
            .setPositiveButton("Open Settings") { _, _ ->
                startActivity(Intent(android.provider.Settings.ACTION_WIRELESS_SETTINGS))
            }
            .setNegativeButton("Cancel") { dialog, _ ->
                dialog.dismiss()
            }
            .show()
    }

    companion object {
        fun startThisActivity(context: Context) {
            context.startActivity(Intent(context, MainActivity::class.java))
        }
    }
}
