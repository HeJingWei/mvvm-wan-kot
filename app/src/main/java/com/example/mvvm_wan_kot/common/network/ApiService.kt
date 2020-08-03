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

    //获取首页文章
    @GET("article/list/{page}/json")
    suspend fun getHomeArticle(@Path("page") page: Int): ApiResult<Pagination<Article>>

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

    //收藏
    @POST("lg/collect/{id}/json")
    suspend fun collect(@Path("id") id: Int): ApiResult<Any?>

    //取消收藏
    @POST("lg/uncollect_originId/{id}/json")
    suspend fun collectCancel(@Path("id") id: Int): ApiResult<Any?>

    //获取公众号列表
    @GET("wxarticle/chapters/json")
    suspend fun getProjectChapters(): ApiResult<List<ProjectChapter>>

    //获取公众号下的文章
    @GET("wxarticle/list/{id}/{page}/json")
    suspend fun getProjectList(
        @Path("id") id: Int,
        @Path("page") page: Int
    ): ApiResult<Pagination<Project>>

    //获取项目分类
    @GET("project/tree/json")
    suspend fun getProjectTree(): ApiResult<List<ProjectChapter>>

    //根据分类获取项目列表
    @GET("project/list/{page}/json")
    suspend fun getProjectByAuthor(
        @Path("page") page: Int,
        @Query("cid") cid: Int
    ): ApiResult<Pagination<Article>>

    //获取导航数据
    @GET("navi/json")
    suspend fun getNavigation(): ApiResult<List<Navigation>>

    //获取体系数据
    @GET("tree/json")
    suspend fun getSysTree(): ApiResult<List<SystemBean>>

    //体系下的文章
    @GET("article/list/{page}/json")
    suspend fun getSystemList(
        @Path("page") page: Int,
        @Query("cid") cid: Int
    ): ApiResult<Pagination<Article>>

    //获取搜索热词
    @GET("hotkey/json")
    suspend fun getHotKey():
            ApiResult<List<HotKey>>

    //搜索 注意：支持多个关键词，用空格隔开
    @FormUrlEncoded
    @POST("article/query/{page}/json")
    suspend fun queryArticleListByKey(
        @Path("page") page: Int,
        @Field("k") key: String
    ): ApiResult<Pagination<Article>>
}