package com.example.mvvm_wan_kot.ui.common.webview

import android.graphics.Bitmap
import android.view.View
import androidx.databinding.library.baseAdapters.BR
import com.example.mvvm_wan_kot.R
import com.example.mvvm_wan_kot.common.base.BaseVMActivity
import com.example.mvvm_wan_kot.common.utils.ActivityManager
import com.example.mvvm_wan_kot.model.bean.Article
import com.tencent.smtt.sdk.WebChromeClient
import com.tencent.smtt.sdk.WebView
import com.tencent.smtt.sdk.WebViewClient
import kotlinx.android.synthetic.main.activity_web_detail.*
import kotlinx.android.synthetic.main.layout_toolbar.*
import org.koin.androidx.viewmodel.ext.android.getViewModel

class WebViewActivity : BaseVMActivity<WebViewViewModel>() {
    override fun getLayoutResId() = R.layout.activity_web_detail

    companion object {
        const val ARTICLE = "article"
        fun goDetailActivity(article: Article) {
            val map = HashMap<String, Any>()
            map[ARTICLE] = article
            ActivityManager.start(WebViewActivity::class.java, map)
        }
    }

    override fun initView() {
        mBinding.setVariable(BR.vm, mViewModel)

        toolbar.run {
            setTitle(R.string.loading)
            setNavigationOnClickListener { onBackPressed() }
        }
    }

    override fun initData() {
        intent?.extras?.getParcelable<Article>(ARTICLE)?.let {
            mViewModel.addReadHistory(it)
            webView.loadUrl(it.link)
        }
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

    override fun initVM(): WebViewViewModel = getViewModel()

    override fun startObserve() {

    }
}