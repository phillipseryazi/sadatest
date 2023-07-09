package com.example.sadatest.ui.elements

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.airbnb.lottie.compose.LottieAnimation
import com.airbnb.lottie.compose.LottieCompositionSpec
import com.airbnb.lottie.compose.LottieConstants
import com.airbnb.lottie.compose.rememberLottieComposition
import com.example.sadatest.R
import com.example.sadatest.ui.models.HomeEvent
import com.example.sadatest.ui.theme.SadaTestTheme
import com.example.sadatest.utils.TestTags

@Composable
fun BusyAnim(handleEvent: (event: HomeEvent) -> Unit) {
    val composition by rememberLottieComposition(LottieCompositionSpec.RawRes(R.raw.lottie_busy_animation))
    Column(
        modifier = Modifier
            .background(MaterialTheme.colorScheme.background)
            .testTag(TestTags.BUSY_ANIM_TEST_TAG),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        LottieAnimation(
            composition = composition,
            iterations = LottieConstants.IterateForever,
        )
        Spacer(modifier = Modifier.height(8.dp))
        Text(
            text = stringResource(R.string.text_something),
            style = MaterialTheme.typography.bodyLarge,
            color = MaterialTheme.colorScheme.scrim,
            fontWeight = FontWeight.Bold
        )
        Spacer(modifier = Modifier.height(4.dp))
        Text(
            text = stringResource(R.string.text_blocking_alien),
            style = MaterialTheme.typography.bodySmall,
            fontWeight = FontWeight.Bold,
            color = MaterialTheme.colorScheme.outline
        )
        Spacer(modifier = Modifier.height(16.dp))
        OutlinedButton(
            onClick = {
                handleEvent(
                    HomeEvent.SearchGitRepos(
                        mapOf(
                            "language" to "",
                            "sort" to "stars"
                        )
                    )
                )
            },
            shape = RoundedCornerShape(6.dp),
            modifier = Modifier.testTag(TestTags.RETRY_BTN_TEST_TAG),
            border = BorderStroke(
                width = 1.dp, color = MaterialTheme.colorScheme.tertiary
            )
        ) {
            Text(
                text = stringResource(R.string.text_retry),
                style = MaterialTheme.typography.bodyLarge,
                color = MaterialTheme.colorScheme.tertiary,
                fontWeight = FontWeight.Bold
            )
        }
    }
}

@Preview
@Composable
fun BusyAnimPreview() {
    SadaTestTheme {
        BusyAnim(
            handleEvent = {}
        )
    }
}
