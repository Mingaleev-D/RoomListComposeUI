package com.example.roomlistcomposeui.ui.list

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import com.example.roomlistcomposeui.R
import com.example.roomlistcomposeui.domain.model.ListModel

/**
 * @author : Mingaleev D
 * @data : 28.10.2023
 */

@Composable
fun ListItem(
    list: ListModel,
    onItemClick: () -> Unit,
    onDeleteConfirm: () -> Unit
) {

   var showDIalog by remember { mutableStateOf(false) }

   if (showDIalog) {
      DeleteDialog(
          title = "Удалить",
          msg = stringResource(id = R.string.text_dummy),
          onDialogDismiss = { showDIalog = false },
          onConfirmBtnClick = {
             onDeleteConfirm()
             showDIalog = false
          }
      )
   }

   Card(
       modifier = Modifier.clickable { onItemClick() },
       elevation = CardDefaults.cardElevation(defaultElevation = 4.dp)
   ) {

      Row(
          modifier = Modifier
              .fillMaxSize()
              .padding(10.dp),
          verticalAlignment = Alignment.CenterVertically
      ) {

         Column(
             modifier = Modifier.weight(9f)
         ) {

            Text(
                text = list.name,
                style = MaterialTheme.typography.titleLarge.copy(fontWeight = FontWeight.Bold),
                maxLines = 1,
                overflow = TextOverflow.Ellipsis
            )

            Text(
                text = list.surname,
                style = MaterialTheme.typography.titleMedium,
                maxLines = 1,
                overflow = TextOverflow.Ellipsis
            )
         }

         IconButton(onClick = { showDIalog = true }, modifier = Modifier.weight(1f)) {
            Icon(imageVector = Icons.Filled.Delete, contentDescription = null)

         }
      }
   }

}