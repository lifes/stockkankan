import androidx.compose.ui.window.Tray
import androidx.compose.ui.window.Window
import androidx.compose.ui.window.application
import org.jetbrains.compose.resources.painterResource
import stockkankan.composeapp.generated.resources.Res
import stockkankan.composeapp.generated.resources.fa_vicon
import java.awt.Dimension
import java.awt.Toolkit

fun main(){

    val screenSize: Dimension = Toolkit.getDefaultToolkit().screenSize

    println("height:" +screenSize.height)
    println("width:" +screenSize.width)
}