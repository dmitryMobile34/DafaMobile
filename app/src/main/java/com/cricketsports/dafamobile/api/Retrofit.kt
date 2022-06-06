package com.cricketsports.dafamobile.api

import com.cricketsports.dafamobile.datamodel.DataModel
import retrofit2.Call
import retrofit2.http.GET

interface RetrofitInterface {
    @GET("v1/matches?apikey=4083c19b-3bd7-442a-84b8-1d04ecec59f4&offset=0")
    fun getNews(): Call<DataModel>
}