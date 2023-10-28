package com.example.roomlistcomposeui.ui.detail

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.roomlistcomposeui.domain.model.ListModel
import com.example.roomlistcomposeui.domain.repository.UserRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

/**
 * @author : Mingaleev D
 * @data : 28.10.2023
 */

@HiltViewModel
class DetailViewModel @Inject constructor(
    private val repository: UserRepository,
    private val savedStateHandle: SavedStateHandle
) : ViewModel() {

   var state by mutableStateOf(DetailUiState())

   private val _eventFlow = MutableSharedFlow<UiEvent>()
   val eventFlow = _eventFlow.asSharedFlow()

   private var currentUserId: Int? = null

   init {
      getUserDetails()
   }

   fun onEvent(events: DetailEvent) {
      when (events) {
         is DetailEvent.EnterAge -> {
            state = state.copy(
                age = events.age
            )
         }

         is DetailEvent.EnterDescription -> {
            state = state.copy(
                description = events.description
            )
         }

         is DetailEvent.EnterName -> {
            state = state.copy(
                name = events.name
            )
         }

         is DetailEvent.EnterSurname -> {
            state = state.copy(
                surname = events.surname
            )
         }

         DetailEvent.SAveButton -> {
            viewModelScope.launch {
               try {
                  saveUser()
                  _eventFlow.emit(UiEvent.SaveNote)
               } catch (ex: Exception) {
                  _eventFlow.emit(
                      UiEvent.ShowToast(
                          message = ex.message ?: "Неизвестная ошибка"
                      )
                  )

               }
            }
         }

         DetailEvent.SelectedFemale -> {
            state = state.copy(
                gender = 2
            )
         }

         DetailEvent.SelectedMale -> {
            state = state.copy(
                gender = 1
            )
         }
      }
   }

   private fun saveUser() {

      val age = state.age.toIntOrNull()

      when {
         state.name.isEmpty() -> throw TextFieldException("Неоходимо ввести имя")
         state.age.isEmpty() -> throw TextFieldException("Неоходимо ввести возраст")
         state.gender == 0 -> throw TextFieldException("Неоходимо выбрать пол")
         state.surname.isEmpty() -> throw TextFieldException("Неоходимо ввести фамилию")
         state.description.isEmpty() -> throw TextFieldException("Неоходимо ввести краткую информацию")
         age == null || age < 1 || age > 150 -> throw TextFieldException("Возраст должен быть в диапазоне от 1 до 150")
      }

      val trimmedName = state.name.trim()
      val trimmedSurname = state.surname.trim()

      viewModelScope.launch {
         repository.addOrUpdateUser(
             user = ListModel(
                 age = state.age,
                 name = trimmedName,
                 gender = state.gender,
                 surname = trimmedSurname,
                 description = state.description,
                 id = currentUserId
             )
         )
      }
   }

   private fun getUserDetails() {
      savedStateHandle.get<Int>(key = "id")?.let { id ->
         if (id != -1) {
            viewModelScope.launch {
               repository.getUSerById(id)?.apply {
                  state = state.copy(
                      name = name,
                      age = age,
                      gender = gender,
                      surname = surname,
                      description = description,

                      )
                  currentUserId = id
               }
            }
         }
      }
   }

   sealed class UiEvent {
      data class ShowToast(val message: String) : UiEvent()
      object SaveNote : UiEvent()
   }

}

data class DetailUiState(
    val age: String = "",
    val name: String = "",
    val gender: Int = 0,
    val surname: String = "",
    val description: String = ""
)

sealed class DetailEvent {
   data class EnterName(val name: String) : DetailEvent()
   data class EnterAge(val age: String) : DetailEvent()
   data class EnterSurname(val surname: String) : DetailEvent()
   data class EnterDescription(val description: String) : DetailEvent()
   object SelectedMale : DetailEvent()
   object SelectedFemale : DetailEvent()
   object SAveButton : DetailEvent()
}

class TextFieldException(message: String?) : Exception(message)
