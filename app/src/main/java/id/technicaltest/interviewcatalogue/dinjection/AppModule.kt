package id.technicaltest.interviewcatalogue.dinjection

import id.technicaltest.core.domain.usecase.ShowInteractor
import id.technicaltest.core.domain.usecase.ShowUseCase
import id.technicaltest.core.utils.Const
import id.technicaltest.interviewcatalogue.view.movie.MovieViewModel
import id.technicaltest.interviewcatalogue.view.movie.details.DetailViewModel
import id.technicaltest.interviewcatalogue.view.movie.series.SeriesViewModel
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.FlowPreview
import org.koin.android.viewmodel.dsl.viewModel
import org.koin.core.qualifier.named
import org.koin.dsl.module

val useCaseModule = module {
    factory<ShowUseCase> { ShowInteractor(get()) }
}

@ExperimentalCoroutinesApi
@FlowPreview
val viewModelModule = module {
    scope(named(Const.VIEW_MODEL)) {
        viewModel { MovieViewModel(get()) }
        viewModel { SeriesViewModel(get()) }
        viewModel { DetailViewModel(get()) }
    }

}