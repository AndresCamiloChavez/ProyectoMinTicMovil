package com.example.agrolibre.domain.data.network

import android.content.ContentValues.TAG
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.agrolibre.model.Comment
import com.example.agrolibre.model.Product
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.QueryDocumentSnapshot
import java.lang.Exception

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
    fun getCommentsData(): LiveData<MutableList<Comment>>{
        val mutableData = MutableLiveData<MutableList<Comment>>()



        FirebaseFirestore.getInstance().collection("Comments").get().addOnSuccessListener {
            val listData = mutableListOf<Comment>()
            for (document: QueryDocumentSnapshot in it){
                val content = document.getString("content")
                val score = document.getString("score")
                val user = document.getString("user")
                val userImageUrl = document.getString("userImageUrl")
                val comment = Comment(user!!, score!!, content!!, userImageUrl!!)
                listData.add(comment)

            }
            mutableData.value = listData
        }
        return mutableData
    }
    fun setComment(comment: Comment): Boolean{
        var succes = false
        try {
            FirebaseFirestore.getInstance().collection("Comments").add(comment)
            succes  = true
        }catch (e:Exception){
            Log.d("Error", e.message.toString())
        }
        return succes
    }
}

//FirebaseFirestore.getInstance().collection("Comments").add(comment)
