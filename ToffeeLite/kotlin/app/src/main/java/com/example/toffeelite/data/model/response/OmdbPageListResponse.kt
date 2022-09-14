package com.example.toffeelite.data.model.response

interface OmdbPageListResponse<T> {
    var response: String
    var search: List<T>
    var totalResults: String
}