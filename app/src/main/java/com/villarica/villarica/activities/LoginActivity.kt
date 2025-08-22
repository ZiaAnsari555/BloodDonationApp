package com.villarica.villarica.activities

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.core.view.isVisible
import com.villarica.villarica.R
import com.villarica.villarica.base.BaseActivity
import com.villarica.villarica.databinding.ActivityLoginBinding

class LoginActivity: BaseActivity() {
    private var binding: ActivityLoginBinding? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding?.root)
        binding?.toolbar?.ivMenu?.isVisible = false
        binding?.toolbar?.tvTitle?.text = getString(R.string.login)
        binding?.btnLogin?.setOnClickListener {
            MainActivity.startThisActivity(this)
        }
    }





    companion object {
        fun startThisActivity(context: Context) {
            val intent = Intent(context, LoginActivity::class.java)
            context.startActivity(intent)

        }
    }
}