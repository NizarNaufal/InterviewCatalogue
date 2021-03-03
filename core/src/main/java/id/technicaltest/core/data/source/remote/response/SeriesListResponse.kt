package id.technicaltest.core.data.source.remote.response

import com.google.gson.annotations.SerializedName

data class SeriesListResponse(

    @SerializedName("results")
    val seriesList: List<SeriesResponse>

)