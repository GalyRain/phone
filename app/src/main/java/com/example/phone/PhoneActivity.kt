package com.example.phone

import NetworkClient
import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import com.example.phone.databinding.ActivityPhoneBinding
import okhttp3.*

class PhoneActivity : Activity() {
    lateinit var bindingClass: ActivityPhoneBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        bindingClass = ActivityPhoneBinding.inflate(layoutInflater)
        setContentView(bindingClass.root)
        initButtons()
    }

    private fun initButtons() {
        bindingClass.btNext.setOnClickListener {
            var phone: String = bindingClass.etInputPhone.text.toString()
            phone = phone.replace("-", "").replace(" ", "")
            if (phone.trim().length == 10) {
                Thread(Runnable {
                    var responseText =
                        NetworkClient().getOperation(NetworkClient().urlGetSms + phone)
                    if (responseText?.contains("error") == true) {
                        runOnUiThread {
                            var textError: String = getString(R.string.errorServer)
                            Log.d("MyLog", "$responseText")
                            Toast.makeText(applicationContext, textError, Toast.LENGTH_SHORT)
                                .show()
                        }
                    } else {
                        runOnUiThread {
                            Log.d("MyLog", "$responseText")
                            val intent = Intent(this@PhoneActivity, CodeActivity::class.java)
                            intent.putExtra(Constance.PHONE, bindingClass.etInputPhone.text.toString())
                            startActivity(intent)
                        }
                    }
                }).start()
            } else {
                var textError: String = getString(R.string.errorPhone)
                Toast.makeText(applicationContext, textError, Toast.LENGTH_SHORT).show()
            }
        }
    }
}