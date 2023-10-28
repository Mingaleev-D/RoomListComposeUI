package com.example.roomlistcomposeui.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.roomlistcomposeui.domain.model.ListModel

/**
 * @author : Mingaleev D
 * @data : 28.10.2023
 */

@Database(entities = [ListModel::class], version = 1)
abstract class UserDatabase : RoomDatabase() {

   abstract val userDao: UserDao
}