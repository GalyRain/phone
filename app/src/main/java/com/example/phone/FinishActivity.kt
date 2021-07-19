package com.example.phone

import NetworkClient
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log

class FinishActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_finish)
        var token = "eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJpc3MiOiJodHRwOi8vcHVsdHRheGkvYXBpL2F1dGhlbnRpY2F0ZVVzZXJzIiwiaWF0IjoxNTQwNjczNjU4LCJleHAiOjE1NDg3MDg4NTgsIm5iZiI6MTU0MDY3MzY1OCwianRpIjoiRHFndXNaU1lsRmYzbG1UYSIsInN1YiI6MSwicHJ2IjoiODdlMGFmMWVmOWZkMTU4MTJmZGVjOTcxNTNhMTRlMGIwNDc1NDZhYSJ9.-xtggpWCdgiZKsE8G9FGKD9Tg-lO9Pi2xAKkZsCNHlI"
        Thread(Runnable {
            var responseText = NetworkClient().getOperation(NetworkClient().urlGetInfo, token)
            runOnUiThread {
                Log.d("MyLog", "$responseText")
                }

        }).start()
    }
}