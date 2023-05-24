package com.example.carrefourbankchallenge.presentation.ui.components

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import com.example.carrefourbankchallenge.R
import com.example.carrefourbankchallenge.presentation.ui.theme.DeepBlack
import com.example.carrefourbankchallenge.presentation.ui.theme.dp0

@Composable
fun AppTopBar(
    modifier: Modifier = Modifier,
    title: String,
    showBackButton: Boolean,
    navigateBack: () -> Unit
) {
    TopAppBar(
        backgroundColor = DeepBlack,
        elevation = dp0,
        title = {
            Text(
                text = title,
                color = Color.White,
                fontWeight = FontWeight.Bold,
                modifier = modifier.fillMaxWidth(),
                style = MaterialTheme.typography.h6
            )
        },
        navigationIcon = if (showBackButton) {
            {
                IconButton(onClick = { navigateBack.invoke() }) {
                    Icon(
                        tint = Color.White,
                        imageVector = Icons.Filled.ArrowBack,
                        contentDescription = stringResource(R.string.description_back_button)
                    )
                }
            }
        } else null
    )
}