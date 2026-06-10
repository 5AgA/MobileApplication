package ddwu.com.mobileapp.week07.retrofittest.data.network

import android.content.Context
import android.util.Log
import ddwu.com.mobileapp.week07.retrofittest.R
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class RefService(val context: Context) {
    val TAG = "RefService"
    val movieService: IBoxOfficeService

    init {
        val retrofit : Retrofit = Retrofit.Builder()
            .baseUrl(context.resources.getString(R.string.kobis_url))
            .addConverterFactory( GsonConverterFactory.create() )
            .build()

        movieService = retrofit.create(IBoxOfficeService::class.java)
    }

    suspend fun getMovies(key: String, date: String)  : List<Movie>?   {
        val root = movieService.getDailyBoxOffice("json", key, date)

        return root.movieResult.movies
    }

}