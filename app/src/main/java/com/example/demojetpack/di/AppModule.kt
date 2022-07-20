package com.example.demojetpack.di

import android.app.Application
import androidx.room.Room
import com.example.demojetpack.data.AccountDatabase
import com.example.demojetpack.repo.AppRepository
import com.example.demojetpack.repo.AppRepositoryImp
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton


@InstallIn(SingletonComponent::class)
@Module
class AppModule {

    @Singleton
    @Provides
    fun provideDatabase(application: Application): AccountDatabase {
        return Room.databaseBuilder(
            application,
            AccountDatabase::class.java,
            AccountDatabase.DATABASE_NAME
        ).build()
    }

    @Singleton
    @Provides
    fun provideRepository(database: AccountDatabase): AppRepository{
        return AppRepositoryImp(database)
    }
}