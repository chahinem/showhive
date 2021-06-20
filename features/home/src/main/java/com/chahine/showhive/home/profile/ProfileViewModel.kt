package com.chahine.showhive.home.profile

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.chahine.showhive.home.util.LoadedValue
import com.chahine.trakt.api.TraktApi
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import timber.log.Timber
import javax.inject.Inject

@HiltViewModel
class ProfileViewModel @Inject constructor(
    private val traktApi: TraktApi,
) : ViewModel() {

    private val _profileFlow = MutableStateFlow<LoadedValue<List<ProfileItem>, Exception>>(LoadedValue.Loading)

    val profileFlow = _profileFlow.asStateFlow()

    @Suppress("TooGenericExceptionCaught")
    fun fetchProfile() = viewModelScope.launch {
        _profileFlow.emit(LoadedValue.Loading)
        try {
            val settings = traktApi.settings()
            _profileFlow.emit(LoadedValue.Success(listOf(ProfileItem.UserCard(settings.user))))
        } catch (exception: Exception) {
            Timber.e(exception)
            _profileFlow.emit(LoadedValue.Error(exception))
        }
    }
}
