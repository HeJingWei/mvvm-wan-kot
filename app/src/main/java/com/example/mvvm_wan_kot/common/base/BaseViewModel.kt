package com.example.mvvm_wan_kot.common.base

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.mvvm_wan_kot.R
import com.example.mvvm_wan_kot.common.App
import com.example.mvvm_wan_kot.common.ext.showToast
import com.example.mvvm_wan_kot.common.network.ApiException
import com.google.gson.JsonParseException
import kotlinx.coroutines.*
import java.net.ConnectException
import java.net.SocketTimeoutException

typealias Block<T> = suspend () -> T
typealias Error = suspend (e: Exception) -> Unit
typealias Cancel = suspend (e: Exception) -> Unit

open class BaseViewModel : ViewModel(){
    /**
     * 创建并执行协程
     * @param block 协程中执行
     * @param error 错误时执行
     * @return Job
     */
    fun launchOnUI(block: Block<Unit>, error: Error? = null, cancel: Cancel? = null): Job {
        return viewModelScope.launch {
            try {
                block.invoke()
            } catch (e: Exception) {
                when (e) {
                    is CancellationException -> {
                        cancel?.invoke(e)
                    }
                    else -> {
                        onError(e)
                        error?.invoke(e)
                    }
                }
            }
        }
    }

    suspend fun <T> launchOnIO(block: suspend CoroutineScope.() -> T) {
        withContext(Dispatchers.IO) {
            block
        }
    }

    /**
     * 统一处理错误
     * @param e 异常
     */
    private fun onError(e: Exception) {
        when (e) {
            is ApiException -> {
                when (e.code) {
                    -1 -> {
                        // 其他api错误
                        App.instance.showToast(e.message)
                    }
                    else -> {
                        // 其他错误
                        App.instance.showToast(e.message)
                    }
                }
            }
            is ConnectException -> {
                // 连接失败
                App.instance.showToast(App.instance.getString(R.string.network_connection_failed))
            }
            is SocketTimeoutException -> {
                // 请求超时
                App.instance.showToast(App.instance.getString(R.string.network_request_timeout))
            }
            is JsonParseException -> {
                // 数据解析错误
                App.instance.showToast(App.instance.getString(R.string.api_data_parse_error))
            }
            else -> {
                // 其他错误
                e.message?.let { App.instance.showToast(it) }
            }
        }
    }

    fun isInputValid(vararg param : String):Boolean{
        param.forEach {
            if (it.isBlank()) return false
        }
        return true
    }
}