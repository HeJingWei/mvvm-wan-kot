package com.example.mvvm_wan_kot.common.network

class ApiException(var code: Int, override var message: String) : RuntimeException()