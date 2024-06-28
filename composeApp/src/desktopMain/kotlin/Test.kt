import androidx.compose.foundation.layout.Column
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.window.Window
import androidx.compose.ui.window.application
import androidx.compose.ui.window.singleWindowApplication
import kotlinx.coroutines.DelicateCoroutinesApi
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

fun main() {
    singleWindowApplication {
        var cnt by remember { mutableStateOf(0) }
        Text2(cnt, {cnt++}, {cnt--})
    }
}

@Composable
fun Text2(cnt: Int, add: ()->Unit, ff: ()-> Unit){
    Column {
        Text("$cnt")
        Button(onClick = add){
            Text("add")
        }
        Button(onClick = ff){
            Text("ff")
        }
    }
}