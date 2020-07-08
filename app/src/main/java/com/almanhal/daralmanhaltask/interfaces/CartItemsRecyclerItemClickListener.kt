package com.android.test.interfaces

import android.widget.TextView
import com.almanhal.daralmanhaltask.base.BaseRecyclerListener

interface CartItemsRecyclerItemClickListener<T>: BaseRecyclerListener {
    fun onItemClickListener(item:T, flag:Boolean)
    fun onItemClickListenerChangeQuantity(item:T, increaseFlag:Boolean)

}