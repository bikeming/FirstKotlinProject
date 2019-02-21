package com.fulan.mykotlin.mykotlinapp.network

import com.fulan.mykotlin.mykotlinapp.Constant
import com.google.gson.GsonBuilder
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

/**
 *
 * @ClassName: com.fulan.mykotlin.mykotlinapp
 * @Description:
 * @author: fjm
 * @date: 2018/8/29 13:31
 * @Version:1.0
 */
class MRetrofit private constructor() {

    private val mRetrofit: Retrofit

    companion object {
        val instance: MRetrofit by lazy {
            MRetrofit()
        }
    }

    init {
        mRetrofit = Retrofit.Builder()
                .client(okHttpClient())
                .baseUrl(Constant.BaseUrl)
                .addConverterFactory(GsonConverterFactory.create(GsonBuilder().create()))
                .build()
    }

    fun getHttpApi(): HttpApi {
        return mRetrofit.create(HttpApi::class.java)
    }

    private fun okHttpClient(): OkHttpClient? {
        val builder = OkHttpClient.Builder()
                .retryOnConnectionFailure(true)
//                    .addInterceptor(ReceivedCookiesInterceptor(FlApp.getINSTANCE()))
//                    .addInterceptor(AddCookiesInterceptor(FlApp.getINSTANCE()))
                .connectTimeout(5, TimeUnit.SECONDS)
        val httpLoggingInterceptor = HttpLoggingInterceptor()
        httpLoggingInterceptor.level = HttpLoggingInterceptor.Level.BODY
        builder.addInterceptor(httpLoggingInterceptor)
        return builder.build()
    }
}