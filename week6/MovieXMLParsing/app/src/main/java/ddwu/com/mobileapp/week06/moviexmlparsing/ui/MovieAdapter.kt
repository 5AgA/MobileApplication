package ddwu.com.mobileapp.week06.moviexmlparsing.ui

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import ddwu.com.mobileapp.week06.moviexmlparsing.databinding.ListItemBinding
import ddwu.com.mobileapp.week06.naverxmlparsing.data.Movie

class MovieAdapter : RecyclerView.Adapter<MovieAdapter.ItemHolder>(){

    var items: List<Movie>? = null

    override fun getItemCount(): Int = items?.size ?: 0

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemHolder {
        val itemBinding = ListItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ItemHolder(itemBinding)
    }

    override fun onBindViewHolder(holder: ItemHolder, position: Int) {
        holder.itemBinding.tvItem.text = (items?.get(position) as Movie).toString()
    }

    class ItemHolder(val itemBinding: ListItemBinding) : RecyclerView.ViewHolder(itemBinding.root)

}