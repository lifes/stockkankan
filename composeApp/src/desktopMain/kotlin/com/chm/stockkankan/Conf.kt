package com.chm.stockkankan

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material.Button
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp


@Composable
fun StockCodesTextField(getInitText: ()-> String, onSave: (List<String>) -> Unit = {}) {

    Column(modifier = Modifier.fillMaxWidth().fillMaxHeight().padding(start = 20.dp, end = 20.dp)){
        var text by remember { mutableStateOf(getInitText()) }
        Row(modifier = Modifier.height(80.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text( "股票代码 ", modifier = Modifier.width(60.dp))
            Text("❓")
            OutlinedTextField(
                value = text,
                onValueChange = { text = it
                   // val stockCodes = text.replace("，",",").split(",").map { it.trim() }.filter { it.isNotBlank() }
                   // onSave(stockCodes)
                },
                label = { Text("请输入股票代码,以逗号隔开") },
                modifier = Modifier.padding(start = 40.dp).fillMaxWidth()
            )
        }

        Row(modifier = Modifier.height(60.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            println("我被重组了？没啥地方水电费水电费")
            Text( "测试 ", modifier = Modifier.width(60.dp))
            Text("❓")
            var text by remember { mutableStateOf("") }
            OutlinedTextField(
                value = text,
                onValueChange = { text = it },
                label = { Text("测试区域") },
                modifier = Modifier.padding(start = 40.dp)
            )
        }

        Row(modifier = Modifier.height(200.dp),
            horizontalArrangement = Arrangement.Center,
            verticalAlignment = Alignment.CenterVertically){
            Button(onClick = {
                val stockCodes = text.replace("，",",").split(",").map { it.trim() }.filter { it.isNotBlank() }
                onSave(stockCodes)
                println("保存成功")
            }){
                Text("保存")
            }
        }
    }
}