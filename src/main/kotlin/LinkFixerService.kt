import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json
import java.io.OutputStream
import java.net.HttpURLConnection
import java.net.URL

object LinkFixerService {
    const val BASE_URL = "http://localhost:8080/"
    private val json = Json{ ignoreUnknownKeys = true }

    fun getFixedLink(link: String): String {
        var connection: HttpURLConnection? = null
        return try {
            val urlString = BASE_URL + "fixLink"
            val url = URL(urlString)
            connection = (url.openConnection() as HttpURLConnection).apply {
                requestMethod = "POST"
                doOutput = true
                setRequestProperty("Content-Type", "application/json")
                setRequestProperty("Accept", "application/json")
                readTimeout = 5_000
            }

            connection.outputStream.use { os: OutputStream ->
                val jsonBody = Json.encodeToString(Link(link))
                os.write(jsonBody.toByteArray(Charsets.UTF_8))
                os.flush()
            }

            val response = connection.inputStream.bufferedReader().readText()
            json.decodeFromString<Link>(response).link
        } catch (e: Exception) {
            "Error: ${e.message}"
        }
        finally {
            connection?.disconnect()
        }
    }
}