package io.github.maylcf.coinconverter.domain.di

import io.github.maylcf.coinconverter.domain.GetExchangeValueUseCase
import org.koin.core.context.loadKoinModules
import org.koin.core.module.Module
import org.koin.dsl.module

object DomainModule {

    fun load() {
        loadKoinModules(useCaseModules())
    }

    private fun useCaseModules(): Module {
        return module {
            factory { GetExchangeValueUseCase(get()) }
        }
    }
}