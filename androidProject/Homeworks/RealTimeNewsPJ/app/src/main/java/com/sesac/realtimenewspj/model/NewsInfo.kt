package com.sesac.realtimenewspj.model

data class NewsInfo(
    val datetime_string: String = "",
    val paper: Paper = Paper(),
    val section: Section = Section(),
    val title: String = "",
    val content: String = "",
    val author: String = "",
    val url: String = "",
)