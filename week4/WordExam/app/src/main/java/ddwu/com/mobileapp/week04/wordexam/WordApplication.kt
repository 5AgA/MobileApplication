package ddwu.com.mobileapp.week04.wordexam

import android.app.Application
import ddwu.com.mobileapp.week04.wordexam.data.WordDatabase
import ddwu.com.mobileapp.week04.wordexam.data.WordRepository

class WordApplication: Application() {
    val wordDatabase by lazy {
        WordDatabase.getDatabase(this)
    }

    val wordDao by lazy {
        wordDatabase.wordDao()
    }

    val wordRepo by lazy {
        WordRepository(wordDatabase.wordDao())
    }
}