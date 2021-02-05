package com.lazygeniouz.roomdb_practise.adapter

import android.annotation.SuppressLint
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.RecyclerView
import com.lazygeniouz.roomdb_practise.databinding.ItemRecyclerviewBinding
import com.lazygeniouz.roomdb_practise.databse.room.TestEntity

@SuppressLint("SetTextI18n")
/**
 * RecyclerView ViewHolder to show Entity Name & Last Name
 * in a good looking CardView
 */
class TestViewHolder(itemView: ItemRecyclerviewBinding) :
    RecyclerView.ViewHolder(itemView.root) {
    private val textView: TextView = itemView.entityName
    private val cardView: CardView = itemView.cardView


    /**
     * Requires a [TestEntity] Object to show Names
     * & the click listener callback
     */
    fun bind(entity: TestEntity, listener: ((Int) -> Unit)?) {
        textView.text = "${entity.firstName} ${entity.lastName}"
        cardView.setOnClickListener {
            listener?.invoke(entity.uid)
        }
    }
}