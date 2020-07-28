package com.example.mvvm_wan_kot.ui.common

import android.graphics.Bitmap
import android.view.View

import com.example.mvvm_wan_kot.R
import com.example.mvvm_wan_kot.common.base.BaseActivity
import com.example.mvvm_wan_kot.common.utils.ActivityManager
import com.tencent.smtt.sdk.WebChromeClient
import com.tencent.smtt.sdk.WebView
import com.tencent.smtt.sdk.WebViewClient
import kotlinx.android.synthetic.main.activity_web_detail.*
import kotlinx.android.synthetic.main.layout_toolbar.*

class WebViewActivity : BaseActivity() {
    override fun getLayoutResId() = R.layout.activity_web_detail


    companion object {
        const val URL = "url"
        fun goDetailActivity(link: String) {
            val map = HashMap<String, String>()
            map[URL] = link
            ActivityManager.start(WebViewActivity::class.java, map)
        }
    }

    override fun initView() {
        toolbar.run {
            setTitle(R.string.loading)
            setNavigationOnClickListener { onBackPressed() }
        }
        intent?.extras?.getString(URL)?.let {
            webView.loadUrl(it)
        }
    }

    override fun initData() {
        progressBar.progressDrawable = this.resources
            .getDrawable(R.drawable.color_progressbar)
        webView.run {
            webViewClient = object : WebViewClient() {
                override fun onPageStarted(view: WebView?, url: String?, favicon: Bitmap?) {
                    super.onPageStarted(view, url, favicon)
                    progressBar.visibility = View.VISIBLE
                }

                override fun onPageFinished(view: WebView?, url: String?) {
                    super.onPageFinished(view, url)
                    progressBar.visibility = View.GONE
                }
            }

            webChromeClient = object : WebChromeClient() {
                override fun onProgressChanged(view: WebView?, newProgress: Int) {
                    super.onProgressChanged(view, newProgress)
                    progressBar.progress = newProgress
                }

                override fun onReceivedTitle(view: WebView?, title: String?) {
                    super.onReceivedTitle(view, title)
                    title?.let {
                        toolbar.setTitle(title)
                    }
                }
            }
        }
    }
}