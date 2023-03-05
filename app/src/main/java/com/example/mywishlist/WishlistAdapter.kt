package com.example.mywishlist

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

//adapter will always need an arrayList param
class WishlistAdapter(private val itemList: List<Wishlist>) : RecyclerView.Adapter<WishlistAdapter.ViewHolder>() {

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val name: TextView
        val price: TextView
        val url: TextView

        init {
            name = itemView.findViewById(R.id.nameTv)
            price = itemView.findViewById(R.id.priceTv)
            url = itemView.findViewById(R.id.urlTv)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val context = parent.context
        val inflater = LayoutInflater.from(context)
        val wishlistView = inflater.inflate(R.layout.wishlist_item, parent, false)
        return ViewHolder(wishlistView)
    }

    //bind data from the editText to its corresponding view
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        //each individual row within recyclerView
        val item = itemList.get(position)
        holder.name.text = item.name
        holder.price.text = item.price
        holder.url.text = item.url
    }

    override fun getItemCount(): Int {
        return itemList.size
    }
}