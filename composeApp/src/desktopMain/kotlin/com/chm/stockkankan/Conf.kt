package com.chm.stockkankan

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun StockCodesTextField(stockCodes:List<String>, onSave: (List<String>) -> Unit = {}) {
    Column(modifier = Modifier.fillMaxWidth().fillMaxHeight().padding(start = 20.dp,top=40.dp, end = 20.dp)) {
        Row(
            verticalAlignment = Alignment.CenterVertically
        ) {
            var text by remember { mutableStateOf(stockCodes.joinToString(", ")) }
            Text("股票代码 ", modifier = Modifier.width(60.dp))
            OutlinedTextField(
                value = text,
                onValueChange = {
                    text = it
                    val stockCodes = text.replace("，", ",").split(",").map { it.trim() }
                        .filter { it.isNotBlank() }
                    onSave(stockCodes)
                },
                label = { Text("请输入股票代码,以逗号隔开") },
                modifier = Modifier.padding(start = 40.dp).fillMaxWidth()
            )
        }

        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier.padding(top = 100.dp)
        ){
            Text("配置文件位置 ", modifier = Modifier.width(100.dp))
            Text(fileName)
        }
    }
}