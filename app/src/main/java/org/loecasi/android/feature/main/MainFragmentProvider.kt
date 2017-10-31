package org.loecasi.android.feature.main

import dagger.Module
import dagger.android.ContributesAndroidInjector
import org.loecasi.android.feature.main.home.HomeFragment
import org.loecasi.android.feature.main.home.HomeScreenModule

/**
 * Created by dani on 10/31/17.
 */
@Module
abstract class MainFragmentProvider {

    @ContributesAndroidInjector(modules = arrayOf(HomeScreenModule::class))
    abstract fun provideHomeFragmentFactory(): HomeFragment
}
