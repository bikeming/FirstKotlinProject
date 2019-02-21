package com.fulan.mykotlin.mykotlinapp.network

import com.fulan.mykotlin.mykotlinapp.bean.HomeBean
import com.fulan.mykotlin.mykotlinapp.bean.ProjectBean
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path

/**
 *
 * @ClassName: com.fulan.mykotlin.mykotlinapp
 * @Description:
 * @author: fjm
 * @date: 2018/8/22 14:28
 * @Version:1.0
 */
interface HttpApi {
    @GET("/article/list/{page}/json")
    fun getHomeData(@Path("page") page: Int): Call<HomeBean>

    @GET("/project/list/1/json?cid=294")
    fun getProjectData(): Call<ProjectBean>

}