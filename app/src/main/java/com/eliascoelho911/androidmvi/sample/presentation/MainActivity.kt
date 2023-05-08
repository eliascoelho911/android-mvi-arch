package com.eliascoelho911.androidmvi.sample.presentation

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import com.eliascoelho911.androidmvi.sample.presentation.counter.CounterScreen
import com.eliascoelho911.androidmvi.sample.presentation.theme.SampleTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            SampleTheme {
                CounterScreen()
            }
        }
    }
}