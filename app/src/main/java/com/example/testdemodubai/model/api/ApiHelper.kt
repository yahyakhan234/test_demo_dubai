package com.example.testdemodubai.model.api

import com.example.testdemodubai.model.data.BaseClass

interface ApiHelper {
    suspend fun getMostPopular(section: String, period: String, apikey: String) : BaseClass
}