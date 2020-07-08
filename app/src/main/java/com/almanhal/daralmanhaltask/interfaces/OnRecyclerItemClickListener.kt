package com.android.test.interfaces

import android.widget.TextView
import com.almanhal.daralmanhaltask.base.BaseRecyclerListener

interface OnRecyclerItemClickListener<T>: BaseRecyclerListener {
    fun onItemClickListener(item:T, flag:Boolean)

}