import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.get
import io.ktor.client.statement.bodyAsText
import io.ktor.http.charset
import io.ktor.utils.io.charsets.Charset
import io.ktor.utils.io.charsets.Charsets

class Greeting {
    private val platform = getPlatform()

    fun greet(): String {
        return "Hello, ${platform.name}!"
    }

    private val client = HttpClient()

    suspend fun getText(): String {
        val url = "https://qt.gtimg.cn/utf8/?q=sz300418,sz002761"
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
            if (line.isBlank()) continue;
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