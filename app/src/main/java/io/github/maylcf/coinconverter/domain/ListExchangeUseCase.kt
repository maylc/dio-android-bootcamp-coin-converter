package io.github.maylcf.coinconverter.domain

import io.github.maylcf.coinconverter.core.UseCase
import io.github.maylcf.coinconverter.data.model.ExchangeResponseValue
import io.github.maylcf.coinconverter.data.repository.CoinRepository
import kotlinx.coroutines.flow.Flow

class ListExchangeUseCase(
    private val repository: CoinRepository
) : UseCase.NoParam<List<ExchangeResponseValue>>() {

    override suspend fun execute(): Flow<List<ExchangeResponseValue>> {
        return repository.list()
    }
}