package com.rktuhinbd.webviewapp

import android.content.Context
import android.content.Intent
import android.view.View
import android.webkit.WebResourceError
import android.webkit.WebResourceRequest
import android.webkit.WebView
import android.webkit.WebViewClient
import android.widget.ProgressBar
import com.airbnb.lottie.LottieAnimationView

class MyWebViewClient(
    private val context: Context,
    private val progressBar: ProgressBar,
    private val errorAnim: LottieAnimationView
) : WebViewClient() {
    @Deprecated("Deprecated in Java")
    override fun shouldOverrideUrlLoading(view: WebView?, url: String?): Boolean {
        if (url != null) {
            if (url.contains("intent://")) {
                val intent = Intent.parseUri(url, Intent.URI_INTENT_SCHEME)
                intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
                context.startActivity(intent)
                return true
            }
        }
        return super.shouldOverrideUrlLoading(view, url)
    }

    override fun onPageFinished(view: WebView?, url: String?) {
        super.onPageFinished(view, url)
        progressBar.visibility = View.GONE
    }

    override fun onReceivedError(
        view: WebView?,
        request: WebResourceRequest?,
        error: WebResourceError?
    ) {
        if (error?.errorCode == ERROR_HOST_LOOKUP) {
            errorAnim.visibility = View.VISIBLE
            progressBar.visibility = View.GONE
            view?.loadUrl("about:blank")
        } else {
            errorAnim.visibility = View.GONE
        }

        super.onReceivedError(view, request, error)
    }

}
