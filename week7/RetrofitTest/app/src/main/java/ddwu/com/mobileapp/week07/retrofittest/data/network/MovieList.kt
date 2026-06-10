package ddwu.com.mobileapp.week07.retrofittest.data.network

import com.google.gson.annotations.SerializedName

/*data class 를 사용하여 DTO 작성*/
data class Root(
    @SerializedName("boxOfficeResult")
    val movieResult: BoxOfficeResult,
)

data class BoxOfficeResult(
    @SerializedName("dailyBoxOfficeList")
    val movies: List<Movie>
)

data class Movie(
    val rank: String,
    @SerializedName("movieNm")
    val title: String,
    @SerializedName("openDt")
    val openDate: String
)



