package com.paysky.ui.githubList

import androidx.lifecycle.*
import com.paysky.data.model.Repository
import com.paysky.data.network.Resource
import com.paysky.data.network.Resource.Companion.STATUS_ERROR
import com.paysky.data.network.Resource.Companion.STATUS_SUCCESS
import com.paysky.data.network.github.GithubReposAPI
import io.reactivex.schedulers.Schedulers

import javax.inject.Inject

class GithubViewModel
@Inject constructor(private val api: GithubReposAPI) : ViewModel() {

    internal var page = 1
    internal var items_per_page = 15

    private val repositoriesLiveData = MediatorLiveData<Resource<List<Repository>>>()

    init {
        getRepos()
    }

    fun getRepos(): LiveData<Resource<List<Repository>>> {

        val result = MediatorLiveData<Resource<List<Repository>>>()

        val source = LiveDataReactiveStreams.fromPublisher(
            api.getRepositories(page, items_per_page)
                .subscribeOn(Schedulers.io())
        )

        result.addSource(source) { items ->
            items?.let {
                if (it.isEmpty())
                    result.value = Resource.zeroItems(items)
                else
                    result.value = Resource.success(items)
            }
            result.removeSource(source)
        }

        val status = MutableLiveData<Int>()

        result.addSource(status) { st: Int? ->
            st?.let {
                when (it) {
                    STATUS_ERROR -> result.value = Resource.error(result.value?.data)
                    STATUS_SUCCESS -> {
                        result.value?.data?.let {
                            result.value = Resource.success(it)
                        }
                    }
                    else -> {
                        result.value = Resource.error(result.value?.data)
                    }
                }
            }
        }
        return result
    }

    fun getMoreRepos(): LiveData<Resource<List<Repository>>> {
        page += 1
        return getRepos()
    }

    companion object {
        private val TAG = "GithubViewModel"
    }
}
