package com.linus.client

import com.linus.client.interceptor.BaseUrlInterceptor
import com.linus.client.interceptor.RequestEncryptInterceptor
import okhttp3.OkHttpClient
import java.util.*
import java.util.concurrent.TimeUnit

object OkHttpTimeOutHolder {
    private var OK_HTTP_CLIENT: OkHttpClient? = null
    private var okHttpClientMap = HashMap<String, OkHttpClient?>()
    fun getOkHttpClient(timeOut: Long, timeUnit: TimeUnit): OkHttpClient? {
        OK_HTTP_CLIENT = okHttpClientMap.get("$timeOut${timeUnit.name}")
        if (OK_HTTP_CLIENT == null) {
            val builder = OkHttpClient.Builder()
                .addInterceptor(BaseUrlInterceptor())
                .addInterceptor(RequestEncryptInterceptor())
                .retryOnConnectionFailure(true)
                .connectTimeout(timeOut, timeUnit)
                .writeTimeout(timeOut, timeUnit)
                .readTimeout(timeOut, timeUnit)
            OK_HTTP_CLIENT = builder.build()
            okHttpClientMap.put("$timeOut${timeUnit.name}", OK_HTTP_CLIENT)
        }
        return OK_HTTP_CLIENT
    }
}