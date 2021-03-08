package com.devianwahyu.books

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.devianwahyu.books.adapter.ListBookAdapter
import com.devianwahyu.books.model.Book
import com.devianwahyu.books.model.BookData
import com.devianwahyu.books.screen.DetailActivity
import com.devianwahyu.books.screen.ProfileActivity

class MainActivity : AppCompatActivity() {

    private lateinit var listBookAdapter: ListBookAdapter
    private var list: ArrayList<Book> = arrayListOf()
    private lateinit var rvBook: RecyclerView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        supportActionBar?.apply {
            title = "Book List"
        }

        rvBook = findViewById(R.id.rv_books)

        showListBook()
        setListClickAction()
    }

    private fun setListClickAction() {
        listBookAdapter.setOnItemClickCallback(object
            : ListBookAdapter.OnItemClickCallback {
            override fun onItemClick(data: Book) {
                val manageDetailIntent = Intent(this@MainActivity,
                        DetailActivity::class.java).apply {
                    putExtra(DetailActivity.EXTRA_TITLE,
                            data.bookTitle)
                    putExtra(DetailActivity.EXTRA_AUTHOR,
                            data.bookAuthor)
                    putExtra(DetailActivity.EXTRA_PUBLISH_YEAR,
                            data.bookPublishYear)
                    putExtra(DetailActivity.EXTRA_SYNOPSIS,
                            data.bookSynopsis)
                    putExtra(DetailActivity.EXTRA_IMAGE,
                            data.bookCover)
                }
                startActivity(manageDetailIntent)
            }
        })
    }

    private fun showListBook() {
        list.addAll(BookData.listBook)
        listBookAdapter = ListBookAdapter(list)

        rvBook.apply {
            layoutManager = LinearLayoutManager(this@MainActivity)
//            adapter = ListBookAdapter(list)
            adapter = listBookAdapter
            setHasFixedSize(true)
        }
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        setMode(item.itemId)
        return super.onOptionsItemSelected(item)
    }

    private fun setMode(itemId: Int) {
        when(itemId) {
            R.id.action_profile -> {
                val intent = Intent(this, ProfileActivity::class.java)
                startActivity(intent)
            }
        }
    }
}