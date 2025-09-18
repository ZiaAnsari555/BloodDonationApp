package com.villarica.villarica.activities

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.core.view.isVisible
import com.villarica.villarica.R
import com.villarica.villarica.base.BaseActivity
import com.villarica.villarica.databinding.ActivitySearchDonorBinding

class SearchDonorActivity: BaseActivity() {
    private var binding : ActivitySearchDonorBinding? = null
    private var bloodGroup: String? = null
    private var city : String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySearchDonorBinding.inflate(layoutInflater)
        setContentView(binding?.root)
        binding?.apply {
            toolbar.ivMenu.isVisible = true
            toolbar.ivMenu.setOnClickListener {
                finish()
            }
            toolbar.tvTitle.text = getString(R.string.search_donor)
            btnAddDonor.setOnClickListener {
                if (city.isNullOrEmpty() || bloodGroup.isNullOrEmpty()){
                    Toast.makeText(this@SearchDonorActivity, "Please Select city", Toast.LENGTH_SHORT).show()
                    return@setOnClickListener
                }
                AvailableDonorsActivity.startThisActivity(this@SearchDonorActivity, city?:"", bloodGroup?:"")
            }
            tvEnterCity.setOnClickListener {
                showCityDialog(this@SearchDonorActivity, onSelected = {selected ->
                    city = selected
                    tvEnterCity.text = city
                })
            }
            tvSelectBloodType.setOnClickListener {
                showBloodGroupDialog(this@SearchDonorActivity, onSelected = {selected ->
                    bloodGroup = selected
                    tvSelectBloodType.text = bloodGroup
                })
            }
        }
    }

    private fun showBloodGroupDialog(context: Context, onSelected: (String) -> Unit) {
        val bloodGroups = arrayOf(
            "A+", "A-", "B+", "B-", "O+", "O-", "AB+", "AB-"
        )
        val builder = AlertDialog.Builder(context)
        builder.setTitle("Select Blood Group")
        builder.setItems(bloodGroups) { dialog, which ->
            onSelected(bloodGroups[which])  // callback with selected value
            dialog.dismiss()
        }
        builder.show()
    }

    private fun showCityDialog(context: Context, onSelected: (String) -> Unit) {
        val cities = arrayOf(
            "Faisalabad"
        )
        val builder = AlertDialog.Builder(context)
        builder.setTitle("Select City")
        builder.setItems(cities) { dialog, which ->
            onSelected(cities[which])  // callback with selected value
            dialog.dismiss()
        }
        builder.show()
    }

    companion object {
        fun startThisActivity(context: Context) {
            context.startActivity(Intent(context, SearchDonorActivity::class.java))
        }
    }
}