package com.example.agrolibre.domain.data.network

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.agrolibre.model.Product
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.QueryDocumentSnapshot

class Repo {
    fun getProductsData(): LiveData<MutableList<Product>>{
        val mutableData = MutableLiveData<MutableList<Product>>()

        FirebaseFirestore.getInstance().collection("products").get().addOnSuccessListener {
            val listData = mutableListOf<Product>()
            for(document: QueryDocumentSnapshot in it){
                val imageUrl = document.getString("imageUrl")
                val name = document.getString("name")
                val price = document.getDouble("price")?.toFloat()
                val description = document.getString("description")
                val product = Product(imageUrl!!, name!!, price!!, description!!)
                listData.add(product)
            }
            mutableData.value = listData
        }
        return mutableData
    }
}