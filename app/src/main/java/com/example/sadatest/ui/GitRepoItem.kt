package com.example.sadatest.ui

import androidx.compose.foundation.background
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
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.example.sadatest.R
import com.example.sadatest.domain.GitRepo
import com.example.sadatest.domain.Owner
import com.example.sadatest.ui.theme.SadaTestTheme
import com.example.sadatest.utils.TestTags


@Composable
fun GitRepoItem(repo: GitRepo) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .background(color = MaterialTheme.colorScheme.background)
            .testTag(TestTags.GIT_ITEM_TEST_TAG)
    ) {
        Divider(
            modifier = Modifier
                .width(350.dp)
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
                Spacer(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(4.dp)
                )
                Text(
                    text = repo.description,
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
                        contentDescription = ""
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
                        contentDescription = ""
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
        Spacer(
            modifier = Modifier
                .fillMaxWidth()
                .height(12.dp)
        )
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
            )
        )
    }
}
