package ddwu.com.mobileapp.week06.naverparsing.data

import android.graphics.Bitmap

data class Book (
    var title: String?,
    var author: String?,
    var publisher: String?,
    var image: String?
) {
    override fun toString(): String {
        return "책 제목 : $title\n작가 : $author\n출판사 : $publisher\n이미지 : $image"
    }
}