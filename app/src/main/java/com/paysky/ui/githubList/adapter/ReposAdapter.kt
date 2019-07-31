package com.paysky.ui.githubList.adapter

import androidx.recyclerview.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import com.bumptech.glide.RequestManager
import com.paysky.R
import com.paysky.data.model.Repository

class ReposAdapter(val requestManager: RequestManager) : RecyclerView.Adapter<ReposAdapter.ViewHolder>() {

    var repos: MutableList<Repository> = ArrayList<Repository>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            LayoutInflater.from(parent.context)
                .inflate(R.layout.repo_list_item, parent, false)
        )
    }

    override fun getItemCount(): Int {
        return repos.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val repo = repos[position]
        requestManager.load(repo.owner.avatar_url).into(holder.item_image)
        holder.ownerName.text = repo.owner.login
        holder.item_forks.text = repo.forks_count.toString()
        holder.item_language.text = repo.language
    }

    fun addRepos(repos: MutableList<Repository>?) {
        this.repos.addAll(repos!!)
    }

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val item_image = itemView.findViewById<ImageView>(R.id.item_image)
        val ownerName = itemView.findViewById<TextView>(R.id.ownerName)
        val item_forks = itemView.findViewById<TextView>(R.id.item_forks)
        val item_language = itemView.findViewById<TextView>(R.id.item_language)
    }
}