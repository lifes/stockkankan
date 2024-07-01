package com.chm.stockkankan

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material.AlertDialog
import androidx.compose.material.Text
import androidx.compose.material.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun YesNoDialog(
    onYes: () -> Unit,
    onNo: () -> Unit,
    onCancel: () -> Unit,
) {
    AlertDialog(
        onDismissRequest = {},
        buttons = {
            Row(
                modifier = Modifier.fillMaxWidth().padding(8.dp),
                horizontalArrangement = Arrangement.End,
            ) {
                TextButton(onClick = onCancel) {
                    Text(text = "Cancel")
                }
                TextButton(onClick = {
                    onYes()
                }) {
                    Text(text = "Yes")
                }
            }
        },
        title = { Text(text = "退出会保存配置的股票信息") },
        text = { Text(text = "你真的要退出吗？") },
        modifier = Modifier.width(400.dp),
    )
}