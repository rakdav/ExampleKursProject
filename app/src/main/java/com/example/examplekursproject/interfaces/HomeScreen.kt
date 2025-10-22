package com.example.examplekursproject.interfaces

import android.os.Build.VERSION.SDK_INT
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.ViewModel
import coil.ImageLoader
import coil.compose.rememberAsyncImagePainter
import coil.decode.GifDecoder
import coil.decode.ImageDecoderDecoder
import coil.request.ImageRequest
import com.example.examplekursproject.models.DataState
import com.example.examplekursproject.models.Food
import com.example.examplekursproject.viewmodels.HomeViewModel
import kotlinx.serialization.builtins.serializer

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
@Composable
fun ShowLazyList(foods: MutableList<Food>){
    LazyColumn {
        items(foods){
            food->CardItem(food)
        }
    }
}

@Composable
fun CardItem(food: Food){
    val painter = rememberAsyncImagePainter(
        model = ImageRequest.Builder(LocalContext.current)
            .data(food.Image)
            .build(),
        imageLoader = ImageLoader.Builder(LocalContext.current)
            .components {
                if (SDK_INT >= 28) {
                    add(ImageDecoderDecoder.Factory())
                } else {
                    add(GifDecoder.Factory())
                }
            }
            .build()
    )
    Card(modifier = Modifier.fillMaxSize().height(200.dp).padding(10.dp),
        colors = CardDefaults.cardColors(contentColor = Color.Black,
            containerColor = Color.White)) {

        Image(
            painter = painter,
            modifier = Modifier.fillMaxWidth().height(150.dp),
            contentDescription = "My content description",
            contentScale = ContentScale.Fit
        )

        Text(
            text = food.Name!!,
            fontSize = MaterialTheme.typography.labelLarge.fontSize,
            modifier = Modifier
                .fillMaxWidth().
            align(alignment = Alignment.CenterHorizontally),
            textAlign = TextAlign.Center,
            color = Color.Blue
        )
    }
}

