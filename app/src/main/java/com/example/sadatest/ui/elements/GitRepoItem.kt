package com.example.sadatest.ui.elements

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.tween
import androidx.compose.animation.expandVertically
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.shrinkVertically
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.widthIn
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Divider
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.example.sadatest.R
import com.example.sadatest.domain.GitRepo
import com.example.sadatest.domain.Owner
import com.example.sadatest.ui.theme.SadaTestTheme
import com.example.sadatest.utils.TestTags


@Composable
fun GitRepoItem(
    expanded: Boolean,
    repo: GitRepo,
    onItemClicked: () -> Unit,
) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .background(color = MaterialTheme.colorScheme.background)
            .clickable { onItemClicked() }
            .testTag(TestTags.GIT_REPO_ITEM_TEST_TAG)

    ) {
        Divider(
            modifier = Modifier
                .width(300.dp)
                .align(Alignment.End),
            color = MaterialTheme.colorScheme.outline
        )
        Spacer(
            modifier = Modifier
                .fillMaxWidth()
                .height(8.dp)
        )
        Row(
            modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Spacer(modifier = Modifier.width(16.dp))
            AsyncImage(
                model = repo.owner.avatar_url,
                placeholder = painterResource(id = R.drawable.img_placeholder),
                contentDescription = null,
                contentScale = ContentScale.Crop,
                modifier = Modifier
                    .size(50.dp)
                    .clip(CircleShape)
            )
            Spacer(modifier = Modifier.width(8.dp))
            Column(modifier = Modifier.widthIn(100.dp, 250.dp)) {
                Text(
                    text = repo.owner.login,
                    style = MaterialTheme.typography.bodySmall,
                    color = MaterialTheme.colorScheme.outline
                )
                Spacer(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(4.dp)
                )
                Text(
                    text = repo.name,
                    style = MaterialTheme.typography.bodyMedium,
                    fontWeight = FontWeight.Bold,
                    color = MaterialTheme.colorScheme.scrim
                )
                ExpandedContent(isExpanded = expanded, repo = repo)
            }
        }
        Spacer(
            modifier = Modifier
                .fillMaxWidth()
                .height(12.dp)
        )
    }
}

@Composable
fun ExpandedContent(
    isExpanded: Boolean,
    repo: GitRepo
) {
    val enterTransition = remember {
        expandVertically(
            expandFrom = Alignment.Top,
            animationSpec = tween(1000)
        ) + fadeIn(initialAlpha = .3f, animationSpec = tween(1000))
    }
    val exitTransition = remember {
        shrinkVertically(
            shrinkTowards = Alignment.Top,
            animationSpec = tween(1000)
        ) + fadeOut(animationSpec = tween(1000))
    }
    AnimatedVisibility(
        visible = isExpanded,
        enter = enterTransition,
        exit = exitTransition
    ) {
        Column(modifier = Modifier.testTag(TestTags.EXPANDED_CONTENT_TEST_TAG)) {
            Spacer(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(4.dp)
            )
            Text(
                text = repo.description,
                maxLines = 5,
                overflow = TextOverflow.Ellipsis,
                style = MaterialTheme.typography.bodyMedium,
                color = MaterialTheme.colorScheme.scrim
            )
            Spacer(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(4.dp)
            )
            Row(
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Icon(
                    modifier = Modifier.size(8.dp),
                    painter = painterResource(id = R.drawable.ic_dot),
                    contentDescription = "",
                    tint = MaterialTheme.colorScheme.scrim
                )
                Spacer(modifier = Modifier.width(2.dp))
                Text(
                    text = repo.language,
                    style = MaterialTheme.typography.bodySmall,
                    color = MaterialTheme.colorScheme.scrim
                )
                Spacer(modifier = Modifier.width(6.dp))
                Icon(
                    modifier = Modifier.size(12.dp),
                    painter = painterResource(id = R.drawable.ic_star),
                    contentDescription = "",
                    tint = Color.Yellow
                )
                Spacer(modifier = Modifier.width(2.dp))
                Text(
                    text = repo.stargazers_count.toString(),
                    style = MaterialTheme.typography.bodySmall,
                    color = MaterialTheme.colorScheme.scrim
                )
            }
        }
    }
}

@Preview
@Composable
fun GitRepoItemPreview() {
    SadaTestTheme {
        GitRepoItem(
            repo = GitRepo(
                owner = Owner(login = "testUser", avatar_url = ""),
                name = "testRepo",
                description = "This is a test repo",
                stargazers_count = 11022,
                language = "Python"
            ),
            expanded = false,
            onItemClicked = {}
        )
    }
}
