package com.example.roomlistcomposeui.data.local

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Query
import androidx.room.Upsert
import com.example.roomlistcomposeui.domain.model.ListModel
import kotlinx.coroutines.flow.Flow

/**
 * @author : Mingaleev D
 * @data : 28.10.2023
 */

@Dao
interface UserDao {

   @Upsert
   suspend fun addOrUpdateUser(user:ListModel)

   @Delete
   suspend fun deleteUser(user:ListModel)

   @Query("SELECT * FROM ListModel WHERE id = :userId")
   suspend fun getUSerById(userId:Int):ListModel?

   @Query("SELECT * FROM ListModel")
   fun getAllUser():Flow<List<ListModel>>
}