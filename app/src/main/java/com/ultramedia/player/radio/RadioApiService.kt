package com.ultramedia.player.radio

import retrofit2.http.GET
import retrofit2.http.Query

interface RadioApiService {

    @GET("json/stations/search")
    suspend fun searchStations(
        @Query("name") name: String = "",
        @Query("limit") limit: Int = 100,
        @Query("offset") offset: Int = 0,
        @Query("order") order: String = "name",
        @Query("reverse") reverse: Boolean = false
    ): List<RadioStationResponse>

    @GET("json/stations/bycountry")
    suspend fun getStationsByCountry(
        @Query("country") country: String,
        @Query("limit") limit: Int = 100
    ): List<RadioStationResponse>

    @GET("json/stations/bygenre")
    suspend fun getStationsByGenre(
        @Query("genre") genre: String,
        @Query("limit") limit: Int = 100
    ): List<RadioStationResponse>

    @GET("json/countries")
    suspend fun getCountries(): List<CountryResponse>

    @GET("json/genres")
    suspend fun getGenres(): List<GenreResponse>

    @GET("json/stations/bytag")
    suspend fun getStationsByTag(
        @Query("tag") tag: String,
        @Query("limit") limit: Int = 100
    ): List<RadioStationResponse>
}

data class RadioStationResponse(
    val stationuuid: String,
    val name: String,
    val url: String,
    val favicon: String?,
    val country: String?,
    val countrycode: String?,
    val language: String?,
    val languagecodes: String?,
    val bitrate: Int,
    val codec: String?,
    val tags: String?,
    val votes: Int,
    val negativevotes: Int,
    val lastchangetime: String?,
    val hls: Boolean,
    val clickcount: Int,
    val clicktrend: Int
)

data class CountryResponse(
    val name: String,
    val iso_3166_1: String,
    val stationcount: Int
)

data class GenreResponse(
    val name: String,
    val stationcount: Int
)

data class RadioStation(
    val stationId: String,
    val name: String,
    val url: String,
    val favicon: String?,
    val country: String?,
    val language: String?,
    val bitrate: Int,
    val codec: String?,
    val tags: String?
) {
    companion object {
        fun fromResponse(response: RadioStationResponse): RadioStation {
            return RadioStation(
                stationId = response.stationuuid,
                name = response.name,
                url = response.url,
                favicon = response.favicon,
                country = response.country,
                language = response.language,
                bitrate = response.bitrate,
                codec = response.codec,
                tags = response.tags
            )
        }
    }
}
