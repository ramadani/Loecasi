package org.loecasi.android.di

import android.app.Application
import android.content.Context
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

/**
 * Created by dani on 10/25/17.
 */
@Module
class AppModule {

    @Provides
    @Singleton
    fun provideContext(app: Application): Context = app
}
