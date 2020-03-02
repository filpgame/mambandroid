package br.com.top.mambandroid

import android.annotation.SuppressLint
import android.content.res.Configuration
import android.os.Build
import android.os.Bundle
import android.webkit.WebView
import androidx.appcompat.app.AppCompatActivity
import br.com.top.mambandroid.webinterface.*
import kotlinx.android.synthetic.main.activity_browser.*

@SuppressLint("SetJavaScriptEnabled")
class BrowserActivity : AppCompatActivity() {
    private val printerInterface: PrinterInterface by lazy { PrinterInterface() }
    private val appInterface: AppInterface by lazy { AppInterface() }
    private val storageInterface: StorageInterface by lazy { StorageInterface() }
    private val keyboardInterface: KeyboardInterface by lazy { KeyboardInterface() }
    private val merchantInterface: MerchantInterface by lazy { MerchantInterface() }
    private val paymentInterface: PaymentInterface by lazy { PaymentInterface() }
    private val systemInterface: SystemInterface by lazy { SystemInterface() }
    private val cancellationInterface: CancellationInterface by lazy { CancellationInterface() }
    private val httpInterface: HttpInterface by lazy { HttpInterface() }
    private val cardInterface: CardInterface by lazy { CardInterface() }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_browser)
        val path = intent.getStringExtra(EXTRA_PATH)
        WebView.setWebContentsDebuggingEnabled(true)
        webview.apply {
            settings.javaScriptEnabled = true
            settings.allowFileAccess = true
            settings.databaseEnabled = true
            settings.domStorageEnabled = true
            settings.allowContentAccess = true
            webViewClient = printerInterface

            addJavascriptInterface(printerInterface, PrinterInterface.VARIABLE_NAME)
            addJavascriptInterface(appInterface, AppInterface.VARIABLE_NAME)
            addJavascriptInterface(storageInterface, StorageInterface.VARIABLE_NAME)
            addJavascriptInterface(keyboardInterface, KeyboardInterface.VARIABLE_NAME)
            addJavascriptInterface(merchantInterface, MerchantInterface.VARIABLE_NAME)
            addJavascriptInterface(paymentInterface, PaymentInterface.VARIABLE_NAME)
            addJavascriptInterface(systemInterface, SystemInterface.VARIABLE_NAME)
            addJavascriptInterface(cancellationInterface, CancellationInterface.VARIABLE_NAME)
            addJavascriptInterface(httpInterface, HttpInterface.VARIABLE_NAME)
            addJavascriptInterface(cardInterface, CardInterface.VARIABLE_NAME)

            loadUrl(path)
        }
    }

    // Workaround appcompat-1.1.0 bug https://issuetracker.google.com/issues/141132133
    override fun applyOverrideConfiguration(overrideConfiguration: Configuration) {
        if (Build.VERSION.SDK_INT in 21..22) {
            return
        }
        super.applyOverrideConfiguration(overrideConfiguration)
    }

    override fun onBackPressed() {
        if (webview.canGoBack()) {
            webview.goBack()
        } else {
            super.onBackPressed()
        }
    }

    companion object {
        const val EXTRA_PATH = "path"
    }
}
