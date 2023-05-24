package com.example.carrefourbankchallenge.presentation.ui.fragments.home.widgets

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import com.example.carrefourbankchallenge.presentation.ui.theme.dp40

@Composable
fun AvatarEmptyImage(
    modifier: Modifier = Modifier
) {
    Canvas(modifier = modifier.size(dp40), onDraw = {
        drawCircle(color = Color.Gray)
    })
}