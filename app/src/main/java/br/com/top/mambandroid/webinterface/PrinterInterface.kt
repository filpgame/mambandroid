package br.com.top.mambandroid.webinterface

import android.util.Log
import android.webkit.JavascriptInterface

class PrinterInterface : WebAppInterface() {

    @JavascriptInterface
    fun doPrint(content: Any, config: Any) {
        Log.d(TAG, "print() called with: content = [${content}], config = [${config}]")
    }

    @JavascriptInterface
    fun print(content: Any?, config: Any?) {
        Log.d(TAG, "print() called with: content = [${content}], config = [${config}]")
    }

    @JavascriptInterface
    fun getPaperWidth(): Int = 380

    @JavascriptInterface
    fun isPrinting(): Boolean = false

    @JavascriptInterface
    fun failedPrinting(): Boolean = false

    @JavascriptInterface
    fun doFeed(pixels: Int){
        Log.d(TAG, "doFeed() called with: pixels = [${pixels}]")
    }

    companion object {
        const val VARIABLE_NAME = "androidPrinter"
        const val TAG = "PrinterInterface"
    }
}