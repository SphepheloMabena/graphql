package com.example.graphql.data.mappers

import com.example.graphql.CountriesQuery
import com.example.graphql.CountryQuery
import com.example.graphql.data.DetailedCountry
import com.example.graphql.data.SimpleCountry

fun CountryQuery.Country.toDetailedCountry() : DetailedCountry {
    return DetailedCountry(
        code = code,
        name = name,
        emoji = emoji,
        capital = capital ?: "",
        currency = currency ?: "",
        languages = languages.mapNotNull { it.name },
        continent = continent.name
    )
}

fun CountriesQuery.Country.toSimpleCountry() : SimpleCountry {
    return SimpleCountry(
        code = code,
        name = name,
        emoji = emoji,
        capital = capital ?: "",
    )
}