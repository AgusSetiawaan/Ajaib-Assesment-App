package com.example.ajaibassesmentapp.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.ajaibassesmentapp.data.GithubUserData
import com.example.ajaibassesmentapp.data.GithubUserRepos
import com.example.ajaibassesmentapp.databinding.ItemHeaderDetailUserBinding
import com.example.ajaibassesmentapp.databinding.ItemListRepositoryBinding

class GithubUserDetailAdapter(private val data: GithubUserData): RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    private val TYPE_HEADER = 0
    private val TYPE_REPOS = 1

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return when(viewType){
            TYPE_HEADER ->{
                val binding = ItemHeaderDetailUserBinding.inflate(LayoutInflater.from(parent.context), parent, false)
                ItemHeaderViewHolder(parent.context, binding)
            }
            else -> {
                val binding = ItemListRepositoryBinding.inflate(LayoutInflater.from(parent.context), parent, false)
                ItemListRepositoryViewHolder(parent.context, binding)
            }
        }
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        if(position == 0){
            (holder as ItemHeaderViewHolder).bind(data)
        }
        else{
            (holder as ItemListRepositoryViewHolder).bind(data.photoUrl, data.listRepos[position -1])
        }
    }

    override fun getItemViewType(position: Int): Int {
        return if(position == 0) TYPE_HEADER
        else TYPE_REPOS
    }

    override fun getItemCount(): Int = data.listRepos.size + 1

    class ItemHeaderViewHolder(private val context: Context, private val binding: ItemHeaderDetailUserBinding): RecyclerView.ViewHolder(binding.root){

        fun bind(data: GithubUserData){
            binding.name.text = data.name
            binding.username.text = "@${data.username}"
            binding.city.text = data.location
            binding.email.text = data.email
            binding.titleName.text = data.company
            binding.tvFollowers.text = "${data.followers} Followers"
            binding.tvFollowing.text = "${data.following} Following"

            Glide.with(context).load(data.photoUrl).into(binding.userAvatar)
        }

    }

    class ItemListRepositoryViewHolder(private val context: Context, private val binding: ItemListRepositoryBinding): RecyclerView.ViewHolder(binding.root){

        fun bind(photoUrl: String, item: GithubUserRepos){
            Glide.with(context).load(photoUrl).into(binding.userAvatar)

            binding.repoTitle.text = item.name
            binding.repoDescription.text = item.description
            binding.likeCount.text = "${item.stargazerCount}"
            binding.updatedAt.text = item.updatedAt
        }
    }
}