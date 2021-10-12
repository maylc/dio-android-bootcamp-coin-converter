package io.github.maylcf.coinconverter.data.repository

import com.google.gson.Gson
import io.github.maylcf.coinconverter.core.exceptions.RemoteException
import io.github.maylcf.coinconverter.data.model.ErrorResponse
import io.github.maylcf.coinconverter.data.service.AwesomeService
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException

class CoinRepositoryImpl(private val service: AwesomeService) : CoinRepository {

    override suspend fun getExchangeValues(coins: String) = flow {
        try {
            val exchangeValues = service.exchangeValues(coins)
            val exchange = exchangeValues.values.first()
            emit(exchange)
        } catch (e: HttpException) {
            val json = e.response()?.errorBody()?.string()
            val errorResponse = Gson().fromJson(json, ErrorResponse::class.java)
            throw RemoteException(errorResponse.message)
        }
    }
}