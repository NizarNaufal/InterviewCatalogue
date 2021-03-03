package id.technicaltest.favorite.di

import id.technicaltest.favorite.favorite.FavoriteViewModel
import id.technicaltest.core.utils.Const
import org.koin.android.viewmodel.dsl.viewModel
import org.koin.core.qualifier.named
import org.koin.dsl.module

val favoriteModule = module {
    scope(named(Const.VIEW_MODEL)) {
        viewModel { FavoriteViewModel(get()) }
    }
}
