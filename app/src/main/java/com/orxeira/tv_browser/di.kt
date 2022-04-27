package com.orxeira.tv_browser

import android.app.Application
import androidx.room.Room
import com.orxeira.data.PermissionChecker
import com.orxeira.data.RegionRepository
import com.orxeira.data.TvShowRepository
import com.orxeira.data.datasource.LocationDataSource
import com.orxeira.data.datasource.TvShowLocalDataSource
import com.orxeira.data.datasource.TvShowRemoteDataSource
import com.orxeira.tv_browser.data.AndroidPermissionChecker
import com.orxeira.tv_browser.data.PlayServicesLocationDataSource
import com.orxeira.tv_browser.data.database.TvShowDatabase
import com.orxeira.tv_browser.data.database.TvShowRoomDataSource
import com.orxeira.tv_browser.data.server.TvShowServerDataSource
import com.orxeira.tv_browser.ui.detail.DetailViewModel
import com.orxeira.tv_browser.ui.main.MainViewModel
import com.orxeira.usecases.FindTvShowUseCase
import com.orxeira.usecases.GetPopularTvShowsUseCase
import com.orxeira.usecases.RequestPopularTvShowsUseCase
import org.koin.android.ext.koin.androidApplication
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.core.context.startKoin
import org.koin.core.logger.Level
import org.koin.core.qualifier.named
import org.koin.dsl.module

fun Application.initDI() {
    startKoin {
        androidLogger(Level.ERROR)
        androidContext(this@initDI)
        modules(appModule, dataModule, useCasesModule)
    }
}

private val appModule = module {
    single(named("api_key")) { androidApplication().getString(R.string.api_key) }
    single {
        Room.databaseBuilder(
            get(),
            TvShowDatabase::class.java, "tvShow-db"
        ).build()
    }

    single { get<TvShowDatabase>().tvShowDao() }

    factory<TvShowLocalDataSource> { TvShowRoomDataSource(get()) }
    factory<TvShowRemoteDataSource> { TvShowServerDataSource(get(named("api_key"))) }

    factory<LocationDataSource> { PlayServicesLocationDataSource(get()) }
    factory<PermissionChecker> { AndroidPermissionChecker(get()) }

    viewModel { MainViewModel(get(), get()) }
    viewModel { (id: Int) -> DetailViewModel(id, get()) }
}
private val dataModule = module {
    factory { RegionRepository(get(), get()) }
    factory { TvShowRepository(get(), get(), get()) }
}
private val useCasesModule = module {
    factory { GetPopularTvShowsUseCase(get()) }
    factory { RequestPopularTvShowsUseCase(get()) }
    factory { FindTvShowUseCase(get()) }
}