package com.example.graphql.presentation.viewModels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.graphql.data.DetailedCountry
import com.example.graphql.data.SimpleCountry
import com.example.graphql.domain.usecase.GetCountriesUsecase
import com.example.graphql.domain.usecase.GetCountryUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CountriesViewModel @Inject constructor(
    private val getCountriesUsecase: GetCountriesUsecase,
    private val getCountryUseCase: GetCountryUseCase
): ViewModel(
) {
    private val _state = MutableStateFlow(CountriesState())
    val state = _state.asStateFlow()

    init {
        viewModelScope.launch {
            _state.update {
                it.copy(isLoading = true)
            }

            _state.update {
                it.copy(
                    countries = getCountriesUsecase.execute(),
                    isLoading = false
                )
            }
        }
    }

    data class CountriesState(
        val countries: List<SimpleCountry> = emptyList(),
        val isLoading: Boolean = false,
        val selectedCountry: DetailedCountry? = null
    )
}