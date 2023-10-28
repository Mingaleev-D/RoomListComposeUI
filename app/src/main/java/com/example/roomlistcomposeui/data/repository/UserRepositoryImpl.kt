package com.example.roomlistcomposeui.data.repository

import com.example.roomlistcomposeui.data.local.UserDao
import com.example.roomlistcomposeui.domain.model.ListModel
import com.example.roomlistcomposeui.domain.repository.UserRepository
import kotlinx.coroutines.flow.Flow

/**
 * @author : Mingaleev D
 * @data : 28.10.2023
 */

class UserRepositoryImpl(
    private val dao: UserDao
) : UserRepository {

   override suspend fun addOrUpdateUser(user: ListModel) {
      dao.addOrUpdateUser(user)
   }

   override suspend fun deleteUser(user: ListModel) {
      dao.deleteUser(user)
   }

   override suspend fun getUSerById(userId: Int): ListModel? {
      return dao.getUSerById(userId)
   }

   override fun getAllUser(): Flow<List<ListModel>> {
      return dao.getAllUser()
   }
}