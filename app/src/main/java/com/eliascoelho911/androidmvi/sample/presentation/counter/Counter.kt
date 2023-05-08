package com.eliascoelho911.androidmvi.sample.presentation.counter

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Button
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.eliascoelho911.androidmvi.sample.R
import org.koin.androidx.compose.koinViewModel

@Composable
fun CounterScreen(viewModel: CounterViewModel = koinViewModel()) {
    val state by viewModel.state.collectAsState()

    CounterContent(
        state = state,
        onCounterIncremented = { viewModel.dispatch(CounterEvent.Increment) },
        onCounterDecremented = { viewModel.dispatch(CounterEvent.Decrement) }
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CounterContent(
    state: CounterState,
    onCounterIncremented: () -> Unit,
    onCounterDecremented: () -> Unit
) {
    Scaffold(
        topBar = { CounterAppBar() }
    ) { padding ->
        Box(
            modifier = Modifier
                .padding(padding)
                .fillMaxSize()
        ) {
            Column(
                modifier = Modifier.align(Alignment.Center),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                when (state.syncState) {
                    is CounterSyncState.Loading -> {
                        CircularProgressIndicator(modifier = Modifier.padding(bottom = 16.dp))
                    }

                    is CounterSyncState.Error -> {
                        Text(text = state.syncState.message)
                    }

                    CounterSyncState.Loaded -> {
                        Text(text = state.counter.value.toString())
                        Spacer(modifier = Modifier.size(16.dp))

                        Row {
                            Button(onClick = onCounterDecremented) {
                                Text(text = "Decrement")
                            }
                            Spacer(modifier = Modifier.size(8.dp))
                            Button(onClick = onCounterIncremented) {
                                Text(text = "Increment")
                            }
                        }
                    }
                }
            }
        }

    }
}

@Composable
@OptIn(ExperimentalMaterial3Api::class)
private fun CounterAppBar() {
    TopAppBar(title = { Text(text = stringResource(id = R.string.counter_screen_title)) })
}