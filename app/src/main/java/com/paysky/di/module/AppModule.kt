package com.paysky.di.module

import android.app.Application
import android.graphics.drawable.Drawable
import androidx.core.content.ContextCompat
import com.bumptech.glide.Glide
import com.bumptech.glide.RequestManager
import com.bumptech.glide.request.RequestOptions
import com.paysky.R
import com.paysky.ui.githubList.adapter.ReposAdapter
import com.paysky.utils.Constants
import dagger.Module
import dagger.Provides
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

import javax.inject.Singleton

@Module
class AppModule {

    @Singleton
    @Provides
    internal fun provideRetrofitInstance(): Retrofit {
        return Retrofit.Builder()
            .baseUrl(Constants.BASE_URL)
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    @Singleton
    @Provides
    internal fun provideRequestOptions(): RequestOptions {
        return RequestOptions
            .placeholderOf(R.mipmap.ic_launcher)
            .error(R.drawable.ic_launcher_background)
    }

    @Singleton
    @Provides
    internal fun provideRequestManager(application: Application, options: RequestOptions): RequestManager {
        return Glide.with(application)
            .setDefaultRequestOptions(options)
    }

    @Singleton
    @Provides
    internal fun provideAppDrawable(application: Application): Drawable? {
        return ContextCompat.getDrawable(application, R.mipmap.ic_launcher)
    }
}
