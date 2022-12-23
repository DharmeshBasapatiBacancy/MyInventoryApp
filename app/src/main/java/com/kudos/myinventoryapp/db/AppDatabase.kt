package com.kudos.myinventoryapp.db

import androidx.room.Database
import androidx.room.RoomDatabase
import com.kudos.myinventoryapp.db.dao.InventoryDao
import com.kudos.myinventoryapp.db.entity.Item

@Database(entities = [Item::class], version = 1, exportSchema = false)
abstract class AppDatabase : RoomDatabase() {

    abstract fun inventoryDao(): InventoryDao
}