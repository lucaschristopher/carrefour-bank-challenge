package com.example.carrefourbankchallenge.presentation.ui.components

import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.TextUnit
import com.example.carrefourbankchallenge.core.constants.ZERO

@Composable
fun CustomText(
    modifier: Modifier = Modifier,
    resourceId: Int,
    text: String,
    fontSize: TextUnit
) {
    val globalText = stringResource(resourceId, text)
    val start = globalText.indexOf(text)
    val spanStyles = listOf(
        AnnotatedString.Range(
            SpanStyle(fontWeight = FontWeight.Bold),
            start = ZERO,
            end = start
        )
    )

    Text(
        modifier = modifier,
        text = AnnotatedString(
            text = globalText,
            spanStyles = spanStyles,
        ),
        fontSize = fontSize
    )
}