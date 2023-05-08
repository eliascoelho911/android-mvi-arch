package com.eliascoelho911.androidmvi.sample.presentation.counter

import io.mockk.coVerify
import io.mockk.mockk
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.UnconfinedTestDispatcher
import kotlinx.coroutines.test.setMain
import org.junit.Before
import org.junit.Test

@OptIn(ExperimentalCoroutinesApi::class)
class CounterViewModelTest {
    private val reducer: CounterReducer = mockk(relaxed = true)

    private lateinit var viewModel: CounterViewModel

    @Before
    fun setup() {
        Dispatchers.setMain(UnconfinedTestDispatcher())
    }

    @Test
    fun `init Should dispatch Started event`() {
        // When
        createViewModel()

        // Then
        coVerify { reducer.dispatch(CounterEvent.Started) }
    }

    private fun createViewModel() {
        viewModel = CounterViewModel(
            reducer = reducer
        )
    }
}