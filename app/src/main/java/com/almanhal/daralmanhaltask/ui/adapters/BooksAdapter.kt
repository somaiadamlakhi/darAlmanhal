package com.almanhal.daralmanhaltask.ui.adapters

import android.content.Context
import android.os.Handler
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import androidx.fragment.app.FragmentActivity
import androidx.lifecycle.*
import com.almanhal.daralmanhaltask.R
import com.almanhal.daralmanhaltask.base.BaseRecyclerAdapter
import com.almanhal.daralmanhaltask.base.BaseViewHolder
import com.almanhal.daralmanhaltask.ui.viewmodel.BooksViewModel
import com.almanhal.local_db.entity.BookModel
import com.android.test.interfaces.OnRecyclerItemClickListener
import com.android.test.repository.Repository
import com.bumptech.glide.Glide
import kotlinx.android.synthetic.main.item_book.view.*


class BooksAdapter( context: Context) :
    BaseRecyclerAdapter<BookModel, OnRecyclerItemClickListener<BookModel>, BooksAdapter.BookVH>(
        context
    ) {


     var mViewModel: BooksViewModel? = null
    override fun onCreateViewHolder(viewGroup: ViewGroup, i: Int): BookVH {
        mViewModel = ViewModelProviders.of((context as FragmentActivity)!!)
            .get<BooksViewModel>(BooksViewModel::class.java)
//        observeLiveData()
        return BookVH(mViewModel!!,inflate(R.layout.item_book, viewGroup))
    }


//    override fun onBindViewHolder(holder: BookVH, position: Int) {
//        super.onBindViewHolder(holder, position)
//
////        var item = this!!.items!![position] as BookModel
////        holder.increaseQuan.setOnClickListener{
////            item.quantity +=1
////
////            mViewModel!!.updateBookToCart(book = item)
////
////            if(item.quantity == 0){
////
////            }
////
////        }
//
//    }

     inner class BookVH(mViewModel:BooksViewModel,itemView: View) :
        BaseViewHolder<BookModel, OnRecyclerItemClickListener<BookModel>>(itemView) {


        var bookNameText: TextView
        var bookPriceText: TextView
        var bookImage: ImageView
        var addToCartImage: ImageView
        var quantityLinear: LinearLayout
        var increaseQuan: ImageView
        var decreaseQuan: ImageView
        var quantityLabel: TextView


        init {
            bookNameText = itemView.book_name_tv
            bookPriceText = itemView.book_price_tv
            bookImage = itemView.book_cover_image
            addToCartImage = itemView.add_to_cart_img
            quantityLinear = itemView.quantity_linear
            increaseQuan = itemView.incerase_img
            decreaseQuan = itemView.decerase_img
            quantityLabel = itemView.quantity_tv
        }

        override fun onBind(
            item: BookModel,
            listener: OnRecyclerItemClickListener<BookModel>?
        ) {
            bookNameText.text = item.bookName
            bookPriceText.text = item.bookUnitPrice.toString() + " " + item.currency

            Glide
                .with(itemView.context)
                .load(item.bookImage)
                .error(R.drawable.app_icon)
                .into(bookImage)

//            bookNameText.setOnClickListener {
//
//                listener?.onItemClickListener(item, true)
//            }

            addToCartImage.setOnClickListener {
                quantityLinear.visibility = View.VISIBLE
                addToCartImage.visibility = View.INVISIBLE
                item.addedToCart = true
                item.quantity = 1
                item.bookTotalPrice = item.bookUnitPrice
                listener?.onItemClickListener(item, true)
                addCartItem(item)

            }

            increaseQuan.setOnClickListener{
                item.quantity+=1
                quantityLabel.text = item.quantity.toString()

                item.bookTotalPrice = item.bookUnitPrice * item.quantity
                bookPriceText.text = item.bookTotalPrice.toString() +" " + item.currency


            }

            decreaseQuan.setOnClickListener{
                if(item.quantity>1) {
                    item.quantity -= 1
                    quantityLabel.text = item.quantity.toString()
                    item.bookTotalPrice = item.bookUnitPrice * item.quantity
                    bookPriceText.text = item.bookTotalPrice.toString() +" "+ item.currency


                }
                else {
                    quantityLinear.visibility = View.INVISIBLE
                    addToCartImage.visibility = View.VISIBLE
                    item.quantity = 0
                    item.addedToCart = false
                    item.bookTotalPrice = 0.0
                    mViewModel!!.updateBookToCart(item)
                }

            }



        }


    }




    fun addCartItem(book:BookModel){
        Handler().postDelayed({
           mViewModel!!.updateBookToCart(book)
            //doSomethingHere()
            mViewModel!!.getAllBooksList()!!.value
        }, 100)
    }



}