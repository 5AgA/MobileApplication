package ddwu.com.mobileapp.week06.naverparsing.data.network

import android.content.Context
import ddwu.com.mobileapp.week06.naverparsing.R
import ddwu.com.mobileapp.week06.naverparsing.data.Book
import ddwu.com.mobileapp.week06.naverparsing.data.network.util.NaverBookParser
import ddwu.com.mobileapp.week06.naverparsing.data.network.util.NetworkUtil
import java.net.URLEncoder

class NetworkService(private val context: Context) {
    fun getBooksByKeyword(keyword: String) : List<Book> {
        val address = context.resources.getString(R.string.naver_url)
        val params = HashMap<String, String>()
        params["d_titl"] = URLEncoder.encode(keyword, "UTF-8")

        val result = try {
            NetworkUtil(context).sendRequest(NetworkUtil.GET, address, params)
        } catch(e: Exception) {
            e.printStackTrace()
            null
        }

        return if (result != null) {
            NaverBookParser().parse(result)
        } else {
            // 네트워크 요청 실패 시 빈 리스트 반환
            emptyList()
        }
    }

}