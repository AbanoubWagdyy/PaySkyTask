package com.paysky.di.github;

import com.paysky.data.network.Resource;
import com.paysky.data.network.github.GithubReposAPI;
import dagger.Module;
import dagger.Provides;
import retrofit2.Retrofit;

@Module
public class GithubAuthModule {

    @Provides
    static GithubReposAPI provideGithubReposAPI(Retrofit retrofit) {
        return retrofit.create(GithubReposAPI.class);
    }
}