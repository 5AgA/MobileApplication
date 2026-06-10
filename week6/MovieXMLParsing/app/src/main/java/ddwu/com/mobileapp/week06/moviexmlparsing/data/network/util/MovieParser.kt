package ddwu.com.mobileapp.week06.moviexmlparsing.data.network.util

import android.util.Xml
import ddwu.com.mobileapp.week06.naverxmlparsing.data.Movie
import org.xmlpull.v1.XmlPullParser
import org.xmlpull.v1.XmlPullParserException
import java.io.IOException
import java.io.InputStream

class MovieParser {
    private val ns: String? = null

    companion object {
        /*Parsing 에 사용할 TAG 정적상수 선언*/
        val FAULT_RESULT = "faultResult"
        val UPPER_TAG = "dailyBoxOfficeList"
        val ITEM_TAG = "dailyBoxOffice"
        val RANK_TAG = "rank"
        val TITLE_TAG = "movieNm"
        val OPEN_DATE = "openDt"
    }

    @Throws(XmlPullParserException::class, IOException::class)
    fun parse(inputStream: InputStream?) : List<Movie> {
        inputStream.use { inputStream ->  // use : 범위 함수, close 자동 호출
            val parser : XmlPullParser = Xml.newPullParser()

            /*Parser 의 동작 정의, next() 호출 전 반드시 호출 필요*/
            parser.setFeature(XmlPullParser.FEATURE_PROCESS_NAMESPACES, false)

            /* Paring 대상이 되는 inputStream 설정 */
            parser.setInput(inputStream, null)

            /*Parsing 대상 태그의 상위 태그까지 이동*/
            while (parser.name != UPPER_TAG) {
                // 상위 태그를 만나면 멈춤
                parser.next()
            }

            return readBoxOffice(parser)
        }
    }

    @Throws(XmlPullParserException::class, IOException::class)
    private fun readBoxOffice(parser: XmlPullParser) : List<Movie> {
        val movies = mutableListOf<Movie>()

        parser.require(XmlPullParser.START_TAG, ns, UPPER_TAG)

        while(parser.next() != XmlPullParser.END_TAG) {
            // 열리는 태그 찾기
            if (parser.eventType != XmlPullParser.START_TAG) {
                continue
            }
            if (parser.name == ITEM_TAG) {
                movies.add( readDailyBoxOffice(parser) )
            } else {
                skip(parser)
            }
        }

        return movies
    }


    @Throws(XmlPullParserException::class, IOException::class)
    private fun readDailyBoxOffice(parser: XmlPullParser) : Movie {
        parser.require(XmlPullParser.START_TAG, ns, ITEM_TAG)

        /*Parsing 한 TEXT 값을 저장할 변수 선언*/
        var rank : Int? = null
        var title : String? = null
        var openDate : String? = null

        while (parser.next() != XmlPullParser.END_TAG) {
            if (parser.eventType != XmlPullParser.START_TAG) {
                continue
            }
            when (parser.name) {
                /*TAG 명에 따라 변수에 TEXT 저장*/
                RANK_TAG -> rank = readTextInTag(parser, RANK_TAG).toInt()
                TITLE_TAG -> title = readTextInTag(parser, TITLE_TAG)
                OPEN_DATE -> openDate = readTextInTag(parser, OPEN_DATE)

                else -> skip(parser)
            }
        }
        return Movie(rank, title, openDate)
    }


    @Throws(IOException::class, XmlPullParserException::class)
    private fun readTextInTag (parser: XmlPullParser, tag: String): String {
        parser.require(XmlPullParser.START_TAG, ns, tag)
        var text = ""
        if (parser.next() == XmlPullParser.TEXT) {
            text = parser.text
            parser.nextTag()
        }
        parser.require(XmlPullParser.END_TAG, ns, tag)
        return text
    }


    @Throws(XmlPullParserException::class, IOException::class)
    private fun skip(parser: XmlPullParser) {
        if (parser.eventType != XmlPullParser.START_TAG) {
            throw IllegalStateException()
        }
        var depth = 1
        while (depth != 0) {
            when (parser.next()) {
                XmlPullParser.END_TAG -> depth--
                XmlPullParser.START_TAG -> depth++
            }
        }
    }
}