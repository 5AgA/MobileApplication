package ddum.com.mobile.week09.naverretrofitsample.data.network

import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Query

interface INaverBookSearch {

    @GET("v1/search/book.json")
    suspend fun getBooksByKeyword(
        @Query("query") query: String,
        @Header("X-Naver-Client-Id") clientID: String,
        @Header("X-Naver-Client-Secret") clientSecret: String,
    ) : BookRoot
}