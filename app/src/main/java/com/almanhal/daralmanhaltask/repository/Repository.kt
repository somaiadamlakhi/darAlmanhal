package com.android.test.repository

import android.app.Application
import com.almanhal.local_db.entity.BookModel
import com.almanhal.local_db.entity.SubCategoryModel
import com.android.test.local_db.dao.BookDao
import com.android.test.local_db.database.AppDatabase
import com.android.test.local_db.dao.CategoryDao
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import kotlin.coroutines.CoroutineContext


class Repository(private var application: Application) : CoroutineScope {

    override val coroutineContext: CoroutineContext
        get() = Dispatchers.Main

    var mDao: CategoryDao
    var booksDao: BookDao

    init {
        mDao = AppDatabase.getInstance(application).categoryDao()
        booksDao = AppDatabase.getInstance(application).booksDao()

    }

    fun getAllCategory() = mDao.getAllCategory()

    fun insertCategory(category: SubCategoryModel) {
        launch { insertCategorySuspend(category) }
    }

    fun insertCategoryList(categoryList: List<SubCategoryModel>) {
        launch { insertCategoryListSuspend(categoryList) }
    }

    private suspend fun insertCategorySuspend(category: SubCategoryModel) {
        withContext(Dispatchers.IO) {
            mDao.insert(category)
        }
    }

    private suspend fun insertCategoryListSuspend(categoryList: List<SubCategoryModel>) {
        withContext(Dispatchers.IO) {
            mDao.insertList(categoryList)
        }
    }


    /**
     * Books
     */
    fun getAllBooks() = booksDao.getAllBooks()

    fun insertBook(book: BookModel) {
        launch { insertBookSuspend(book) }
    }

    fun insertBooksList(booksList: List<BookModel>) {
        launch { insertBooksListSuspend(booksList) }
    }

    private suspend fun insertBookSuspend(book: BookModel) {
        withContext(Dispatchers.IO) {
            booksDao.insert(book)
        }
    }

    private suspend fun insertBooksListSuspend(booksList: List<BookModel>) {
        withContext(Dispatchers.IO) {
            booksDao.insertList(booksList)
        }
    }

    fun clearBooksList() {
        launch { clearBooksTableSuspend() }
    }

    private suspend fun clearBooksTableSuspend() {
        withContext(Dispatchers.IO) {
            booksDao.deleteAll()
        }
    }




    fun insertCartItem(book: BookModel) {
        launch { insertBookSuspend(book) }
    }

    fun updateCartItem(book: BookModel) {
        launch { updateCartItemSuspend(book) }
    }


    private suspend fun updateCartItemSuspend(book: BookModel) {
        withContext(Dispatchers.IO) {
            booksDao.updateTodo(book)

        }
    }




    fun getCartBadge() = booksDao.getCartBadge()

}