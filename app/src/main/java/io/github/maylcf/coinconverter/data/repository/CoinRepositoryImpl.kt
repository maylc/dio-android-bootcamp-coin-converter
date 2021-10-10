package io.github.maylcf.coinconverter.data.repository

import io.github.maylcf.coinconverter.data.service.AwesomeService
import kotlinx.coroutines.flow.flow

class CoinRepositoryImpl(private val service: AwesomeService) : CoinRepository {

    override suspend fun getExchangeValues(coins: String) = flow {
        val exchangeValues = service.exchangeValues(coins)
        val exchange = exchangeValues.values.first()
        emit(exchange)
    }
}