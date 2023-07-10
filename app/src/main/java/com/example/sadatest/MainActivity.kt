package com.example.sadatest

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.example.sadatest.ui.HomeScreen
import com.example.sadatest.ui.theme.SadaTestTheme
import com.example.sadatest.ui.viewmodels.GitRepoViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    private val viewModel: GitRepoViewModel by viewModels()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            SadaTestTheme {
                HomeScreen(
                    state = viewModel.homeScreenState.collectAsStateWithLifecycle().value,
                    handleEvent = viewModel::handleEvent
                )
            }
        }
    }
}
