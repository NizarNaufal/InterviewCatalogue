package id.technicaltest.interviewcatalogue.view.movie.details

import androidx.lifecycle.*
import id.technicaltest.core.data.Resource
import id.technicaltest.core.data.source.local.entity.DoubleTrigger
import id.technicaltest.core.domain.model.Show
import id.technicaltest.core.domain.usecase.ShowUseCase
import id.technicaltest.core.utils.Const
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class DetailViewModel(private val showUseCase: ShowUseCase) : ViewModel() {
    //Value if already shimmer or not
    private var isAlreadyShimmer: Boolean = false

    //Trigger by showId and showType to load detail and similar list
    private var doubleTrigger = MutableLiveData<DoubleTrigger>()

    //Trigger when similar list is empty
    private var listEmptyTrigger = MutableLiveData<Unit>()

    //Get detail show
    private var show = doubleTrigger.switchMap {
        when (it.showType) {
            Const.MOVIE_TYPE ->
                showUseCase.getMovieDetail(it.showId).asLiveData()
            else ->
                showUseCase.getSeriesDetail(it.showId).asLiveData()
        }
    }

    //Get similar list
    private var similarList = doubleTrigger.switchMap {
        when (it.showType) {
            Const.MOVIE_TYPE ->
                showUseCase.getSimilarMovieList(it.showId).asLiveData()
            else ->
                showUseCase.getSimilarSeriesList(it.showId).asLiveData()
        }
    }

    private var popularReview = doubleTrigger.switchMap {
        when (it.showType) {
            Const.MOVIE_TYPE ->
                showUseCase.getReviewDetail(it.showId).asLiveData()
            else ->
                showUseCase.getSimilarSeriesList(it.showId).asLiveData()
        }
    }
    //Get popular list
    //Triggered when similar list is empty
    private var popularList = listEmptyTrigger.switchMap {
        val showType = doubleTrigger.value?.showType
        val page = 1

        when (showType) {
            Const.MOVIE_TYPE ->
                showUseCase.getMovieList(page).asLiveData()
            else ->
                showUseCase.getSeriesList(page).asLiveData()
        }
    }


    fun setAlreadyShimmer() {
        isAlreadyShimmer = true
    }

    fun setShowIdAndType(show_id: String, show_type: Int) {
        doubleTrigger.postValue(DoubleTrigger(show_id, show_type))
    }

    fun setListEmptyTrigger() {
        listEmptyTrigger.postValue(Unit)
    }

    fun setFavorite(show: Show) {
        viewModelScope.launch(Dispatchers.IO) {
            show.isFavorite = if (show.isFavorite == 0) 1 else 0
            showUseCase.setFavorite(show)
        }
    }

    fun getShow(): LiveData<Resource<Show>> = show

    fun getIsAlreadyShimmer() = isAlreadyShimmer

    fun getSimilarList(): LiveData<Resource<List<Show>>> = similarList

    fun getPopularList(): LiveData<Resource<List<Show>>> = popularList

    fun getReviewList(): LiveData<Resource<List<Review>>> = popularReview


}