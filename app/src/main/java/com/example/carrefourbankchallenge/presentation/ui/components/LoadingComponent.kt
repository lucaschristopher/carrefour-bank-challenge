package com.example.carrefourbankchallenge.presentation.ui.components

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.tooling.preview.Preview
import com.example.carrefourbankchallenge.core.constants.LOADING_COMPONENT_TAG
import com.example.carrefourbankchallenge.presentation.ui.theme.CarrefourBankChallengeTheme
import com.example.carrefourbankchallenge.presentation.ui.theme.DarkBlue

@Composable
fun LoadingComponent(
    modifier: Modifier = Modifier
) {
    Box(
        modifier = modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        CircularProgressIndicator(
            modifier = modifier.testTag(LOADING_COMPONENT_TAG),
            color = DarkBlue
        )
    }
}

@Preview(showBackground = true)
@Composable
fun LoadingComponentPreview() {
    CarrefourBankChallengeTheme {
        LoadingComponent()
    }
}