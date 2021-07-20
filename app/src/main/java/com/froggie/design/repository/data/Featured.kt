package com.froggie.design.repository.data

data class Featured(val total: String, val perPage: String, val totalPages: String, val from: String, val to: String, val currentPage: String, val results: List<Result>)