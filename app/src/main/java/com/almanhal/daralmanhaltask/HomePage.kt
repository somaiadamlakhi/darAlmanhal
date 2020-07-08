package com.almanhal.daralmanhaltask

import android.content.Intent
import android.os.Bundle

import androidx.recyclerview.widget.DefaultItemAnimator
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.viewpager.widget.ViewPager
import com.almanhal.daralmanhaltask.base.BaseActivity
import com.almanhal.daralmanhaltask.ui.CartActivity
import com.almanhal.daralmanhaltask.ui.adapters.SubCategoryAdapter
import com.almanhal.daralmanhaltask.ui.main.SectionsPagerAdapter
import com.almanhal.local_db.entity.SubCategoryModel
import com.android.test.interfaces.OnRecyclerItemClickListener
import com.google.android.material.tabs.TabLayout
import kotlinx.android.synthetic.main.activity_home_page.*
import java.util.*
import kotlin.collections.ArrayList


class HomePage : BaseActivity(), OnRecyclerItemClickListener<SubCategoryModel> {
    var mAdapter: SubCategoryAdapter? = null

    override fun layoutRes(): Int {
        return R.layout.activity_home_page

    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val sectionsPagerAdapter = SectionsPagerAdapter(this, supportFragmentManager)
        val viewPager: ViewPager = findViewById(R.id.view_pager)

        viewPager.adapter = sectionsPagerAdapter
        val tabs: TabLayout = findViewById(R.id.tabs)

        tabs.setupWithViewPager(viewPager)
        viewPager.currentItem = sectionsPagerAdapter.count
        viewPager.offscreenPageLimit = sectionsPagerAdapter.count


        initView()
        getSampleList()

    }


    private fun initView() {
        subcategory_rv.layoutManager =
            LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false)
        subcategory_rv.itemAnimator = DefaultItemAnimator()
        cart_frame.setOnClickListener {
            startActivity(Intent(this, CartActivity::class.java))
        }
    }

    override fun onItemClickListener(item: SubCategoryModel, flag: Boolean) {


    }

    private fun initAdapter(data: List<SubCategoryModel>) {
        mAdapter = SubCategoryAdapter(applicationContext)
        mAdapter!!.setItems(data)
        mAdapter!!.setListener(this)
        subcategory_rv.adapter = mAdapter
    }

    private fun getSampleList() {

        val listOfCategory = ArrayList<SubCategoryModel>()
        listOfCategory.add(
            SubCategoryModel(
                UUID.randomUUID().toString(),
                getString(R.string.sience)
            )
        )
        listOfCategory.add(
            SubCategoryModel(
                UUID.randomUUID().toString(),
                getString(R.string.math)
            )
        )
        listOfCategory.add(
            SubCategoryModel(
                UUID.randomUUID().toString(),
                getString(R.string.english_language)
            )
        )
        listOfCategory.add(
            SubCategoryModel(
                UUID.randomUUID().toString(),
                getString(R.string.arabic_language)
            )
        )

        if (mAdapter == null)
            initAdapter(listOfCategory)
    }



}