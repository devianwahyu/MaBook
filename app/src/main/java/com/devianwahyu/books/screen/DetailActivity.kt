package com.devianwahyu.books.screen

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageView
import android.widget.TextView
import com.bumptech.glide.Glide
import com.devianwahyu.books.R

class DetailActivity : AppCompatActivity() {

    private lateinit var tvDetailTitle: TextView
    private lateinit var tvDetailAuthor: TextView
    private lateinit var tvDetailPublishYear: TextView
    private lateinit var tvDetailSynopsis: TextView
    private lateinit var imgDetailCover: ImageView

    companion object {
        const val EXTRA_TITLE = "extra_title"
        const val EXTRA_AUTHOR = "extra_author"
        const val EXTRA_PUBLISH_YEAR = "extra_publish_year"
        const val EXTRA_SYNOPSIS = "extra_synopsis"
        const val EXTRA_IMAGE = "extra_image"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail)
        supportActionBar?.apply {
            title = "Book Detail"
        }

        tvDetailTitle = findViewById(R.id.detail_title)
        tvDetailAuthor = findViewById(R.id.detail_author)
        tvDetailPublishYear = findViewById(R.id.detail_publish_year)
        tvDetailSynopsis = findViewById(R.id.detail_synopsis)
        imgDetailCover = findViewById(R.id.detail_book_cover)

        showProductDetail()
    }

    private fun showProductDetail() {
        tvDetailTitle.text = intent.getStringExtra(EXTRA_TITLE)
        tvDetailAuthor.text = intent.getStringExtra(EXTRA_AUTHOR)
        tvDetailPublishYear.text = intent.getStringExtra(EXTRA_PUBLISH_YEAR)
        tvDetailSynopsis.text = intent.getStringExtra(EXTRA_SYNOPSIS)
        Glide.with(this)
            .load(intent.getIntExtra(EXTRA_IMAGE, 0))
            .into(imgDetailCover)
    }
}