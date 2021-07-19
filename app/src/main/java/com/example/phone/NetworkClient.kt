import okhttp3.MediaType.Companion.toMediaType
import okhttp3.MultipartBody
import okhttp3.OkHttpClient
import okhttp3.Request
import okhttp3.RequestBody
import okhttp3.RequestBody.Companion.toRequestBody
import org.json.JSONObject
import java.io.IOException
import java.util.concurrent.TimeUnit

var ServerURL = "https://dev.pulttaxi.ru/api/"

//val client = OkHttpClient()
val client = OkHttpClient.Builder()
    .connectTimeout(30, TimeUnit.SECONDS)
    .writeTimeout(30, TimeUnit.SECONDS)
    .readTimeout(30, TimeUnit.SECONDS).build()
class NetworkClient {
    fun getOperation(urlString: String): String? {
        val request = Request.Builder()
            .url(ServerURL + urlString)
            .get()
            .build()
        val response = client.newCall(request).execute()
        return (response.body?.string());
    }

    fun getOperation(urlString: String, token: String): String? {
        val request = Request.Builder()
            .addHeader("token", token)
            .url(ServerURL + urlString)
            .get()
            .build()
        val response = client.newCall(request).execute()
        return (response.body?.string());
    }

    fun postOperation(urlString: String, bodyJSONObject: JSONObject): String? {
        val mediaType = "application/json; charset=utf-8".toMediaType()
        val requestBody = bodyJSONObject.toString().toRequestBody(mediaType)

        val request = Request.Builder()
            .url(ServerURL + urlString)
            .post(requestBody)
            .build()
        val response = client.newCall(request).execute()
        return (response.body?.string());
    }


    val urlGetSms = "requestSMSCodeClient?phone_number=7"
    val urlAuthenticate = "authenticateClients"
    val urlGetInfo = "client/getInfo"


}


