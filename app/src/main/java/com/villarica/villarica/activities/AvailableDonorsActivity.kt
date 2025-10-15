package com.villarica.villarica.activities

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.activity.viewModels
import androidx.core.view.isVisible
import androidx.lifecycle.LifecycleOwner
import com.villarica.villarica.R
import com.villarica.villarica.adapters.DonorsAdapter
import com.villarica.villarica.base.BaseActivity
import com.villarica.villarica.databinding.ActivityAvailableDonorsBinding
import com.villarica.villarica.viewmodels.AvailableDonorsViewModel

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

    private fun AvailableDonorsViewModel.init(lifecycleOwner: LifecycleOwner) {
        viewModel.response.observe(lifecycleOwner) {
            hideLoading()
            when (it.success) {
                true -> {
                    binding?.apply {
                        it.data.let { list ->
                            adapter?.list = list
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