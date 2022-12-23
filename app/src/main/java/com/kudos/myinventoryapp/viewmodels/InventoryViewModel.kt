package com.kudos.myinventoryapp.viewmodels

import androidx.lifecycle.*
import com.kudos.myinventoryapp.db.entity.Item
import com.kudos.myinventoryapp.repository.MainRepository
import com.kudos.myinventoryapp.utils.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class InventoryViewModel @Inject constructor(private val mainRepository: MainRepository) :
    ViewModel() {

    private val _insertStatus = MutableLiveData<Resource<Boolean>>()
    val insertStatus: LiveData<Resource<Boolean>> = _insertStatus

    fun insertItem(item: Item) {
        viewModelScope.launch {
            try {
                mainRepository.insertItem(item)
                _insertStatus.value = Resource.success(true)
            } catch (e: Exception) {
                _insertStatus.value = Resource.error("Error: ${e.localizedMessage}", false)
            }
        }
    }

    private val _deleteStatus = MutableLiveData<Resource<Boolean>>()
    val deleteStatus: LiveData<Resource<Boolean>> = _deleteStatus

    fun deleteItem(item: Item) {
        viewModelScope.launch {
            try {
                mainRepository.deleteItem(item)
                _deleteStatus.value = Resource.success(true)
            } catch (e: Exception) {
                _deleteStatus.value = Resource.error("Error: ${e.localizedMessage}", false)
            }
        }
    }

    val allItems: LiveData<List<Item>> = mainRepository.getItems().asLiveData()

}