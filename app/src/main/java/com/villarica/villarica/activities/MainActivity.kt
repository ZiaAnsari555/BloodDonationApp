package com.villarica.villarica.activities

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.core.view.GravityCompat
import com.villarica.villarica.adapters.ProductsAdapter
import com.villarica.villarica.databinding.ActivityMainBinding
import com.villarica.villarica.ui.theme.VillRiceWearHouseAndroidTheme

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

    private fun openSideMenu(){
        if (binding?.drawerLayout?.isDrawerOpen(GravityCompat.START) == true)
            binding?.drawerLayout?.closeDrawer(GravityCompat.START)
        else
            binding?.drawerLayout?.openDrawer(GravityCompat.START)
    }

    companion object {
        fun startThisActivity(context: Context) {
            context.startActivity(Intent(context, MainActivity::class.java))
        }
    }
}