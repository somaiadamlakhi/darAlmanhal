package com.almanhal.daralmanhaltask.ui.adapters

import android.content.Context
import android.graphics.Color
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.almanhal.daralmanhaltask.R
import com.almanhal.daralmanhaltask.base.BaseRecyclerAdapter
import com.almanhal.daralmanhaltask.base.BaseViewHolder
import com.almanhal.local_db.entity.SubCategoryModel
import com.android.test.interfaces.OnRecyclerItemClickListener
import kotlinx.android.synthetic.main.item_sub_category.view.*

class SubCategoryAdapter(context: Context) :
    BaseRecyclerAdapter<SubCategoryModel, OnRecyclerItemClickListener<SubCategoryModel>, SubCategoryAdapter.CategoryVH>(
        context
    ) {

     var row_index: Int = -1

    override fun onCreateViewHolder(viewGroup: ViewGroup, i: Int): CategoryVH {
        return CategoryVH(inflate(R.layout.item_sub_category, viewGroup))
    }

    override fun onBindViewHolder(holder: CategoryVH, position: Int) {
        super.onBindViewHolder(holder, position)
        holder.tvCategoryName.setOnClickListener(View.OnClickListener {
            row_index = position
            notifyDataSetChanged()
        })
        if (row_index === position) {
            holder.tvCategoryName.setBackgroundResource(R.drawable.rounded_shape_orange)
        } else {
            holder.tvCategoryName.setBackgroundResource(R.drawable.rounded_shape_green)
        }



    }

    class CategoryVH(itemView: View) :
        BaseViewHolder<SubCategoryModel, OnRecyclerItemClickListener<SubCategoryModel>>(itemView) {


        var tvCategoryName: TextView


        init {
            tvCategoryName = itemView.sub_category_name_tv
        }

        override fun onBind(
            item: SubCategoryModel,
            listener: OnRecyclerItemClickListener<SubCategoryModel>?
        ) {
            tvCategoryName.text = "      "+item.categoryName+"      "
            tvCategoryName.setOnClickListener {

                listener?.onItemClickListener(item, true)
            }



        }


    }

}