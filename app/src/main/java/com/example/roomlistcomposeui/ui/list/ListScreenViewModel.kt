package com.example.roomlistcomposeui.ui.list

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.roomlistcomposeui.domain.model.ListModel
import com.example.roomlistcomposeui.domain.repository.UserRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

/**
 * @author : Mingaleev D
 * @data : 28.10.2023
 */

@HiltViewModel
class ListScreenViewModel @Inject constructor(
    private val repository: UserRepository
) : ViewModel() {

   private var _userList = MutableStateFlow<List<ListModel>>(emptyList())
   val userList = _userList.asStateFlow()

   init {
      viewModelScope.launch {
         repository.getAllUser().collect {
            _userList.value = it
         }
      }
   }

   fun deleteUser(user: ListModel) {
      viewModelScope.launch {
         repository.deleteUser(user)
      }
   }
}