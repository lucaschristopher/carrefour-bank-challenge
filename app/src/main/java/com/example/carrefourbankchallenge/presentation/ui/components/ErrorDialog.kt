package com.example.carrefourbankchallenge.presentation.ui.components

import androidx.compose.material.AlertDialog
import androidx.compose.material.Text
import androidx.compose.material.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import com.example.carrefourbankchallenge.R
import com.example.carrefourbankchallenge.presentation.ui.theme.CarrefourBankChallengeTheme
import com.example.carrefourbankchallenge.presentation.ui.theme.DarkBlue

@Composable
fun ErrorDialog(
    modifier: Modifier = Modifier,
    errorMessage: String? = null,
    onClick: () -> Unit = {}
) {
    val openDialog = remember { mutableStateOf(true) }

    if (openDialog.value) {
        AlertDialog(
            onDismissRequest = {},
            confirmButton = {
                TextButton(onClick = {
                    onClick()
                    openDialog.value = false
                }) {
                    Text(
                        text = stringResource(id = R.string.label_reload).uppercase(),
                        color = DarkBlue
                    )
                }
            },
            title = {
                Text(text = stringResource(R.string.error_dialog_title))
            },
            text = {
                Text(
                    text =
                    if (errorMessage.isNullOrBlank()) stringResource(R.string.error_dialog_default_description)
                    else stringResource(
                        R.string.error_dialog_default_description_with_code,
                        errorMessage
                    )
                )
            }
        )
    }
}

@Preview(showBackground = true)
@Composable
fun ErrorDialogPreview() {
    CarrefourBankChallengeTheme {
        ErrorDialog()
    }
}