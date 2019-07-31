package com.paysky.ui.githubList

import android.os.Bundle
import android.view.View
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.malinskiy.superrecyclerview.OnMoreListener
import com.paysky.R
import com.paysky.data.model.Repository
import com.paysky.viewModels.ViewModelProviderFactory
import dagger.android.support.DaggerAppCompatActivity
import kotlinx.android.synthetic.main.activity_main.*
import javax.inject.Inject
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.LinearLayoutManager
import com.bumptech.glide.RequestManager
import com.google.android.material.snackbar.Snackbar
import com.paysky.data.network.Resource
import com.paysky.ui.githubList.adapter.ReposAdapter
import com.paysky.utils.PaddingItemDecoration


class GithubActivity : DaggerAppCompatActivity(), Observer<Resource<List<Repository>>>, OnMoreListener {

    override fun onChanged(t: Resource<List<Repository>>?) {
        if (t?.status == Resource.STATUS_ERROR) {
            Snackbar.make(findViewById(android.R.id.content), "Error !.", Snackbar.LENGTH_SHORT).show()
        } else if (t?.data != null && t?.data?.isNotEmpty()!!) {
            mAdapter.addRepos(t?.data as MutableList<Repository>?)
            mAdapter.notifyDataSetChanged()
        } else {
//            recyclerRepos.setOnMoreListener(null)
//            recyclerRepos.removeMoreListener()
//            recyclerRepos.isLoadingMore = false
//            recyclerRepos.progressView.visibility = View.GONE
            recyclerRepos.removeMoreListener()
            recyclerRepos.hideMoreProgress()
        }
    }

    @Inject
    lateinit var viewModelProviderFactory: ViewModelProviderFactory

    @Inject
    lateinit var requestManager: RequestManager

    lateinit var mAdapter: ReposAdapter

    lateinit var githubViewModel: GithubViewModel

    private var mLayoutManager: RecyclerView.LayoutManager? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        initui()
        initViewModelObserver()
    }

    private fun initui() {
        mLayoutManager = LinearLayoutManager(applicationContext)
        recyclerRepos.setLayoutManager(mLayoutManager)
        recyclerRepos.addItemDecoration(PaddingItemDecoration())
        recyclerRepos.setupMoreListener(this, 3)
        mAdapter = ReposAdapter(requestManager)
        recyclerRepos.adapter = mAdapter
    }

    private fun initViewModelObserver() {
        githubViewModel = ViewModelProviders.of(this, viewModelProviderFactory)
            .get(GithubViewModel::class.java)

        githubViewModel.getRepos().observe(this, this)
    }

    override fun onMoreAsked(overallItemsCount: Int, itemsBeforeMore: Int, maxLastVisiblePosition: Int) {
        githubViewModel.getMoreRepos().observe(this, this)
    }
}
