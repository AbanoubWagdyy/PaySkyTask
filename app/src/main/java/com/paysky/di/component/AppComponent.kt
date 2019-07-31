package com.paysky.di.component

import android.app.Application
import com.paysky.BaseApplication
import com.paysky.di.github.GithubViewModelsModule
import com.paysky.di.module.ActivityBuildersModule
import com.paysky.di.module.AppModule
import com.paysky.di.module.ViewModelFactoryModule
import dagger.BindsInstance
import dagger.Component
import dagger.android.AndroidInjector
import dagger.android.support.AndroidSupportInjectionModule
import javax.inject.Singleton


@Singleton
@Component(modules = [AndroidSupportInjectionModule::class, ActivityBuildersModule::class, AppModule::class, ViewModelFactoryModule::class])
interface AppComponent : AndroidInjector<BaseApplication> {

    @Component.Builder
    interface Builder {

        @BindsInstance
        fun application(application: Application): Builder

        fun build(): AppComponent
    }
}