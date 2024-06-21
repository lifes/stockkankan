import androidx.compose.ui.window.Tray
import androidx.compose.ui.window.Window
import androidx.compose.ui.window.application
import org.jetbrains.compose.resources.painterResource
import stockkankan.composeapp.generated.resources.Res
import stockkankan.composeapp.generated.resources.fa_vicon

fun main() = application {
    Window(
        onCloseRequest = ::exitApplication,
        title = "stockkankan",
    ) {
        App()
    }
    Tray( icon = painterResource(Res.drawable.fa_vicon))
}