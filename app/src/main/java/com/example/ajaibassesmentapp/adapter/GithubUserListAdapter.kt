package com.example.ajaibassesmentapp.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.ajaibassesmentapp.data.GithubUserData
import com.example.ajaibassesmentapp.databinding.ItemListUserBinding

class GithubUserListAdapter(private val onClickUser: (GithubUserData) -> Unit): ListAdapter<GithubUserData, GithubUserListAdapter.ItemListUserViewHolder>(
    DIFF_CALLBACK
) {

    class ItemListUserViewHolder(private val context: Context,
                                 private val binding: ItemListUserBinding,
                                 private val onClickUser: (GithubUserData) -> Unit
    ): RecyclerView.ViewHolder(binding.root){

        fun bind(item: GithubUserData){
            binding.name.text = item.name
            binding.username.text = "@${item.username}"
            binding.city.text = item.location
            binding.email.text = item.email
            binding.titleName.text = item.company

            Glide.with(context).load(item.photoUrl).into(binding.userAvatar)

            binding.cardView.setOnClickListener {
                onClickUser(item)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemListUserViewHolder {
        val binding = ItemListUserBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ItemListUserViewHolder(parent.context, binding, onClickUser)
    }

    override fun onBindViewHolder(holder: ItemListUserViewHolder, position: Int) {
        val item = getItem(position)
        if(item != null){
            holder.bind(item)
        }
    }

    companion object {
        val DIFF_CALLBACK = object : DiffUtil.ItemCallback<GithubUserData>() {
            override fun areItemsTheSame(oldItem: GithubUserData, newItem: GithubUserData): Boolean {
                return oldItem == newItem
            }

            override fun areContentsTheSame(oldItem: GithubUserData, newItem: GithubUserData): Boolean {
                return oldItem.id == newItem.id
            }
        }
    }
}