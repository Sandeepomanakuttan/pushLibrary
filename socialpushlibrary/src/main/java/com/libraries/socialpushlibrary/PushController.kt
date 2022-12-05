package com.libraries.socialpushlibrary

import android.content.Context
import android.os.Build
import android.provider.Settings
import android.widget.Toast
import com.google.firebase.messaging.FirebaseMessaging
import com.libraries.push.PushApi
import kotlinx.coroutines.DelicateCoroutinesApi
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class PushController {

    companion object{
        @OptIn(DelicateCoroutinesApi::class)
        fun storeToken(context: Context, packageName: String){
            FirebaseMessaging.getInstance().token.addOnCompleteListener {
                if (!it.isSuccessful){
                    Toast.makeText(context, "unSuccefful", Toast.LENGTH_SHORT).show()
                    return@addOnCompleteListener
                }

                val api = getInstance()
                val id  = Settings.Secure.getString(context.contentResolver,
                    Settings.Secure.ANDROID_ID)

                val deviceName = Build.MANUFACTURER + Build.MODEL

//                GlobalScope.launch {
//                    api.insertPushKey(packageName,it.result.toString(),id,deviceName)
//                }
                Toast.makeText(context, "succe $packageName", Toast.LENGTH_SHORT).show()

            }
        }

        private fun getInstance(): PushApi {
            val interceptor = HttpLoggingInterceptor()
            interceptor.setLevel(HttpLoggingInterceptor.Level.BODY)
            val client: OkHttpClient = OkHttpClient.Builder().addInterceptor(interceptor).build()

            return Retrofit.Builder().baseUrl("https://www.pushpro.in/insertions/")
                .addConverterFactory(GsonConverterFactory.create())
                .client(client)
                // we need to add converter factory to
                // convert JSON object to Java object
                .build()
                .create(PushApi::class.java)
        }

    }

}