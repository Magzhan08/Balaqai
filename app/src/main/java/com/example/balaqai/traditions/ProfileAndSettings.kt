package com.example.balaqai.traditions

import android.app.Dialog
import android.content.Context
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.view.Window
import android.widget.ImageView
import android.widget.TextView
import com.example.balaqai.R

class ProfileAndSettings {
    companion object {
        fun showProfileDialog(context: Context,userName: String, userAge: String, userEmail: String) {
            val dialog = Dialog(context)
            dialog.requestWindowFeature(Window.FEATURE_NO_TITLE)
            dialog.setCancelable(false)
            dialog.setContentView(R.layout.profile_custom_dialog)
            dialog.window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))

            var tvName : TextView = dialog.findViewById(R.id.userNameProfile)
            tvName.text = "Аты:  $userName"
            var tvAge : TextView = dialog.findViewById(R.id.userAgeProfile)
            tvAge.text = "Жасы:  $userAge "
            var tvEmail : TextView = dialog.findViewById(R.id.userNumberProfile)
            tvEmail.text = "Email: $userEmail"
            val btnClose: ImageView = dialog.findViewById(R.id.btn_profile_close)
            btnClose.setOnClickListener {
                dialog.dismiss()
            }
            dialog.show()
        }
    }
}

