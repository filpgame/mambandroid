package br.com.top.mambandroid.webinterface

import android.util.Log
import android.webkit.JavascriptInterface
import br.com.top.mambandroid.BuildConfig

class AppInterface : WebAppInterface() {
    private var isDevMode = false

    @JavascriptInterface
    fun doClose() {
        Log.d(TAG, "doClose() called")
    }

    @JavascriptInterface
    fun getVersion(): String = BuildConfig.VERSION_NAME

    @JavascriptInterface
    fun getProxyURL(url: String): String {
        return "https://poiproxy.stone.com.br/v1/proxy?url=$url"
    }

    @JavascriptInterface
    fun getIsDevMode(): Boolean = isDevMode

    @JavascriptInterface
    fun setIsDevMode(isDevMode: Boolean) {
        this.isDevMode = isDevMode
    }

    @JavascriptInterface
    fun isRunningOnDevice() = true

    companion object {
        const val TAG = "AppInterface"
        const val VARIABLE_NAME = "androidApp"
    }
}