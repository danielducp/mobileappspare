package com.sid1817733.sid1817733finalassignment.book

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.sid1817733.sid1817733finalassignment.R
import com.sid1817733.sid1817733finalassignment.bookdatabase.Book

import kotlinx.android.synthetic.main.custom_row.view.*

class BookListAdapter: RecyclerView.Adapter<BookListAdapter.MyViewHolder>() {

    private var bookList = emptyList<Book>()
    private lateinit var moduleName: String

    class MyViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        return MyViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.custom_row, parent, false)
        )
    }

    override fun getItemCount(): Int {
        return bookList.size
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val currentItem = bookList[position]
        holder.itemView.name_txt.text = currentItem.bookName.toString()


    }

    fun setData(book: List<Book>, moduleName: String) {
        this.bookList = book
        this.moduleName = moduleName

        notifyDataSetChanged()
    }
}