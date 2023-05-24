package com.example.carrefourbankchallenge.presentation.ui.components

import androidx.compose.material.CircularProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import coil.compose.AsyncImagePainter
import coil.compose.SubcomposeAsyncImage
import coil.compose.SubcomposeAsyncImageContent
import com.example.carrefourbankchallenge.R
import com.example.carrefourbankchallenge.presentation.ui.fragments.home.widgets.AvatarEmptyImage
import com.example.carrefourbankchallenge.presentation.ui.theme.GoldYellow

@Composable
fun AvatarImage(
    modifier: Modifier = Modifier,
    avatarUrl: String
) {
    SubcomposeAsyncImage(
        modifier = modifier,
        model = avatarUrl,
        contentDescription = stringResource(R.string.description_avatar_image)
    ) {

        when (painter.state) {
            is AsyncImagePainter.State.Loading -> CircularProgressIndicator(color = GoldYellow)
            is AsyncImagePainter.State.Error -> AvatarEmptyImage()
            else -> SubcomposeAsyncImageContent()
        }
    }
}