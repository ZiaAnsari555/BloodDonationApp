package com.villarica.villarica.activities

import android.content.Context
import android.content.Intent
import android.os.Bundle
import com.villarica.villarica.base.BaseActivity
import com.villarica.villarica.databinding.ActivitySelectBinding

class SelectActivity: BaseActivity() {
    private var binding : ActivitySelectBinding? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivitySelectBinding.inflate(layoutInflater)
        setContentView(binding?.root)
        binding?.btnFindDonor?.setOnClickListener {
            SearchDonorActivity.startThisActivity(this)
        }

        binding?.btnAddDonor?.setOnClickListener {
            AddDonorActivity.startThisActivity(this)
        }
    }

    companion object {
        fun startThisActivity(context: Context) {
            context.startActivity(Intent(context, SelectActivity::class.java))
        }
    }
}