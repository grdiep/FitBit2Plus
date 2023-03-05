package com.example.mywishlist

import android.app.Activity
import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.view.inputmethod.InputMethodManager
import android.widget.Button
import android.widget.EditText
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class MainActivity : AppCompatActivity() {
    var wishlist: MutableList<Wishlist> = mutableListOf()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val submitButton = findViewById<Button>(R.id.submitButton)
        val name = findViewById<EditText>(R.id.nameEt)
        val price = findViewById<EditText>(R.id.priceEt)
        val url = findViewById<EditText>(R.id.urlEt)
        val wishlistRv = findViewById<RecyclerView>(R.id.wishlistRv)

        var adapter = WishlistAdapter(wishlist)
        wishlistRv.adapter = adapter
        wishlistRv.layoutManager = LinearLayoutManager(this)

        submitButton.setOnClickListener {
            val _name = name.text.toString()
            val _price = price.text.toString()
            val _url = url.text.toString()
            var item = Wishlist(_name, _price, _url)

            wishlist.add(item)
            adapter.notifyDataSetChanged()

            name.text.clear()
            price.text.clear()
            url.text.clear()

            hideKeyboard()
        }
    }

    private fun Activity.hideKeyboard() {
        hideKeyboard(currentFocus ?: View(this))
    }

    private fun Context.hideKeyboard(view: View) {
        val inputMethodManager = getSystemService(Activity.INPUT_METHOD_SERVICE) as InputMethodManager
        inputMethodManager.hideSoftInputFromWindow(view.windowToken, 0)
    }
}