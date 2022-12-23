package com.kudos.myinventoryapp.repository

import com.kudos.myinventoryapp.db.dao.InventoryDao
import com.kudos.myinventoryapp.db.entity.Item
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class MainRepository @Inject constructor(private val inventoryDao: InventoryDao) {

    suspend fun insertItem(item: Item) {
        inventoryDao.insert(item)
    }

    suspend fun updateItem(item: Item) {
        inventoryDao.update(item)
    }

    suspend fun deleteItem(item: Item) {
        inventoryDao.delete(item)
    }

    fun getItem(id: Int): Flow<Item> {
        return inventoryDao.getItem(id)
    }

    fun getItems(): Flow<List<Item>> {
        return inventoryDao.getItems()
    }

}