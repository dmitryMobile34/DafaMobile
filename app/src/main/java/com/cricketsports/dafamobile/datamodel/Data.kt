package com.cricketsports.dafamobile.datamodel

data class Data (
    val date: String,
    val dateTimeGMT: String,
    val fantasyEnabled: Boolean,
    val hasSquad: Boolean,
    val id: String,
    val matchType: String,
    val name: String,
    val series_id: String,
    val status: String,
    val teams: List<String>,
    val venue: String
)