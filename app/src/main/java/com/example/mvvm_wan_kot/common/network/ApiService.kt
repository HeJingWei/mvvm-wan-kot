package com.example.mvvm_wan_kot.common.network

import com.example.mvvm_wan_kot.model.bean.Article
import com.example.mvvm_wan_kot.model.bean.Banner
import com.example.mvvm_wan_kot.model.bean.Pagination
import com.example.mvvm_wan_kot.model.bean.User
import retrofit2.http.*

interface ApiService {

    companion object {
        const val BASE_URL = "https://www.wanandroid.com"
    }

    //登录
    @FormUrlEncoded
    @POST("/user/login")
    suspend fun login(
        @Field("username") username: String,
        @Field("password") password: String
    ): ApiResult<User>

    //注册
    @FormUrlEncoded
    @POST("/user/register")
    suspend fun register(
        @Field("username") username: String,
        @Field("password") password: String,
        @Field("repassword") rePassword: String
    ): ApiResult<Any>

    //获取置顶文章
    @GET("/article/top/json")
    suspend fun getTopArticleList(): ApiResult<List<Article>>

    //获取banner
    @GET("/banner/json")
    suspend fun getBanner(): ApiResult<List<Banner>>

    //获取广场数据
    @GET("/user_article/list/{page}/json")
    suspend fun getUserArticleList(@Path("page") page: Int): ApiResult<Pagination<Article>>
}