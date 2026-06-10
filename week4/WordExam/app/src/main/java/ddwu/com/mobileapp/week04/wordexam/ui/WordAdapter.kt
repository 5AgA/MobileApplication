package ddwu.com.mobileapp.week04.wordexam.ui

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import ddwu.com.mobileapp.week04.wordexam.data.Word
import ddwu.com.mobileapp.week04.wordexam.databinding.ListWordBinding

class WordAdapter(var words: List<Word>) : RecyclerView.Adapter<WordAdapter.WordViewHolder> () {

    override fun getItemCount(): Int = words.size

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): WordViewHolder {
        val wordBinding = ListWordBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return WordViewHolder(wordBinding)
    }

    override fun onBindViewHolder(holder: WordViewHolder, position: Int) {
        holder.wordBinding.tvWord.text = words[position].toString()
        holder.wordBinding.root.setOnClickListener {
            wordClickListener?.onWordClick(it, position)
        }
    }

    class WordViewHolder(val wordBinding: ListWordBinding) : RecyclerView.ViewHolder(wordBinding.root)

    interface OnWordClickListener {
        fun onWordClick(view: View, pos: Int)
    }

    var wordClickListener: OnWordClickListener? = null

    fun setOnWordClickListener (listener: OnWordClickListener?) {
        wordClickListener = listener
    }



}