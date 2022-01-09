package com.han.layoutimplpj

data class ArticleData(
    val title: String,
    val cateIcon: Int,
    val genderDesc: String,
    val genderIcon: Int,
    val content: String,
    val imgLeft: Int,
    val imgRight: Int,
    val commentCount: Int = 0,
    val likesCount: Int = 0
)