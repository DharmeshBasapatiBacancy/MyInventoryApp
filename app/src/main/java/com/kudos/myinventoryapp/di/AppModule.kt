package com.kudos.myinventoryapp.di

import com.kudos.myinventoryapp.db.dao.InventoryDao
import com.kudos.myinventoryapp.repository.MainRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class AppModule {

    @Singleton
    @Provides
    fun provideMainRepository(inventoryDao: InventoryDao): MainRepository {
        return MainRepository(inventoryDao)
    }

}