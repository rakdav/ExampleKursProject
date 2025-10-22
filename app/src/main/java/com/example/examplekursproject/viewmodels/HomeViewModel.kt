package com.example.examplekursproject.viewmodels

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import com.example.examplekursproject.models.DataState
import com.example.examplekursproject.models.Food
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import java.util.Locale

class HomeViewModel: ViewModel() {
    val response: MutableState<DataState> = mutableStateOf(DataState.Empty)
    init {
        fetchDataFromFirebase()
    }
    private fun fetchDataFromFirebase(){
        val tempList=mutableListOf<Food>()
        response.value= DataState.Loading
        FirebaseDatabase.getInstance().getReference("Category").
        addListenerForSingleValueEvent(object: ValueEventListener {
                override fun onDataChange(snapshot: DataSnapshot) {
                    for (dataSnap in snapshot.children){
                        val foodItem=dataSnap.getValue(Food::class.java)
                        if(foodItem!=null) tempList.add(foodItem)
                    }
                    response.value= DataState.Success(tempList)
                }
                override fun onCancelled(error: DatabaseError) {
                    response.value= DataState.Failure(error.message)
                }
            }
        )
    }
}