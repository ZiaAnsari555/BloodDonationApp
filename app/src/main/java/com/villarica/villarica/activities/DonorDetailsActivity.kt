package com.villarica.villarica.activities

import android.content.Context
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.core.view.isVisible
import com.google.gson.Gson
import com.villarica.villarica.R
import com.villarica.villarica.base.BaseActivity
import com.villarica.villarica.databinding.ActivityDonorDetailsBinding
import com.yodeck.models.donors_response.Data

class DonorDetailsActivity :BaseActivity(){
    private var binding: ActivityDonorDetailsBinding? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDonorDetailsBinding.inflate(layoutInflater)
        setContentView(binding?.root)
        binding?.apply {
            toolbar.ivMenu.isVisible = true
            toolbar.ivMenu.setOnClickListener {
                finish()
            }
            toolbar.tvTitle.text = getString(R.string.donor_details)
        }
        intent?.extras?.apply {
            val donorJson = getString("donor")
            val donor = Gson().fromJson(donorJson, Data::class.java)
            setData(donor)
        }
    }

    private fun setData(item: Data){
        binding?.tvName?.text = item.name
        binding?.tvCity?.text = item.city
        binding?.tvPhone?.text = item.phone
        binding?.tvBloodGroup?.text = item.blood_group
    }


    companion object {
        fun startThisActivity(context: Context, donor: Data) {
            val donorJson = Gson().toJson(donor)
            context.startActivity(
                Intent(context, DonorDetailsActivity::class.java)
                .putExtra("donor", donorJson))
        }
    }


}