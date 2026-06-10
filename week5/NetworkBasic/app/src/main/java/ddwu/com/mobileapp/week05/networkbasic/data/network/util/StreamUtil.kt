package ddwu.com.mobileapp.week05.networkbasic.data.network.util

import android.graphics.Bitmap
import android.graphics.BitmapFactory
import java.io.BufferedReader
import java.io.InputStream
import java.io.InputStreamReader
import java.lang.StringBuilder

class StreamUtil {

    fun readStreamToString(iStream : InputStream?) : String {
        val resultBuilder = StringBuilder()

        // 스트림을 텍스트로
        val inputStreamReader = InputStreamReader(iStream)
        val bufferedReader = BufferedReader(inputStreamReader)

        var readLine : String? = bufferedReader.readLine()
        while (readLine != null) {
            resultBuilder.append(readLine + System.lineSeparator())
            readLine = bufferedReader.readLine()
        }

        bufferedReader.close()
        return resultBuilder.toString()
    }


    fun readStreamToImage(iStream: InputStream?) : Bitmap {
        val bitmap = BitmapFactory.decodeStream(iStream)
        return bitmap
    }

}