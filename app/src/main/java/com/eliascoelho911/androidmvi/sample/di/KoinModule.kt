package com.eliascoelho911.androidmvi.sample.di

import com.eliascoelho911.androidmvi.sample.data.repository.CounterRepositoryImpl
import com.eliascoelho911.androidmvi.sample.domain.repository.CounterRepository
import com.eliascoelho911.androidmvi.sample.presentation.counter.CounterReducer
import com.eliascoelho911.androidmvi.sample.presentation.counter.CounterViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val appModule = module {
    factory<CounterRepository> { CounterRepositoryImpl() }

    viewModel { CounterViewModel(
        reducer = CounterReducer(
            get()
        )
    ) }
}