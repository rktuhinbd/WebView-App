package com.rktuhinbd.webviewapp

import android.os.Bundle
import android.webkit.WebView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.WindowCompat
import com.rktuhinbd.webviewapp.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)

        enableEdgeToEdge()

        setContentView(binding.root)

        initWebView()
    }


    private fun initWebView() {
        // WebViewClient allows you to handle
        // onPageFinished and override Url loading
        binding.webView.webViewClient =
            MyWebViewClient(this, binding.progressBar, binding.lottieAnimationView)

        resetWebView(binding.webView)

        // this will load the url of the website
        binding.webView.loadUrl(BuildConfig.webUrl)

        // this will enable the javascript settings, it can also allow xss vulnerabilities
        binding.webView.settings.javaScriptEnabled = false

        binding.webView.settings.loadWithOverviewMode = true
        binding.webView.settings.useWideViewPort = true

        // if you want to enable zoom feature
        binding.webView.settings.setSupportZoom(true)
    }

    // if you press Back button this code will work
    override fun onBackPressed() {
        // if your web view can go back it will go back
        if (binding.webView.canGoBack())
            binding.webView.goBack()
        // if your web view cannot go back
        // it will exit the application
        else
            super.onBackPressed()
    }
}

fun resetWebView(webView: WebView) {
    webView.clearCache(true)   // Clears the entire cache
    webView.clearHistory()     // Clears the browsing history
    webView.clearFormData()    // Clears any stored form data
}