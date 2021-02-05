package com.lazygeniouz.roomdb_practise.viewmodel

import android.app.Application
import androidx.lifecycle.*
import com.lazygeniouz.roomdb_practise.databse.repository.TestRepository
import com.lazygeniouz.roomdb_practise.databse.room.TestEntity
import kotlinx.coroutines.launch

/**
 * Same'ol ViewModel logic to separate UI & Data Layer
 */
class TestViewModel(application: Application) : AndroidViewModel(application) {

    private val testRepository by lazy { TestRepository(application) }
    private var testEntityLiveData: LiveData<List<TestEntity>> = MutableLiveData()

    init {
        // Initialising the testEntityLiveData
        viewModelScope.launch { testEntityLiveData = testRepository.getEntities().asLiveData() }
    }

    /**
     * Insert an Entity to Database via the TestRepository
     */
    fun insertEntity() = viewModelScope.launch {
        testRepository.insertEntity()
    }

    /**
     * Delete an Entity with the passed **id** via the TestRepository
     */
    fun deleteEntity(id: Int) = viewModelScope.launch { testRepository.deleteEntity(id) }

    /**
     * Delete all the Entities in the Database via the TestRepository
     */
    fun nukeAllEntities() = viewModelScope.launch { testRepository.nukeAllEntities() }

    /**
     * Observe the changes to the underlying data
     */
    fun observe(owner: LifecycleOwner, listener: (List<TestEntity>) -> Unit) =
        testEntityLiveData.observe(owner = owner) { listener.invoke(it) }
}