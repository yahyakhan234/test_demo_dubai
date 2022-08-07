package com.example.testdemodubai.model.api

import com.example.testdemodubai.model.data.BaseClass
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface ApiService {
    @GET("svc/mostpopular/v2/mostviewed/{section}/{period}.json")
    suspend fun getMostPopular(@Path("section") section:String,@Path("period") period:String,@Query("api-key") apikey:String):BaseClass
}