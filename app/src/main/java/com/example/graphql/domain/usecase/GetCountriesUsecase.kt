package com.example.graphql.domain.usecase

import com.example.graphql.data.SimpleCountry
import com.example.graphql.domain.CountryClient

class GetCountriesUsecase(
    private val countryClient: CountryClient
) {
    suspend fun execute() : List<SimpleCountry> {
        return countryClient
            .getCountries()
            .sortedBy { it.name }
    }
}