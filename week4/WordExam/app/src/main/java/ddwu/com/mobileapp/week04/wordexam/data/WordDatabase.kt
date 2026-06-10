package ddwu.com.mobileapp.week04.wordexam.data

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [Word::class], version=1)
abstract class  WordDatabase : RoomDatabase() {
    abstract fun wordDao(): WordDao

    companion object {
        @Volatile
        private var INSTANCE: WordDatabase?= null

        fun getDatabase(context: Context) : WordDatabase {
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext, WordDatabase::class.java, "words.db"
                ).build()
                INSTANCE = instance
                instance
            }
        }
    }

}