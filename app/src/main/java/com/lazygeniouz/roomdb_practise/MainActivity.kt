package com.lazygeniouz.roomdb_practise

import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.lazygeniouz.roomdb_practise.adapter.TestAdapter
import com.lazygeniouz.roomdb_practise.databinding.ActivityMainBinding
import com.lazygeniouz.roomdb_practise.databse.room.TestEntity
import com.lazygeniouz.roomdb_practise.viewmodel.TestViewModel

/**
 * [MainActivity] : App's UI Entry Point
 */
class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private lateinit var adapter: TestAdapter
    private val testViewModel by lazy {
        ViewModelProvider.AndroidViewModelFactory(application)
            .create(TestViewModel::class.java)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        initViews()
        initRecyclerView()
        observeEntities()
    }

    // Setting up contentView with ViewBinding
    private fun initViews() {
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.addToDb.setOnClickListener { testViewModel.insertEntity() }
    }

    // Setting up RecyclerView
    private fun initRecyclerView() {
        adapter = TestAdapter()
        adapter.addCallback { testViewModel.deleteEntity(it) }
        binding.recyclerView.adapter = adapter
    }

    /**
     * Observe the Entity ([TestEntity]) and update RecyclerView with appropriate DiffUtils
     */
    private fun observeEntities() {
        testViewModel.observe(this) { list ->
            adapter.insertEntities(list)
            if (adapter.itemCount == 0) binding.emptyView.visibility = View.VISIBLE
            else binding.emptyView.visibility = View.GONE
        }
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.main, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        testViewModel.nukeAllEntities()
        return true
    }
}