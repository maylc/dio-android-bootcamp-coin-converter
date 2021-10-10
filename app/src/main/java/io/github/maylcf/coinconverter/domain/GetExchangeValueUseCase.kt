package io.github.maylcf.coinconverter.domain

import io.github.maylcf.coinconverter.core.UseCase
import io.github.maylcf.coinconverter.data.model.ExchangeResponseValue
import io.github.maylcf.coinconverter.data.repository.CoinRepository
import kotlinx.coroutines.flow.Flow

class GetExchangeValueUseCase(private val repository: CoinRepository) :
    UseCase<String, ExchangeResponseValue>() {

    override suspend fun execute(param: String): Flow<ExchangeResponseValue> {
        return repository.getExchangeValues(param)
    }
}