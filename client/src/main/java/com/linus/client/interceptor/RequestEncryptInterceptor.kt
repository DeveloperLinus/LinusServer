package com.linus.client.interceptor

import okhttp3.Interceptor
import okhttp3.Response

class RequestEncryptInterceptor : Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        val request = chain.request()
        
    }
}