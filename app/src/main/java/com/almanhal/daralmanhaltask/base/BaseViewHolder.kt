package com.almanhal.daralmanhaltask.base
import android.view.View
import androidx.recyclerview.widget.RecyclerView

abstract class BaseViewHolder <T,L : BaseRecyclerListener>(itemView: View) : RecyclerView.ViewHolder(itemView) {

    abstract fun onBind(item: T, listener: L?)
}