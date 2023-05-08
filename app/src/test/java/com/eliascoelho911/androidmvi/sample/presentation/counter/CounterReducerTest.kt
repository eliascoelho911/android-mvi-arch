package com.eliascoelho911.androidmvi.sample.presentation.counter

import com.eliascoelho911.androidmvi.sample.domain.entity.Counter
import com.eliascoelho911.androidmvi.sample.domain.repository.CounterRepository
import io.mockk.coEvery
import io.mockk.mockk
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runTest
import org.junit.Assert.*
import org.junit.Test

@OptIn(ExperimentalCoroutinesApi::class)
class CounterReducerTest {
    private val repository: CounterRepository = mockk(relaxed = true)

    @Test
    fun `Started Should change state to Loaded When repository returns success`() = runTest {
        // Given
        val initialState = CounterState()
        val reducer = CounterReducer(
            initialState = initialState,
            repository = repository
        )
        val expectedState = initialState.loaded(Counter(0))
        coEvery { repository.get() } returns Result.success(Counter(0))

        // When
        reducer.dispatch(CounterEvent.Started)

        // Then
        assertEquals(expectedState, reducer.state.value)
    }

    @Test
    fun `Started Should change state to Error When repository returns failure`() = runTest {
        // Given
        val initialState = CounterState()
        val reducer = CounterReducer(
            initialState = initialState,
            repository = repository
        )
        val expectedState = initialState.error("Error")
        coEvery { repository.get() } returns Result.failure(Throwable("Error"))

        // When
        reducer.dispatch(CounterEvent.Started)

        // Then
        assertEquals(expectedState, reducer.state.value)
    }

    @Test
    fun `Increment Should increment counter When repository returns success`() = runTest {
        // Given
        val initialState = CounterState().loaded(Counter(0))
        val reducer = CounterReducer(
            initialState = initialState,
            repository = repository
        )
        val expectedState = initialState.loaded(Counter(1))
        coEvery { repository.increment() } returns Result.success(Counter(1))

        // When
        reducer.dispatch(CounterEvent.Increment)

        // Then
        assertEquals(expectedState, reducer.state.value)
    }

    @Test
    fun `Increment Should change state to Error When repository returns failure`() = runTest {
        // Given
        val initialState = CounterState().loaded(Counter(0))
        val reducer = CounterReducer(
            initialState = initialState,
            repository = repository
        )
        val expectedState = initialState.error("Error")
        coEvery { repository.increment() } returns Result.failure(Throwable("Error"))

        // When
        reducer.dispatch(CounterEvent.Increment)

        // Then
        assertEquals(expectedState, reducer.state.value)
    }

    @Test
    fun `Decrement Should decrement counter When repository returns success`() = runTest {
        // Given
        val initialState = CounterState().loaded(Counter(0))
        val reducer = CounterReducer(
            initialState = initialState,
            repository = repository
        )
        val expectedState = initialState.loaded(Counter(-1))
        coEvery { repository.decrement() } returns Result.success(Counter(-1))

        // When
        reducer.dispatch(CounterEvent.Decrement)

        // Then
        assertEquals(expectedState, reducer.state.value)
    }

    @Test
    fun `Decrement Should change state to Error When repository returns failure`() = runTest {
        // Given
        val initialState = CounterState().loaded(Counter(0))
        val reducer = CounterReducer(
            initialState = initialState,
            repository = repository
        )
        val expectedState = initialState.error("Error")
        coEvery { repository.decrement() } returns Result.failure(Throwable("Error"))

        // When
        reducer.dispatch(CounterEvent.Decrement)

        // Then
        assertEquals(expectedState, reducer.state.value)
    }
}