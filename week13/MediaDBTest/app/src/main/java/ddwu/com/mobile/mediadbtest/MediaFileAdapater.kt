package ddwu.com.mobile.mediadbtest

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import ddwu.com.mobile.mediadbtest.databinding.ListItemBinding

class MediaFileAdapater : RecyclerView.Adapter<MediaFileAdapater.MediaFileHolder>() {

    var medias : List<MediaDto>? = null
    var listener: OnItemClickListener? = null

    override fun getItemCount(): Int {
        return medias?.size ?: 0
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MediaFileHolder {
        val itemBinding = ListItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return MediaFileHolder(itemBinding)
    }


    override fun onBindViewHolder(holder: MediaFileHolder, position: Int) {
        holder.itemBinding.tvItem.text = medias?.get(position).toString()
        holder.itemBinding.clItem.setOnClickListener {
            listener?.onItemClick(holder.itemBinding.clItem, position)
        }
    }

    class MediaFileHolder(val itemBinding: ListItemBinding) : RecyclerView.ViewHolder(itemBinding.root)

    interface OnItemClickListener {
        fun onItemClick(v: View, position: Int)
    }

    public fun setOnItemClickListener(listener: OnItemClickListener) {
        this.listener = listener
    }

}