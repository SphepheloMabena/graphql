package com.example.graphql.domain

import com.apollographql.apollo.ApolloClient
import com.example.graphql.CountriesQuery
import com.example.graphql.CountryQuery
import com.example.graphql.data.DetailedCountry
import com.example.graphql.data.SimpleCountry
import com.example.graphql.data.mappers.toDetailedCountry
import com.example.graphql.data.mappers.toSimpleCountry

class ApolloCountryClient(
    private val apolloClient: ApolloClient
): CountryClient {
    override suspend fun getCountries(): List<SimpleCountry> {
        return apolloClient
            .query(CountriesQuery())
            .execute()
            .data
            ?.countries
            ?.map { it.toSimpleCountry() }
            ?: emptyList()
    }

    override suspend fun getCountry(code: String): DetailedCountry? {
        return apolloClient
            .query(CountryQuery(code))
            .execute()
            .data
            ?.country
            ?.toDetailedCountry()
    }
}