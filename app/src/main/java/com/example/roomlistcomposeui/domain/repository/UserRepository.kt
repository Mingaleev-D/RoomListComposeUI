package com.example.roomlistcomposeui.domain.repository

import com.example.roomlistcomposeui.domain.model.ListModel
import kotlinx.coroutines.flow.Flow

/**
 * @author : Mingaleev D
 * @data : 28.10.2023
 */

interface UserRepository {

   suspend fun addOrUpdateUser(user: ListModel)

   suspend fun deleteUser(user: ListModel)

   suspend fun getUSerById(userId:Int): ListModel?

   fun getAllUser(): Flow<List<ListModel>>
}