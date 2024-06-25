import kotlinx.coroutines.DelicateCoroutinesApi
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

@OptIn(DelicateCoroutinesApi::class)
fun main() {
    GlobalScope.launch {
        println("fuck")
    }
    Thread.sleep(1000)
}

open class F() {}

class S : F() {

}

fun F.fuck() = println("fuck from f")
fun S.fuck() = println("fuck from s")