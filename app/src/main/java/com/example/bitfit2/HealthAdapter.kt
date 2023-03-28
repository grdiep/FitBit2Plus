package com.example.bitfit2

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

//adapter will always need an arrayList param
class HealthAdapter(private val itemList: List<Health>) : RecyclerView.Adapter<HealthAdapter.ViewHolder>() {

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val name: TextView
        val calories: TextView

        init {
            name = itemView.findViewById(R.id.foodTv)
            calories = itemView.findViewById(R.id.caloriesTv)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val context = parent.context
        val inflater = LayoutInflater.from(context)
        val view = inflater.inflate(R.layout.health_item, parent, false)
        return ViewHolder(view)
    }

    //bind data from the editText to its corresponding view
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = itemList[position]
        holder.name.text = item.name
        holder.calories.text = item.calories
    }

    override fun getItemCount(): Int {
        return itemList.size
    }
}