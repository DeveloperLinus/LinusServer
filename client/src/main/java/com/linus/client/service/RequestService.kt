package com.linus.client.service

import com.linus.commonlib.bean.ADBean
import io.reactivex.Observable
import retrofit2.Response
import retrofit2.http.FormUrlEncoded
import retrofit2.http.Headers
import retrofit2.http.POST

interface RequestService {
    companion object {
        const val URL_OFFLINE = "url_name:offline"
    }
    @Headers(URL_OFFLINE)
    @FormUrlEncoded
    @POST("getPadFaceAD")
    fun getPadFaceAD() : Observable<Response<ADBean>>
}