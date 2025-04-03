package com.pivnoydevelopment.onlineschool.common.data.network

import com.pivnoydevelopment.onlineschool.common.data.network.dto.CoursesResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface GoogleDiskApi {
    @GET("u/0/uc")
    suspend fun getCourses(
        @Query("id") id: String,
        @Query("export") export: String = "download"
    ): CoursesResponse
}