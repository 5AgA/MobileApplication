package ddwu.com.mobileapp.week06.moviexmlparsing.data

import ddwu.com.mobileapp.week06.naverxmlparsing.data.Movie
import ddwu.com.mobileapp.week06.naverxmlparsing.data.network.NetworkService

class MXPRepository(private val netService: NetworkService){

    suspend fun showDailyBoxOffice(date: String) : List<Movie> {
        return netService.getDailyBoxOffice(date)
    }

}