package com.example.roomlistcomposeui.ui.detail

import android.content.res.Configuration
import android.widget.Toast
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.RadioButton
import androidx.compose.material3.RadioButtonDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusDirection
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.roomlistcomposeui.R
import com.example.roomlistcomposeui.ui.theme.RoomListComposeUITheme
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.collectLatest

/**
 * @author : Mingaleev D
 * @data : 28.10.2023
 */

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DetailListScreen(
    viewModel: DetailViewModel = hiltViewModel(),
    onBackCkick: () -> Unit,
    onSaveClick: () -> Unit
) {

   val state = viewModel.state

   val focusRequester = remember { FocusRequester() }
   val focusManager = LocalFocusManager.current

   LaunchedEffect(key1 = Unit) {
      delay(500)
      focusRequester.requestFocus()
   }

   val context = LocalContext.current

   LaunchedEffect(key1 = Unit) {
      viewModel.eventFlow.collectLatest { event ->
         when (event) {
            DetailViewModel.UiEvent.SaveNote -> {
               onSaveClick()
               Toast.makeText(context, "Сохранено", Toast.LENGTH_SHORT).show()
            }

            is DetailViewModel.UiEvent.ShowToast -> {
               Toast.makeText(context, event.message, Toast.LENGTH_SHORT).show()
            }
         }
      }
   }

   Scaffold(
       topBar = {
          DetailTopBar(
              onBackClick = onBackCkick

          )
       }
   ) {

      Column(
          modifier = Modifier
              .fillMaxSize()
              .padding(16.dp)
              .padding(it)
      ) {

         OutlinedTextField(
             value = state.name,
             onValueChange = { newvalue ->
                viewModel.onEvent(DetailEvent.EnterName(newvalue))
             },
             modifier = Modifier
                 .fillMaxWidth()
                 .focusRequester(focusRequester),
             label = {
                Text(text = "Имя")
             },
             textStyle = MaterialTheme.typography.bodyLarge,
             singleLine = true,
             keyboardOptions = KeyboardOptions(
                 imeAction = ImeAction.Next
             ),
             keyboardActions = KeyboardActions(
                 onNext = {
                    focusManager.moveFocus(FocusDirection.Next)
                 }
             )
         )

         Spacer(modifier = Modifier.height(10.dp))

         OutlinedTextField(
             value = state.surname,
             onValueChange = { newvalue ->
                viewModel.onEvent(DetailEvent.EnterSurname(newvalue))
             },
             modifier = Modifier.fillMaxWidth(),
             label = {
                Text(text = "Фамилия")
             },
             textStyle = MaterialTheme.typography.bodyLarge,
             singleLine = true,
             keyboardOptions = KeyboardOptions(
                 imeAction = ImeAction.Next
             ),
             keyboardActions = KeyboardActions(
                 onNext = {
                    focusManager.moveFocus(FocusDirection.Next)
                 }
             )
         )

         Spacer(modifier = Modifier.height(10.dp))

         Row(
             modifier = Modifier.fillMaxWidth(),
             verticalAlignment = Alignment.CenterVertically
         ) {

            OutlinedTextField(
                value = state.age,
                onValueChange = { newvalue ->
                   viewModel.onEvent(DetailEvent.EnterAge(newvalue))
                },
                modifier = Modifier.weight(1f),
                label = {
                   Text(text = "Возраст")
                },
                textStyle = MaterialTheme.typography.bodyLarge,
                singleLine = true,
                keyboardOptions = KeyboardOptions(
                    keyboardType = KeyboardType.Number,
                    imeAction = ImeAction.Next
                ),
                keyboardActions = KeyboardActions(
                    onNext = {
                       focusManager.moveFocus(FocusDirection.Next)
                    }
                )
            )

            RadioGroup(
                modifier = Modifier.padding(horizontal = 10.dp),
                text = "M",
                selected = state.gender == 1,
                onClick = {
                   viewModel.onEvent(DetailEvent.SelectedMale)
                }
            )
            RadioGroup(
                modifier = Modifier.padding(horizontal = 10.dp),
                text = "Ж",
                selected = state.gender == 2,
                onClick = {
                   viewModel.onEvent(DetailEvent.SelectedFemale)
                }
            )

         }

         Spacer(modifier = Modifier.height(10.dp))

         OutlinedTextField(
             value = state.description,
             onValueChange = { newvalue ->
                viewModel.onEvent(DetailEvent.EnterDescription(newvalue))
             },
             modifier = Modifier
                 .fillMaxWidth()
                 .weight(1f),
             label = {
                Text(text = "Краткая информация")
             },
             textStyle = MaterialTheme.typography.bodyLarge,
         )

         Spacer(modifier = Modifier.height(6.dp))

         Button(onClick = {
            viewModel.onEvent(DetailEvent.SAveButton)
            //onSaveClick()

         }) {
            Text(
                text = " Сохранить ",
                style = MaterialTheme.typography.titleLarge.copy(color = MaterialTheme.colorScheme.tertiary)
            )
         }
      }

   }

}

@Composable
fun RadioGroup(
    modifier: Modifier = Modifier,
    text: String,
    selected: Boolean,
    onClick: () -> Unit
) {

   Row(
       modifier = modifier.clickable { onClick() },
       verticalAlignment = Alignment.CenterVertically
   ) {

      RadioButton(
          selected = selected,
          onClick = onClick,
          colors = RadioButtonDefaults.colors(selectedColor = MaterialTheme.colorScheme.primary)
      )

      Text(text = text, style = MaterialTheme.typography.bodyLarge)
   }

}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DetailTopBar(
    onBackClick: () -> Unit
) {
   TopAppBar(
       title = {
          Text(
              text = stringResource(id = R.string.text_dummy),
              style = MaterialTheme.typography.headlineSmall.copy(color = MaterialTheme.colorScheme.tertiary)
          )
       },
       navigationIcon = {
          IconButton(onClick = { onBackClick() }) {
             Icon(imageVector = Icons.Filled.ArrowBack, contentDescription = null)
          }
       }
   )
}

@Preview(
    name = "Ночь-Night Mode",
    uiMode = Configuration.UI_MODE_NIGHT_YES
)
@Preview(
    name = "День-Day Mode",
    uiMode = Configuration.UI_MODE_NIGHT_NO,
    showBackground = true
)
@Composable
private fun DetailListScreenPreview() {
   RoomListComposeUITheme {
      //DetailListScreen()
   }
}