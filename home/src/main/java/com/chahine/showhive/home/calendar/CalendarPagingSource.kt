package com.chahine.showhive.home.calendar

import androidx.paging.PagingSource
import com.chahine.trakt.api.TraktApiClient
import com.chahine.trakt.entities.CalendarShowEntry
import com.chahine.trakt.entities.Extended
import okio.IOException
import retrofit2.HttpException
import java.time.ZonedDateTime
import java.time.format.DateTimeFormatter.ofPattern
import javax.inject.Inject

class CalendarPagingSource @Inject constructor(
    private val traktApiClient: TraktApiClient,
) : PagingSource<ZonedDateTime, CalendarShowEntry>() {

    companion object {
        private const val DATE_RANGE = 30L
        private const val MAX_DATE_RANGE = 90L

        private val DATE_FORMATTER = ofPattern("yyyy-MM-dd")
    }

    override suspend fun load(params: LoadParams<ZonedDateTime>): LoadResult<ZonedDateTime, CalendarShowEntry> {
        val page = params.key ?: ZonedDateTime.now()
        val startDate = page.format(DATE_FORMATTER)

        return try {
            val now = ZonedDateTime.now()
            val response = traktApiClient.myShows(startDate, DATE_RANGE.toInt(), Extended.FULL)
            LoadResult.Page(
                data = response,
                prevKey = if (now.minusDays(MAX_DATE_RANGE).isBefore(page)) page.minusDays(DATE_RANGE) else null,
                nextKey = if (now.plusDays(MAX_DATE_RANGE).isAfter(page)) page.plusDays(DATE_RANGE) else null
            )
        } catch (exception: IOException) {
            LoadResult.Error(exception)
        } catch (exception: HttpException) {
            LoadResult.Error(exception)
        }
    }
}