package com.example.practicabottomnav

import android.content.Intent
import android.graphics.Bitmap
import android.graphics.Color
import android.net.Uri
import android.os.Bundle
import android.view.KeyEvent
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.webkit.WebChromeClient
import android.webkit.WebResourceRequest
import android.webkit.WebView
import android.webkit.WebViewClient
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import com.example.practicabottomnav.databinding.FragmentWebBinding
import kotlin.properties.Delegates

class WebFragment : Fragment() {
    lateinit var webView: WebView
    lateinit var binding: FragmentWebBinding
    var atras by Delegates.notNull<Int>()
    var alante by Delegates.notNull<Int>()


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        binding= FragmentWebBinding.inflate(inflater,container,false)
        return binding.root


    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
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

        binding.retroceder.setOnLongClickListener{
            if(webView.canGoBack()){
                webView.goBackOrForward(atras)
            }
            true
        }
        binding.avanzar.setOnLongClickListener{
            if(webView.canGoForward()){
                webView.goBackOrForward(alante-1)
            }
            true
        }
    }

    inner class MyWebViewClient: WebViewClient(){
        override fun shouldOverrideUrlLoading(view: WebView?, request: WebResourceRequest?): Boolean {
            val uri = request?.url
            val host = uri?.host.toString()
            if (host.contains("wikipedia.org")) {
                return false
            } else {
                val intent = Intent(Intent.ACTION_VIEW, uri)
                startActivity(intent)
                return true}
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
            atras = -webView.copyBackForwardList().currentIndex
            alante = webView.copyBackForwardList().size - webView.copyBackForwardList().currentIndex
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