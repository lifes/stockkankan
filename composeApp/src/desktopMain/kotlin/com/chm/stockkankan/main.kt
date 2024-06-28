package com.chm.stockkankan

import androidx.compose.foundation.window.WindowDraggableArea
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.rememberUpdatedState
import androidx.compose.runtime.setValue
import androidx.compose.ui.unit.DpSize
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Tray
import androidx.compose.ui.window.Window
import androidx.compose.ui.window.application
import androidx.compose.ui.window.rememberWindowState
import kotlinx.coroutines.launch
import org.jetbrains.compose.resources.painterResource
import stockkankan.composeapp.generated.resources.Res
import stockkankan.composeapp.generated.resources.fa_vicon

fun main() = application {
    var showStockInfoView by remember { mutableStateOf(false) }
    var onCloseRequested by remember { mutableStateOf(false) }
    var windowState = rememberWindowState()
    var quitDialogWindowState = rememberWindowState(size = DpSize(300.dp, 200.dp))
    var showConfWindow by remember { mutableStateOf(false) }

    var stockCodes by remember { mutableStateOf(listOf("sz300418", "sz002761", "sz000099")) }
    Tray(icon = painterResource(Res.drawable.fa_vicon),
        menu = {
            Item("配置", onClick = { showConfWindow = true })
            Item("老板键", onClick = {
                showStockInfoView = !showStockInfoView
            })
            Item("退出", onClick = { onCloseRequested = true })
        })
    if (showStockInfoView) {
        StockInfoView(stockCodes, windowState)
    }

    if (onCloseRequested) {
        Window(
            onCloseRequest = {},
            alwaysOnTop = true,
            state = quitDialogWindowState
        ) {
            WindowDraggableArea {
                YesNoDialog(
                    onYes = ::exitApplication,
                    onNo = {},
                    onCancel = { onCloseRequested = false })
            }
        }
    }


    if (showConfWindow) {
        Window(
            onCloseRequest = { showConfWindow = false },
            title = "配置"
        ) {
            StockCodesTextField(
                getInitText = { stockCodes.joinToString(",") },
                onSave = { stockCodes = it })
        }
    }
}


