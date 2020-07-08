package com.almanhal.daralmanhaltask.ui.viewmodel

import android.app.Application
import android.content.Context
import android.content.SharedPreferences
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import com.almanhal.daralmanhaltask.R
import com.almanhal.local_db.entity.BookModel
import com.android.test.repository.Repository
import java.util.*
import kotlin.collections.ArrayList

class BooksViewModel(application: Application) : AndroidViewModel(application) {

    var booksLiveData: LiveData<List<BookModel>>? = null
    var cartItemLiveDate: LiveData<List<BookModel>>? = null

    var mRepository: Repository? = null
    var context: Context = getApplication<Application>().applicationContext

    init {

        mRepository = Repository(application)

        booksLiveData = mRepository?.getAllBooks()
        cartItemLiveDate = mRepository?.getCartBadge()
        saveSampleData()

    }

    internal fun getAllBooksList(): LiveData<List<BookModel>>? {
        return booksLiveData
    }

    internal fun saveSampleData() {
//        mRepository?.clearBooksList()
        val preferences =
            context.getSharedPreferences("DarAlmanhal", Context.MODE_PRIVATE)
        val editor: SharedPreferences.Editor = preferences.edit()


        if (!preferences.getBoolean("isDataAdded", false)) {
            mRepository?.insertBooksList(getSampleList())
            editor.putBoolean("isDataAdded", true)
            editor.commit()
            editor.apply()


        }
    }


    internal fun updateBookToCart(book: BookModel) {

            mRepository?.updateCartItem(book)


// put values in editor

// put values in editor
    }

//    internal fun getCartItemNumber():Int{
//        mRepository.getAllBooks().
//    }

//    internal fun getCartBadge() {
//        mRepository?.getCartBadge()
//    }

    internal fun getCartBadge(): LiveData<List<BookModel>>? {
        return cartItemLiveDate
    }

    private fun getSampleList(): List<BookModel> {

        val listOfBooks = ArrayList<BookModel>()
        listOfBooks.add(
            BookModel(
                UUID.randomUUID().toString(),
                context.resources.getString(R.string.book1),
                "https://www.manhal.com/platform/books/97/screenshoots/261ad8e2ebae9543bbd89161addea450.png",
                context.resources.getString(R.string.currency),
                0, 5.0, 0.0, false
            )
        )

        listOfBooks.add(
            BookModel(
                UUID.randomUUID().toString(),
                context.resources.getString(R.string.book2),
                "https://scontent.fymy1-2.fna.fbcdn.net/v/t1.0-9/p720x720/94979441_1937707939695271_4218409452409192448_o.png?_nc_cat=103&_nc_sid=8024bb&_nc_ohc=YYgMPJlBDusAX_Ns5ui&_nc_ht=scontent.fymy1-2.fna&oh=0a43d0ef618b3301dd948cf93bb7de58&oe=5F11A561",
                context.resources.getString(R.string.currency),
                0, 3.5, 0.0, false
            )
        )
        listOfBooks.add(
            BookModel(
                UUID.randomUUID().toString(),
                context.resources.getString(R.string.book3),
                "https://lh3.googleusercontent.com/proxy/IfRGoLotaQKjS3OTsY1WkMQ7y_PT0rUrTZ8mcC9yxZTL2lxyz-8ftyacuqwI3qgf9V2kY-7EJoQDFl6LJ0af8JozD4HKrnwcFziwv44CjWjlQ59eoDvf4NwhwYBnKWyPEccQht1QRilwyRf3vTm3Af9ErRRk2oujBJxrwjTVRkVHQZ1Of7y5HDOcG6Kr5juATv1fcEeo-ZIMrzAwe7Nsc1I_VJl69rySRIEmdz3vl2ZK6Y5Ghg",
                context.resources.getString(R.string.currency),
                0, 7.0, 0.0, false
            )
        )


        listOfBooks.add(
            BookModel(
                UUID.randomUUID().toString(),
                context.resources.getString(R.string.book4),
                "https://pdf-epub-fb2-alkutub.info/timthumb.php?w=570&h=778&zc=1&a=t&s=1&src=http%3A%2F%2Ften.uberupload.ru%2Fbooks%2Fbd47d014d4ae311130222dd673f52a70.jpg&q=30",
                context.resources.getString(R.string.currency),
                0, 4.5, 0.0, false
            )
        )

        listOfBooks.add(
            BookModel(
                UUID.randomUUID().toString(),
                context.resources.getString(R.string.book5),
                "https://kutib-sawtiat-majania.info/timthumb.php?w=370&h=500&zc=1&a=t&s=1&src=http%3A%2F%2Ften.uberupload.ru%2Fbooks%2F453392cfdc1f495c195accd6d3cc3189.jpg&q=31",
                context.resources.getString(R.string.currency),
                0, 4.5, 0.0, false
            )
        )





        return listOfBooks
    }


}