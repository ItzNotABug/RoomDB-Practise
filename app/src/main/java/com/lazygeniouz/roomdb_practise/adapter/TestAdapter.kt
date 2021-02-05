package com.lazygeniouz.roomdb_practise.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.lazygeniouz.roomdb_practise.databinding.ItemRecyclerviewBinding
import com.lazygeniouz.roomdb_practise.databse.room.TestEntity
import com.lazygeniouz.roomdb_practise.diff_util.TestEntityDiffUtil

/**
 * RecyclerView Adapter to show Entities from Database
 */
class TestAdapter : RecyclerView.Adapter<TestViewHolder>() {

    /**
     *  Click listener to pass the [TestEntity.uid]
     *  for Deleting an [TestEntity] from Database
     */
    private var listener: ((Int) -> Unit)? = null

    // Data list containing TestEntity
    private var list = ArrayList<TestEntity>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TestViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = ItemRecyclerviewBinding.inflate(inflater)
        return TestViewHolder(binding)
    }

    override fun onBindViewHolder(holder: TestViewHolder, ignore: Int) {
        val entity = list[holder.adapterPosition]
        holder.bind(entity, listener)
    }

    override fun getItemCount(): Int = list.size

    /**
     * Insert new items in RecyclerView
     */
    fun insertEntities(newList: List<TestEntity>) = addEntities(newList)

    /**
     * Differentiate new list with the older one using [DiffUtil]
     * then insert the unique items in RecyclerView
     * @see TestEntityDiffUtil for implementation
     */
    private fun addEntities(newList: List<TestEntity>) {
        val diffUtil = TestEntityDiffUtil(list, newList)
        val result = DiffUtil.calculateDiff(diffUtil)
        list.clear()
        list.addAll(newList)
        result.dispatchUpdatesTo(this)
    }

    /**
     * Callback to pass the [TestEntity.uid]
     */
    fun addCallback(callback: (Int) -> Unit) {
        this.listener = callback
    }
}