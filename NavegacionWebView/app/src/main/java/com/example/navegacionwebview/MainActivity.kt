package com.example.navegacionwebview

import android.content.Intent
import android.graphics.Bitmap
import android.graphics.Color
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.webkit.WebChromeClient
import android.webkit.WebResourceRequest
import android.webkit.WebView
import android.webkit.WebViewClient
import com.example.navegacionwebview.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    lateinit var webView: WebView
    lateinit var binding: ActivityMainBinding


    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        webView = binding.webView

        val ajustes = webView.settings
        ajustes.displayZoomControls = true
        ajustes.builtInZoomControls = true
        ajustes.javaScriptEnabled = true
        webView.webViewClient = MyWebViewClient()
        webView.webChromeClient = MyChrome()
        webView.loadUrl("https://www.wikipedia.org")

        binding.retroceder.setOnClickListener {
            if (webView.canGoBack()){
                webView.goBack()
            }
        }
        binding.avanzar.setOnClickListener {
            if(webView.canGoForward()){
                webView.goForward()
            }
        }



    }
    inner class MyWebViewClient: WebViewClient(){
        override fun shouldOverrideUrlLoading(view: WebView?, request: WebResourceRequest?): Boolean {
            val url = request?.url.toString()
            if (url.contains(("wikipedia.org"))){
                return false
            }
           else{
               return super.shouldOverrideUrlLoading(view, request)
            }
        }
        override fun onPageStarted(view: WebView?, url: String?, favicon: Bitmap?) {
            super.onPageStarted(view, url, favicon)
            binding.progreso.visibility = View.VISIBLE
            binding.reload.setImageResource(R.drawable.cancelar)
        }
        override fun onPageFinished(view: WebView?, url: String?) {
            super.onPageFinished(view, url)
            binding.progreso.visibility = View.GONE
            binding.reload.setImageResource(R.drawable.reload_page)
            binding.oninbrow.setOnClickListener {
                val opinbrow =  Intent(Intent.ACTION_VIEW)
                opinbrow.data = Uri.parse(url)
                startActivity(opinbrow)
            }
            binding.reload.setOnClickListener {
                webView.loadUrl(url.toString())
            }

            if(webView.canGoBack()){
                binding.retroceder.setColorFilter(Color.argb(255, 0 , 0 , 0))
            }
            else{
                binding.retroceder.setColorFilter(Color.argb(255, 63, 63, 63))
            }

            if(webView.canGoForward()){
                binding.avanzar.setColorFilter(Color.argb(255,0,0,0))
            }
            else{
                binding.avanzar.setColorFilter(Color.argb(255,126,63,63))
            }

        }

    }
    inner class MyChrome: WebChromeClient(){
        override fun onProgressChanged(view: WebView?, newProgress: Int) {
            super.onProgressChanged(view, newProgress)
            binding.progreso.progress = newProgress
            binding.reload.setOnClickListener {
                webView.stopLoading()
            }
        }
        override fun onReceivedTitle(view: WebView?, title: String?) {
            super.onReceivedTitle(view, title)
            binding.titulo.text = title
        }
    }





}