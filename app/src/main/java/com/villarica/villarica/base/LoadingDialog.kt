package com.villarica.villarica.base

import android.app.Dialog
import android.content.Context
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.view.Window
import android.widget.TextView
import com.villarica.villarica.R

class LoadingDialog(private val context: Context) {
    private var dialog: Dialog? = null

    fun show(message: String = "Loading...") {
        if (dialog?.isShowing == true) return

        dialog = Dialog(context).apply {
            requestWindowFeature(Window.FEATURE_NO_TITLE)
            setContentView(R.layout.dialog_loading)
            setCancelable(false)
            window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))

            findViewById<TextView>(R.id.tvLoadingMessage).text = message
        }
        dialog?.show()
    }

    fun dismiss() {
        dialog?.dismiss()
    }
}
