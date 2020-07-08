package com.android.test.local_db.dao

import androidx.lifecycle.LiveData
import androidx.room.*
import com.almanhal.local_db.entity.SubCategoryModel

@Dao
interface CategoryDao {

    @Query("SELECT * FROM SubCategoryModel")
    fun getAllCategory(): LiveData<List<SubCategoryModel>>

    @Insert
    fun insert(vararg category: SubCategoryModel)

    @Delete
    fun delete(category: SubCategoryModel)

    @Insert
    fun insertList(categoryList : List<SubCategoryModel>)

    @Update
    fun updateTodo(vararg category: SubCategoryModel)
}