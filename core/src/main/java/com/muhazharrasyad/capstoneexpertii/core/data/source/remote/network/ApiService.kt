package com.muhazharrasyad.capstoneexpertii.core.data.source.remote.network

import com.muhazharrasyad.capstoneexpertii.core.data.source.remote.response.ListSportResponse
import retrofit2.http.GET

interface ApiService {
    @GET("list")
    suspend fun getList(): ListSportResponse
}
