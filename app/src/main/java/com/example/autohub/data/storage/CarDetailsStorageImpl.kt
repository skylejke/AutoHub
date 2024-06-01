package com.example.autohub.data.storage

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.autohub.data.storage.model.CarDto
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore

class CarDetailsStorageImpl : CarDetailsStorage {

    private val dataBase = FirebaseFirestore.getInstance()
    override fun getFavourites(): LiveData<List<CarDto>> {
        val favouritesListLiveData = MutableLiveData<List<CarDto>>()
        val currentUser = FirebaseAuth.getInstance().currentUser

        if (currentUser == null) {
            favouritesListLiveData.value = emptyList()
            return favouritesListLiveData
        }

        dataBase.collection("users")
            .document(currentUser.uid)
            .collection("favourite")
            .addSnapshotListener { value, error ->
                if (error != null) {
                    return@addSnapshotListener
                }
                if (value != null) {
                    val favouriteList = value.documents.mapNotNull { it.toObject(CarDto::class.java) }
                    favouritesListLiveData.value = favouriteList
                } else {
                    favouritesListLiveData.value = emptyList()
                }
            }
        return favouritesListLiveData
    }

    override fun checkIfCarIsFavoutrite(carDto: CarDto): LiveData<Boolean> {
        val isFavouriteLiveData = MutableLiveData<Boolean>()
        val currentUser = FirebaseAuth.getInstance().currentUser

        if (currentUser == null) {
            isFavouriteLiveData.value = false
            return isFavouriteLiveData
        }

        dataBase.collection("users")
            .document(currentUser.uid)
            .collection("favourite")
            .addSnapshotListener { value, error ->
                if (error != null) {
                    return@addSnapshotListener
                }
                value?.documents?.map { it.toObject(CarDto::class.java)!! }?.let {
                    isFavouriteLiveData.value = it.contains(carDto)
                } ?: run {
                    isFavouriteLiveData.value = false
                }
            }
        return isFavouriteLiveData
    }

    override fun addToFavourite(id: String, carMap: HashMap<String, Any>) {
        val currentUser = FirebaseAuth.getInstance().currentUser

        if (currentUser != null) {
            dataBase.collection("users").document(currentUser.uid)
                .collection("favourite").document(id).set(carMap)
        }
    }

    override fun deleteFromFavourite(id: String) {
        val currentUser = FirebaseAuth.getInstance().currentUser

        if (currentUser != null) {
            dataBase.collection("users").document(currentUser.uid)
                .collection("favourite").document(id).delete()
        }
    }
}
