package com.villarica.villarica.activities

import android.content.Context
import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.widget.Toast
import androidx.activity.viewModels
import androidx.annotation.RequiresApi
import androidx.core.view.isVisible
import androidx.lifecycle.LifecycleOwner
import com.villarica.villarica.R
import com.villarica.villarica.adapters.DonorsAdapter
import com.villarica.villarica.base.BaseActivity
import com.villarica.villarica.databinding.ActivityAvailableDonorsBinding
import com.villarica.villarica.viewmodels.AvailableDonorsViewModel
import com.yodeck.models.donors_response.Data
import java.time.LocalDate
import java.time.format.DateTimeFormatter

class AvailableDonorsActivity: BaseActivity() {
    private var binding : ActivityAvailableDonorsBinding? = null
    private val viewModel: AvailableDonorsViewModel by viewModels()
    private var bloodGroup: String? = null
    private var city : String? = null
    private var adapter : DonorsAdapter? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityAvailableDonorsBinding.inflate(layoutInflater)
        setContentView(binding?.root)
        binding?.apply {
            toolbar.ivMenu.isVisible = true
            toolbar.ivMenu.setOnClickListener {
                finish()
            }
            toolbar.tvTitle.text = getString(R.string.available_donors)
            loadBannerAd(adView)
        }
        initRecyclerView()
        viewModel.init(this)
        intent?.extras?.apply {
            bloodGroup = getString("blood_group")
            city = getString("city")
            showLoading()
            viewModel.getDonorsData(bloodGroup?:"", city?:"", 0, 100)
        }
    }

    @RequiresApi(Build.VERSION_CODES.O)
    private fun AvailableDonorsViewModel.init(lifecycleOwner: LifecycleOwner) {
        viewModel.response.observe(lifecycleOwner) {
            hideLoading()
            when (it.success) {
                true -> {
                    val formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd")
                    binding?.apply {
                        it.data.let { list ->
                            // Sort by dob (youngest first)
                            val sortedList = list.sortedByDescending {
                                if (it.dob.isNotEmpty()) {
                                    LocalDate.parse(it.dob, formatter)
                                } else {
                                    // Return a very old date if dob is missing,
                                    // so it appears at the bottom of the list
                                    LocalDate.MAX
                                }
                            }
                            adapter?.list = sortedList.toCollection(ArrayList())

                        }
                    }
                }

                false -> {

                }
            }
        }
        viewModel.error.observe(lifecycleOwner){
            hideLoading()
            Toast.makeText(this@AvailableDonorsActivity, it, Toast.LENGTH_SHORT).show()
        }
    }

    private fun initRecyclerView(){
        adapter = DonorsAdapter(onItemClick = { donor ->
            // open Donor details....
            DonorDetailsActivity.startThisActivity(this@AvailableDonorsActivity, donor)
        })
        binding?.rcvAvailableDonor?.adapter = adapter
    }

    companion object {
        fun startThisActivity(context: Context, city: String, bloodGroup: String) {
            context.startActivity(Intent(context, AvailableDonorsActivity::class.java)
                .putExtra("city", city)
                .putExtra("blood_group", bloodGroup))
        }
    }
}