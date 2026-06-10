package ddwu.com.mobileapp.week06.naverparsing.data

import ddwu.com.mobileapp.week06.naverparsing.data.network.NetworkService

class NXPRepository(private val netService: NetworkService){

    fun showBooksByKeyword(keyword: String) : List<Book> {
        return netService.getBooksByKeyword(keyword)
    }

}