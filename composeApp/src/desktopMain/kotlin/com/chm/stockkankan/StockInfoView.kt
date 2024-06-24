package com.chm.stockkankan

import androidx.compose.desktop.ui.tooling.preview.Preview
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.window.WindowDraggableArea
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Window
import io.ktor.client.HttpClient
import io.ktor.client.request.get
import io.ktor.client.statement.bodyAsText
import kotlinx.coroutines.delay
import java.nio.charset.Charset

@Preview
@Composable
fun StockInfoView(stockCodes: List<String>) {
    Window(
        onCloseRequest = {},
        title = "股票窗口",
        transparent = true,
        undecorated = true,
        alwaysOnTop = true,

    ) {
        WindowDraggableArea {
            val javaVersion = getJdkInfo()
            Column(modifier = Modifier.fillMaxSize().alpha(0.8F).background(Color.Black)) {
                var text by remember { mutableStateOf("Loading") }
                LaunchedEffect(Unit) {
                    var a = 0
                    while(true){
                        text = try {
                            StockHttpUtil.getStokInfo(stockCodes)
                        } catch (e: Exception) {
                            e.stackTraceToString() ?: "error"
                        }
                        a = a+1
                        text = "$javaVersion$a:\n$text"
                        delay(1000)
                    }
                }
                Text("$text", color = Color.White, fontSize = 11.sp, lineHeight = 18.sp);
            }
        }
    }
}

object StockHttpUtil{
    private val client  = HttpClient()
    suspend fun getStokInfo(stockCodes: List<String>): String {
        val url = "https://qt.gtimg.cn/utf8/?q="+stockCodes.joinToString(",")
        val response = client.get(url)
        val lines = response.bodyAsText(Charset.forName("utf-8")).split("\n")

        println("我被执行了")

        data class StockInfo(
            val name: String,
            val yestClose: String,
            val currentPrice: String,
            val changeRate: String
        )

        val stockInfos = mutableListOf<StockInfo>()
        for (line in lines) {
            if (line.isBlank()) continue
            val values = line.substring(line.indexOf("=") + 2, line.length - 2).split("~")
            val stockInfo = StockInfo(values[1], values[4], values[3], values[32] + '%')
            stockInfos.add(stockInfo)
        }

        return buildString {
            for(stockInfo in stockInfos) {
                append(stockInfo.name + " " + stockInfo.changeRate + "\n")
            }
        }
    }
}
fun getJdkInfo(): String = buildString {

    appendLine("Java version: "+System.getProperty("java.version"))
    appendLine("Java vendor: "+System.getProperty("java.vendor"))
    //appendLine("Java home: "+System.getProperty("java.home"))
    //appendLine("Os name: "+System.getProperty("os.name"))
    //appendLine("Os arch: "+System.getProperty("os.arch"))
    //appendLine("Os version: "+System.getProperty("os.version"))
}
