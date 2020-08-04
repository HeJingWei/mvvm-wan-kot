package com.example.mvvm_wan_kot.ui.common.webview

import com.example.mvvm_wan_kot.common.base.BaseViewModel
import com.example.mvvm_wan_kot.model.bean.Article

class WebViewViewModel(private val webViewRepository: WebViewRepository) : BaseViewModel() {

    fun addReadHistory(article: Article) {
        launchOnUI(block =
        {
            webViewRepository.addReadHistory(article)
        }
        )
    }
}