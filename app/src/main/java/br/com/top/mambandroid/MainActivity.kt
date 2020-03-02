package br.com.top.mambandroid

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.activity_main.*
import java.io.File


class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        appsRecyclerView.layoutManager = LinearLayoutManager(this)

        val apps = mutableListOf<App>()
        assets.list(APP_FOLDER)?.forEach {
            apps.add(App(it, "$ASSET_URI$APP_FOLDER${File.separator}$it${File.separator}$INDEX_FILE"))
        }
        appsRecyclerView.adapter = AppAdapter(apps, ::onAppClick)
    }

    private fun onAppClick(app: App) {
        val intent = Intent(this, BrowserActivity::class.java)
        intent.putExtra(BrowserActivity.EXTRA_PATH, app.path)
        startActivity(intent)
    }

    companion object {
        const val APP_FOLDER = "apps"
        const val ASSET_URI = "file:///android_asset/"
        const val INDEX_FILE = "index.html"
    }
}