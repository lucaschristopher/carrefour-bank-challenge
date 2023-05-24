package com.example.carrefourbankchallenge.presentation.ui.fragments.home.widgets

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.tooling.preview.Preview
import androidx.constraintlayout.compose.ConstraintLayout
import com.example.carrefourbankchallenge.presentation.model.UserUiModel
import com.example.carrefourbankchallenge.presentation.ui.components.AvatarImage
import com.example.carrefourbankchallenge.presentation.ui.components.LineDivider
import com.example.carrefourbankchallenge.presentation.ui.preview.userUiModelMockPreview
import com.example.carrefourbankchallenge.presentation.ui.theme.*

@Composable
fun UserItem(
    modifier: Modifier = Modifier,
    data: UserUiModel,
    action: (String) -> Unit
) {

    ConstraintLayout(
        modifier = modifier
            .fillMaxWidth()
            .background(WhiteAccent)
            .clickable { action(data.login) }
    ) {
        val (content, divider) = createRefs()

        ConstraintLayout(
            modifier = modifier
                .fillMaxWidth()
                .padding(horizontal = dp16, vertical = dp20)
                .constrainAs(content) {
                    top.linkTo(parent.top)
                    bottom.linkTo(parent.bottom)
                    start.linkTo(parent.start)
                    end.linkTo(parent.end)
                }
        ) {
            val (avatar, columnUser) = createRefs()

            AvatarImage(
                modifier = modifier
                    .size(dp48)
                    .clip(CircleShape)
                    .constrainAs(avatar) {
                        top.linkTo(parent.top)
                        start.linkTo(parent.start)
                        bottom.linkTo(parent.bottom)
                    },
                avatarUrl = data.avatarUrl
            )

            Column(
                verticalArrangement = Arrangement.SpaceAround,
                horizontalAlignment = Alignment.Start,
                modifier = modifier
                    .padding(start = dp12)
                    .constrainAs(columnUser) {
                        top.linkTo(parent.top)
                        bottom.linkTo(parent.bottom)
                        start.linkTo(avatar.end)
                    }
            ) {
                TitleField(
                    text = data.login,
                    modifier = modifier
                        .padding(bottom = dp4)
                )

                SubtitleField(text = "ID #${data.id}")
            }
        }

        LineDivider(modifier = modifier
            .fillMaxWidth()
            .padding(top = dp20, bottom = dp4, start = dp16, end = dp16)
            .constrainAs(divider) {
                bottom.linkTo(parent.bottom)
                start.linkTo(parent.start)
                end.linkTo(parent.end)
            })
    }
}

@Preview(showBackground = true)
@Composable
fun RepoItemPreview() {
    CarrefourBankChallengeTheme {
        UserItem(
            data = userUiModelMockPreview,
            action = { }
        )
    }
}