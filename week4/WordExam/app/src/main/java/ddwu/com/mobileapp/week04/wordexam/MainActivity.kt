 package ddwu.com.mobileapp.week04.wordexam

import android.os.Bundle
import android.text.Editable
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import ddwu.com.mobileapp.week04.wordexam.databinding.ActivityMainBinding
import ddwu.com.mobileapp.week04.wordexam.data.Word
import ddwu.com.mobileapp.week04.wordexam.ui.WordAdapter
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.launch

 class MainActivity : AppCompatActivity() {

    val binding by lazy {
        ActivityMainBinding.inflate(layoutInflater)
    }

    val wordRepo by lazy {
        (application as WordApplication).wordRepo
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
//        enableEdgeToEdge()
        setContentView(binding.root)
//        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
//            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
//            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
//            insets
//        }

        // Adapter 구현
        val adapter = WordAdapter(ArrayList<Word>())
        val layoutManager = LinearLayoutManager(this)
        layoutManager.orientation = LinearLayoutManager.VERTICAL

        adapter.setOnWordClickListener(object: WordAdapter.OnWordClickListener {
            override fun onWordClick(view: View, pos: Int) {
                // 5. rvWords 에서 클릭한 단어로 wordDao를 사용하여  DB에서 의미 검색 후 의미 칸에 표시

                // 어댑터에서 포지션에 해당하는 단어 갖고 오기
                val selectedWord = adapter.words[pos]

                // editText에 단어와 의미 각각 추가
                binding.etWord.text = Editable.Factory.getInstance().newEditable(selectedWord.word)
                binding.etMeaning.text = Editable.Factory.getInstance().newEditable(selectedWord.meaning)

            }
        })

        // layoutManager 와 adapter 연결
        binding.rvWords.layoutManager = layoutManager
        binding.rvWords.adapter = adapter

        // 2. wordDao 객체에서 전체 word 를 가져와 rvWords(RecyclerView) 에 지정
        // Flow<List<Word>> 를 사용하여 갱신 정보를 자동 반영하도록 구성
        val wordFlow = wordRepo.allWords

        // 코루틴 Dispatchers.Main
        CoroutineScope(Dispatchers.Main).launch {
            // 변경 사항 있을 경우에만 작동
            wordFlow.distinctUntilChanged().collect { words ->
                // 어댑터에 리스트 갱신
                adapter.words = words

                // 어댑터의 데이터 변경 사실 알리기
                adapter.notifyDataSetChanged()
            }
        }



        // 3. 화면에 입력한 단어와 의미를 읽어와 Word 로 만든 후 wordDao 를 사용하여 DB 저장
        binding.btnSave.setOnClickListener {
            // edit 칸에 적은 단어와 의미 각각 불러오기
            val w = binding.etWord.text.toString()
            val m = binding.etMeaning.text.toString()

            // 코루틴 Dispatchers.IO
            CoroutineScope(Dispatchers.IO).launch {
                wordRepo.addWord(Word(w, m))
            }
        }

        // 4. 화면에 입력한 단어로 Word 로 만든 후(의미는 빈문자열) wordDao 를 사용하여 DB 삭제
        binding.btnDelete.setOnClickListener {
            // edit 칸에 적은 단어 불러오기
            val w = binding.etWord.text.toString()

            // 코루틴 Dispatchers.IO
            CoroutineScope(Dispatchers.IO).launch {
                // edit 칸에 의미가 없으므로 word에 해당하는 의미 불러와서
                val m = wordRepo.getWordMeaning(w)

                // Word 객체 만들고 해당 객체를 리스트에서 삭제
                wordRepo.deleteWord(Word(w, m))
            }
        }

        // 수정 버튼
        binding.btnEdit.setOnClickListener {
            // edit 칸에 적은 단어와 의미 각각 불러오기
            val w = binding.etWord.text.toString()
            val m = binding.etMeaning.text.toString()

            // 코루틴 Dispatchers.IO
            CoroutineScope(Dispatchers.IO).launch {
                wordRepo.editWord(Word(w, m))
            }
        }

    }

}