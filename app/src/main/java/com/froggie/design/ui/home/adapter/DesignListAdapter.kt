package com.froggie.design.ui.home.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.airbnb.lottie.LottieDrawable
import com.bumptech.glide.Glide
import com.froggie.design.databinding.ItemDesignBinding
import com.froggie.design.repository.data.Result

class DesignListAdapter :
    ListAdapter<Result, DesignListAdapter.DesignListViewHolder>(ItemDiffUtil()) {

    private lateinit var clickListener: OnItemClickListener

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DesignListViewHolder {
        val binding = ItemDesignBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return DesignListViewHolder(binding)
    }

    override fun onBindViewHolder(holder: DesignListViewHolder, position: Int) {
        val result = getItem(position)
        if (result != null) {
            holder.bind(result)
            registerOnClickListener(holder, position)
        }
    }

    private fun registerOnClickListener(holder: DesignListViewHolder, position: Int) {
        holder.itemView.setOnClickListener {
            if (position != RecyclerView.NO_POSITION) {
                val item = getItem(position)
                clickListener.onItemClick(item)
            }
        }
    }


    fun setItemClickListener(listener: OnItemClickListener) {
        this.clickListener = listener
    }

    inner class DesignListViewHolder(private val itemDesignBinding: ItemDesignBinding) :
        RecyclerView.ViewHolder(itemDesignBinding.root) {
        fun bind(result: Result) {
            itemDesignBinding.apply {
                Glide.with(itemView.context).load(result.createdBy.avatarUrl)
                    .into(itemDesignBinding.ivCreatedBy)
                ivMainDesign.imageAssetsFolder = "images/"
                ivMainDesign.setCacheComposition(true)
                ivMainDesign.setAnimationFromUrl(result.lottieUrl)
                ivMainDesign.repeatCount = LottieDrawable.INFINITE
                ivMainDesign.playAnimation()
                creatorName.text = result.createdBy.name
                designName.text = result.name
            }
        }
    }

    class ItemDiffUtil : DiffUtil.ItemCallback<Result>() {
        override fun areItemsTheSame(oldItem: Result, newItem: Result): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: Result, newItem: Result): Boolean {
            return oldItem.hashCode() == newItem.hashCode()
        }
    }

    interface OnItemClickListener {
        fun onItemClick(designData: Result)
    }
}