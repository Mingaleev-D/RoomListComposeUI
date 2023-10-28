package com.example.roomlistcomposeui.ui.list

import androidx.compose.material3.AlertDialog
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable

/**
 * @author : Mingaleev D
 * @data : 28.10.2023
 */

@Composable
fun DeleteDialog(
    title: String,
    msg: String,
    onDialogDismiss: () -> Unit,
    onConfirmBtnClick: () -> Unit
) {

   AlertDialog(
       onDismissRequest = { onDialogDismiss() },
       confirmButton = {
          TextButton(onClick = { onConfirmBtnClick() }) {
             Text(text = "Da")
          }
       },
       dismissButton = {
          TextButton(onClick = { onDialogDismiss()}) {
             Text(text = "No")
          }
       },
       title = {
          Text(
              text = title,
              style = MaterialTheme.typography.titleLarge.copy(color = MaterialTheme.colorScheme.tertiary)
          )
       },
       text = {
          Text(
              text = msg,
              style = MaterialTheme.typography.bodyLarge.copy(color = MaterialTheme.colorScheme.tertiary)
          )
       }
   )
}