package com.example.phone

import NetworkClient
import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import com.example.phone.databinding.ActivityCodeBinding
import org.json.JSONObject

class CodeActivity : Activity() {
    lateinit var bindingClass: ActivityCodeBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        bindingClass = ActivityCodeBinding.inflate(layoutInflater)
        setContentView(bindingClass.root)
    }

    fun onClickGo(view: View) {
        val phoneIntent: String = intent.getStringExtra(Constance.PHONE).toString()
        val code1: String = bindingClass.edCode1.text.toString()
        val code2: String = bindingClass.edCode2.text.toString()
        val code3: String = bindingClass.edCode3.text.toString()
        val code4: String = bindingClass.edCode4.text.toString()

        if(code1.trim().length == 1 && code2.trim().length == 1  && code3.trim().length == 1  && code4.trim().length == 1) {
            Thread(Runnable {
                var codeSMS = "$code1$code2$code3$code4"
                var json = JSONObject("{'phone_number':'$phoneIntent','password':'$codeSMS'}")
                var responseText = NetworkClient().postOperation(NetworkClient().urlAuthenticate, json)
                if (responseText?.contains("invalid_credentials") == true) {
                    runOnUiThread {
                        Log.d("MyLog", "$responseText")
                        var textError: String = getString(R.string.invalidCredentials)
                        Toast.makeText(applicationContext, textError, Toast.LENGTH_SHORT)
                            .show()
                    }
                }
                else {
                    runOnUiThread {
                        Log.d("MyLog", "$responseText")
                        val intent = Intent(this@CodeActivity, FinishActivity::class.java)
                        startActivity(intent)
                    }
                }
            }).start()

        }
        else {
            var textError: String = getString(R.string.errorCode)
            Toast.makeText(applicationContext, textError, Toast.LENGTH_SHORT).show()
        }
    }
}