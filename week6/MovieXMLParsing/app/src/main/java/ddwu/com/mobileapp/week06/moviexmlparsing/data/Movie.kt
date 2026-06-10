package ddwu.com.mobileapp.week06.naverxmlparsing.data

data class Movie(
    /* Parsing 결과를 저장할 Movie 의 멤버 선언 */
    var rank: Int?,
    var title: String?,
    var openDate: String?
) {
    override fun toString(): String {
        return "$rank : $title ($openDate)"
    }
}