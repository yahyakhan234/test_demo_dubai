package com.example.testdemodubai.model.api


class ApiHelperImpl(private val apiService: ApiService) : ApiHelper {
    override suspend fun getMostPopular(section: String, period: String, apikey: String) = apiService.getMostPopular(section,period,apikey)
}