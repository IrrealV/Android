package com.estech.webviewexample

import android.graphics.Bitmap
import android.os.Bundle
import android.view.KeyEvent
import android.view.View
import android.webkit.*
import androidx.appcompat.app.AppCompatActivity
import com.estech.webviewexample.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    lateinit var webView: WebView
    lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        webView = binding.webview


//        val myHtml = "<html><body><h1>Welcome to Tutlane</h1>" +
//                "<h2>Welcome to Tutlane</h2><h3>Welcome to Tutlane</h3>" +
//                "<p>It's a Static Web HTML Content.</p>" +
//                "</body></html>"
//        webView.loadData(myHtml, "text/html", "UTF-8")

        val ajustes = webView.settings
        ajustes.displayZoomControls = true
        webView.settings.builtInZoomControls = true
        ajustes.javaScriptEnabled = true
        webView.webViewClient = MywebViewClient()
        webView.webChromeClient = MyChrome()
        webView.loadUrl("https://escuelaestech.es")

        binding.button.setOnClickListener{
            webView.goForward()
        }


    }
    inner class MywebViewClient: WebViewClient(){
        override fun shouldOverrideUrlLoading(view: WebView?, request: WebResourceRequest?): Boolean {
            val url = request?.url.toString()
            if (url.contains(("escuelaestech.es"))){
                return false
            }
            else{
                return super.shouldOverrideUrlLoading(view, request)
            }
        }

        override fun onPageStarted(view: WebView?, url: String?, favicon: Bitmap?) {
            super.onPageStarted(view, url, favicon)
            binding.progressBar.visibility = View.VISIBLE
        }

        override fun onPageFinished(view: WebView?, url: String?) {
            super.onPageFinished(view, url)
            binding.progressBar.visibility = View.GONE
        }
    }

    inner class MyChrome : WebChromeClient(){
        override fun onProgressChanged(view: WebView?, newProgress: Int) {
            super.onProgressChanged(view, newProgress)
            binding.progressBar.progress = newProgress
        }

        override fun onReceivedIcon(view: WebView?, icon: Bitmap?) {
            super.onReceivedIcon(view, icon)
            binding.imageView.setImageBitmap(icon)
        }
    }

    override fun onBackPressed() {
        webView.goBack()
        //super.onBackPressed()
    }
}