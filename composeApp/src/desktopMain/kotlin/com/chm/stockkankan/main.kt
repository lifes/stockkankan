package com.chm.stockkankan

import androidx.compose.foundation.window.WindowDraggableArea
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.unit.DpSize
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Tray
import androidx.compose.ui.window.Window
import androidx.compose.ui.window.application
import androidx.compose.ui.window.rememberWindowState
import org.jetbrains.compose.resources.painterResource
import stockkankan.composeapp.generated.resources.Res
import stockkankan.composeapp.generated.resources.fa_vicon
import java.io.BufferedReader
import java.io.File
import java.io.FileReader
import java.io.FileWriter
fun main() = application {

    var showStockInfoView by remember { mutableStateOf(false) }
    var onCloseRequested by remember { mutableStateOf(false) }
    var windowState = rememberWindowState(size = DpSize(100.dp, 80.dp))
    var quitDialogWindowState = rememberWindowState(size = DpSize(300.dp, 200.dp))
    var showConfWindow by remember { mutableStateOf(false) }
    var stockCodes by remember { mutableStateOf(stockCodes0) }

    Tray(icon = painterResource(Res.drawable.fa_vicon),
        menu = {
            Item("配置", onClick = { showConfWindow = true })
            Separator()
            Item("老板键", onClick = {
                showStockInfoView = !showStockInfoView
            })
            Separator()
            Item("退出", onClick = { onCloseRequested = true })
        })
    if (showStockInfoView) {
        StockInfoView(stockCodes, windowState)
    }

    if (onCloseRequested) {
        Window(
            title = "",
            onCloseRequest = {},
            alwaysOnTop = true,
            state = quitDialogWindowState
        ) {
            WindowDraggableArea {
                YesNoDialog(
                    onYes = {
                        val file = File(fileName)
                        saveTofile(file, stockCodes)
                        exitApplication()
                    },
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
            StockCodesTextField(stockCodes,
                onSave = { stockCodes = it })
        }
    }
}

val fileName = "conf001.txt"
val stockCodes0 = getStockCodesFromFile(File(fileName))
fun saveTofile(file: File, stockCodes: List<String>) {
    try {
        if (!file.exists()) {
            file.createNewFile()
        }
        FileWriter(file).use {
            it.write(stockCodes.joinToString(","))
        }
    } catch (e: Exception) {
        e.printStackTrace()
    }
}

fun getStockCodesFromFile(file: File): List<String> {
    try {
        if(!file.exists()){
            return listOf()
        }
        var stockString = ""
        BufferedReader(FileReader(file)).use { reader ->
            var line: String?
            while (reader.readLine().also { line = it } != null) {
                stockString += line
            }
        }
        println("a" + stockString)
        return stockString.split(",").map { it.trim() }.filter { it.isNotBlank() }
    } catch (e: Exception) {
        e.printStackTrace()
        return listOf()
    }
}


