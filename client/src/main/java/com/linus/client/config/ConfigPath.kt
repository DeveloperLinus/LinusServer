package com.linus.client.config

object ConfigPath {
    var deviceUseEnvironment = "1"
    private val baseUrl = BuildConfig.BASE_URL_ROOT
    private val debug = BuildConfig.DEBUG
    fun getBaseUrl() : String{
        return if (debug) {
            "321"
        } else {
           "123"
        }
    }
    var offlineCommunicationIp = "192.168.1.0"
}