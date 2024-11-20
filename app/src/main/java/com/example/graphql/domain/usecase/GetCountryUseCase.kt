package com.example.graphql.domain.usecase

import com.example.graphql.data.DetailedCountry
import com.example.graphql.domain.CountryClient

class GetCountryUseCase(
    private val countryClient: CountryClient
) {
    suspend fun execute(code: String) : DetailedCountry? {
        return countryClient
            .getCountry(code = code)
    }
}