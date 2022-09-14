package com.example.toffeelite.data.model.response

interface BasePageListResponse<T> {
    var page: Int
    var results: List<T>
}