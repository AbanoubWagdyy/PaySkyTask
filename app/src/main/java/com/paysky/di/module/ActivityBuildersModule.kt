package com.paysky.di.module

import com.paysky.di.github.GithubAuthModule
import com.paysky.di.github.GithubViewModelsModule
import com.paysky.ui.githubList.GithubActivity
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class ActivityBuildersModule {
    @ContributesAndroidInjector(modules = [GithubViewModelsModule::class , GithubAuthModule::class])
    internal abstract fun contributeAuthActivity(): GithubActivity
}