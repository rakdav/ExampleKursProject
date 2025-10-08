package com.example.examplekursproject.models

sealed class DataState {
    class Success(val data: MutableList<Food>): DataState()
    class Failure(val message: String): DataState()
    object Loading: DataState()
    object Empty: DataState()
}