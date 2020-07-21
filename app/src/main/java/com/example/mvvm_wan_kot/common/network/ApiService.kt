package com.example.mvvm_wan_kot.common.network

import com.example.mvvm_wan_kot.model.bean.*
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

    //获取个人积分
    @GET("lg/coin/userinfo/json")
    suspend fun getUserIntegral(): ApiResult<IntegralBean>

    //获取个人积分获取列表
    @GET("lg/coin/list/{page}/json")
    suspend fun getIntegralList(@Path("page") page: Int): ApiResult<Pagination<IntegralItem>>

    //获取积分排行榜
    @GET("coin/rank/{page}/json")
    suspend fun getIntegralRankList(@Path("page") page: Int): ApiResult<Pagination<IntegralBean>>

    //获取个人收藏
    @GET("lg/collect/list/{page}/json")
    suspend fun getCollect(@Path("page") page: Int): ApiResult<Pagination<Article>>

    //取消收藏
    @POST("lg/uncollect_originId/{id}/json")
    suspend fun collectCancel(@Path("id") id: Int): ApiResult<Any?>
}