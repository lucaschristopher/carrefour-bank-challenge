package com.example.carrefourbankchallenge.presentation.ui.fragments.detail

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.constraintlayout.compose.ConstraintLayout
import com.example.carrefourbankchallenge.R
import com.example.carrefourbankchallenge.presentation.model.UserUiModel
import com.example.carrefourbankchallenge.presentation.ui.components.CustomText
import com.example.carrefourbankchallenge.presentation.ui.theme.*

@Composable
fun UserDetailScreen(
    modifier: Modifier = Modifier,
    user: UserUiModel
) {
    Box(
        modifier.fillMaxSize()
    ) {

        ConstraintLayout(
            modifier = modifier
                .fillMaxSize()
                .padding(dp16)
        ) {
            val (name, email, location, company, repoSession, repos) = createRefs()

            CustomText(
                modifier = modifier
                    .padding(top = dp8)
                    .constrainAs(name) {
                        top.linkTo(parent.top)
                        start.linkTo(parent.start)
                    },
                resourceId = R.string.name_owner_repo,
                text = user.name,
                fontSize = sp16
            )

            CustomText(
                modifier = modifier
                    .padding(top = dp8)
                    .constrainAs(email) {
                        top.linkTo(name.bottom)
                        start.linkTo(parent.start)
                    },
                resourceId = R.string.email_owner_repo,
                text = user.email,
                fontSize = sp16
            )

            CustomText(
                modifier = modifier
                    .padding(top = dp8)
                    .constrainAs(location) {
                        top.linkTo(email.bottom)
                        start.linkTo(parent.start)
                    },
                resourceId = R.string.location_owner_repo,
                text = user.location,
                fontSize = sp16
            )

            CustomText(
                modifier = modifier
                    .padding(top = dp8)
                    .constrainAs(company) {
                        top.linkTo(location.bottom)
                        start.linkTo(parent.start)
                    },
                resourceId = R.string.company_owner_repo,
                text = user.company,
                fontSize = sp16
            )

            Text(
                modifier = modifier
                    .padding(top = dp8)
                    .constrainAs(repoSession) {
                        start.linkTo(parent.start)
                        top.linkTo(company.bottom)
                    }
                    .padding(vertical = dp8),
                text = stringResource(id = R.string.title_repo),
                fontWeight = FontWeight.Bold,
                fontSize = sp16,
            )

            LazyColumn(
                modifier
                    .fillMaxHeight(f075)
                    .fillMaxWidth()
                    .padding(top = dp10, bottom = dp20)
                    .constrainAs(repos) {
                        start.linkTo(parent.start)
                        top.linkTo(repoSession.bottom)
                    },
                horizontalAlignment = Alignment.Start
            ) {
                items(user.repos) { repo ->
                    ConstraintLayout(
                        modifier = modifier
                            .fillMaxSize()
                            .padding(bottom = dp4)
                            .background(GrayAccent)
                    ) {
                        val (repoName, forks) = createRefs()

                        CustomText(
                            modifier = modifier
                                .padding(dp8)
                                .constrainAs(repoName) {
                                    top.linkTo(parent.top)
                                    start.linkTo(parent.start)
                                },
                            text = repo.name,
                            resourceId = R.string.name_owner_repo,
                            fontSize = sp16
                        )

                        CustomText(
                            modifier = modifier
                                .padding(dp8)
                                .constrainAs(forks) {
                                    top.linkTo(repoName.bottom)
                                    start.linkTo(parent.start)
                                },
                            text = repo.forks.toString(),
                            resourceId = R.string.forks_repo,
                            fontSize = sp14
                        )
                    }
                }
            }
        }
    }
}