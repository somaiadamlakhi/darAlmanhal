package com.android.test.local_db.dao

import androidx.lifecycle.LiveData
import androidx.room.*
import com.almanhal.local_db.entity.BookModel

@Dao
interface BookDao {

    @Query("SELECT * FROM bookTable")
    fun getAllBooks(): LiveData<List<BookModel>>

    @Query ("SELECT * From bookTable WHERE addedToCart")
    fun getCartBadge() : LiveData<List<BookModel>>

    @Insert
    fun insert(vararg book: BookModel)

    @Delete
    fun delete(book: BookModel)

    @Insert
    fun insertList(books : List<BookModel>)

    @Update
    fun updateTodo(vararg book: BookModel)

    @Query ("DELETE From bookTable")
    fun deleteAll()



}