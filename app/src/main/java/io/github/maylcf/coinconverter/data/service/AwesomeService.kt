package io.github.maylcf.coinconverter.data.service

import io.github.maylcf.coinconverter.data.model.ExchangeResponse
import retrofit2.http.GET
import retrofit2.http.Path

interface AwesomeService {

    @GET("/json/last/{coins}")
    suspend fun exchangeValues(@Path("coins") coins: String): ExchangeResponse
}