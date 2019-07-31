package com.paysky.di.github

import androidx.lifecycle.ViewModel
import com.paysky.di.ViewModelKey
import com.paysky.ui.githubList.GithubViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module
abstract class GithubViewModelsModule {
    @Binds
    @IntoMap
    @ViewModelKey(GithubViewModel::class)
    abstract fun bindGithubViewModel(viewModel: GithubViewModel): ViewModel
}