package com.example.autohub.data.storage

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.autohub.data.storage.model.CarDto
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore

class CarDetailsStorageImpl : CarDetailsStorage {

    private val dataBase = FirebaseFirestore.getInstance()

    override fun checkIfCarIsFavoutrite(carDto: CarDto): LiveData<Boolean> {
        val isFavouriteLiveData = MutableLiveData<Boolean>()
        dataBase.collection("users")
            .document(FirebaseAuth.getInstance().currentUser!!.uid)
            .collection("favourite")
            .addSnapshotListener { value, error ->
                if (error != null) {
                    return@addSnapshotListener
                }
                value?.documents?.map { it.toObject(CarDto::class.java)!! }?.let {
                    isFavouriteLiveData.value = it.contains(carDto)
                }
            }
        return isFavouriteLiveData
    }
}