package com.example.journeyordestination.view


import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.example.journeyordestination.R
import com.example.journeyordestination.databinding.ItemViewBinding

const val TAG = "adapter"

class DestinationAdapter(
    private val removeItem: (int: Int) -> Unit
) : androidx.recyclerview.widget.ListAdapter<String, DestinationAdapter.DestinationViewholder>(
    DiffUtilCallback
) {

    object DiffUtilCallback : DiffUtil.ItemCallback<String>() {
        override fun areItemsTheSame(oldItem: String, newItem: String): Boolean {
            return oldItem == newItem
        }

        override fun areContentsTheSame(oldItem: String, newItem: String): Boolean {
            return oldItem == newItem
        }
    }

    inner class DestinationViewholder(
        private val binding: ItemViewBinding
    ) : ViewHolder(binding.root) {

        init {
            val context = binding.textView.context
            binding.textView.setOnClickListener { createAlertDialog(context) }
        }

        fun createAlertDialog(context: Context?) {
            context?.let {
                androidx.appcompat.app.AlertDialog.Builder(it).setIcon(R.drawable.ic_delete_row)
                    .setMessage("Are you sure you want to delete this Item?")
                    .setPositiveButton("Yes") { _, _ ->
                        removeItem(bindingAdapterPosition)
                        notifyItemRemoved(bindingAdapterPosition)
                    }.setNegativeButton("Cancel") { _, _ ->
                    }.create().show()
            }
        }

        fun bind(duration: String) {
            binding.textView.text = duration
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DestinationViewholder {
        return DestinationViewholder(
            DataBindingUtil.inflate(
                LayoutInflater.from(parent.context), R.layout.item_view, parent, false
            )
        )
    }

    override fun onBindViewHolder(holder: DestinationViewholder, position: Int) {

        holder.bind(getItem(position))
    }


}




