package com.libraries.push

import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.POST

interface PushApi {

    @FormUrlEncoded
    @POST("store_token_app.php")
    suspend fun insertPushKey(@Field("packageName") packageName: String,@Field("token") token: String,@Field("device_id") device_id: String,@Field("device_name") device_name: String)



    @FormUrlEncoded
    @POST("silent_response_recieve.php")
    suspend fun silentPush(
        @Field("packageName") packageName: String,
        @Field("token") token: String,
        @Field("active") allow: Boolean
    ):SilentResponse

}
