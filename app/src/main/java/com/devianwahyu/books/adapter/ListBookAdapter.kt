package com.devianwahyu.books.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.devianwahyu.books.R
import com.devianwahyu.books.model.Book

class ListBookAdapter(val listBook: ArrayList<Book>): RecyclerView.Adapter<ListBookAdapter.ListBookViewHolder>() {

    private lateinit var onItemClickCallback: OnItemClickCallback

    fun setOnItemClickCallback(onItemClickCallback: OnItemClickCallback) {
        this.onItemClickCallback = onItemClickCallback
    }

    interface OnItemClickCallback {
        fun onItemClick(data: Book)
    }

    override fun onCreateViewHolder(viewGroup: ViewGroup, i: Int): ListBookAdapter.ListBookViewHolder {
        val view: View = LayoutInflater.from(viewGroup.context)
            .inflate(R.layout.item_book, viewGroup, false)
        return ListBookViewHolder(view)
    }

    override fun getItemCount(): Int = listBook.size

    override fun onBindViewHolder(holder: ListBookAdapter.ListBookViewHolder, position: Int) {
        holder.bind(listBook[position])
        holder.itemView.setOnClickListener() {
            onItemClickCallback.onItemClick(listBook[holder.adapterPosition])
        }
    }

    inner class ListBookViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private var tvBookTitle: TextView = itemView.findViewById(R.id.tv_book_title)
        private var tvBookAuthor: TextView = itemView.findViewById(R.id.tv_book_author)
        private var tvBookPublishYear: TextView = itemView.findViewById(R.id.tv_book_publish_year)
        private var imgBookCover: ImageView = itemView.findViewById(R.id.img_book_cover)

        fun bind(book: Book) {
            with(itemView) {
                tvBookTitle.text = book.bookTitle
                tvBookAuthor.text = book.bookAuthor
                tvBookPublishYear.text = book.bookPublishYear
                Glide.with(this).load(book.bookCover).into(imgBookCover)
            }
        }
    }
}