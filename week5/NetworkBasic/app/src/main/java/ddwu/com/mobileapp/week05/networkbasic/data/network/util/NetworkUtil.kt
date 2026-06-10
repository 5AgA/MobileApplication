package ddwu.com.mobileapp.week05.networkbasic.data.network.util

import android.content.Context
import android.graphics.Bitmap
import android.net.ConnectivityManager
import android.net.NetworkInfo
import android.util.Log
import java.io.BufferedWriter
import java.io.IOException
import java.io.InputStream
import java.io.OutputStreamWriter
import java.net.HttpURLConnection
import java.net.SocketTimeoutException
import java.net.URL
import javax.net.ssl.HttpsURLConnection
import kotlin.jvm.Throws

class NetworkUtil(val context: Context) {

    val TAG = "NetworkUtil"

    val streamUtil = StreamUtil()

    fun downloadText(address: String) : String? {
        var receivedContents : String? = null
        var resultStream : InputStream? = null
        var conn : HttpURLConnection? = null

        try {
            conn = getConnection("GET", address, null)
            resultStream = conn?.inputStream                                    // 응답 결과 스트림 확인
            receivedContents = streamUtil.readStreamToString (resultStream)     // stream 처리 함수를 구현한 후 사용
        } catch (e: Exception) {        // MalformedURLException, IOExceptionl, SocketTimeoutException 등 처리 필요
            e.printStackTrace()
        } finally {
            if (resultStream != null) { try { resultStream.close()} catch (e: IOException) { Log.d(TAG, e.message!!) } }
            if (conn != null) conn.disconnect()
        }

        return receivedContents
    }


    // Download image(binary data) from network
    fun downloadImage(address : String) : Bitmap? {
        var receivedBitmap : Bitmap? = null
        var resultStream : InputStream? = null
        var conn: HttpsURLConnection? = null

        try {
            // getConnection() 을 사용하여 connection 요청 후 inputStream 을 가져와 Bitmap 으로 변환
            conn = getConnection("GET", address, null)
            resultStream = conn?.inputStream
            // 변환한 Bitmap 을 receivedBitmap 에 저장
            receivedBitmap = streamUtil.readStreamToImage(resultStream)
        } catch (e : Exception) {
            e.printStackTrace()
        } finally {
            if (resultStream != null) { try { resultStream.close()} catch (e: IOException) { Log.d(TAG, e.message!!) } }
            if (conn != null) conn.disconnect()
        }

        return receivedBitmap
    }


    // upload and download data on network
    fun sendPostData(requestMethod: String, address: String, data: String) : String? {
        var receivedContents : String? = null
        var resultStream : InputStream? = null
        var conn : HttpsURLConnection? = null

        try {
            // getConnection() 사용하여 Connection 을 post 방식으로 data 를 전달하며 요청하여 결과 inputStream 을 String으로 변환
            conn = getConnection("POST", address, data)
            resultStream = conn?.inputStream
            // 변환한 String 을 receivedContents 에 저장
            receivedContents = streamUtil.readStreamToString(resultStream)

        } catch (e: Exception) {        // MalformedURLException, IOExceptionl, SocketTimeoutException 등 처리 필요
            e.printStackTrace()
        } finally {
            if (resultStream != null) { try { resultStream.close()} catch (e: IOException) { Log.d(TAG, e.message!!) } }
            if (conn != null) conn.disconnect()
        }
        return receivedContents
    }


    @Throws(IOException::class, SocketTimeoutException::class)
    private fun getConnection(requestMethod: String, address: String, data: String?) : HttpsURLConnection? {
        val url = URL(address)
        var conn = url.openConnection() as HttpsURLConnection

        conn.readTimeout = 5000                                 // 읽기 타임아웃 지정 - SocketTimeoutException
        conn.connectTimeout = 5000                              // 연결 타임아웃 지정 - SocketTimeoutException
        conn.doInput = true                                     // 서버 응답 지정 – default
        conn.requestMethod = requestMethod                      // 연결 방식 지정 - or POST

        if (requestMethod.equals("POST")) {
            conn.doOutput = true  // 서버에 데이터 전송하겠다
            conn.setRequestProperty("content-type", "application/x-www-form-urlencoded; charset=UTF-8")
            val params = "subject=" + data
            val outStreamWriter : OutputStreamWriter = OutputStreamWriter(conn.outputStream, "UTF-8")
            val writer : BufferedWriter = BufferedWriter(outStreamWriter)
            writer.write(params)
            writer.flush()
        }

//        conn.connect()                                        // 통신 링크 열기 – 트래픽 발생
        val responseCode = conn.responseCode
        if (responseCode != HttpURLConnection.HTTP_OK) {        // 서버 전송 및 응답 결과 수신
            throw IOException("Http Error Code: $responseCode")
            return null
        }

        return conn
    }



    fun getNetworkInfo() : String {
        val connMgr = context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        var isWifiConn: Boolean = false
        var isMobileConn: Boolean = false

        connMgr.allNetworks.forEach { network ->
            connMgr.getNetworkInfo(network)?.apply {
                if (type == ConnectivityManager.TYPE_WIFI) {
                    isWifiConn = isWifiConn or isConnected
                }
                if (type == ConnectivityManager.TYPE_MOBILE) {
                    isMobileConn = isMobileConn or isConnected
                }
            }
        }

        val result = StringBuilder()
        result.append("Wifi connected: $isWifiConn\n")
        result.append("Mobile connected: $isMobileConn\n")

        Log.d(TAG, "Wifi connected: $isWifiConn")
        Log.d(TAG, "Mobile connected: $isMobileConn")

        return result.toString()
    }


    fun isOnline(): Boolean {
        val connMgr = context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        val networkInfo: NetworkInfo? = connMgr.activeNetworkInfo
        return networkInfo?.isConnected == true
    }


}