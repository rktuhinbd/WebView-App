package com.rktuhinbd.webviewapp

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.rktuhinbd.webviewapp.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)

        setContentView(binding.root)


        initWebView()
    }


    private fun initWebView() {
        // WebViewClient allows you to handle
        // onPageFinished and override Url loading
        binding.webView.webViewClient =
            MyWebViewClient(this, binding.progressBar, binding.lottieAnimationView)

        // this will load the url of the website
        binding.webView.loadUrl(BuildConfig.webUrl)

        // this will enable the javascript settings, it can also allow xss vulnerabilities
        binding.webView.settings.javaScriptEnabled = true

        // if you want to enable zoom feature
        binding.webView.settings.setSupportZoom(true)
    }

    // if you press Back button this code will work
    override fun onBackPressed() {
        // if your webview can go back it will go back
        if (binding.webView.canGoBack())
            binding.webView.goBack()
        // if your webview cannot go back
        // it will exit the application
        else
            super.onBackPressed()
    }
}