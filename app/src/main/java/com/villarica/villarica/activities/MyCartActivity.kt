package com.villarica.villarica.activities

import android.os.Bundle
import com.villarica.villarica.adapters.CartAdapter
import com.villarica.villarica.base.BaseActivity
import com.villarica.villarica.databinding.ActivityMyCartBinding

class MyCartActivity: BaseActivity() {
    private var binding: ActivityMyCartBinding? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMyCartBinding.inflate(layoutInflater)
        setContentView(binding?.root)
        binding?.rcvCart?.adapter = CartAdapter()
    }


}