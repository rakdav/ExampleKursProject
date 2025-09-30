package com.example.examplekursproject

import androidx.activity.compose.setContent
import android.os.Bundle
import androidx.activity.ComponentActivity
import com.example.examplekursproject.interfaces.LoginForm
import com.example.examplekursproject.ui.theme.ExampleKursProjectTheme


class LoginActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ExampleKursProjectTheme {
                LoginForm()
            }
        }
    }
}