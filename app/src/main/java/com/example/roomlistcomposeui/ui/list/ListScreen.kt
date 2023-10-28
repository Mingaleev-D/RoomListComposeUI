package com.example.roomlistcomposeui.ui.list

import android.content.res.Configuration
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.roomlistcomposeui.R
import com.example.roomlistcomposeui.domain.model.ListModel
import com.example.roomlistcomposeui.ui.theme.RoomListComposeUITheme

/**
 * @author : Mingaleev D
 * @data : 28.10.2023
 */

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ListScreen(
    viewModel: ListScreenViewModel = hiltViewModel(),
    onFabClick: () -> Unit,
    onItemClick: (Int?) -> Unit
) {

   val userList by viewModel.userList.collectAsState()

   Scaffold(
       topBar = {
          ListAppBar()
       },
       floatingActionButton = {
          ListFab(
              onfabClick = onFabClick
          )
       }
   ) {

      LazyColumn(
          contentPadding = PaddingValues(16.dp),
          modifier = Modifier
              .fillMaxSize()
              .padding(it),
          verticalArrangement = Arrangement.spacedBy(16.dp),
      ) {

         items(userList) { demoList ->
            ListItem(
                list = demoList,
                onItemClick = { onItemClick(demoList.id) },
                onDeleteConfirm = {viewModel.deleteUser(demoList)}
            )
         }

      }

      if (userList.isEmpty()) {
         Box(
             modifier = Modifier
                 .fillMaxSize()
                 .padding(it),
             contentAlignment = Alignment.Center
         ) {

            Text(
                text = stringResource(R.string.text_dummy),
                style = MaterialTheme.typography.titleLarge.copy(textAlign = TextAlign.Center)
            )

         }
      }

   }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ListAppBar() {
   TopAppBar(
       title = {
          Text(
              text = stringResource(id = R.string.text_dummy),
              style = MaterialTheme.typography.headlineSmall.copy(fontWeight = FontWeight.Bold)
          )
       })
}

@Composable
fun ListFab(
    onfabClick: () -> Unit
) {
   FloatingActionButton(
       onClick = { onfabClick() },
       containerColor = MaterialTheme.colorScheme.primary
   ) {
      Icon(imageVector = Icons.Filled.Add, contentDescription = null)
   }
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
private fun ListSscreenPreview() {
   RoomListComposeUITheme {
      // ListScreen()
   }
}