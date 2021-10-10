package io.github.maylcf.coinconverter.data.repository

import io.github.maylcf.coinconverter.data.model.ExchangeResponseValue
import kotlinx.coroutines.flow.Flow

interface CoinRepository {

    suspend fun getExchangeValues(coins: String): Flow<ExchangeResponseValue>
}