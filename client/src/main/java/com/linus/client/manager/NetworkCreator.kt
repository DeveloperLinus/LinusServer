package com.linus.client.manager

import com.linus.client.OkHttpTimeOutHolder
import com.linus.client.config.ConfigPath
import com.linus.client.server.RequestService
import com.linus.client.service.RequestService
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.scalars.ScalarsConverterFactory
import java.util.concurrent.TimeUnit

class NetworkCreator {
    private object RetrofitHolder {
        val RETROFIT_CLIENT: Retrofit = Retrofit.Builder()
            .baseUrl(ConfigPath.getBaseUrl())
            .client(OkHttpTimeOutHolder.getOkHttpClient(5, TimeUnit.MINUTES))
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .addConverterFactory(ScalarsConverterFactory.create())
//            .addConverterFactory(CustomGsonConverterFactory.create()) //                .addConverterFactory(GsonConverterFactory.create())
            .build()
    }
    private object RequestServiceHolder {
        val REQUEST_SERVICE: RequestService = RetrofitHolder.RETROFIT_CLIENT.create(RequestService::class.java)
    }

    fun getRequestService(): RequestService? {
        return RequestServiceHolder.REQUEST_SERVICE
    }
}