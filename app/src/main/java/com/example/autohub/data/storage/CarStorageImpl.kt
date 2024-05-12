package com.example.autohub.data.storage

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.autohub.data.api.RetrofitCarInstance
import com.example.autohub.data.storage.model.CarDto
import com.example.autohub.data.storage.model.RecordsDto
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore

class CarStorageImpl : CarStorage {

    override suspend fun getCars(): RecordsDto {
        return RetrofitCarInstance.api.getCars().body() ?: RecordsDto(list = emptyList())
    }

    override suspend fun searchCarsByMake(make: String): RecordsDto {
        return RetrofitCarInstance.api.searchCarsByMake(make).body()
            ?: RecordsDto(list = emptyList())
    }

    override fun getFavourites(): LiveData<List<CarDto>> {
        val favouritesListLiveData = MutableLiveData<List<CarDto>>()
        var favouriteList: List<CarDto>
        FirebaseFirestore.getInstance().collection("users")
            .document(FirebaseAuth.getInstance().currentUser!!.uid)
            .collection("favourite")
            .addSnapshotListener { value, error ->
                if (error != null) {
                    return@addSnapshotListener
                }
                favouriteList = value!!.documents.map { it.toObject(CarDto::class.java)!! }
                favouritesListLiveData.value = favouriteList
            }
        return favouritesListLiveData
    }
}