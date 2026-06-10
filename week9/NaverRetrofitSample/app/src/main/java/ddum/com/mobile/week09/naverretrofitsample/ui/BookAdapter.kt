package ddum.com.mobile.week09.naverretrofitsample.ui

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import ddum.com.mobile.week09.naverretrofitsample.data.network.Book
import ddum.com.mobile.week09.naverretrofitsample.databinding.ListItemBinding


class BookAdapter : RecyclerView.Adapter<BookAdapter.BookHolder>() {
    var books: List<Book>? = null

    override fun getItemCount(): Int {
        return books?.size ?: 0
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BookHolder {
        val itemBinding = ListItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return BookHolder(itemBinding)
    }

    override fun onBindViewHolder(holder: BookHolder, position: Int) {
        holder.itemBinding.tvItem.text = books?.get(position).toString()
        holder.itemBinding.clItem.setOnClickListener{
            clickListener?.onItemClick(it, position)
        }
    }

    class BookHolder(val itemBinding: ListItemBinding) : RecyclerView.ViewHolder(itemBinding.root)

    interface OnItemClickListener {
        fun onItemClick(view: View, position: Int)
    }

    var clickListener: OnItemClickListener? = null

    fun setOnItemClickListener(listener: OnItemClickListener) {
        this.clickListener = listener
    }

}