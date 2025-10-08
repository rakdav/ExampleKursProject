package com.example.examplekursproject.interfaces

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.sp
import androidx.lifecycle.ViewModel
import com.example.examplekursproject.models.DataState
import com.example.examplekursproject.viewmodels.HomeViewModel

@Composable
fun Home(viewModel: HomeViewModel){
    when(val result=viewModel.response.value){
        is DataState.Loading->{
            Box(modifier = Modifier.fillMaxSize(),
                contentAlignment = Alignment.Center){
                CircularProgressIndicator()
            }
        }
        is DataState.Success->{
            ShowLazyList(result.data)
        }
        is DataState.Failure->{
            Box(modifier = Modifier.fillMaxSize(),
                contentAlignment = Alignment.Center){
                Text(text = result.message,
                    fontSize = MaterialTheme.typography.bodyMedium.fontSize )
            }
        }
        else -> {
            Box(modifier = Modifier.fillMaxSize(),
                contentAlignment = Alignment.Center){
                Text(text = "Error FetchingData",
                    fontSize = MaterialTheme.typography.bodyMedium.fontSize)
            }
        }
    }
}