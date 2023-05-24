package com.example.carrefourbankchallenge.presentation.ui.components

import androidx.compose.material.Divider
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import com.example.carrefourbankchallenge.presentation.ui.theme.af02
import com.example.carrefourbankchallenge.presentation.ui.theme.dp1

@Composable
fun LineDivider(modifier: Modifier = Modifier) {
    Divider(
        modifier = modifier,
        color = Color.Gray.copy(af02),
        thickness = dp1,
    )
}