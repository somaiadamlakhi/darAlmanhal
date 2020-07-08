package com.almanhal.daralmanhaltask.ui.main

import android.os.Bundle
import android.os.Handler
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.almanhal.daralmanhaltask.HomePage
import com.almanhal.daralmanhaltask.R
import com.almanhal.daralmanhaltask.ui.adapters.BooksAdapter
import com.almanhal.daralmanhaltask.ui.viewmodel.BooksViewModel
import com.almanhal.local_db.entity.BookModel
import com.google.android.material.tabs.TabLayout


/**
 * A placeholder fragment containing a simple view.
 */
class BooksFragment : Fragment() {//, OnRecyclerItemClickListener<BookModel>

    private var mAdapter: BooksAdapter? = null

    //    private lateinit var mViewModel: BooksViewModel
    var mViewModel: BooksViewModel? = null

    var cartBadge: TextView? = null

    //    var mViewModel: SubCategoryViewModel? = null
    var books_rv: RecyclerView? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mViewModel = ViewModelProviders.of(this).get(BooksViewModel::class.java)
    }


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val root = inflater.inflate(R.layout.fragment_home_page, container, false)
        books_rv = root.findViewById(R.id.books_rv)
        val inner_tabs: TabLayout = root.findViewById(R.id.inner_tabs)

//        val textView = view!!.rootView.findViewById(R.id.cart_badge_tv) as TextView
        cartBadge = (this.activity as HomePage?)!!.findViewById(R.id.cart_badge_tv) as TextView
//cartBadge!!.visibility = View.VISIBLE
        observeLiveData()
        observeBadgeSize()

        return root
    }


    companion object {
        /**
         * The fragment argument representing the section number for this
         * fragment.
         */
        private const val ARG_SECTION_NUMBER = "section_number"

        /**
         * Returns a new instance of this fragment for the given section
         * number.
         */
        @JvmStatic
        fun newInstance(sectionNumber: Int): BooksFragment {

            return BooksFragment().apply {
                arguments = Bundle().apply {
                    putInt(ARG_SECTION_NUMBER, sectionNumber)
                }
            }
        }

    }


    private fun observeLiveData() {

        mViewModel?.getAllBooksList()?.observe(this!!.activity!!, Observer { data ->
            if (mAdapter == null)
                initAdapter(data)

        })
    }


    private fun observeBadgeSize() {
// omitted ViewModel for simplicity


        mViewModel?.getCartBadge()?.observe(this!!.activity!!, Observer {
                data ->


            Handler().postDelayed({
                if (cartBadge != null)
                    if (data.isNotEmpty()) {
                        cartBadge!!.visibility = View.VISIBLE
                        cartBadge!!.text = data.size.toString()
                    } else
                        cartBadge!!.visibility = View.GONE
//            cartBadge.text = mViewModel!!.getCartBadge().toString()//quantity.toString()
//            cartItems= mViewModel!!.mRepository!!.getCartBadge()
                //doSomethingHere()
            }, 100)
        })
    }

    private fun initAdapter(data: List<BookModel>?) {
        books_rv!!.layoutManager =
            LinearLayoutManager(this!!.activity!!, LinearLayoutManager.VERTICAL, false)
        mAdapter = BooksAdapter( this!!.activity!!)
        mAdapter!!.setItems(data)
//        mAdapter!!.setListener(this)
        books_rv!!.adapter = mAdapter

    }

//    override fun onItemClickListener(item: BookModel, flag: Boolean) {
//        mViewModel!!.updateBookToCart(item)
//
//    }
}