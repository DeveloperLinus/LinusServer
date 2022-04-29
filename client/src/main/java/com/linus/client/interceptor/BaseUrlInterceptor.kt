package com.linus.client.interceptor

import com.linus.client.config.ConfigPath
import okhttp3.HttpUrl
import okhttp3.Interceptor
import okhttp3.Response

class BaseUrlInterceptor : Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        val request = chain.request()
        val oldHttpUrl = request.url()
        val builder = request.newBuilder()
        if ("2" == ConfigPath.deviceUseEnvironment) {
            var headerValues = request.header("url_name")
            if (headerValues != null && headerValues.length > 0) {
                val headerValue = headerValues.get(0)
                var newBaseUrl: HttpUrl? = null
                if("offline".equals(headerValue)) {
                    newBaseUrl = HttpUrl.parse("http://${ConfigPath.offlineCommunicationIp}/")
                } else {
                    newBaseUrl = oldHttpUrl
                }

                val newFullUrl = 
            }
        } else {

        }
    }

    private fun getNewHttpUrl(oldHttpUrl: HttpUrl, newBaseUrl: HttpUrl, isOffline: Boolean) : HttpUrl {

    }
}