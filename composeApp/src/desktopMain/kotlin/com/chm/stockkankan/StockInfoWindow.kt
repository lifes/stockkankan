package com.chm.stockkankan

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
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
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.unit.DpSize
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Window
import androidx.compose.ui.window.WindowState
import io.ktor.client.HttpClient
import io.ktor.client.request.get
import io.ktor.client.statement.bodyAsText
import kotlinx.coroutines.delay
import java.nio.charset.Charset


@Composable
fun StockInfoView(stockCodes: List<String>, windowState: WindowState) {
    Window(
        onCloseRequest = {},
        title = "股票窗口",
        transparent = true,
        undecorated = true,
        alwaysOnTop = true,
        state = windowState,
    ) {
        //设置最小窗口大小
        with (LocalDensity.current) {
            val minSize = DpSize(25.dp, 25.dp).toSize()
            window.minimumSize = java.awt.Dimension(minSize.width.toInt(), minSize.height.toInt())
        }
        WindowDraggableArea {
            Column(modifier = Modifier.fillMaxSize().background(Color.Black.copy(0.2f)),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally) {
                var text by remember { mutableStateOf("Loading") }
                LaunchedEffect(stockCodes) {
                    while(true){
                        text = try {
                            StockHttpUtil.getStockInfo(stockCodes)
                        } catch (e: Exception) {
                            e.stackTraceToString() ?: "error"
                        }
                        delay(1000)
                    }
                }
                Text("$text", color = Color.White, fontSize = 11.sp, lineHeight = 18.sp)
            }
        }
    }
}

object StockHttpUtil{
    private val client  = HttpClient()

    suspend fun getStockInfo(stockCodes: String): String {
      return  getStockInfo(stockCodes.split(","))
    }
    suspend fun getStockInfo(stockCodes: List<String>): String {
        val url = "https://qt.gtimg.cn/utf8/?q="+stockCodes.joinToString(",")
        val response = client.get(url)
        val lines = response.bodyAsText(Charset.forName("utf-8")).split("\n")

        data class StockInfo(
            val name: String,
            val yestClose: String,
            val currentPrice: String,
            val changeRate: String
        )

        val stockInfos = mutableListOf<StockInfo>()
        for (line in lines) {
            if (line.isBlank()) continue
            if(!line.contains("v_pv_none_match")){
                val values = line.substring(line.indexOf("=") + 2, line.length - 2).split("~")
                //fix s？
                if(values.size > 32){
                    val stockInfo = StockInfo(values[1], values[4], values[3], values[32] + '%')
                    stockInfos.add(stockInfo)
                }
            }
        }
        return stockInfos.joinToString(separator = "\n") {"${it.name} ${it.changeRate}"}
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
