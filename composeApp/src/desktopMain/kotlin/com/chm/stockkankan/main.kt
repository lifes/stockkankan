package com.chm.stockkankan

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.window.Tray
import androidx.compose.ui.window.application
import androidx.compose.ui.window.rememberWindowState
import org.jetbrains.compose.resources.painterResource
import stockkankan.composeapp.generated.resources.Res
import stockkankan.composeapp.generated.resources.fa_vicon

fun main() = application {
    var showStockInfoView by remember { mutableStateOf(true) }
    val windowState = rememberWindowState()

    var stockCodes by remember {mutableStateOf(listOf("sz300418", "sz002761","sz000099"))}
    Tray(icon = painterResource(Res.drawable.fa_vicon),
        menu = {
            Item("退出？", onClick = ::exitApplication)
            Item("配置", onClick = {})
            Item("老板键", onClick = {
                showStockInfoView = !showStockInfoView
            })
        })

    if(showStockInfoView) {
        StockInfoView(stockCodes, windowState)
    }

}