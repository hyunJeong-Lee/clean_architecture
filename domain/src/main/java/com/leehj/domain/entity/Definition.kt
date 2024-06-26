package com.leehj.domain.entity

data class Definition(
    val antonyms: List<String>, //반의어
    val definition: String, //정의
    val example: String?, //예시
    val synonyms: List<String> //동의어
)
