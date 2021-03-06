package com.chahine.showhive.home.discover

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.chahine.trakt.api.entities.TrendingShow
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class DiscoverRepository @Inject constructor(
    private val pagingSource: DiscoverPagingSource
) {

    companion object {
        private const val PAGE_SIZE = 10
    }

    fun trending(): Flow<PagingData<TrendingShow>> {
        return Pager(
            config = PagingConfig(
                pageSize = PAGE_SIZE,
                enablePlaceholders = false,
                initialLoadSize = PAGE_SIZE
            ),
            pagingSourceFactory = { pagingSource }
        ).flow
    }
}
