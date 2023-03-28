package com.example.bitfit2

import android.app.Activity
import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.view.inputmethod.InputMethodManager
import android.widget.Button
import android.widget.EditText
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import kotlinx.coroutines.launch

class MainActivity : AppCompatActivity() {
    var healthList: MutableList<Health> = mutableListOf()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val submitButton = findViewById<Button>(R.id.submitButton)
        val name = findViewById<EditText>(R.id.foodEt)
        val price = findViewById<EditText>(R.id.caloriesEt)
        val recyclerView = findViewById<RecyclerView>(R.id.healthRv)

        var adapter = HealthAdapter(healthList)
        recyclerView.adapter = adapter

        lifecycleScope.launch {
            (application as HealthApplication).db.HealthDao().getAll().collect { databaseList ->
                databaseList.map { entity ->
                    Health(
                        entity.name,
                        entity.calories
                    )
                }.also { mappedList ->
                    healthList.clear()
                    healthList.addAll(mappedList)
                    adapter.notifyDataSetChanged()
                }
            }
        }

        recyclerView.layoutManager = LinearLayoutManager(this)

        submitButton.setOnClickListener {
            val _name = name.text.toString()
            val _price = price.text.toString()
            var item = Health(_name, _price)

            healthList.add(item)
            adapter.notifyDataSetChanged()

            name.text.clear()
            price.text.clear()

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