package com.example.sadatest.ui.elements

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.res.stringResource
import com.example.sadatest.R
import com.example.sadatest.utils.TestTags

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SadaNavbar() {
    TopAppBar(
        title = {
            Text(
                text = stringResource(R.string.text_trending),
                style = MaterialTheme.typography.bodyLarge,
                color = MaterialTheme.colorScheme.scrim
            )
        },
        modifier = Modifier
            .fillMaxWidth()
            .background(MaterialTheme.colorScheme.background)
            .testTag(TestTags.NAVBAR_TEST_TAG),
        navigationIcon = {},
        actions = {}
    )
}
