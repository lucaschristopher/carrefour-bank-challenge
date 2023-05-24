package com.example.carrefourbankchallenge.presentation.ui.fragments.home.widgets

import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import com.example.carrefourbankchallenge.core.constants.DESCRIPTION_MAX_LINE
import com.example.carrefourbankchallenge.presentation.ui.theme.GoldYellow
import com.example.carrefourbankchallenge.presentation.ui.theme.sp12

@Composable
fun SubtitleField(
    modifier: Modifier = Modifier,
    text: String
) {
    Text(
        modifier = modifier,
        maxLines = DESCRIPTION_MAX_LINE,
        overflow = TextOverflow.Ellipsis,
        text = text,
        color = GoldYellow,
        fontSize = sp12,
        fontWeight = FontWeight.Bold
    )
}