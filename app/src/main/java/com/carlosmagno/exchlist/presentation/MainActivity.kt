package com.carlosmagno.exchlist.presentation

import MainApp
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import com.carlosmagno.exchlist.presentation.ui.theme.ExchListTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ExchListTheme {
                MainApp()
            }
        }
    }
}