package com.almanhal.local_db.entity

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.util.*

@Entity(tableName = "bookTable")
data class BookModel(@PrimaryKey var id: String,
                     var bookName: String,
                     var bookImage: String,
                     var currency: String,
                     var quantity: Int,
                     var bookUnitPrice: Double,
                     var bookTotalPrice: Double,
                     var addedToCart: Boolean) {



}