import androidx.compose.desktop.ui.tooling.preview.Preview
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.window.WindowDraggableArea
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.TextUnitType
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Window
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

@Preview
@Composable
actual fun StockInfoWindow() {

    Window(
        onCloseRequest = {},
        title = "股票窗口",
        transparent = true,
        undecorated = true,
        alwaysOnTop = true

    ) {
        WindowDraggableArea {
            Column(modifier = Modifier.fillMaxSize().alpha(0.8F).background(Color.Black)) {

                var text by remember { mutableStateOf("Loading") }
                LaunchedEffect(true) {
                    var a = 1
                    while(true){
                        text = try {
                            Greeting().getText()
                        } catch (e: Exception) {
                            e.stackTraceToString() ?: "error"
                        }
                        a = a+1
                        text = "$a:\n\n$text"
                        delay(1000)
                    }
                }
                Text("$text", color = Color.White, fontSize = 11.sp, lineHeight = 18.sp);

            }
        }
    }
}