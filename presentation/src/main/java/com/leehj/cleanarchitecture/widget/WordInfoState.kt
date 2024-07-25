package com.leehj.cleanarchitecture.widget

import com.leehj.domain.entity.WordInfo

data class WordInfoState(
    val wordInfoItems: List<WordInfo> = emptyList(),
    val isLoading: Boolean = false
)