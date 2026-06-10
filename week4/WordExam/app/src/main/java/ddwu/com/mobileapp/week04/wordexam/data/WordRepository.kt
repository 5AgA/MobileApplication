package ddwu.com.mobileapp.week04.wordexam.data;

import kotlinx.coroutines.flow.Flow

class WordRepository(private val wordDao: WordDao) {
    val allWords : Flow<List<Word>> = wordDao.showAllWords()

    suspend fun addWord(word: Word) {
        wordDao.insertWord(word)
    }

    suspend fun editWord(word: Word) {
        wordDao.updateWord(word)
    }

    suspend fun deleteWord(word: Word) {
        wordDao.deleteWord(word)
    }

    suspend fun getWordMeaning(word: String) : String {
        val meaning = wordDao.getWordMeaning(word)
        return meaning
    }
}
