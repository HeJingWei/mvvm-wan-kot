package com.example.mvvm_wan_kot.common.network

import com.example.mvvm_wan_kot.BuildConfig
import com.example.mvvm_wan_kot.common.App
import com.franmontiel.persistentcookiejar.PersistentCookieJar
import com.franmontiel.persistentcookiejar.cache.SetCookieCache
import com.franmontiel.persistentcookiejar.persistence.SharedPrefsCookiePersistor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

object RetrofitClient {

    val service by lazy { getService(ApiService::class.java) }

    private val cookieJar by lazy { PersistentCookieJar(SetCookieCache(), SharedPrefsCookiePersistor(App.instance)) }

    private val client:OkHttpClient
    get() {
        val builder = OkHttpClient.Builder()
        val logging = HttpLoggingInterceptor()
        if (BuildConfig.DEBUG) {
            logging.level = HttpLoggingInterceptor.Level.BODY
        } else {
            logging.level = HttpLoggingInterceptor.Level.BASIC
        }

        builder.addInterceptor(logging)
            .cookieJar(cookieJar)
            .connectTimeout(60, TimeUnit.SECONDS)

        return builder.build()
    }

    private fun <S> getService(serviceClass: Class<S>): S {
        return Retrofit.Builder()
            .client(client)
            .addConverterFactory(GsonConverterFactory.create())
            .baseUrl(ApiService.BASE_URL)
            .build().create(serviceClass)
    }

    fun clearCookie() = cookieJar.clear()

}