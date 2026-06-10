package ddwu.com.mobileapp.week06.naverparsing.ui

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import ddwu.com.mobileapp.week06.naverparsing.data.Book
import ddwu.com.mobileapp.week06.naverparsing.databinding.ListItemBinding

class BookAdapter : RecyclerView.Adapter<BookAdapter.ItemHolder>(){

    var books: List<Book>? = null

    override fun getItemCount(): Int = books?.size ?: 0

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemHolder {
        val itemBinding = ListItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ItemHolder(itemBinding)
    }

    override fun onBindViewHolder(holder: ItemHolder, position: Int) {
        holder.itemBinding.tvItem.text = (books?.get(position) as Book).toString()
    }

    class ItemHolder(val itemBinding: ListItemBinding) : RecyclerView.ViewHolder(itemBinding.root)

}