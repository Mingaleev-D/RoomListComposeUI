package com.example.roomlistcomposeui.di

import android.content.Context
import androidx.room.Room
import com.example.roomlistcomposeui.data.local.UserDatabase
import com.example.roomlistcomposeui.data.repository.UserRepositoryImpl
import com.example.roomlistcomposeui.domain.repository.UserRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

   @Provides
   @Singleton
   fun provideUserDatabase(
       @ApplicationContext context: Context
   ): UserDatabase {
      return Room.databaseBuilder(
          context,
          UserDatabase::class.java,
          "user_database"
      ).build()
   }

   @Provides
   @Singleton
   fun provideUserRepository(
       db: UserDatabase
   ): UserRepository {
      return UserRepositoryImpl(dao = db.userDao)
   }

}
