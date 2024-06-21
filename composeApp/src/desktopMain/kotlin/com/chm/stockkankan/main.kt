package com.chm.stockkankan

import androidx.compose.ui.window.Tray
import androidx.compose.ui.window.application
import org.jetbrains.compose.resources.painterResource
import stockkankan.composeapp.generated.resources.Res
import stockkankan.composeapp.generated.resources.fa_vicon

fun main() = application {
    Tray(icon = painterResource(Res.drawable.fa_vicon),
        menu = {
            Item("退出", onClick = ::exitApplication)
            Item("配置", onClick = ::exitApplication)
            Item("老板键", onClick = ::exitApplication)
        })
    StockInfoView()
}