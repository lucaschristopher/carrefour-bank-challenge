package com.example.carrefourbankchallenge.presentation.ui.fragments.home.widgets

import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import com.example.carrefourbankchallenge.core.constants.TITLE_MAX_LINE
import com.example.carrefourbankchallenge.presentation.ui.theme.DarkBlue
import com.example.carrefourbankchallenge.presentation.ui.theme.sp18

@Composable
fun TitleField(
    modifier: Modifier = Modifier,
    text: String
) {
    Text(
        modifier = modifier,
        maxLines = TITLE_MAX_LINE,
        overflow = TextOverflow.Ellipsis,
        text = text,
        color = DarkBlue,
        fontSize = sp18,
        fontWeight = FontWeight.SemiBold
    )
}