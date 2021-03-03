package id.technicaltest.favorite.favorite

import androidx.lifecycle.*
import id.technicaltest.core.data.Resource
import id.technicaltest.core.domain.model.Show
import id.technicaltest.core.domain.usecase.ShowUseCase

class FavoriteViewModel(private val showUseCase: ShowUseCase) : ViewModel() {
    private val refreshTrigger = MutableLiveData(Unit)

    private var movieList = refreshTrigger.switchMap {
        showUseCase.getFavoriteMovieList().asLiveData()
    }

    private var seriesList = refreshTrigger.switchMap {
        showUseCase.getFavoriteSeriesList().asLiveData()
    }

    fun getFavoriteMovies(): LiveData<Resource<List<Show>>> = movieList

    fun getFavoriteSeries(): LiveData<Resource<List<Show>>> = seriesList

    fun refresh() {
        refreshTrigger.value = Unit
    }
}