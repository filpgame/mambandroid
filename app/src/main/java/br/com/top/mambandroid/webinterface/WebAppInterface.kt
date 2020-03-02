package br.com.top.mambandroid.webinterface

import android.graphics.Bitmap
import android.util.Log
import android.webkit.JavascriptInterface
import android.webkit.WebView
import android.webkit.WebViewClient

abstract class WebAppInterface : WebViewClient() {

    @JavascriptInterface
    fun toast(string: String) {
        Log.d(TAG, "toast() called with: string = [${string}]")
    }

    override fun onPageStarted(view: WebView?, url: String?, favicon: Bitmap?) {
        Log.d(TAG, "onPageStarted() called with: view = [${view}], url = [${url}], favicon = [${favicon}]")

        view?.evaluateJavascript("window.\$Printer = ${PrinterInterface.VARIABLE_NAME}", null)
        view?.evaluateJavascript("window.\$App = ${AppInterface.VARIABLE_NAME}", null)
        view?.evaluateJavascript("window.\$Storage = ${StorageInterface.VARIABLE_NAME}", null)
        view?.evaluateJavascript("window.\$Keyboard = ${KeyboardInterface.VARIABLE_NAME}", null)
        view?.evaluateJavascript("window.\$Merchant = ${MerchantInterface.VARIABLE_NAME}", null)
        view?.evaluateJavascript("window.\$Payment = ${PaymentInterface.VARIABLE_NAME}", null)
        view?.evaluateJavascript("window.\$System = ${SystemInterface.VARIABLE_NAME}", null)
        view?.evaluateJavascript("window.\$Cancellation = ${CancellationInterface.VARIABLE_NAME}", null)
        view?.evaluateJavascript("window.\$Http = ${HttpInterface.VARIABLE_NAME}", null)
        view?.evaluateJavascript("window.\$Card = ${CardInterface.VARIABLE_NAME}", null)
    }

    companion object {
        const val TAG = "WebAppInterface"
    }
}