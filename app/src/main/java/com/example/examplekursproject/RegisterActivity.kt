package com.example.examplekursproject

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.examplekursproject.interfaces.LoginForm
import com.example.examplekursproject.interfaces.RegisterForm
import com.example.examplekursproject.ui.theme.ExampleKursProjectTheme

class RegisterActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ExampleKursProjectTheme {
                RegisterForm()
            }
        }
    }
}