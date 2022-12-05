package com.libraries.push

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.libraries.socialpushlibrary.PushController

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        PushController.storeToken(this,BuildConfig.APPLICATION_ID)
    }
}