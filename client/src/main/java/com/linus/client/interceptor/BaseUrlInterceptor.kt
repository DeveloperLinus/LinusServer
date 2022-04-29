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

                val newFullUrl = getNewHttpUrl(oldHttpUrl, newBaseUrl!!, true)
                return chain.proceed(builder.url(newFullUrl).build())
            } else {
                val newHttpUrl = getNewHttpUrl(oldHttpUrl, oldHttpUrl, false)
                return chain.proceed(builder.url(newHttpUrl).build())
            }
        } else {
            val newBaseUrl = HttpUrl.parse(oldHttpUrl.toString())
            val newHttpUrl = getNewHttpUrl(oldHttpUrl, newBaseUrl!!, false)
            return chain.proceed(builder.url(newHttpUrl).build())
        }
    }

    private fun getNewHttpUrl(oldHttpUrl: HttpUrl, newBaseUrl: HttpUrl, isOfflineLine: Boolean) : HttpUrl {
        val builder = oldHttpUrl.newBuilder()
        val httpUrlBuild = builder
            .scheme(newBaseUrl.scheme()) // 更改网络协议
            .host(newBaseUrl.host()) // 更换主机名
            .port(newBaseUrl.port()) // 更换端口

        val segmentList = oldHttpUrl.encodedPathSegments()
        if (isOfflineLine) {
            httpUrlBuild.setEncodedPathSegment(0, "eiserver")
                .setEncodedPathSegment(1, "client")
                .addEncodedPathSegment("pad")
                .addEncodedPathSegment(segmentList.get(1))
        } else {
            httpUrlBuild.setEncodedPathSegment(1, "faceRecognition")
                .addEncodedPathSegment(segmentList.get(1))
        }
        return httpUrlBuild.build()
    }
}