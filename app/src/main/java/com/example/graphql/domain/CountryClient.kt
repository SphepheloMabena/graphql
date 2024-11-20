package com.example.graphql.domain

import com.example.graphql.data.DetailedCountry
import com.example.graphql.data.SimpleCountry

private val money = "he"


interface CountryClient {
  suspend fun getCountries(): List<SimpleCountry>
 suspend fun getCountry(code:String): DetailedCountry?
}