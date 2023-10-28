package com.example.roomlistcomposeui.ui

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.rememberNavController
import com.example.roomlistcomposeui.ui.detail.DetailListScreen
import com.example.roomlistcomposeui.ui.detail.DetailViewModel
import com.example.roomlistcomposeui.ui.list.ListScreen
import com.example.roomlistcomposeui.ui.navigation.NavGraphSetup
import com.example.roomlistcomposeui.ui.theme.RoomListComposeUITheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

   override fun onCreate(savedInstanceState: Bundle?) {
      super.onCreate(savedInstanceState)
      setContent {
         RoomListComposeUITheme {
            val navController = rememberNavController()
            NavGraphSetup(navController = navController)

         }
      }
   }
}

